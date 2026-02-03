package com.rekahdo.goodshop.authentication_service.exceptions.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ErrorResponse {

    private interface SingleErrorView{};
    private interface MultiErrorView{};

    @JsonView({SingleErrorView.class, MultiErrorView.class})
    private String exception;

    @JsonView({SingleErrorView.class, MultiErrorView.class})
    private String line;

    @JsonView({SingleErrorView.class, MultiErrorView.class})
    private String error;

    @JsonView({SingleErrorView.class, MultiErrorView.class})
    private int status;

    @JsonView(SingleErrorView.class)
    private String message;

    @JsonView(MultiErrorView.class)
    private List<String> messages;

    @JsonView(MultiErrorView.class)
    private int errorCount = 0;

    @JsonView({SingleErrorView.class, MultiErrorView.class})
    private String description;

    @JsonView({SingleErrorView.class, MultiErrorView.class})
    private String timestamp = Instant.now().toString();

    public ErrorResponse(Exception ex, HttpStatus status, String description) {
        exception = ex.getClass().getSimpleName();
        line = findErrorLine(ex);
        message = ex.getMessage();
        this.error = status.getReasonPhrase();
        this.status = status.value();
        this.description = description;
    }

    public ErrorResponse(Exception ex, HttpStatusCode statusCode, String description) {
        this(ex, Objects.requireNonNull(HttpStatus.resolve(statusCode.value())), description);
    }

    public ErrorResponse(BindException ex, HttpStatusCode statusCode, WebRequest request) {
        this(ex, statusCode, request.getDescription(true));
        this.messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        this.errorCount = ex.getErrorCount();
    }

    public ErrorResponse(Exception ex, HttpStatusCode statusCode) {
        this(ex, statusCode, (String) null);
    }

    public ErrorResponse(ResponseStatusException ex) {
        this(ex, ex.getStatusCode());
    }

    public ErrorResponse(Exception ex, HttpStatus status) {
        this(ex, status, (String) null);
    }

    public ErrorResponse(Exception ex) {
        this(ex, HttpStatus.INTERNAL_SERVER_ERROR, (String) null);
    }

    public static ErrorResponse getInstance(FeignException ex) throws JsonProcessingException {
        return new ObjectMapper().readValue(ex.contentUTF8(), ErrorResponse.class);
    }

    private String findErrorLine(Exception ex){
        StackTraceElement[] stackTrace = ex.getStackTrace();
        return stackTrace.length == 0 ? "Unknown Error Line" : String.format("%s.%s:line-%d",
                stackTrace[0].getClassName(), stackTrace[0].getMethodName(), stackTrace[0].getLineNumber());
    }

    public MappingJacksonValue view() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(this);
        mappingJacksonValue.setSerializationView((messages == null ?
                SingleErrorView.class : MultiErrorView.class));
        return mappingJacksonValue;
    }

}