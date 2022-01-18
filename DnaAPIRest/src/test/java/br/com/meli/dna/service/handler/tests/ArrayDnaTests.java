package br.com.meli.dna.service.handler.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meli.dna.service.handler.ArrayHandler;
import br.com.meli.dna.service.handler.ValidacaoHandler;

@SpringBootTest
public class ArrayDnaTests {

	String arrayOK [][] = null;
	String arrayNok [][] = null;

	ArrayHandler arrayHandler;
	
	@Before
	public void setUp() {
		String arrayOK [][] = {{"C", "T", "G", "A", "G", "A", "T", "G", "A", "G", "A", "G"}, 
							   {"C", "T", "A", "T", "G", "C", "T", "A", "T", "G", "C", "A"},
							   {"T", "T", "A", "T", "G", "T", "G", "A", "T", "A", "T", "G"},
							   {"G", "G", "G", "T", "T", "G", "T", "G", "G", "G", "G", "G"},
							   {"C", "C", "A", "C", "T", "A", "G", "A", "C", "T", "A", "T"},
							   {"T", "C", "A", "C", "T", "G", "C", "A", "C", "T", "G", "T"},
							   {"C", "T", "G", "A", "G", "A", "T", "G", "A", "G", "A", "G"},
							   {"C", "T", "A", "T", "G", "C", "T", "T", "T", "A", "C", "A"},
							   {"T", "T", "A", "T", "A", "T", "T", "A", "T", "G", "T", "G"},
							   {"A", "T", "G", "A", "G", "G", "G", "G", "G", "G", "T", "G"},
							   {"C", "C", "A", "C", "T", "A", "C", "A", "C", "T", "A", "T"},
							   {"T", "C", "A", "C", "T", "G", "C", "A", "C", "T", "G", "T"}};
		
		this.arrayOK = arrayOK;
		
		String arrayNok [][] = {{"C", "T", "G", "A", "A", "T", "G", "A", "G", "A", "G"}, 
							   {"C", "T", "A", "T", "C", "T", "A", "T", "G", "C", "A"},
							   {"T", "T", "A", "T", "T", "G", "A", "T", "A", "T", "G"},
							   {"G", "G", "G", "T", "G", "T", "G", "G", "G", "G", "G"},
							   {"C", "C", "A", "C", "A", "G", "A", "C", "T", "A", "T"},
							   {"T", "T", "A", "T", "T", "T", "A", "T", "G", "T", "G"},
							   {"A", "T", "G", "A", "G", "G", "G", "G", "G", "T", "G"},
							   {"C", "C", "A", "C", "A", "C", "A", "C", "T", "A", "T"},
							   {"T", "C", "A", "C", "G", "C", "A", "C", "T", "G", "T"}};

		this.arrayNok = arrayNok;
		
		arrayHandler = new ArrayHandler(new ValidacaoHandler());
	}
	
	@Test
	public void testMatrizQuadrada() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("AAAAAA");
		lista.add("AAAAAA");
		lista.add("TATTGT");
		lista.add("AGggGg");
		lista.add("CCCCTA");
		lista.add("TCACTB");
		
		try {
			arrayHandler.ehMatrizQuadrada(lista);
			assertTrue(true);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	
	
	@Test
	public void testMatrizNaoQuadrada() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("AAAAAA");
		lista.add("AAAAAA");
		lista.add("TATTGT");
		lista.add("AGggGg");
		lista.add("CCCCTA");
		lista.add("TCACTBe");
		
		try {
			arrayHandler.ehMatrizQuadrada(lista);
			assertFalse(true);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testMontagemArrayQuadrado() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("AAAAAA");
		lista.add("AAAAAA");
		lista.add("TATTGT");
		lista.add("AGggGg");
		lista.add("CCCCTA");
		lista.add("TCACTA");
		
		try {
			arrayHandler.montagemMatrizQuadrada(lista);
			assertTrue(true);
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testValidaArrayQuadrado() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("AAAAAA");
		lista.add("AAAAAA");
		lista.add("TATTGT");
		lista.add("AGggGg");
		lista.add("CCCCTA");
		lista.add("TCACTC");
		
		try {
			String[][] montagemMatrizQuadrada = arrayHandler.montagemMatrizQuadrada(lista);
			
			if(montagemMatrizQuadrada.length == montagemMatrizQuadrada[0].length) {
				assertTrue(true);
			}else {
				assertTrue(false);
			}

		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	
	
	@Test
	public void testArrayNaoQuadrado() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("AAAAAA");
		lista.add("AAAAAA");
		lista.add("TATTGTWWW");
		lista.add("AGggGg");
		lista.add("CCCCTA");
		lista.add("TCACTB");
		lista.add("AGggGg");
		lista.add("CCCCTA");
		lista.add("TCACTBTTT");
		lista.add("AGggGg");
		lista.add("CCC");
		lista.add("TCACTB");

		try {
			arrayHandler.montagemMatrizQuadrada(lista);
			assertFalse(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testArrayComPoucosDadosParaMonatagemMatrizQuadrada() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("AAAAAA");
		lista.add("AAAAAA");

		try {
			arrayHandler.montagemMatrizQuadrada(lista);
			assertFalse(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testBuscarLinhasDnaMatriz() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {
			arrayHandler.buscarLinhasDnaMatriz(arrayOK, listaSequenciaDNA);
			assertEquals(listaSequenciaDNA.get(0).toString(), "CTGAGATGAGAG");
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testBuscarColunasDnaMatriz() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {
			arrayHandler.buscarColunasDnaMatriz(arrayOK, listaSequenciaDNA);
			assertEquals(listaSequenciaDNA.get(0).toString(), "CCTGCTCCTACT");
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testbBscarDiagonalEsquerdaAbaixoDaMatriz() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {
			
			arrayHandler.buscarDiagonalEsquerdaAbaixoDaMatriz(arrayOK, listaSequenciaDNA);
			assertEquals(listaSequenciaDNA.get(6).toString(), "CTGCT");
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testBuscarDiagonalDireitaAcimaDaMatriz() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {
			
			arrayHandler.buscarDiagonalDireitaAcimaDaMatriz(arrayOK, listaSequenciaDNA);
			assertEquals(listaSequenciaDNA.get(8).toString(), "AGTG");
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testBuscarDiagonalEsquerdaAcimaDaMatriz() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {

			arrayHandler.buscarDiagonalEsquerdaAcimaDaMatriz(arrayOK, listaSequenciaDNA);
			assertEquals(listaSequenciaDNA.get(0).toString(), "TCGTGACAGACG");
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testBuscarDiagonalDireitaAbaixoDaMatriz() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {

			arrayHandler.buscarDiagonalDireitaAbaixoDaMatriz(arrayOK, listaSequenciaDNA);
			assertEquals(listaSequenciaDNA.get(3).toString(), "TAGATGGT");
		}catch(Exception e) {
			assertFalse(true);
		}
	}
	
	
	@Test
	public void testBuscarDiagonalDireitaAbaixoDaMatrizComErro() {
		
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();

		try {

			arrayHandler.buscarDiagonalDireitaAbaixoDaMatriz(arrayNok, listaSequenciaDNA);
			assertNotEquals(listaSequenciaDNA.get(3).length(), 6);
		}catch(Exception e) {
			assertTrue(false);
		}
	}


}
