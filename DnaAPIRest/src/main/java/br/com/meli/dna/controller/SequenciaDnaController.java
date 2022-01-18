package br.com.meli.dna.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.dna.service.interfaces.ISequenciaDnaService;
import br.com.meli.dna.vo.DnaVO;
import br.com.meli.dna.vo.EstatisticaDnaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Api(value = "Meli DNA Endpoint", tags = {"simian, human, meli, dna"})
@RestController
public class SequenciaDnaController {
	
	@Autowired
	private ISequenciaDnaService sequenciaDnaService;
	
	/**
	 * 
	 * @param dnaVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Analisa um conjunto de DNA para determinar se é símio ou humano. Por fim, grava no banco de dados.", response = DnaVO.class)
	@PostMapping(value = "/v1/simian")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody DnaVO verificarSeDnaEhSimio(@RequestBody DnaVO dnaVO) throws Exception{
		
		log.info("> INICIO verificarSeDnaEhSimio");
		return sequenciaDnaService.verificarSeEhSimioESalvarDna(dnaVO);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Retorna a quantidade e a proporção de símios e humanos", response = EstatisticaDnaVO.class)
	@GetMapping(value = "/v1/stats")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody EstatisticaDnaVO calcularQuantidadeDeTiposDeDNAExistentes() throws Exception{
		
		log.info("> INICIO calcularQuantidadeDeTiposDeDNAExistentes");
		
		return sequenciaDnaService.calcularQuantidadeDeTiposDeDNA();
	}
	
	/**
	 * 
	 * @param dnaVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Salva uma sequência de DNA já pré-determinado.", response = DnaVO.class)
	@PostMapping(value = "/v1")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody DnaVO salvarSequenciaDna(@RequestBody DnaVO dnaVO) throws Exception{

		log.info("> INICIO salvarSequenciaDna");
		
		return sequenciaDnaService.salvarSequenciaDna(dnaVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca DNA a partir de um ID.", response = DnaVO.class)
	@GetMapping(value = "/v1/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<DnaVO> buscarPorId(@PathVariable(value="id") Long id) throws Exception{
		
		log.info("> INICIO buscarPorId: {}", id);
		
		ResponseEntity<DnaVO> retorno = ResponseEntity.ok(sequenciaDnaService.buscarPorId(id));
		
		return retorno;
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param assembler
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Busca uma lista de DNAs por paginação.", response = DnaVO.class)
	@GetMapping(value = "/v1")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody ResponseEntity<PagedResources<DnaVO>> buscarTodosDnaPorPaginacao(
									@RequestParam(value = "page", defaultValue = "0") Short page,
									@RequestParam(value = "size", defaultValue = "10") Short size,
									 PagedResourcesAssembler assembler) throws Exception{

		log.info("> INICIO buscarTodosDnaPorPaginacao");
		
		Pageable pageable = PageRequest.of(page, size);
		Page<DnaVO> pageDnaVO = sequenciaDnaService.buscarTodosDnaPorPaginacao(pageable);
		
		//ajuste final do hateoas
		pageDnaVO.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(SequenciaDnaController.class).buscarPorId(p.getKey())).withSelfRel());
			} catch (Exception ex) {
				log.warn("> ERRO montagem dos links hateoas.");
			}
		});

		return new ResponseEntity<PagedResources<DnaVO>>(assembler.toResource(pageDnaVO), HttpStatus.OK);
	}

	/**
	 * 
	 * @param dnaVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Atualiza os dados de um DNA na base de dados.", response = DnaVO.class)
	@PutMapping(value = "/v1")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public @ResponseBody DnaVO atualizarDna(@RequestBody DnaVO dnaVO) throws Exception{

		log.info("> INICIO atualizarDna");
		
		return sequenciaDnaService.atualizarDna(dnaVO);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "Exclui um DNA da base de dados.")
	@DeleteMapping(value = "/v1/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registros encontrados"),
							@ApiResponse(code = 204, message = "Nenhum registro encontrado"),
							@ApiResponse(code = 400, message = "Requisição inválida"),
							@ApiResponse(code = 401, message = "Não autenticado"), 
							@ApiResponse(code = 403, message = "Não autorizado"),
							@ApiResponse(code = 500, message = "Erro interno no servidor"),
							@ApiResponse(code = 504, message = "Tempo limite da requisição excedido") })
	public ResponseEntity<?> excluirDnaPorId(@PathVariable(value="id") Long id) throws Exception{

		log.info("> INICIO excluirDnaPorId");
		
		sequenciaDnaService.excluirDnaPorId(id);
		
		return ResponseEntity.ok().build();
	}
	
}
