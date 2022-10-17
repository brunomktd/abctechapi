package br.com.fiap.abctechapi.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum StatusEnum {
    PENDENTE("0"),
    ANDAMENTO("1"),
    CONCLUIDO("2"),
    CANCELADO("3");

    private String code;

    public static StatusEnum getStatusEnumByCode(String code) {
        Optional<StatusEnum> statusEnumOptional = Arrays.stream(StatusEnum.values()).filter(s -> s.code.equalsIgnoreCase(code)).findFirst();
        return statusEnumOptional.orElse(null);
    }

}
