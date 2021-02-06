/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Modelo.Complementos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Contratos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ROBERTO
 */
public class ComplementosJpaController implements Serializable {

    public ComplementosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Complementos complementos) {
        if (complementos.getContratosList() == null) {
            complementos.setContratosList(new ArrayList<Contratos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Contratos> attachedContratosList = new ArrayList<Contratos>();
            for (Contratos contratosListContratosToAttach : complementos.getContratosList()) {
                contratosListContratosToAttach = em.getReference(contratosListContratosToAttach.getClass(), contratosListContratosToAttach.getIdcontrato());
                attachedContratosList.add(contratosListContratosToAttach);
            }
            complementos.setContratosList(attachedContratosList);
            em.persist(complementos);
            for (Contratos contratosListContratos : complementos.getContratosList()) {
                Complementos oldIdcomplementoOfContratosListContratos = contratosListContratos.getIdcomplemento();
                contratosListContratos.setIdcomplemento(complementos);
                contratosListContratos = em.merge(contratosListContratos);
                if (oldIdcomplementoOfContratosListContratos != null) {
                    oldIdcomplementoOfContratosListContratos.getContratosList().remove(contratosListContratos);
                    oldIdcomplementoOfContratosListContratos = em.merge(oldIdcomplementoOfContratosListContratos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Complementos complementos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Complementos persistentComplementos = em.find(Complementos.class, complementos.getIdcomplemento());
            List<Contratos> contratosListOld = persistentComplementos.getContratosList();
            List<Contratos> contratosListNew = complementos.getContratosList();
            List<Contratos> attachedContratosListNew = new ArrayList<Contratos>();
            for (Contratos contratosListNewContratosToAttach : contratosListNew) {
                contratosListNewContratosToAttach = em.getReference(contratosListNewContratosToAttach.getClass(), contratosListNewContratosToAttach.getIdcontrato());
                attachedContratosListNew.add(contratosListNewContratosToAttach);
            }
            contratosListNew = attachedContratosListNew;
            complementos.setContratosList(contratosListNew);
            complementos = em.merge(complementos);
            for (Contratos contratosListOldContratos : contratosListOld) {
                if (!contratosListNew.contains(contratosListOldContratos)) {
                    contratosListOldContratos.setIdcomplemento(null);
                    contratosListOldContratos = em.merge(contratosListOldContratos);
                }
            }
            for (Contratos contratosListNewContratos : contratosListNew) {
                if (!contratosListOld.contains(contratosListNewContratos)) {
                    Complementos oldIdcomplementoOfContratosListNewContratos = contratosListNewContratos.getIdcomplemento();
                    contratosListNewContratos.setIdcomplemento(complementos);
                    contratosListNewContratos = em.merge(contratosListNewContratos);
                    if (oldIdcomplementoOfContratosListNewContratos != null && !oldIdcomplementoOfContratosListNewContratos.equals(complementos)) {
                        oldIdcomplementoOfContratosListNewContratos.getContratosList().remove(contratosListNewContratos);
                        oldIdcomplementoOfContratosListNewContratos = em.merge(oldIdcomplementoOfContratosListNewContratos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = complementos.getIdcomplemento();
                if (findComplementos(id) == null) {
                    throw new NonexistentEntityException("The complementos with id " + id + " no longer exists.");
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
            Complementos complementos;
            try {
                complementos = em.getReference(Complementos.class, id);
                complementos.getIdcomplemento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The complementos with id " + id + " no longer exists.", enfe);
            }
            List<Contratos> contratosList = complementos.getContratosList();
            for (Contratos contratosListContratos : contratosList) {
                contratosListContratos.setIdcomplemento(null);
                contratosListContratos = em.merge(contratosListContratos);
            }
            em.remove(complementos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Complementos> findComplementosEntities() {
        return findComplementosEntities(true, -1, -1);
    }

    public List<Complementos> findComplementosEntities(int maxResults, int firstResult) {
        return findComplementosEntities(false, maxResults, firstResult);
    }

    private List<Complementos> findComplementosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Complementos.class));
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

    public Complementos findComplementos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Complementos.class, id);
        } finally {
            em.close();
        }
    }

    public int getComplementosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Complementos> rt = cq.from(Complementos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
