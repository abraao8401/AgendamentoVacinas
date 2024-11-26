package br.com.agenda.service;

import br.com.agenda.model.Vacina;
import br.com.agenda.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class VacinaService {

    /**
     * Salvar uma nova vacina.
     */
    public void salvarVacina(Vacina vacina) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(vacina);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar vacina: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Buscar vacina pelo ID.
     */
    public Vacina buscarVacinaPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Vacina.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Listar todas as vacinas.
     */
    public List<Vacina> listarVacinas() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("SELECT v FROM Vacina v", Vacina.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Atualizar os dados de uma vacina.
     */
    public void atualizarVacina(Vacina vacina) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(vacina);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar vacina: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Excluir vacina pelo ID.
     */
    public void excluirVacina(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Vacina vacina = em.find(Vacina.class, id);
            if (vacina != null) {
                em.remove(vacina);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao excluir vacina: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
