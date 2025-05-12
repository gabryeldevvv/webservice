package com.lojavirtual.api.advice;

import com.lojavirtual.api.exception.CategoriaNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RequestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        logger.error("Violação de integridade de dados:", ex);
        logStackTraceDetails(ex);

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Violação de integridade de dados");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        logger.warn("Erro de validação:", ex);

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        logger.error("Erro interno inesperado:", ex);
        logStackTraceDetails(ex);

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Erro interno");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<Map<String,String>> handleNotFound(CategoriaNaoEncontradaException ex) {
        logger.warn("Categoria não encontrada:", ex);
        Map<String,String> body = new HashMap<>();
        body.put("erro", "Recurso não encontrado");
        body.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    private void logStackTraceDetails(Exception ex) {
        if (ex.getStackTrace().length > 0) {
            StackTraceElement element = ex.getStackTrace()[0];
            logger.debug("Ocorrido em: {}.{}(): linha {}",
                    element.getClassName(),
                    element.getMethodName(),
                    element.getLineNumber());
        }
    }
}
