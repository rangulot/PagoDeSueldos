/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Detallesnominas;
import Modelo.Nominas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hacke
 */
public class NominasJpaController implements Serializable {

    public NominasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nominas nominas) {
        if (nominas.getDetallesnominasList() == null) {
            nominas.setDetallesnominasList(new ArrayList<Detallesnominas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallesnominas> attachedDetallesnominasList = new ArrayList<Detallesnominas>();
            for (Detallesnominas detallesnominasListDetallesnominasToAttach : nominas.getDetallesnominasList()) {
                detallesnominasListDetallesnominasToAttach = em.getReference(detallesnominasListDetallesnominasToAttach.getClass(), detallesnominasListDetallesnominasToAttach.getIddetallenomina());
                attachedDetallesnominasList.add(detallesnominasListDetallesnominasToAttach);
            }
            nominas.setDetallesnominasList(attachedDetallesnominasList);
            em.persist(nominas);
            for (Detallesnominas detallesnominasListDetallesnominas : nominas.getDetallesnominasList()) {
                Nominas oldIdnominaOfDetallesnominasListDetallesnominas = detallesnominasListDetallesnominas.getIdnomina();
                detallesnominasListDetallesnominas.setIdnomina(nominas);
                detallesnominasListDetallesnominas = em.merge(detallesnominasListDetallesnominas);
                if (oldIdnominaOfDetallesnominasListDetallesnominas != null) {
                    oldIdnominaOfDetallesnominasListDetallesnominas.getDetallesnominasList().remove(detallesnominasListDetallesnominas);
                    oldIdnominaOfDetallesnominasListDetallesnominas = em.merge(oldIdnominaOfDetallesnominasListDetallesnominas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nominas nominas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nominas persistentNominas = em.find(Nominas.class, nominas.getIdnomina());
            List<Detallesnominas> detallesnominasListOld = persistentNominas.getDetallesnominasList();
            List<Detallesnominas> detallesnominasListNew = nominas.getDetallesnominasList();
            List<String> illegalOrphanMessages = null;
            for (Detallesnominas detallesnominasListOldDetallesnominas : detallesnominasListOld) {
                if (!detallesnominasListNew.contains(detallesnominasListOldDetallesnominas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallesnominas " + detallesnominasListOldDetallesnominas + " since its idnomina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detallesnominas> attachedDetallesnominasListNew = new ArrayList<Detallesnominas>();
            for (Detallesnominas detallesnominasListNewDetallesnominasToAttach : detallesnominasListNew) {
                detallesnominasListNewDetallesnominasToAttach = em.getReference(detallesnominasListNewDetallesnominasToAttach.getClass(), detallesnominasListNewDetallesnominasToAttach.getIddetallenomina());
                attachedDetallesnominasListNew.add(detallesnominasListNewDetallesnominasToAttach);
            }
            detallesnominasListNew = attachedDetallesnominasListNew;
            nominas.setDetallesnominasList(detallesnominasListNew);
            nominas = em.merge(nominas);
            for (Detallesnominas detallesnominasListNewDetallesnominas : detallesnominasListNew) {
                if (!detallesnominasListOld.contains(detallesnominasListNewDetallesnominas)) {
                    Nominas oldIdnominaOfDetallesnominasListNewDetallesnominas = detallesnominasListNewDetallesnominas.getIdnomina();
                    detallesnominasListNewDetallesnominas.setIdnomina(nominas);
                    detallesnominasListNewDetallesnominas = em.merge(detallesnominasListNewDetallesnominas);
                    if (oldIdnominaOfDetallesnominasListNewDetallesnominas != null && !oldIdnominaOfDetallesnominasListNewDetallesnominas.equals(nominas)) {
                        oldIdnominaOfDetallesnominasListNewDetallesnominas.getDetallesnominasList().remove(detallesnominasListNewDetallesnominas);
                        oldIdnominaOfDetallesnominasListNewDetallesnominas = em.merge(oldIdnominaOfDetallesnominasListNewDetallesnominas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nominas.getIdnomina();
                if (findNominas(id) == null) {
                    throw new NonexistentEntityException("The nominas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nominas nominas;
            try {
                nominas = em.getReference(Nominas.class, id);
                nominas.getIdnomina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nominas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallesnominas> detallesnominasListOrphanCheck = nominas.getDetallesnominasList();
            for (Detallesnominas detallesnominasListOrphanCheckDetallesnominas : detallesnominasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Nominas (" + nominas + ") cannot be destroyed since the Detallesnominas " + detallesnominasListOrphanCheckDetallesnominas + " in its detallesnominasList field has a non-nullable idnomina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nominas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nominas> findNominasEntities() {
        return findNominasEntities(true, -1, -1);
    }

    public List<Nominas> findNominasEntities(int maxResults, int firstResult) {
        return findNominasEntities(false, maxResults, firstResult);
    }

    private List<Nominas> findNominasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nominas.class));
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

    public Nominas findNominas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nominas.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nominas> rt = cq.from(Nominas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
