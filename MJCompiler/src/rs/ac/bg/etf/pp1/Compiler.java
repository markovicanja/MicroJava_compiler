package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.log4j.Logger;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;

public class Compiler {

	private static Logger log = Logger.getLogger(Compiler.class);
	
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(Compiler.class);
		if (args.length < 1) {
			log.error("Nedovoljno argumenata! ( Ocekivan ulaz:\n<ulazni_fajl>.mj )");
			return;
		}
		
		File sourceCode = new File(args[0]);
		if (!sourceCode.exists()) {
			log.error("Ulazni fajl: " + sourceCode.getAbsolutePath() + " nije pronadjen...");
			return;
		}
			
		log.info("Kompajliranje ulaznog MJ fajla: " + sourceCode.getAbsolutePath());
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCode))) {
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
			
			// Parsiranje
			Symbol s = p.parse(); 
			Program prog = (Program)(s.value); 	        
	        if (p.isErrorDetected()) {
				log.info("Ulazni fajl ima sintaksne greske!");
			} else {
				log.info("\n\n\nSINTAKSNO STABLO\n\n\n");
				log.info(prog.toString(""));
				log.info("===================================");
			}
		}
	}
}
