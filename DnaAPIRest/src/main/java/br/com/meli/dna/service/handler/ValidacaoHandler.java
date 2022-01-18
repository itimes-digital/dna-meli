package br.com.meli.dna.service.handler;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import br.com.meli.dna.enums.TipoLetraDnaEnum;
import br.com.meli.dna.exception.UnSupportedCharacterException;
import br.com.meli.dna.exception.UnSupportedNumberException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidacaoHandler {

	public JSONArray listToJsonArray(List<String> valor) {

		return new JSONArray(valor);
	}
	
	public String listToString(List<String> valor) {

		return new JSONArray(valor).toString();
	}
	
	public List<Object> JsonArrayToList(JSONArray valor) {
		
		return valor.toList();
	}
	
	public String JsonArrayToString(JSONArray valor) {
		
		return valor.toString();
	}
	
	public List<String> stringToList(String valor) {

		return (List<String>) Arrays.asList(valor);
	}
	
	/**
	 * 
	 * @param strNum
	 */
	public void isNumeric(String strNum) {
		
		log.info("> INICIO isNumeric");
		
	    if (strNum == null || strNum.matches("-?\\d+(\\.\\d+)?")) {
	    	throw new UnSupportedNumberException("DNA inválido. Está nulo ou contém números.");
	    }
	    
	    log.info("> FIM isNumeric");
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	/**
	 * 
	 * @param strNum
	 */
	public void isCharacter(String strNum) {
		
		log.info("> INICIO isCharacter");
		
	    if (strNum == null || strNum.matches("[^\\w]")) {
	    	throw new UnSupportedCharacterException("DNA inválido. Está nulo ou contém caracteres especiais.");
	    }
	    
	    log.info("> FIM isCharacter");
	}
	
	public void isPatternDna(String strNum) {
		
		log.info("> INICIO isPatternDna");
		
		if (strNum == null) {
	    	throw new UnSupportedCharacterException("DNA inválido. Existe campo nulo no dado de entrada.");
	    }
		
		if(!strNum.toUpperCase().equals(TipoLetraDnaEnum.LETRA_A.getTipoLetraDna())
			&& !strNum.toUpperCase().equals(TipoLetraDnaEnum.LETRA_C.getTipoLetraDna())
			&& !strNum.toUpperCase().equals(TipoLetraDnaEnum.LETRA_G.getTipoLetraDna())
			&& !strNum.toUpperCase().equals(TipoLetraDnaEnum.LETRA_T.getTipoLetraDna())){
			throw new UnSupportedCharacterException("DNA inválido. Há letra que não faz parte da composição de um DNA.");
		}

	    log.info("> FIM isPatternDna");
	}
}
