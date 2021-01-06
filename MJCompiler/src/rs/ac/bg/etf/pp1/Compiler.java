package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class Compiler {

	private static Logger log = Logger.getLogger(Compiler.class);
	
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(Compiler.class);
		if (args.length < 1) {
			log.error("Nema dovoljno argumenata! ( Ocekivani ulaz: <ulaz>.mj )");
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
            
            // log.info("\n\n================TABELA SIMBOLA====================\n\n");
            tsdump();
			log.info("===================================");
		}
	}
	
	public static void tsdump() {
//		Izvedi novu klasu za SymbolTableVisitor
//		SymbolTableVisitor stv = new SimpleSymbolTableVisitor(false);
//      Tab.dump(stv);
		Tab.dump();
	}
	
}
