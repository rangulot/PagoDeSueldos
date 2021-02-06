/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Nominas;
import Modelo.Empleados;
import Modelo.Deducciones;
import Modelo.Detallesnominas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ROBERTO
 */
public class DetallesnominasJpaController implements Serializable {

    public DetallesnominasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallesnominas detallesnominas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nominas idnomina = detallesnominas.getIdnomina();
            if (idnomina != null) {
                idnomina = em.getReference(idnomina.getClass(), idnomina.getIdnomina());
                detallesnominas.setIdnomina(idnomina);
            }
            Empleados idempleado = detallesnominas.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                detallesnominas.setIdempleado(idempleado);
            }
            Deducciones iddeduccion = detallesnominas.getIddeduccion();
            if (iddeduccion != null) {
                iddeduccion = em.getReference(iddeduccion.getClass(), iddeduccion.getIddeduccion());
                detallesnominas.setIddeduccion(iddeduccion);
            }
            em.persist(detallesnominas);
            if (idnomina != null) {
                idnomina.getDetallesnominasList().add(detallesnominas);
                idnomina = em.merge(idnomina);
            }
            if (idempleado != null) {
                idempleado.getDetallesnominasList().add(detallesnominas);
                idempleado = em.merge(idempleado);
            }
            if (iddeduccion != null) {
                iddeduccion.getDetallesnominasList().add(detallesnominas);
                iddeduccion = em.merge(iddeduccion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallesnominas detallesnominas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallesnominas persistentDetallesnominas = em.find(Detallesnominas.class, detallesnominas.getIddetallesnominas());
            Nominas idnominaOld = persistentDetallesnominas.getIdnomina();
            Nominas idnominaNew = detallesnominas.getIdnomina();
            Empleados idempleadoOld = persistentDetallesnominas.getIdempleado();
            Empleados idempleadoNew = detallesnominas.getIdempleado();
            Deducciones iddeduccionOld = persistentDetallesnominas.getIddeduccion();
            Deducciones iddeduccionNew = detallesnominas.getIddeduccion();
            if (idnominaNew != null) {
                idnominaNew = em.getReference(idnominaNew.getClass(), idnominaNew.getIdnomina());
                detallesnominas.setIdnomina(idnominaNew);
            }
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                detallesnominas.setIdempleado(idempleadoNew);
            }
            if (iddeduccionNew != null) {
                iddeduccionNew = em.getReference(iddeduccionNew.getClass(), iddeduccionNew.getIddeduccion());
                detallesnominas.setIddeduccion(iddeduccionNew);
            }
            detallesnominas = em.merge(detallesnominas);
            if (idnominaOld != null && !idnominaOld.equals(idnominaNew)) {
                idnominaOld.getDetallesnominasList().remove(detallesnominas);
                idnominaOld = em.merge(idnominaOld);
            }
            if (idnominaNew != null && !idnominaNew.equals(idnominaOld)) {
                idnominaNew.getDetallesnominasList().add(detallesnominas);
                idnominaNew = em.merge(idnominaNew);
            }
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getDetallesnominasList().remove(detallesnominas);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getDetallesnominasList().add(detallesnominas);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (iddeduccionOld != null && !iddeduccionOld.equals(iddeduccionNew)) {
                iddeduccionOld.getDetallesnominasList().remove(detallesnominas);
                iddeduccionOld = em.merge(iddeduccionOld);
            }
            if (iddeduccionNew != null && !iddeduccionNew.equals(iddeduccionOld)) {
                iddeduccionNew.getDetallesnominasList().add(detallesnominas);
                iddeduccionNew = em.merge(iddeduccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallesnominas.getIddetallesnominas();
                if (findDetallesnominas(id) == null) {
                    throw new NonexistentEntityException("The detallesnominas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallesnominas detallesnominas;
            try {
                detallesnominas = em.getReference(Detallesnominas.class, id);
                detallesnominas.getIddetallesnominas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallesnominas with id " + id + " no longer exists.", enfe);
            }
            Nominas idnomina = detallesnominas.getIdnomina();
            if (idnomina != null) {
                idnomina.getDetallesnominasList().remove(detallesnominas);
                idnomina = em.merge(idnomina);
            }
            Empleados idempleado = detallesnominas.getIdempleado();
            if (idempleado != null) {
                idempleado.getDetallesnominasList().remove(detallesnominas);
                idempleado = em.merge(idempleado);
            }
            Deducciones iddeduccion = detallesnominas.getIddeduccion();
            if (iddeduccion != null) {
                iddeduccion.getDetallesnominasList().remove(detallesnominas);
                iddeduccion = em.merge(iddeduccion);
            }
            em.remove(detallesnominas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallesnominas> findDetallesnominasEntities() {
        return findDetallesnominasEntities(true, -1, -1);
    }

    public List<Detallesnominas> findDetallesnominasEntities(int maxResults, int firstResult) {
        return findDetallesnominasEntities(false, maxResults, firstResult);
    }

    private List<Detallesnominas> findDetallesnominasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallesnominas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detallesnominas findDetallesnominas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallesnominas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallesnominasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallesnominas> rt = cq.from(Detallesnominas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
