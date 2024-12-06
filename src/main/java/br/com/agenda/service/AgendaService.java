package br.com.agenda.service;

import br.com.agenda.model.Agenda;
import br.com.agenda.model.Vacina;
import br.com.agenda.model.Usuario;
import br.com.agenda.enums.SituacaoAgenda;
import br.com.agenda.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class AgendaService {

    // Listagem completa de todas as agendas
    public List<Agenda> listarTodasAgendas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Agenda> query = em.createQuery("SELECT a FROM Agenda a", Agenda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Listagem de agendas por situação (Canceladas ou Realizadas)
    public List<Agenda> listarAgendasPorSituacao(SituacaoAgenda situacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Agenda> query = em.createQuery("SELECT a FROM Agenda a WHERE a.situacao = :situacao", Agenda.class);
            query.setParameter("situacao", situacao);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Listagem das agendas do dia atual, ordenadas por situação
    public List<Agenda> listarAgendasDoDiaAtual() {
        EntityManager em = JPAUtil.getEntityManager();
        LocalDate hoje = LocalDate.now();

        try {
            return em.createQuery(
                            "SELECT a FROM Agenda a WHERE a.data = :hoje ORDER BY " +
                                    "CASE WHEN a.situacao = 'AGENDADA' THEN 1 " +
                                    "WHEN a.situacao = 'REALIZADA' THEN 2 " +
                                    "WHEN a.situacao = 'CANCELADA' THEN 3 END",
                            Agenda.class)
                    .setParameter("hoje", hoje)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Dar baixa na agenda (alterar situação para Realizada ou Cancelada)
    public void darBaixaNaAgenda(Integer id, SituacaoAgenda situacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Agenda agenda = em.find(Agenda.class, id);
            if (agenda != null) {
                agenda.setSituacao(situacao);
                agenda.setDataSituacao(LocalDate.now()); // Define a data de situação como a data atual
                em.merge(agenda);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Criar uma nova agenda
    public void criarAgenda(Agenda agenda) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(agenda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Atualizar uma agenda
    public void atualizarAgenda(Agenda agenda) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agenda); // O merge atualiza a agenda existente
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Excluir uma agenda
    public void excluirAgenda(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Agenda agenda = em.find(Agenda.class, id);
            if (agenda != null) {
                em.remove(agenda);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Método para listar as agendas de um usuário específico
    public List<Agenda> listarAgendasPorUsuario(Integer idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT a FROM Agenda a WHERE a.usuario.id = :idUsuario ORDER BY " +
                                    "CASE WHEN a.situacao = 'AGENDADA' THEN 1 " +
                                    "WHEN a.situacao = 'REALIZADA' THEN 2 " +
                                    "WHEN a.situacao = 'CANCELADA' THEN 3 END",
                            Agenda.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
