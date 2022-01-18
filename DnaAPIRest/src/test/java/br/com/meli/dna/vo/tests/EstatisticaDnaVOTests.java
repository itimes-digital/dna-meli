package br.com.meli.dna.vo.tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"contagemDnaSimios", "contagemDnaHumanos", "ratio"})
public class EstatisticaDnaVOTests {

	@JsonProperty("count_human_dna")
	private Long contagemDnaHumanos;
	
	@JsonProperty("count_simian_dna")
	private Long contagemDnaSimios;
	
	private Float ratio;
	
	public void setRatio() {
		
		if(contagemDnaHumanos == null || contagemDnaSimios == null) {
			ratio = 0.0F;
		}
		
		ratio = contagemDnaSimios.floatValue() / contagemDnaHumanos.floatValue();
	}
}

