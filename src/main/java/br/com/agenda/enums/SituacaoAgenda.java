package br.com.agenda.enums;

public enum SituacaoAgenda {
    AGENDADO("Agendado"),
    CANCELADO("Cancelado"),
    REALIZADO("Realizado");

    private final String descricao;

    SituacaoAgenda(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
