package actividadFinal.persistencia;

import java.util.List;
import actividadFinal.logica.Plataforma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PlataformaJpaController {

    private EntityManagerFactory emf = null;

    public PlataformaJpaController() {
        emf = Persistence.createEntityManagerFactory("actividadFinalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Plataforma plat) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(plat);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Plataforma buscarUno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plataforma.class, id);
        } finally {
            em.close();
        }
    }

    public List<Plataforma> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Plataforma p", Plataforma.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void editar(Plataforma plat) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(plat);
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
            Plataforma plat = em.find(Plataforma.class, id);
            if (plat != null) {
                em.remove(plat);
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