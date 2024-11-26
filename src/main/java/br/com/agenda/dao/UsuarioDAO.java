package br.com.agenda.dao;

import br.com.agenda.model.Usuario;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public List<Usuario> findByNome(String nome) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE :nome", Usuario.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
