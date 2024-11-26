package br.com.agenda.model;
import br.com.agenda.enums.Periodicidade;
import jakarta.persistence.*;

@Entity
@Table(name = "vacinas")
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String titulo;

    @Column(length = 200)
    private String descricao;

    @Column(nullable = false)
    private Integer doses;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true) // Pode ser null, se o número de doses for 1
    private Periodicidade periodicidade;

    @Column(nullable = true) // Pode ser null, se o número de doses for 1
    private Integer intervalo;

    public Vacina() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDoses() {
        return doses;
    }

    public void setDoses(Integer doses) {
        this.doses = doses;
    }

    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Periodicidade periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }
}
