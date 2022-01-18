package br.com.meli.dna.service.tests;

import static org.junit.Assert.assertEquals;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meli.dna.vo.mock.DnaVOMock;
import br.com.meli.dna.vo.tests.EstatisticaDnaVOTests;

@SpringBootTest
public class ServicesTests {

	DnaVOMock dnaVOMock;
	
	@Before
	public void setUp() {
		
		dnaVOMock = new DnaVOMock();
	}

	@Test
	public void calcularQuantidadeDeTiposDeDNA() {
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		
		EstatisticaDnaVOTests estatisticaDnaVOTests = new EstatisticaDnaVOTests();
		estatisticaDnaVOTests.setContagemDnaHumanos(dnaVOMock.listAllDnaFalse().stream().count());
		estatisticaDnaVOTests.setContagemDnaSimios(dnaVOMock.listAllDnaTrue().stream().count());
		estatisticaDnaVOTests.setRatio();
		assertEquals(df.format(estatisticaDnaVOTests.getRatio()), "0,9");

	}
	
}
