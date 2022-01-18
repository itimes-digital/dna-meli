package br.com.meli.dna.service.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meli.dna.exception.UnSupportedArrayException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArrayHandler {

	@Autowired
	private ValidacaoHandler validacaoHandler;
	
	public ArrayHandler() {
		super();
	}
	
	public ArrayHandler(ValidacaoHandler validacaoHandler) {
		this.validacaoHandler = validacaoHandler;
	}
	
	public void ehMatrizQuadrada(List<String> lista) {

		log.info("> INICIO ehMatrizQuadrada");
		
		if(lista == null || lista.isEmpty()) {
			throw new UnSupportedArrayException("Não há dados para analisar o DNA.");
		}else {
			
			int quantidadeCaracteres 	= 0;
			int tamanhoDaLista 			= lista.size();
			
			for (String valor : lista) {
				quantidadeCaracteres = valor.length() + quantidadeCaracteres;
			}
			
			tamanhoDaLista = tamanhoDaLista * tamanhoDaLista;
			
			if(tamanhoDaLista != quantidadeCaracteres) {
				throw new UnSupportedArrayException("Essa matriz de DNA não é quadrada.");
			}
		}
		
		log.info("> FIM ehMatrizQuadrada");
	}
	
	public String [][] montagemMatrizQuadrada(List<String> lista){
		
		log.info("> INICIO montagemMatrizQuadrada");
		
		String [] [] matrizDna = new String [lista.size()][lista.size()];
		
		int countValor = 0;
		
		for (String valor : lista) {
			
			for (int x = 0; x < valor.length(); x++) {
				
				String elemento = String.valueOf(valor.charAt(x));
				
				validacaoHandler.isNumeric(elemento);

				elemento = validacaoHandler.removerAcentos(elemento.toUpperCase());

				validacaoHandler.isCharacter(elemento);
				
				validacaoHandler.isPatternDna(elemento);
				
				matrizDna[countValor][x] = elemento;
			}
			countValor++;
		}
		
		log.info("> FIM montagemMatrizQuadrada");
		
		return matrizDna;
	}
	
	public void buscarLinhasDnaMatriz(String arrayDna [][], List<StringBuilder> listaSequenciaDNA) {
		
		log.info("> INICIO buscarLinhasDnaMatriz");
		
		StringBuilder linhaDNA = new StringBuilder();
		
		for (int i = 0; i < arrayDna.length; i++) {
			for (int j = 0; j < arrayDna.length; j++) {

				linhaDNA.append(arrayDna[i][j]);
			}
			
			listaSequenciaDNA.add(linhaDNA);
			linhaDNA = new StringBuilder();
		}
		
		log.info("> FIM buscarLinhasDnaMatriz");
	}
	
	public void buscarColunasDnaMatriz(String arrayDna [][], List<StringBuilder> listaSequenciaDNA) {
		
		log.info("> INICIO buscarColunasDnaMatriz");
		
		StringBuilder colunaDNA = new StringBuilder();
		
		for (int i = 0; i < arrayDna.length; i++) {
			for (int j = 0; j < arrayDna.length; j++) {

				colunaDNA.append(arrayDna[j][i]);
			}
			
			listaSequenciaDNA.add(colunaDNA);
			colunaDNA = new StringBuilder();
		}
		
		log.info("> FIM buscarColunasDnaMatriz");
	}
	
	public void buscarDiagonalEsquerdaAbaixoDaMatriz(String arrayDna [][], List<StringBuilder> listaSequenciaDNA) {
		
		log.info("> INICIO buscarDiagonalEsquerdaAbaixoDaMatriz");
		
		int j 						= 0;
		int count 					= 1;
		StringBuilder diagonalDNA 	= new StringBuilder();
		
		for (int i = 1; i < arrayDna.length; ++i) {

			for (; j < (arrayDna.length - count); j++) {

				diagonalDNA.append(arrayDna[i + j][j]);
			}
			
			j = 0;
			++count;
			
			listaSequenciaDNA.add(diagonalDNA);
			diagonalDNA = new StringBuilder();
		}
		
		log.info("> FIM buscarDiagonalEsquerdaAbaixoDaMatriz");
	}
	
	public void buscarDiagonalDireitaAcimaDaMatriz(String arrayDna [][], List<StringBuilder> listaSequenciaDNA) {
		
		log.info("> INICIO buscarDiagonalDireitaAcimaDaMatriz");
		
		int j 						= 0;
		int count 					= 0;
		StringBuilder diagonalDNA 	= new StringBuilder();
		
		for (int i = 0; i < arrayDna.length; ++i) {
			
			for (; j < (arrayDna.length - count); j++) {

				diagonalDNA.append(arrayDna[j][j + i]);
				
			}
			
			j = 0;
			++count;
			
			listaSequenciaDNA.add(diagonalDNA);
			diagonalDNA = new StringBuilder();
		}
		
		log.info("> FIM buscarDiagonalDireitaAcimaDaMatriz");
	}
	
	public void buscarDiagonalEsquerdaAcimaDaMatriz(String arrayDna [][], List<StringBuilder> listaSequenciaDNA) {
		
		log.info("> INICIO buscarDiagonalEsquerdaAcimaDaMatriz");
		
		int j 						= 0;
		int count 					= 0;
		StringBuilder diagonalDNA 	= new StringBuilder();
		
		for (int i = arrayDna.length; i > 0; i--) {

			count = i;
			
			for (; j < arrayDna.length; j++) {
				
				count = count - 1;
				
				if(count == -1) {
					break;
				}

				diagonalDNA.append(arrayDna[count][j]);
	
			}
			
			j = 0;
			++count;
			
			listaSequenciaDNA.add(diagonalDNA);
			diagonalDNA = new StringBuilder();
		}
		
		log.info("> FIM buscarDiagonalEsquerdaAcimaDaMatriz");
	}
	
	public void buscarDiagonalDireitaAbaixoDaMatriz(String arrayDna [][], List<StringBuilder> listaSequenciaDNA) {
		
		log.info("> INICIO buscarDiagonalDireitaAbaixoDaMatriz");
		
		int j 						= 1;
		int count 					= 1;
		int posicao 				= 0;
		StringBuilder diagonalDNA 		= new StringBuilder();
		
		for (int i = (arrayDna.length - 1); i > 0;) {

			posicao = i;
			
			for (; j < arrayDna.length;) {

				diagonalDNA.append(arrayDna[posicao][j]);
				
				posicao = posicao - 1;
				
				if(j == arrayDna.length) {
					break;
				}else {
					++j;
				}
			}

			++count;
			j = count;
			
			listaSequenciaDNA.add(diagonalDNA);
			diagonalDNA = new StringBuilder();
			
			if(j >= arrayDna.length) {
				break;
			}
		}
		
		log.info("> FIM buscarDiagonalDireitaAbaixoDaMatriz");
	}
}
