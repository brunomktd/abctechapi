package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AddressApplication;
import br.com.fiap.abctechapi.application.dto.AddressResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zipCode")
public class ZipCodeController {

    @Autowired
    private AddressApplication addressApplication;

    @GetMapping("/{zipCode}")
    public ResponseEntity<AddressResponseDto> searchZipCode(@PathVariable String zipCode){
        AddressResponseDto address = addressApplication.getAddressByZipCode(zipCode);
        return ResponseEntity.ok(address);
    }
}
