/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Modelo.Deducciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Detallesnominas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ROBERTO
 */
public class DeduccionesJpaController implements Serializable {

    public DeduccionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Deducciones deducciones) {
        if (deducciones.getDetallesnominasList() == null) {
            deducciones.setDetallesnominasList(new ArrayList<Detallesnominas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallesnominas> attachedDetallesnominasList = new ArrayList<Detallesnominas>();
            for (Detallesnominas detallesnominasListDetallesnominasToAttach : deducciones.getDetallesnominasList()) {
                detallesnominasListDetallesnominasToAttach = em.getReference(detallesnominasListDetallesnominasToAttach.getClass(), detallesnominasListDetallesnominasToAttach.getIddetallesnominas());
                attachedDetallesnominasList.add(detallesnominasListDetallesnominasToAttach);
            }
            deducciones.setDetallesnominasList(attachedDetallesnominasList);
            em.persist(deducciones);
            for (Detallesnominas detallesnominasListDetallesnominas : deducciones.getDetallesnominasList()) {
                Deducciones oldIddeduccionOfDetallesnominasListDetallesnominas = detallesnominasListDetallesnominas.getIddeduccion();
                detallesnominasListDetallesnominas.setIddeduccion(deducciones);
                detallesnominasListDetallesnominas = em.merge(detallesnominasListDetallesnominas);
                if (oldIddeduccionOfDetallesnominasListDetallesnominas != null) {
                    oldIddeduccionOfDetallesnominasListDetallesnominas.getDetallesnominasList().remove(detallesnominasListDetallesnominas);
                    oldIddeduccionOfDetallesnominasListDetallesnominas = em.merge(oldIddeduccionOfDetallesnominasListDetallesnominas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Deducciones deducciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deducciones persistentDeducciones = em.find(Deducciones.class, deducciones.getIddeduccion());
            List<Detallesnominas> detallesnominasListOld = persistentDeducciones.getDetallesnominasList();
            List<Detallesnominas> detallesnominasListNew = deducciones.getDetallesnominasList();
            List<Detallesnominas> attachedDetallesnominasListNew = new ArrayList<Detallesnominas>();
            for (Detallesnominas detallesnominasListNewDetallesnominasToAttach : detallesnominasListNew) {
                detallesnominasListNewDetallesnominasToAttach = em.getReference(detallesnominasListNewDetallesnominasToAttach.getClass(), detallesnominasListNewDetallesnominasToAttach.getIddetallesnominas());
                attachedDetallesnominasListNew.add(detallesnominasListNewDetallesnominasToAttach);
            }
            detallesnominasListNew = attachedDetallesnominasListNew;
            deducciones.setDetallesnominasList(detallesnominasListNew);
            deducciones = em.merge(deducciones);
            for (Detallesnominas detallesnominasListOldDetallesnominas : detallesnominasListOld) {
                if (!detallesnominasListNew.contains(detallesnominasListOldDetallesnominas)) {
                    detallesnominasListOldDetallesnominas.setIddeduccion(null);
                    detallesnominasListOldDetallesnominas = em.merge(detallesnominasListOldDetallesnominas);
                }
            }
            for (Detallesnominas detallesnominasListNewDetallesnominas : detallesnominasListNew) {
                if (!detallesnominasListOld.contains(detallesnominasListNewDetallesnominas)) {
                    Deducciones oldIddeduccionOfDetallesnominasListNewDetallesnominas = detallesnominasListNewDetallesnominas.getIddeduccion();
                    detallesnominasListNewDetallesnominas.setIddeduccion(deducciones);
                    detallesnominasListNewDetallesnominas = em.merge(detallesnominasListNewDetallesnominas);
                    if (oldIddeduccionOfDetallesnominasListNewDetallesnominas != null && !oldIddeduccionOfDetallesnominasListNewDetallesnominas.equals(deducciones)) {
                        oldIddeduccionOfDetallesnominasListNewDetallesnominas.getDetallesnominasList().remove(detallesnominasListNewDetallesnominas);
                        oldIddeduccionOfDetallesnominasListNewDetallesnominas = em.merge(oldIddeduccionOfDetallesnominasListNewDetallesnominas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = deducciones.getIddeduccion();
                if (findDeducciones(id) == null) {
                    throw new NonexistentEntityException("The deducciones with id " + id + " no longer exists.");
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
            Deducciones deducciones;
            try {
                deducciones = em.getReference(Deducciones.class, id);
                deducciones.getIddeduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deducciones with id " + id + " no longer exists.", enfe);
            }
            List<Detallesnominas> detallesnominasList = deducciones.getDetallesnominasList();
            for (Detallesnominas detallesnominasListDetallesnominas : detallesnominasList) {
                detallesnominasListDetallesnominas.setIddeduccion(null);
                detallesnominasListDetallesnominas = em.merge(detallesnominasListDetallesnominas);
            }
            em.remove(deducciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Deducciones> findDeduccionesEntities() {
        return findDeduccionesEntities(true, -1, -1);
    }

    public List<Deducciones> findDeduccionesEntities(int maxResults, int firstResult) {
        return findDeduccionesEntities(false, maxResults, firstResult);
    }

    private List<Deducciones> findDeduccionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Deducciones.class));
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

    public Deducciones findDeducciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Deducciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeduccionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Deducciones> rt = cq.from(Deducciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
