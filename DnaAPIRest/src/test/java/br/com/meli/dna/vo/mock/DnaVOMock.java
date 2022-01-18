package br.com.meli.dna.vo.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.meli.dna.vo.tests.DnaVOTests;

public class DnaVOMock {

	public DnaVOTests getDnaVOTestsFalse() {
		DnaVOTests dnaVOTests = new DnaVOTests();
		dnaVOTests.setEhSimio(Boolean.FALSE);
		
		List<String> lista = new ArrayList<String>();
		lista.add("CTGAGA");
		lista.add("CTATGC");
		lista.add("TATTGT");
		lista.add("AGAGGG");
		lista.add("CCCCTA");
		lista.add("TCACTG");
		
		dnaVOTests.setDna(lista);
		return dnaVOTests;
	}
	
	public DnaVOTests getDnaVOTestsTrue() {
		DnaVOTests dnaVOTests = new DnaVOTests();
		dnaVOTests.setEhSimio(Boolean.TRUE);
		
		List<String> lista = new ArrayList<String>();
		lista.add("CTGAGA");
		lista.add("CTATGC");
		lista.add("TATTGT");
		lista.add("AGAGGG");
		lista.add("CCCCTA");
		lista.add("TCACTG");
		
		dnaVOTests.setDna(lista);
		return dnaVOTests;
	}
	
	public List<DnaVOTests> listAllDnaFalse (){
		
		List<DnaVOTests> lista =new ArrayList<DnaVOTests>();
		
		for (int i = 0; i < 200; i++) {
			lista.add(getDnaVOTestsFalse());
		}
		
		return lista;
	}
	
	public List<DnaVOTests> listAllDnaTrue (){
		
		List<DnaVOTests> lista =new ArrayList<DnaVOTests>();
		
		for (int j = 0; j < 180; j++) {
			lista.add(getDnaVOTestsTrue());
		}
		
		return lista;
	}
}
