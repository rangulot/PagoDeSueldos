/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Modelo.Categorias;
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
 * @author hacke
 */
public class CategoriasJpaController implements Serializable {

    public CategoriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categorias categorias) {
        if (categorias.getContratosList() == null) {
            categorias.setContratosList(new ArrayList<Contratos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratos idcontrato = categorias.getIdcontrato();
            if (idcontrato != null) {
                idcontrato = em.getReference(idcontrato.getClass(), idcontrato.getIdcontrato());
                categorias.setIdcontrato(idcontrato);
            }
            List<Contratos> attachedContratosList = new ArrayList<Contratos>();
            for (Contratos contratosListContratosToAttach : categorias.getContratosList()) {
                contratosListContratosToAttach = em.getReference(contratosListContratosToAttach.getClass(), contratosListContratosToAttach.getIdcontrato());
                attachedContratosList.add(contratosListContratosToAttach);
            }
            categorias.setContratosList(attachedContratosList);
            em.persist(categorias);
            if (idcontrato != null) {
                idcontrato.getCategoriasList().add(categorias);
                idcontrato = em.merge(idcontrato);
            }
            for (Contratos contratosListContratos : categorias.getContratosList()) {
                Categorias oldIdcategoriaOfContratosListContratos = contratosListContratos.getIdcategoria();
                contratosListContratos.setIdcategoria(categorias);
                contratosListContratos = em.merge(contratosListContratos);
                if (oldIdcategoriaOfContratosListContratos != null) {
                    oldIdcategoriaOfContratosListContratos.getContratosList().remove(contratosListContratos);
                    oldIdcategoriaOfContratosListContratos = em.merge(oldIdcategoriaOfContratosListContratos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categorias categorias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias persistentCategorias = em.find(Categorias.class, categorias.getIdcategoria());
            Contratos idcontratoOld = persistentCategorias.getIdcontrato();
            Contratos idcontratoNew = categorias.getIdcontrato();
            List<Contratos> contratosListOld = persistentCategorias.getContratosList();
            List<Contratos> contratosListNew = categorias.getContratosList();
            if (idcontratoNew != null) {
                idcontratoNew = em.getReference(idcontratoNew.getClass(), idcontratoNew.getIdcontrato());
                categorias.setIdcontrato(idcontratoNew);
            }
            List<Contratos> attachedContratosListNew = new ArrayList<Contratos>();
            for (Contratos contratosListNewContratosToAttach : contratosListNew) {
                contratosListNewContratosToAttach = em.getReference(contratosListNewContratosToAttach.getClass(), contratosListNewContratosToAttach.getIdcontrato());
                attachedContratosListNew.add(contratosListNewContratosToAttach);
            }
            contratosListNew = attachedContratosListNew;
            categorias.setContratosList(contratosListNew);
            categorias = em.merge(categorias);
            if (idcontratoOld != null && !idcontratoOld.equals(idcontratoNew)) {
                idcontratoOld.getCategoriasList().remove(categorias);
                idcontratoOld = em.merge(idcontratoOld);
            }
            if (idcontratoNew != null && !idcontratoNew.equals(idcontratoOld)) {
                idcontratoNew.getCategoriasList().add(categorias);
                idcontratoNew = em.merge(idcontratoNew);
            }
            for (Contratos contratosListOldContratos : contratosListOld) {
                if (!contratosListNew.contains(contratosListOldContratos)) {
                    contratosListOldContratos.setIdcategoria(null);
                    contratosListOldContratos = em.merge(contratosListOldContratos);
                }
            }
            for (Contratos contratosListNewContratos : contratosListNew) {
                if (!contratosListOld.contains(contratosListNewContratos)) {
                    Categorias oldIdcategoriaOfContratosListNewContratos = contratosListNewContratos.getIdcategoria();
                    contratosListNewContratos.setIdcategoria(categorias);
                    contratosListNewContratos = em.merge(contratosListNewContratos);
                    if (oldIdcategoriaOfContratosListNewContratos != null && !oldIdcategoriaOfContratosListNewContratos.equals(categorias)) {
                        oldIdcategoriaOfContratosListNewContratos.getContratosList().remove(contratosListNewContratos);
                        oldIdcategoriaOfContratosListNewContratos = em.merge(oldIdcategoriaOfContratosListNewContratos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categorias.getIdcategoria();
                if (findCategorias(id) == null) {
                    throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.");
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
            Categorias categorias;
            try {
                categorias = em.getReference(Categorias.class, id);
                categorias.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.", enfe);
            }
            Contratos idcontrato = categorias.getIdcontrato();
            if (idcontrato != null) {
                idcontrato.getCategoriasList().remove(categorias);
                idcontrato = em.merge(idcontrato);
            }
            List<Contratos> contratosList = categorias.getContratosList();
            for (Contratos contratosListContratos : contratosList) {
                contratosListContratos.setIdcategoria(null);
                contratosListContratos = em.merge(contratosListContratos);
            }
            em.remove(categorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categorias> findCategoriasEntities() {
        return findCategoriasEntities(true, -1, -1);
    }

    public List<Categorias> findCategoriasEntities(int maxResults, int firstResult) {
        return findCategoriasEntities(false, maxResults, firstResult);
    }

    private List<Categorias> findCategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categorias.class));
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

    public Categorias findCategorias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categorias> rt = cq.from(Categorias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
