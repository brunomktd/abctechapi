package br.com.fiap.abctechapi.integrations;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

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
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new RuntimeException(String.valueOf(response.status()));
    }
}
