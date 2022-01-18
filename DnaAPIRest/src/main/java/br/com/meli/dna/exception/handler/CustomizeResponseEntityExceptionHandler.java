package br.com.meli.dna.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.meli.dna.exception.ExceptionResponse;
import br.com.meli.dna.exception.MethodNotAllowedException;
import br.com.meli.dna.exception.ResourceNoContentException;
import br.com.meli.dna.exception.ResourcePartialContentException;
import br.com.meli.dna.exception.UnAuthorizedException;
import br.com.meli.dna.exception.UnSupportedArrayException;
import br.com.meli.dna.exception.UnSupportedCharacterException;
import br.com.meli.dna.exception.UnSupportedMediaTypeException;
import br.com.meli.dna.exception.UnSupportedNumberException;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({UnSupportedNumberException.class, 
						UnSupportedArrayException.class,
						UnSupportedCharacterException.class})
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ResourcePartialContentException.class})
	public final ResponseEntity<ExceptionResponse> handleResourcePartialContentException(Exception ex, WebRequest request){
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.PARTIAL_CONTENT);
	}
	
	@ExceptionHandler({ResourceNoContentException.class})
	public final ResponseEntity<ExceptionResponse> handleResourceNoContentException(Exception ex, WebRequest request){
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
	}

	
	@ExceptionHandler(UnSupportedMediaTypeException.class)
	public final ResponseEntity<ExceptionResponse> handleUnsupportedMediaTypeException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler(MethodNotAllowedException.class)
	public final ResponseEntity<ExceptionResponse> handleMethodNotAllowedException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@ExceptionHandler(UnAuthorizedException.class)
	public final ResponseEntity<ExceptionResponse> handleUnauthorizedException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
																	ex.getMessage(), 
																	request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
}
