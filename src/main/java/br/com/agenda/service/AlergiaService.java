package br.com.agenda.service;

import br.com.agenda.model.Alergia;
import br.com.agenda.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class AlergiaService {

    /**
     * Método para criar ou salvar uma nova Alergia.
     */
    public void salvarAlergia(Alergia alergia) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(alergia);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar alergia: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Método para buscar uma Alergia pelo ID.
     */
    public Alergia buscarAlergiaPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Alergia.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Método para listar todas as Alergias.
     */
    public List<Alergia> listarAlergias() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("SELECT a FROM Alergia a", Alergia.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Método para atualizar uma Alergia existente.
     */
    public void atualizarAlergia(Alergia alergia) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(alergia);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar alergia: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Método para excluir uma Alergia pelo ID.
     */
    public void excluirAlergia(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Alergia alergia = em.find(Alergia.class, id);
            if (alergia != null) {
                em.remove(alergia);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao excluir alergia: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
