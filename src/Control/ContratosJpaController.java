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
import Modelo.Empleados;
import Modelo.Categorias;
import Modelo.Complementos;
import Modelo.Contratos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ROBERTO
 */
public class ContratosJpaController implements Serializable {

    public ContratosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratos contratos) {
        if (contratos.getCategoriasList() == null) {
            contratos.setCategoriasList(new ArrayList<Categorias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados idempleado = contratos.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                contratos.setIdempleado(idempleado);
            }
            Categorias idcategoria = contratos.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                contratos.setIdcategoria(idcategoria);
            }
            Complementos idcomplemento = contratos.getIdcomplemento();
            if (idcomplemento != null) {
                idcomplemento = em.getReference(idcomplemento.getClass(), idcomplemento.getIdcomplemento());
                contratos.setIdcomplemento(idcomplemento);
            }
            List<Categorias> attachedCategoriasList = new ArrayList<Categorias>();
            for (Categorias categoriasListCategoriasToAttach : contratos.getCategoriasList()) {
                categoriasListCategoriasToAttach = em.getReference(categoriasListCategoriasToAttach.getClass(), categoriasListCategoriasToAttach.getIdcategoria());
                attachedCategoriasList.add(categoriasListCategoriasToAttach);
            }
            contratos.setCategoriasList(attachedCategoriasList);
            em.persist(contratos);
            if (idempleado != null) {
                idempleado.getContratosList().add(contratos);
                idempleado = em.merge(idempleado);
            }
            if (idcategoria != null) {
                Contratos oldIdcontratoOfIdcategoria = idcategoria.getIdcontrato();
                if (oldIdcontratoOfIdcategoria != null) {
                    oldIdcontratoOfIdcategoria.setIdcategoria(null);
                    oldIdcontratoOfIdcategoria = em.merge(oldIdcontratoOfIdcategoria);
                }
                idcategoria.setIdcontrato(contratos);
                idcategoria = em.merge(idcategoria);
            }
            if (idcomplemento != null) {
                idcomplemento.getContratosList().add(contratos);
                idcomplemento = em.merge(idcomplemento);
            }
            for (Categorias categoriasListCategorias : contratos.getCategoriasList()) {
                Contratos oldIdcontratoOfCategoriasListCategorias = categoriasListCategorias.getIdcontrato();
                categoriasListCategorias.setIdcontrato(contratos);
                categoriasListCategorias = em.merge(categoriasListCategorias);
                if (oldIdcontratoOfCategoriasListCategorias != null) {
                    oldIdcontratoOfCategoriasListCategorias.getCategoriasList().remove(categoriasListCategorias);
                    oldIdcontratoOfCategoriasListCategorias = em.merge(oldIdcontratoOfCategoriasListCategorias);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contratos contratos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratos persistentContratos = em.find(Contratos.class, contratos.getIdcontrato());
            Empleados idempleadoOld = persistentContratos.getIdempleado();
            Empleados idempleadoNew = contratos.getIdempleado();
            Categorias idcategoriaOld = persistentContratos.getIdcategoria();
            Categorias idcategoriaNew = contratos.getIdcategoria();
            Complementos idcomplementoOld = persistentContratos.getIdcomplemento();
            Complementos idcomplementoNew = contratos.getIdcomplemento();
            List<Categorias> categoriasListOld = persistentContratos.getCategoriasList();
            List<Categorias> categoriasListNew = contratos.getCategoriasList();
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                contratos.setIdempleado(idempleadoNew);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                contratos.setIdcategoria(idcategoriaNew);
            }
            if (idcomplementoNew != null) {
                idcomplementoNew = em.getReference(idcomplementoNew.getClass(), idcomplementoNew.getIdcomplemento());
                contratos.setIdcomplemento(idcomplementoNew);
            }
            List<Categorias> attachedCategoriasListNew = new ArrayList<Categorias>();
            for (Categorias categoriasListNewCategoriasToAttach : categoriasListNew) {
                categoriasListNewCategoriasToAttach = em.getReference(categoriasListNewCategoriasToAttach.getClass(), categoriasListNewCategoriasToAttach.getIdcategoria());
                attachedCategoriasListNew.add(categoriasListNewCategoriasToAttach);
            }
            categoriasListNew = attachedCategoriasListNew;
            contratos.setCategoriasList(categoriasListNew);
            contratos = em.merge(contratos);
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getContratosList().remove(contratos);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getContratosList().add(contratos);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.setIdcontrato(null);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                Contratos oldIdcontratoOfIdcategoria = idcategoriaNew.getIdcontrato();
                if (oldIdcontratoOfIdcategoria != null) {
                    oldIdcontratoOfIdcategoria.setIdcategoria(null);
                    oldIdcontratoOfIdcategoria = em.merge(oldIdcontratoOfIdcategoria);
                }
                idcategoriaNew.setIdcontrato(contratos);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            if (idcomplementoOld != null && !idcomplementoOld.equals(idcomplementoNew)) {
                idcomplementoOld.getContratosList().remove(contratos);
                idcomplementoOld = em.merge(idcomplementoOld);
            }
            if (idcomplementoNew != null && !idcomplementoNew.equals(idcomplementoOld)) {
                idcomplementoNew.getContratosList().add(contratos);
                idcomplementoNew = em.merge(idcomplementoNew);
            }
            for (Categorias categoriasListOldCategorias : categoriasListOld) {
                if (!categoriasListNew.contains(categoriasListOldCategorias)) {
                    categoriasListOldCategorias.setIdcontrato(null);
                    categoriasListOldCategorias = em.merge(categoriasListOldCategorias);
                }
            }
            for (Categorias categoriasListNewCategorias : categoriasListNew) {
                if (!categoriasListOld.contains(categoriasListNewCategorias)) {
                    Contratos oldIdcontratoOfCategoriasListNewCategorias = categoriasListNewCategorias.getIdcontrato();
                    categoriasListNewCategorias.setIdcontrato(contratos);
                    categoriasListNewCategorias = em.merge(categoriasListNewCategorias);
                    if (oldIdcontratoOfCategoriasListNewCategorias != null && !oldIdcontratoOfCategoriasListNewCategorias.equals(contratos)) {
                        oldIdcontratoOfCategoriasListNewCategorias.getCategoriasList().remove(categoriasListNewCategorias);
                        oldIdcontratoOfCategoriasListNewCategorias = em.merge(oldIdcontratoOfCategoriasListNewCategorias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contratos.getIdcontrato();
                if (findContratos(id) == null) {
                    throw new NonexistentEntityException("The contratos with id " + id + " no longer exists.");
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
            Contratos contratos;
            try {
                contratos = em.getReference(Contratos.class, id);
                contratos.getIdcontrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratos with id " + id + " no longer exists.", enfe);
            }
            Empleados idempleado = contratos.getIdempleado();
            if (idempleado != null) {
                idempleado.getContratosList().remove(contratos);
                idempleado = em.merge(idempleado);
            }
            Categorias idcategoria = contratos.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.setIdcontrato(null);
                idcategoria = em.merge(idcategoria);
            }
            Complementos idcomplemento = contratos.getIdcomplemento();
            if (idcomplemento != null) {
                idcomplemento.getContratosList().remove(contratos);
                idcomplemento = em.merge(idcomplemento);
            }
            List<Categorias> categoriasList = contratos.getCategoriasList();
            for (Categorias categoriasListCategorias : categoriasList) {
                categoriasListCategorias.setIdcontrato(null);
                categoriasListCategorias = em.merge(categoriasListCategorias);
            }
            em.remove(contratos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contratos> findContratosEntities() {
        return findContratosEntities(true, -1, -1);
    }

    public List<Contratos> findContratosEntities(int maxResults, int firstResult) {
        return findContratosEntities(false, maxResults, firstResult);
    }

    private List<Contratos> findContratosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contratos.class));
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

    public Contratos findContratos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contratos.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contratos> rt = cq.from(Contratos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
