package br.com.braslar.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.braslar.api.domain.ValidacaoException;

@RestControllerAdvice
public class TratadorDeErros {

    private final Logger logger = LoggerFactory.getLogger(TratadorDeErros.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> tratarErro400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // @ExceptionHandler(BadCredentialsException.class)
    // public ResponseEntity tratarErroBadCredentials() {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais
    // inválidas");
    // }

    // @ExceptionHandler(AuthenticationException.class)
    // public ResponseEntity tratarErroAuthentication() {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na
    // autenticação");
    // }

    // @ExceptionHandler(AccessDeniedException.class)
    // public ResponseEntity tratarErroAcessoNegado() {
    // return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    // }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro500(Exception ex) {
        var message = "Erro inesperado no servidor, verifique os logs.";
        logger.error(message, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
