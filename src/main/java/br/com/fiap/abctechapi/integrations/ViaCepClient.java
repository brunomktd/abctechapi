package br.com.fiap.abctechapi.integrations;

import br.com.fiap.abctechapi.application.dto.ViaCepResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "viacep.com.br", configuration = ViaCepConfiguration.class)
public interface ViaCepClient {

    @GetMapping(value = "/ws/{zipCode}/json/", consumes = "application/json")
    ViaCepResponseDto searchZipCode(@PathVariable("zipCode") String zipCode);

}
