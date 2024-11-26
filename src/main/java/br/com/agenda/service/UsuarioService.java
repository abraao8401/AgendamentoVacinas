package br.com.agenda.service;

import br.com.agenda.model.Usuario;
import br.com.agenda.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class UsuarioService {

    /**
     * Método para salvar um novo usuário.
     */
    public void salvarUsuario(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Método para buscar um usuário pelo ID.
     */
    public Usuario buscarUsuarioPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Método para listar todos os usuários.
     */
    public List<Usuario> listarUsuarios() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Método para atualizar um usuário existente.
     */
    public void atualizarUsuario(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Método para excluir um usuário pelo ID.
     */
    public void excluirUsuario(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao excluir usuário: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
