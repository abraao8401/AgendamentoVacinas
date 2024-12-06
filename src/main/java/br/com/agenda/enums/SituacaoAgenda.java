package br.com.agenda.enums;

public enum SituacaoAgenda {
    AGENDADA("Agendada"),
    CANCELADA("Cancelada"),
    REALIZADA("Realizada");

    private final String descricao;

    SituacaoAgenda(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
