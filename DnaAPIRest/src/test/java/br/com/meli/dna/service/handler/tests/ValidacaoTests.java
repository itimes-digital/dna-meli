package br.com.meli.dna.service.handler.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meli.dna.service.handler.ValidacaoHandler;

@SpringBootTest
public class ValidacaoTests {

	ValidacaoHandler validacaoHandler;
	
	@Before
	public void setUp() {
		
		validacaoHandler = new ValidacaoHandler();
	}
	
	
	@Test
	public void testEhNumero() {
		
		try {
			
			validacaoHandler.isNumeric("2");
			assertTrue(false);
		}catch(Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	public void testNaoPodeSerEhAlfa() {
		
		try {
			
			validacaoHandler.isNumeric("A");
			assertTrue(true);
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	
	@Test
	public void testNaoEhAlfa() {
		
		try {
			
			validacaoHandler.isCharacter("A");
			assertTrue(true);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testAcento() {
		
		try {
			
				validacaoHandler.isCharacter("´");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testEspacoEmBranco() {
		
		try {
			
				validacaoHandler.isCharacter(" ");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testExclamacao() {
		
		try {
			
				validacaoHandler.isCharacter("!");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testInterrogacao() {
		
		try {
			
				validacaoHandler.isCharacter("?");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testLetraComAcento() {
		
		try {
			
				validacaoHandler.isCharacter("À");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testLetraBForaDoPadraoDoDna() {
		
		try {
			
				validacaoHandler.isPatternDna("B");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testLetraJForaDoPadraoDoDna() {
		
		try {
			
				validacaoHandler.isPatternDna("J");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testLetraUForaDoPadraoDoDna() {
		
		try {
			
				validacaoHandler.isPatternDna("U");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}
	
	@Test
	public void testLetraXForaDoPadraoDoDna() {
		
		try {
			
				validacaoHandler.isPatternDna("X");
				assertTrue(false);
			}catch(Exception e) {
				assertTrue(true);
		}
	}

}
