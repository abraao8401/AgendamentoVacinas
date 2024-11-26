package br.com.agenda.dao;

import br.com.agenda.model.Vacina;
import jakarta.persistence.EntityManager;
import java.util.List;

public class VacinaDAO extends GenericDAO<Vacina> {

    public VacinaDAO() {
        super(Vacina.class);
    }

    public List<Vacina> findByTitulo(String titulo) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vacina v WHERE v.titulo LIKE :titulo", Vacina.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
