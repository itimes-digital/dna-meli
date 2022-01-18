package br.com.meli.dna.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.meli.dna.vo.DnaVO;
import br.com.meli.dna.vo.EstatisticaDnaVO;

public interface ISequenciaDnaService {
	
	public DnaVO salvarSequenciaDna(DnaVO dnaVO);
	
	public DnaVO verificarSeEhSimioESalvarDna(DnaVO dnaVO);
	
	public EstatisticaDnaVO calcularQuantidadeDeTiposDeDNA();

	public DnaVO buscarPorId(Long id);

	public Page<DnaVO> buscarTodosDnaPorPaginacao(Pageable pageable);
	
	public DnaVO atualizarDna(DnaVO dnaVO);

	public void excluirDnaPorId(Long id);

}
