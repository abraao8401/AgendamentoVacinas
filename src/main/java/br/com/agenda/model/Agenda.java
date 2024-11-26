package br.com.agenda.model;
import br.com.agenda.enums.SituacaoAgenda;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "agendas")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Vacina vacina;

    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String hora;

    @Enumerated(EnumType.STRING) // Mapeando o Enum para uma string no banco de dados
    private SituacaoAgenda situacao;

    @Column(name = "data_situacao", nullable = true)
    private LocalDate dataSituacao;

    @Column(length = 200)
    private String observacoes;

    public Agenda( ) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public SituacaoAgenda getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAgenda situacao) {
        this.situacao = situacao;
    }

    public LocalDate getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(LocalDate dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
