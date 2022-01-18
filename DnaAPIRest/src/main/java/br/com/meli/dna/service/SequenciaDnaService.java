package br.com.meli.dna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.meli.dna.enums.TipoDnaEnum;
import br.com.meli.dna.exception.ResourceNoContentException;
import br.com.meli.dna.exception.ResourcePartialContentException;
import br.com.meli.dna.model.SequenciaDnaModel;
import br.com.meli.dna.repository.DnaRepository;
import br.com.meli.dna.service.handler.ArrayHandler;
import br.com.meli.dna.service.handler.ValidacaoHandler;
import br.com.meli.dna.service.interfaces.ISequenciaDnaService;
import br.com.meli.dna.vo.DnaVO;
import br.com.meli.dna.vo.EstatisticaDnaVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SequenciaDnaService implements ISequenciaDnaService {
	
	@Autowired
	private DnaRepository dnaRepository;
	
	@Autowired
	private ValidacaoHandler validacaoHandler;
	
	@Autowired
	private ArrayHandler arrayHandler;
	

	/**
	 * Salva uma nova sequência de DNA pré-determinada com classificação símio ou humano.
	 */
	@Override
	public DnaVO salvarSequenciaDna(DnaVO dnaVO) {
		
		log.info("> INICIO salvarSequenciaDna");
		
		SequenciaDnaModel sequenciaDnaModel = new SequenciaDnaModel();
		sequenciaDnaModel.setDna(validacaoHandler.listToString(dnaVO.getDna()));
		sequenciaDnaModel.setEhSimio(dnaVO.getEhSimio());
		
		SequenciaDnaModel save = dnaRepository.save(sequenciaDnaModel);
		dnaVO.setKey(save.getId());
		
		log.info("> FIM salvarSequenciaDna");
		
		return dnaVO;

	}

	/**
	 * Faz análise de um conjunto de dados numa estrutura de JSON para qualificaar quem é símio e quem é humano.
	 * 
	 * A leitura de um array quadrado, isto é, uma matriz quadrada onde é analisado a sequência por linha, coluna e diagonal.
	 * 
	 * A parte diagonal foi dividida em 4 parte para melhor análise e simplicidade do algoritmo.
	 * 
	 * Por fim, faz a inserção na base de dados do DNA e a classificação.
	 * 
	 * Havendo a verificação se o mesmo já existe em base de dados.
	 */
	@Override
	public DnaVO verificarSeEhSimioESalvarDna(DnaVO dnaVO) {

		log.info("> INICIO verificarSeEhSimioESalvarDna");
		
		String dna 									= validacaoHandler.listToString(dnaVO.getDna());
		SequenciaDnaModel sequenciaDnaModel 		= new SequenciaDnaModel();
		List<StringBuilder> listaSequenciaDNA 		= new ArrayList<StringBuilder>();
		Integer countSequencia 						= 0;
		Pattern patternDna 							= Pattern.compile("((.)\\2*)");
		List<String> listaDna 						= dnaVO.getDna();
		
		log.info("> Verifica se a matriz eh quadrada.");
		
		arrayHandler.ehMatrizQuadrada(listaDna);

		log.info("> Faz a montagem da matriz quadrada.");
		
		String [][] arrayDna = arrayHandler.montagemMatrizQuadrada(listaDna);
		
		log.info("> Inicia a leitura de linhas, colunas e diagonais de uma matriz");
		
		//Montagem de uma nova estrutura de dados olhando todas as direções da matriz
		arrayHandler.buscarLinhasDnaMatriz(arrayDna, listaSequenciaDNA);
		arrayHandler.buscarColunasDnaMatriz(arrayDna, listaSequenciaDNA);
		arrayHandler.buscarDiagonalEsquerdaAbaixoDaMatriz(arrayDna, listaSequenciaDNA);
		arrayHandler.buscarDiagonalDireitaAcimaDaMatriz(arrayDna, listaSequenciaDNA);
		arrayHandler.buscarDiagonalEsquerdaAcimaDaMatriz(arrayDna, listaSequenciaDNA);
		arrayHandler.buscarDiagonalDireitaAbaixoDaMatriz(arrayDna, listaSequenciaDNA);

		
		log.info("> Identifica o padrão de 4 letras iguais e sequenciais (agrupamento)");
		
		for (StringBuilder valorDna : listaSequenciaDNA) {
			
			 Matcher matcherDna = patternDna.matcher(valorDna);
			 
			 while (matcherDna.find()) {
			     if(matcherDna.group(1).length() == 4) {
			    	 countSequencia++;
			     }
			 }
		}
		
		sequenciaDnaModel.setDna(dna.toUpperCase());
		
		log.info("> Quantidade de sequencia encontrada para determinar simio ou humano {}", countSequencia);
		
		if(countSequencia >= 2) { 
			sequenciaDnaModel.setEhSimio(TipoDnaEnum.SIMIO.getTipoDna());
		}else {
			sequenciaDnaModel.setEhSimio(TipoDnaEnum.HUMANO.getTipoDna());
		}
		
		//Verifica se JSON já existe para manter unicidade dos dados
		//Retornando 0, o dado não existe na base.
		if(dnaRepository.existsDna(dna) == 0){
		
			SequenciaDnaModel save = dnaRepository.save(sequenciaDnaModel);
			dnaVO.setEhSimio(save.getEhSimio());
			dnaVO.setDna(null);
			
			log.info("> Grava no banco de dados.");
			
		}else {
			throw new ResourcePartialContentException("DNA já existente!");
		}

		log.info("> FIM verificarSeEhSimioESalvarDna");
		
		return dnaVO;
	}

	/**
	 * Calcula a proporção de símios e humanos classificados.
	 */
	@Override
	public EstatisticaDnaVO calcularQuantidadeDeTiposDeDNA() {
		
		log.info("> INICIO calcularQuantidadeDeTiposDeDNA");
		
		EstatisticaDnaVO estatisticaDna = new EstatisticaDnaVO();
		estatisticaDna.setContagemDnaHumanos(dnaRepository.countByType(TipoDnaEnum.HUMANO.getTipoDna()));
		estatisticaDna.setContagemDnaSimios(dnaRepository.countByType(TipoDnaEnum.SIMIO.getTipoDna()));
		estatisticaDna.setRatio();

		log.info("> FIM calcularQuantidadeDeTiposDeDNA");
		
		return estatisticaDna;
	}

	/**
	 * Realiza a busca de DNA por ID
	 */
	@Override
	public DnaVO buscarPorId(Long id) {

		log.info("> INICIO buscarPorId {}", id);
		
		SequenciaDnaModel sequenciaDnaModel = dnaRepository.findById(id)
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID."));
		
		DnaVO dnaVO = new DnaVO();
		dnaVO.setKey(sequenciaDnaModel.getId());		
		dnaVO.setDna(validacaoHandler.stringToList(sequenciaDnaModel.getDna()));
		dnaVO.setEhSimio(sequenciaDnaModel.getEhSimio());
		
		log.info("> FIM buscarPorId {}", id);
		
		return dnaVO;
	}

	/**
	 * Faz a busca paginada de DNAs, com os respectivos endpoints.
	 * 
	 * Implementado com Hateoas.
	 */
	@Override
	public Page<DnaVO> buscarTodosDnaPorPaginacao(Pageable pageable) {
		
		log.info("> INICIO buscarTodosDnaPorPaginacao");
		
		var page = dnaRepository.findAll(pageable);

		log.info("> FIM buscarTodosDnaPorPaginacao");
		
		return page.map(this::convertToDnaVO);
	}
	
	private DnaVO convertToDnaVO(SequenciaDnaModel sequenciaDnaModel) {
		
		log.info("> INICIO convertToDnaVO");
		
		DnaVO dnaVO = new DnaVO();
		dnaVO.setKey(sequenciaDnaModel.getId());
		dnaVO.setDna(validacaoHandler.stringToList(sequenciaDnaModel.getDna()));
		dnaVO.setEhSimio(sequenciaDnaModel.getEhSimio());
		
		log.info("> FIM convertToDnaVO");
		
		return dnaVO;
	}
	
	/**
	 * Atualiza um DNA já existente na base de dados.
	 */
	@Override
	public DnaVO atualizarDna(DnaVO dnaVO) {
		
		log.info("> INICIO atualizarDna");
		
		SequenciaDnaModel entity = dnaRepository.findById(dnaVO.getKey())
				.orElseThrow(() -> new ResourceNoContentException("No records found for this ID to update."));
 
		entity.setEhSimio(dnaVO.getEhSimio());
		entity.setDna(validacaoHandler.listToString(dnaVO.getDna()));
		
		dnaRepository.save(entity);
		
		log.info("> FIM atualizarDna");
		
		return dnaVO;
	}

	/**
	 * Exclui um DNA da base de dados, caso ele exista.
	 */
	@Override
	public void excluirDnaPorId(Long id) {
		
		log.info("> INICIO excluirDnaPorId");
		
		if(dnaRepository.existsById(id)) {
			dnaRepository.deleteById(id);
		}else {
			throw new ResourceNoContentException("No records found for this ID.");
		}

		log.info("> FIM excluirDnaPorId");
	}

}
