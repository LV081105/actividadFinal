package actividadFinal.persistencia;

import java.util.List;
import actividadFinal.logica.Videojuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class VideojuegoJpaController {

    private EntityManagerFactory emf = null;

    public VideojuegoJpaController() {
        emf = Persistence.createEntityManagerFactory("actividadFinalPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Videojuego vid) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vid);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public Videojuego buscarUno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Videojuego.class, id);
        } finally {
            em.close();
        }
    }

    public List<Videojuego> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Videojuego v", Videojuego.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Consulta JPQL para el buscador por título
    public List<Videojuego> buscarPorTitulo(String titulo) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT v FROM Videojuego v WHERE LOWER(v.titulo) LIKE :titulo", Videojuego.class)
                .setParameter("titulo", "%" + titulo.toLowerCase() + "%")
                .getResultList();
        } finally {
            em.close();
        }
    }

    public void editar(Videojuego vid) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(vid);
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
            Videojuego vid = em.find(Videojuego.class, id);
            if (vid != null) {
                em.remove(vid);
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