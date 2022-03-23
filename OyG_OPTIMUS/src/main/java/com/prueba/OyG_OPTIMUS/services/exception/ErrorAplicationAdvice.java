package com.prueba.OyG_OPTIMUS.services.exception;

import com.prueba.OyG_OPTIMUS.models.dto.ErrorResponseDTO;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorAplicationAdvice {
	

	
	/**
	 * 
	 * @param exception
	 * @return
	 */
    @ExceptionHandler({ApiNotFound.class})
    public ResponseEntity<ErrorResponseDTO> handleErrorAplicationException(ApiNotFound exception){
        return error(HttpStatus.NOT_FOUND, ErrorResponseDTO.builder()
															.codError("404")
															.descripcionError(exception.getMessage())
															.build()
		);
    }
	@ExceptionHandler({ApiUnprocessableEntity.class})
	public ResponseEntity<ErrorResponseDTO> handleErrorAplicationException2(ApiUnprocessableEntity exception){
		return error(HttpStatus.UNPROCESSABLE_ENTITY, ErrorResponseDTO.builder()
																	.codError("422")
																	.descripcionError(exception.getMessage())
																	.build()
		);
	}
    /**
     * 
     * @param status
     * @param errorResponse
     * @return
     */
    private ResponseEntity<ErrorResponseDTO> error(HttpStatus status, ErrorResponseDTO errorResponse) {
        return ResponseEntity.status(status).body(errorResponse);
    }
}
