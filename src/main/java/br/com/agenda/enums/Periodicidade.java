package br.com.agenda.enums;

public enum Periodicidade {
    DIA(1),
    SEMANAS(2),
    MESES(3),
    ANOS(4);

    private final int codigo;

    Periodicidade(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Periodicidade fromCodigo(int codigo) {
        for (Periodicidade p : Periodicidade.values()) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        throw new IllegalArgumentException("Código inválido para Periodicidade: " + codigo);
    }
}
