package br.com.agenda.service;

import br.com.agenda.model.Agenda;
import br.com.agenda.model.Alergia;
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

            // Se o usuário já tem alergias associadas, vamos persistir a relação na tabela intermediária
            if (usuario.getAlergias() != null && !usuario.getAlergias().isEmpty()) {
                for (Alergia alergia : usuario.getAlergias()) {
                    if (alergia.getId() == null) {
                        em.persist(alergia); // Persistir alergias que ainda não existem no banco
                    } else {
                        em.merge(alergia); // Atualizar alergias existentes
                    }
                }
            }

            // Persistir o usuário
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

            // Verificar e atualizar as alergias do usuário
            if (usuario.getAlergias() != null) {
                for (Alergia alergia : usuario.getAlergias()) {
                    if (alergia.getId() == null) {
                        em.persist(alergia); // Persistir alergias novas
                    } else {
                        em.merge(alergia); // Atualizar alergias existentes
                    }
                }
            }

            // Atualizar o usuário
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

            // Buscar o usuário
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                // Verificar se o usuário possui agendamentos relacionados
                List<Agenda> agendas = em.createQuery("SELECT a FROM Agenda a WHERE a.usuario.id = :usuarioId", Agenda.class)
                        .setParameter("usuarioId", usuario.getId())
                        .getResultList();

                if (!agendas.isEmpty()) {
                    // Excluir todas as agendas do usuário
                    for (Agenda agenda : agendas) {
                        em.remove(agenda); // Excluir agenda
                    }
                }

                // Desassociar alergias, mas não removê-las, caso estejam associadas a outros usuários
                if (usuario.getAlergias() != null) {
                    usuario.getAlergias().clear();
                }

                // Remover o usuário
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
