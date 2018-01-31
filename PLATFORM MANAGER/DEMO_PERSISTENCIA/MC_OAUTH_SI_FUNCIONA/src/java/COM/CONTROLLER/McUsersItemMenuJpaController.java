/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.CONTROLLER;

import COM.CONTROLLER.exceptions.NonexistentEntityException;
import COM.CONTROLLER.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import COM.ENT.McMenuItem;
import COM.ENT.McUsers;
import COM.ENT.McUsersItemMenu;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McUsersItemMenuJpaController implements Serializable {

    public McUsersItemMenuJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McUsersItemMenu mcUsersItemMenu) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McMenuItem mcMenuItemMenuItemId = mcUsersItemMenu.getMcMenuItemMenuItemId();
            if (mcMenuItemMenuItemId != null) {
                mcMenuItemMenuItemId = em.getReference(mcMenuItemMenuItemId.getClass(), mcMenuItemMenuItemId.getMcMenuItemId());
                mcUsersItemMenu.setMcMenuItemMenuItemId(mcMenuItemMenuItemId);
            }
            McUsers mcMenuItemUsersId = mcUsersItemMenu.getMcMenuItemUsersId();
            if (mcMenuItemUsersId != null) {
                mcMenuItemUsersId = em.getReference(mcMenuItemUsersId.getClass(), mcMenuItemUsersId.getMcUsersId());
                mcUsersItemMenu.setMcMenuItemUsersId(mcMenuItemUsersId);
            }
            em.persist(mcUsersItemMenu);
            if (mcMenuItemMenuItemId != null) {
                mcMenuItemMenuItemId.getMcUsersItemMenuList().add(mcUsersItemMenu);
                mcMenuItemMenuItemId = em.merge(mcMenuItemMenuItemId);
            }
            if (mcMenuItemUsersId != null) {
                mcMenuItemUsersId.getMcUsersItemMenuList().add(mcUsersItemMenu);
                mcMenuItemUsersId = em.merge(mcMenuItemUsersId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(McUsersItemMenu mcUsersItemMenu) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McUsersItemMenu persistentMcUsersItemMenu = em.find(McUsersItemMenu.class, mcUsersItemMenu.getMcUsersItemMenuId());
            McMenuItem mcMenuItemMenuItemIdOld = persistentMcUsersItemMenu.getMcMenuItemMenuItemId();
            McMenuItem mcMenuItemMenuItemIdNew = mcUsersItemMenu.getMcMenuItemMenuItemId();
            McUsers mcMenuItemUsersIdOld = persistentMcUsersItemMenu.getMcMenuItemUsersId();
            McUsers mcMenuItemUsersIdNew = mcUsersItemMenu.getMcMenuItemUsersId();
            if (mcMenuItemMenuItemIdNew != null) {
                mcMenuItemMenuItemIdNew = em.getReference(mcMenuItemMenuItemIdNew.getClass(), mcMenuItemMenuItemIdNew.getMcMenuItemId());
                mcUsersItemMenu.setMcMenuItemMenuItemId(mcMenuItemMenuItemIdNew);
            }
            if (mcMenuItemUsersIdNew != null) {
                mcMenuItemUsersIdNew = em.getReference(mcMenuItemUsersIdNew.getClass(), mcMenuItemUsersIdNew.getMcUsersId());
                mcUsersItemMenu.setMcMenuItemUsersId(mcMenuItemUsersIdNew);
            }
            mcUsersItemMenu = em.merge(mcUsersItemMenu);
            if (mcMenuItemMenuItemIdOld != null && !mcMenuItemMenuItemIdOld.equals(mcMenuItemMenuItemIdNew)) {
                mcMenuItemMenuItemIdOld.getMcUsersItemMenuList().remove(mcUsersItemMenu);
                mcMenuItemMenuItemIdOld = em.merge(mcMenuItemMenuItemIdOld);
            }
            if (mcMenuItemMenuItemIdNew != null && !mcMenuItemMenuItemIdNew.equals(mcMenuItemMenuItemIdOld)) {
                mcMenuItemMenuItemIdNew.getMcUsersItemMenuList().add(mcUsersItemMenu);
                mcMenuItemMenuItemIdNew = em.merge(mcMenuItemMenuItemIdNew);
            }
            if (mcMenuItemUsersIdOld != null && !mcMenuItemUsersIdOld.equals(mcMenuItemUsersIdNew)) {
                mcMenuItemUsersIdOld.getMcUsersItemMenuList().remove(mcUsersItemMenu);
                mcMenuItemUsersIdOld = em.merge(mcMenuItemUsersIdOld);
            }
            if (mcMenuItemUsersIdNew != null && !mcMenuItemUsersIdNew.equals(mcMenuItemUsersIdOld)) {
                mcMenuItemUsersIdNew.getMcUsersItemMenuList().add(mcUsersItemMenu);
                mcMenuItemUsersIdNew = em.merge(mcMenuItemUsersIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mcUsersItemMenu.getMcUsersItemMenuId();
                if (findMcUsersItemMenu(id) == null) {
                    throw new NonexistentEntityException("The mcUsersItemMenu with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McUsersItemMenu mcUsersItemMenu;
            try {
                mcUsersItemMenu = em.getReference(McUsersItemMenu.class, id);
                mcUsersItemMenu.getMcUsersItemMenuId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcUsersItemMenu with id " + id + " no longer exists.", enfe);
            }
            McMenuItem mcMenuItemMenuItemId = mcUsersItemMenu.getMcMenuItemMenuItemId();
            if (mcMenuItemMenuItemId != null) {
                mcMenuItemMenuItemId.getMcUsersItemMenuList().remove(mcUsersItemMenu);
                mcMenuItemMenuItemId = em.merge(mcMenuItemMenuItemId);
            }
            McUsers mcMenuItemUsersId = mcUsersItemMenu.getMcMenuItemUsersId();
            if (mcMenuItemUsersId != null) {
                mcMenuItemUsersId.getMcUsersItemMenuList().remove(mcUsersItemMenu);
                mcMenuItemUsersId = em.merge(mcMenuItemUsersId);
            }
            em.remove(mcUsersItemMenu);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<McUsersItemMenu> findMcUsersItemMenuEntities() {
        return findMcUsersItemMenuEntities(true, -1, -1);
    }

    public List<McUsersItemMenu> findMcUsersItemMenuEntities(int maxResults, int firstResult) {
        return findMcUsersItemMenuEntities(false, maxResults, firstResult);
    }

    private List<McUsersItemMenu> findMcUsersItemMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McUsersItemMenu.class));
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

    public McUsersItemMenu findMcUsersItemMenu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McUsersItemMenu.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcUsersItemMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McUsersItemMenu> rt = cq.from(McUsersItemMenu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
