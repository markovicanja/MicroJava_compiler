package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;
import rs.etf.pp1.mj.runtime.Code;

public class Compiler {

	private static Logger log = Logger.getLogger("info");
	private static Logger logError = Logger.getLogger("error");
	private static FileAppender fileAppender, fileAppenderError;
	
	static {
		fileAppender = new FileAppender();
		fileAppender.setLayout(new PatternLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN));
		fileAppender.setFile("test/info.out");
		fileAppender.activateOptions();
		log.addAppender(fileAppender);
		
		fileAppenderError = new FileAppender();
		fileAppenderError.setLayout(new PatternLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN));
		fileAppenderError.setFile("test/error.err");
		fileAppenderError.activateOptions();
		logError.addAppender(fileAppenderError);
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			log.error("Nema dovoljno argumenata! ( Ocekivani ulaz: <ulaz>.mj <izlaz>.obj )");
			return;
		}
		
		File sourceCode = new File(args[0]);
		if (!sourceCode.exists()) {
			log.error("Ulazni fajl [" + sourceCode.getAbsolutePath() + "] nije pronadjen!");
			return;
		}
			
		log.info("Kompajliranje ulaznog fajla " + sourceCode.getAbsolutePath());
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCode))) {
			Yylex lexer = new Yylex(br);			
			MJParser p = new MJParser(lexer);
			
	        if (p.isErrorDetected()) {
				log.info("Ulazni fajl ima sintaksnih gresaka!");
				return;
	        }
	        
	        Symbol s = p.parse(); 
			Program prog = (Program)(s.value); 

			log.info("\n\n================SINTAKSNO STABLO====================\n\n");
			log.info(prog.toString(""));
			
			log.info("\n\n================SEMANTICKA OBRADA====================\n\n");			
			Tab.init(); // pocetni opseg

            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            prog.traverseBottomUp(semanticAnalyzer);
            
            tsdump();
			log.info("===================================");
			
			if (!semanticAnalyzer.isErrorDetected()) {
	        	File objFile = new File(args[1]);
	        	log.info("Generisanje MJ bajtkoda: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();

	        	CodeGenerator codeGenerator = new CodeGenerator(semanticAnalyzer.getOuterScope());
                prog.traverseBottomUp(codeGenerator);
                
                Code.dataSize = semanticAnalyzer.getnVars();
                Code.mainPc = codeGenerator.getMainPc();
                
	        	Code.write(new FileOutputStream(objFile));
	        	log.info("Parsiranje uspesno zavrseno!");
	        }
	        else {
	        	log.error("Parsiranje nije uspesno zavrseno!");
	        }
		}
	}
	
	public static void tsdump() {
		SymbolTableVisitor stv = new MySymbolTableVisitor();
        Tab.dump(stv);
	}
	
}
