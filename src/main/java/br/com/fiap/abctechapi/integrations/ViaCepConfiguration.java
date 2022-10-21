package br.com.fiap.abctechapi.integrations;

import br.com.fiap.abctechapi.handler.exception.ViaCepErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class ViaCepConfiguration implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        Response.Body body = response.body();
        try {
            InputStream inputStream = body.asInputStream();
            new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines();
        } catch (IOException e) {
            throw new ViaCepErrorException(e.getMessage());
        }

        return new ViaCepErrorException(String.valueOf(response.reason()));
    }
}
