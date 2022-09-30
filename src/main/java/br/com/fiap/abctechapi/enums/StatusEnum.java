package br.com.fiap.abctechapi.enums;

public enum StatusEnum {
    PENDENTE,
    ANDAMENTO,
    CONCLUIDO,
    CANCELADO;

    public static StatusEnum getIndexStatus(Long index) {
        if (index == 0) {
            return PENDENTE;
        } else if (index == 1) {
            return ANDAMENTO;
        } else if (index == 2) {
            return CONCLUIDO;
        } else if (index == 3) {
            return CANCELADO;
        }
        return null;
    }
}
