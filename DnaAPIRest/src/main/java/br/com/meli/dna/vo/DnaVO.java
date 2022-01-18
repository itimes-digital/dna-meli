package br.com.meli.dna.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;

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
@JsonPropertyOrder({"id", "dna", "ehSimio"})
public class DnaVO extends ResourceSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 951418213116439228L;

	@JsonProperty("id")
	private Long key;
	
	@NotBlank
	private List<String> dna;
	
	@JsonProperty("is_simian")
	private Boolean ehSimio;
	
	public Boolean getEhSimio() {
		if(ehSimio == null) {
			ehSimio = Boolean.FALSE;
		}
		return ehSimio;
	}
}
