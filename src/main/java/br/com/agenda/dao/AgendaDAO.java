package br.com.agenda.dao;

import br.com.agenda.enums.SituacaoAgenda;
import br.com.agenda.model.Agenda;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class AgendaDAO extends GenericDAO<Agenda> {

    public AgendaDAO() {
        super(Agenda.class);
    }

    public List<Agenda> findBySituacao(SituacaoAgenda situacao) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Agenda a WHERE a.situacao = :situacao", Agenda.class)
                    .setParameter("situacao", situacao)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Agenda> findByData(LocalDate data) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Agenda a WHERE a.data = :data ORDER BY a.situacao", Agenda.class)
                    .setParameter("data", data)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
