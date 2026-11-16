package actividadFinal.persistencia;

import java.util.List;
import actividadFinal.logica.Genero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GeneroJpaController {

    private EntityManagerFactory emf = null;

    public GeneroJpaController() {
        emf = Persistence.createEntityManagerFactory("actividadFinalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Genero gen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gen);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Genero buscarUno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public List<Genero> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void editar(Genero gen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(gen);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }

    public void eliminar(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero gen = em.find(Genero.class, id);
            if (gen != null) {
                em.remove(gen);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }
}