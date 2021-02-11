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
import Modelo.Contratos;
import java.util.ArrayList;
import java.util.List;
import Modelo.Detallesnominas;
import Modelo.Empleados;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hacke
 */
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) {
        if (empleados.getContratosList() == null) {
            empleados.setContratosList(new ArrayList<Contratos>());
        }
        if (empleados.getDetallesnominasList() == null) {
            empleados.setDetallesnominasList(new ArrayList<Detallesnominas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Contratos> attachedContratosList = new ArrayList<Contratos>();
            for (Contratos contratosListContratosToAttach : empleados.getContratosList()) {
                contratosListContratosToAttach = em.getReference(contratosListContratosToAttach.getClass(), contratosListContratosToAttach.getIdcontrato());
                attachedContratosList.add(contratosListContratosToAttach);
            }
            empleados.setContratosList(attachedContratosList);
            List<Detallesnominas> attachedDetallesnominasList = new ArrayList<Detallesnominas>();
            for (Detallesnominas detallesnominasListDetallesnominasToAttach : empleados.getDetallesnominasList()) {
                detallesnominasListDetallesnominasToAttach = em.getReference(detallesnominasListDetallesnominasToAttach.getClass(), detallesnominasListDetallesnominasToAttach.getIddetallesnominas());
                attachedDetallesnominasList.add(detallesnominasListDetallesnominasToAttach);
            }
            empleados.setDetallesnominasList(attachedDetallesnominasList);
            em.persist(empleados);
            for (Contratos contratosListContratos : empleados.getContratosList()) {
                Empleados oldIdempleadoOfContratosListContratos = contratosListContratos.getIdempleado();
                contratosListContratos.setIdempleado(empleados);
                contratosListContratos = em.merge(contratosListContratos);
                if (oldIdempleadoOfContratosListContratos != null) {
                    oldIdempleadoOfContratosListContratos.getContratosList().remove(contratosListContratos);
                    oldIdempleadoOfContratosListContratos = em.merge(oldIdempleadoOfContratosListContratos);
                }
            }
            for (Detallesnominas detallesnominasListDetallesnominas : empleados.getDetallesnominasList()) {
                Empleados oldIdempleadoOfDetallesnominasListDetallesnominas = detallesnominasListDetallesnominas.getIdempleado();
                detallesnominasListDetallesnominas.setIdempleado(empleados);
                detallesnominasListDetallesnominas = em.merge(detallesnominasListDetallesnominas);
                if (oldIdempleadoOfDetallesnominasListDetallesnominas != null) {
                    oldIdempleadoOfDetallesnominasListDetallesnominas.getDetallesnominasList().remove(detallesnominasListDetallesnominas);
                    oldIdempleadoOfDetallesnominasListDetallesnominas = em.merge(oldIdempleadoOfDetallesnominasListDetallesnominas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getIdempleado());
            List<Contratos> contratosListOld = persistentEmpleados.getContratosList();
            List<Contratos> contratosListNew = empleados.getContratosList();
            List<Detallesnominas> detallesnominasListOld = persistentEmpleados.getDetallesnominasList();
            List<Detallesnominas> detallesnominasListNew = empleados.getDetallesnominasList();
            List<Contratos> attachedContratosListNew = new ArrayList<Contratos>();
            for (Contratos contratosListNewContratosToAttach : contratosListNew) {
                contratosListNewContratosToAttach = em.getReference(contratosListNewContratosToAttach.getClass(), contratosListNewContratosToAttach.getIdcontrato());
                attachedContratosListNew.add(contratosListNewContratosToAttach);
            }
            contratosListNew = attachedContratosListNew;
            empleados.setContratosList(contratosListNew);
            List<Detallesnominas> attachedDetallesnominasListNew = new ArrayList<Detallesnominas>();
            for (Detallesnominas detallesnominasListNewDetallesnominasToAttach : detallesnominasListNew) {
                detallesnominasListNewDetallesnominasToAttach = em.getReference(detallesnominasListNewDetallesnominasToAttach.getClass(), detallesnominasListNewDetallesnominasToAttach.getIddetallesnominas());
                attachedDetallesnominasListNew.add(detallesnominasListNewDetallesnominasToAttach);
            }
            detallesnominasListNew = attachedDetallesnominasListNew;
            empleados.setDetallesnominasList(detallesnominasListNew);
            empleados = em.merge(empleados);
            for (Contratos contratosListOldContratos : contratosListOld) {
                if (!contratosListNew.contains(contratosListOldContratos)) {
                    contratosListOldContratos.setIdempleado(null);
                    contratosListOldContratos = em.merge(contratosListOldContratos);
                }
            }
            for (Contratos contratosListNewContratos : contratosListNew) {
                if (!contratosListOld.contains(contratosListNewContratos)) {
                    Empleados oldIdempleadoOfContratosListNewContratos = contratosListNewContratos.getIdempleado();
                    contratosListNewContratos.setIdempleado(empleados);
                    contratosListNewContratos = em.merge(contratosListNewContratos);
                    if (oldIdempleadoOfContratosListNewContratos != null && !oldIdempleadoOfContratosListNewContratos.equals(empleados)) {
                        oldIdempleadoOfContratosListNewContratos.getContratosList().remove(contratosListNewContratos);
                        oldIdempleadoOfContratosListNewContratos = em.merge(oldIdempleadoOfContratosListNewContratos);
                    }
                }
            }
            for (Detallesnominas detallesnominasListOldDetallesnominas : detallesnominasListOld) {
                if (!detallesnominasListNew.contains(detallesnominasListOldDetallesnominas)) {
                    detallesnominasListOldDetallesnominas.setIdempleado(null);
                    detallesnominasListOldDetallesnominas = em.merge(detallesnominasListOldDetallesnominas);
                }
            }
            for (Detallesnominas detallesnominasListNewDetallesnominas : detallesnominasListNew) {
                if (!detallesnominasListOld.contains(detallesnominasListNewDetallesnominas)) {
                    Empleados oldIdempleadoOfDetallesnominasListNewDetallesnominas = detallesnominasListNewDetallesnominas.getIdempleado();
                    detallesnominasListNewDetallesnominas.setIdempleado(empleados);
                    detallesnominasListNewDetallesnominas = em.merge(detallesnominasListNewDetallesnominas);
                    if (oldIdempleadoOfDetallesnominasListNewDetallesnominas != null && !oldIdempleadoOfDetallesnominasListNewDetallesnominas.equals(empleados)) {
                        oldIdempleadoOfDetallesnominasListNewDetallesnominas.getDetallesnominasList().remove(detallesnominasListNewDetallesnominas);
                        oldIdempleadoOfDetallesnominasListNewDetallesnominas = em.merge(oldIdempleadoOfDetallesnominasListNewDetallesnominas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleados.getIdempleado();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
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
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getIdempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            List<Contratos> contratosList = empleados.getContratosList();
            for (Contratos contratosListContratos : contratosList) {
                contratosListContratos.setIdempleado(null);
                contratosListContratos = em.merge(contratosListContratos);
            }
            List<Detallesnominas> detallesnominasList = empleados.getDetallesnominasList();
            for (Detallesnominas detallesnominasListDetallesnominas : detallesnominasList) {
                detallesnominasListDetallesnominas.setIdempleado(null);
                detallesnominasListDetallesnominas = em.merge(detallesnominasListDetallesnominas);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
