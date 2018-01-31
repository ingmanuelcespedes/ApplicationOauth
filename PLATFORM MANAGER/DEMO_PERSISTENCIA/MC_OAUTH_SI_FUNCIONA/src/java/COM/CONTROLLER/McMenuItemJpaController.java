/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.CONTROLLER;

import COM.CONTROLLER.exceptions.NonexistentEntityException;
import COM.CONTROLLER.exceptions.RollbackFailureException;
import COM.ENT.McMenuItem;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import COM.ENT.McUsersItemMenu;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McMenuItemJpaController implements Serializable {

    public McMenuItemJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McMenuItem mcMenuItem) throws RollbackFailureException, Exception {
        if (mcMenuItem.getMcUsersItemMenuList() == null) {
            mcMenuItem.setMcUsersItemMenuList(new ArrayList<McUsersItemMenu>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<McUsersItemMenu> attachedMcUsersItemMenuList = new ArrayList<McUsersItemMenu>();
            for (McUsersItemMenu mcUsersItemMenuListMcUsersItemMenuToAttach : mcMenuItem.getMcUsersItemMenuList()) {
                mcUsersItemMenuListMcUsersItemMenuToAttach = em.getReference(mcUsersItemMenuListMcUsersItemMenuToAttach.getClass(), mcUsersItemMenuListMcUsersItemMenuToAttach.getMcUsersItemMenuId());
                attachedMcUsersItemMenuList.add(mcUsersItemMenuListMcUsersItemMenuToAttach);
            }
            mcMenuItem.setMcUsersItemMenuList(attachedMcUsersItemMenuList);
            em.persist(mcMenuItem);
            for (McUsersItemMenu mcUsersItemMenuListMcUsersItemMenu : mcMenuItem.getMcUsersItemMenuList()) {
                McMenuItem oldMcMenuItemMenuItemIdOfMcUsersItemMenuListMcUsersItemMenu = mcUsersItemMenuListMcUsersItemMenu.getMcMenuItemMenuItemId();
                mcUsersItemMenuListMcUsersItemMenu.setMcMenuItemMenuItemId(mcMenuItem);
                mcUsersItemMenuListMcUsersItemMenu = em.merge(mcUsersItemMenuListMcUsersItemMenu);
                if (oldMcMenuItemMenuItemIdOfMcUsersItemMenuListMcUsersItemMenu != null) {
                    oldMcMenuItemMenuItemIdOfMcUsersItemMenuListMcUsersItemMenu.getMcUsersItemMenuList().remove(mcUsersItemMenuListMcUsersItemMenu);
                    oldMcMenuItemMenuItemIdOfMcUsersItemMenuListMcUsersItemMenu = em.merge(oldMcMenuItemMenuItemIdOfMcUsersItemMenuListMcUsersItemMenu);
                }
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

    public void edit(McMenuItem mcMenuItem) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McMenuItem persistentMcMenuItem = em.find(McMenuItem.class, mcMenuItem.getMcMenuItemId());
            List<McUsersItemMenu> mcUsersItemMenuListOld = persistentMcMenuItem.getMcUsersItemMenuList();
            List<McUsersItemMenu> mcUsersItemMenuListNew = mcMenuItem.getMcUsersItemMenuList();
            List<McUsersItemMenu> attachedMcUsersItemMenuListNew = new ArrayList<McUsersItemMenu>();
            for (McUsersItemMenu mcUsersItemMenuListNewMcUsersItemMenuToAttach : mcUsersItemMenuListNew) {
                mcUsersItemMenuListNewMcUsersItemMenuToAttach = em.getReference(mcUsersItemMenuListNewMcUsersItemMenuToAttach.getClass(), mcUsersItemMenuListNewMcUsersItemMenuToAttach.getMcUsersItemMenuId());
                attachedMcUsersItemMenuListNew.add(mcUsersItemMenuListNewMcUsersItemMenuToAttach);
            }
            mcUsersItemMenuListNew = attachedMcUsersItemMenuListNew;
            mcMenuItem.setMcUsersItemMenuList(mcUsersItemMenuListNew);
            mcMenuItem = em.merge(mcMenuItem);
            for (McUsersItemMenu mcUsersItemMenuListOldMcUsersItemMenu : mcUsersItemMenuListOld) {
                if (!mcUsersItemMenuListNew.contains(mcUsersItemMenuListOldMcUsersItemMenu)) {
                    mcUsersItemMenuListOldMcUsersItemMenu.setMcMenuItemMenuItemId(null);
                    mcUsersItemMenuListOldMcUsersItemMenu = em.merge(mcUsersItemMenuListOldMcUsersItemMenu);
                }
            }
            for (McUsersItemMenu mcUsersItemMenuListNewMcUsersItemMenu : mcUsersItemMenuListNew) {
                if (!mcUsersItemMenuListOld.contains(mcUsersItemMenuListNewMcUsersItemMenu)) {
                    McMenuItem oldMcMenuItemMenuItemIdOfMcUsersItemMenuListNewMcUsersItemMenu = mcUsersItemMenuListNewMcUsersItemMenu.getMcMenuItemMenuItemId();
                    mcUsersItemMenuListNewMcUsersItemMenu.setMcMenuItemMenuItemId(mcMenuItem);
                    mcUsersItemMenuListNewMcUsersItemMenu = em.merge(mcUsersItemMenuListNewMcUsersItemMenu);
                    if (oldMcMenuItemMenuItemIdOfMcUsersItemMenuListNewMcUsersItemMenu != null && !oldMcMenuItemMenuItemIdOfMcUsersItemMenuListNewMcUsersItemMenu.equals(mcMenuItem)) {
                        oldMcMenuItemMenuItemIdOfMcUsersItemMenuListNewMcUsersItemMenu.getMcUsersItemMenuList().remove(mcUsersItemMenuListNewMcUsersItemMenu);
                        oldMcMenuItemMenuItemIdOfMcUsersItemMenuListNewMcUsersItemMenu = em.merge(oldMcMenuItemMenuItemIdOfMcUsersItemMenuListNewMcUsersItemMenu);
                    }
                }
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
                Integer id = mcMenuItem.getMcMenuItemId();
                if (findMcMenuItem(id) == null) {
                    throw new NonexistentEntityException("The mcMenuItem with id " + id + " no longer exists.");
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
            McMenuItem mcMenuItem;
            try {
                mcMenuItem = em.getReference(McMenuItem.class, id);
                mcMenuItem.getMcMenuItemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcMenuItem with id " + id + " no longer exists.", enfe);
            }
            List<McUsersItemMenu> mcUsersItemMenuList = mcMenuItem.getMcUsersItemMenuList();
            for (McUsersItemMenu mcUsersItemMenuListMcUsersItemMenu : mcUsersItemMenuList) {
                mcUsersItemMenuListMcUsersItemMenu.setMcMenuItemMenuItemId(null);
                mcUsersItemMenuListMcUsersItemMenu = em.merge(mcUsersItemMenuListMcUsersItemMenu);
            }
            em.remove(mcMenuItem);
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

    public List<McMenuItem> findMcMenuItemEntities() {
        return findMcMenuItemEntities(true, -1, -1);
    }

    public List<McMenuItem> findMcMenuItemEntities(int maxResults, int firstResult) {
        return findMcMenuItemEntities(false, maxResults, firstResult);
    }

    private List<McMenuItem> findMcMenuItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McMenuItem.class));
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

    public McMenuItem findMcMenuItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McMenuItem.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcMenuItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McMenuItem> rt = cq.from(McMenuItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
