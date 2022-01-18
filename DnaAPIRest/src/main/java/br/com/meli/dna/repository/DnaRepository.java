package br.com.meli.dna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.meli.dna.model.SequenciaDnaModel;

@Repository
public interface DnaRepository extends JpaRepository<SequenciaDnaModel, Long>{

	@Query(value = "SELECT COUNT(id) FROM tab_sequencia_dna WHERE EH_SIMIO = ?", nativeQuery = true)
	Long countByType(Boolean ehSimian);
	
	@Query(value = "SELECT EXISTS(SELECT DISTINCT EH_SIMIO FROM tab_sequencia_dna WHERE CAST(DNA AS JSON) = CAST(? AS JSON)) AS exist", nativeQuery = true)
	Integer existsDna(String dna);
	
}
