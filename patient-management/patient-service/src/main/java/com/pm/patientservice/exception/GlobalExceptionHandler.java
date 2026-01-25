package com.pm.patientservice.exception;

import com.pm.patientservice.constants.ErrorMessages;
import com.pm.patientservice.dto.ApiErrorResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger
            (GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleValidationException(
            MethodArgumentNotValidException ex){
        Map<String, String> errors;
        errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(),
                        error.getDefaultMessage()));

        ApiErrorResponseDTO responseDTO = ApiErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ErrorMessages.VALIDATION_FAILED)
                .validationErrors(errors)
                .build();

        return ResponseEntity.badRequest().body(responseDTO);

    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponseDTO>
    handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {

        log.warn("Email address already exist {}", ex.getMessage());
        ApiErrorResponseDTO apiErrorResponseDTO = ApiErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ErrorMessages.EMAIL_ALREADY_EXISTS)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorResponseDTO);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO>
    handlePatientNotFoundException(PatientNotFoundException ex) {

        log.warn("Patient not found with Id {}", ex.getMessage());
        ApiErrorResponseDTO apiErrorResponseDTO = ApiErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ErrorMessages.PATIENT_NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponseDTO);
    }
}
