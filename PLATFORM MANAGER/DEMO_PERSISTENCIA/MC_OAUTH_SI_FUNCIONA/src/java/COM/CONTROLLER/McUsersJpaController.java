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
import COM.ENT.McTypeOauth;
import COM.ENT.McSessionOauth;
import COM.ENT.McUsers;
import java.util.ArrayList;
import java.util.List;
import COM.ENT.McUsersMenuOauth;
import COM.ENT.McUsersItemMenu;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McUsersJpaController implements Serializable {

    public McUsersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McUsers mcUsers) throws RollbackFailureException, Exception {
        if (mcUsers.getMcSessionOauthList() == null) {
            mcUsers.setMcSessionOauthList(new ArrayList<McSessionOauth>());
        }
        if (mcUsers.getMcUsersMenuOauthList() == null) {
            mcUsers.setMcUsersMenuOauthList(new ArrayList<McUsersMenuOauth>());
        }
        if (mcUsers.getMcUsersItemMenuList() == null) {
            mcUsers.setMcUsersItemMenuList(new ArrayList<McUsersItemMenu>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McTypeOauth mcUsersType = mcUsers.getMcUsersType();
            if (mcUsersType != null) {
                mcUsersType = em.getReference(mcUsersType.getClass(), mcUsersType.getMcTypeOauthId());
                mcUsers.setMcUsersType(mcUsersType);
            }
            List<McSessionOauth> attachedMcSessionOauthList = new ArrayList<McSessionOauth>();
            for (McSessionOauth mcSessionOauthListMcSessionOauthToAttach : mcUsers.getMcSessionOauthList()) {
                mcSessionOauthListMcSessionOauthToAttach = em.getReference(mcSessionOauthListMcSessionOauthToAttach.getClass(), mcSessionOauthListMcSessionOauthToAttach.getMcSessionOauthId());
                attachedMcSessionOauthList.add(mcSessionOauthListMcSessionOauthToAttach);
            }
            mcUsers.setMcSessionOauthList(attachedMcSessionOauthList);
            List<McUsersMenuOauth> attachedMcUsersMenuOauthList = new ArrayList<McUsersMenuOauth>();
            for (McUsersMenuOauth mcUsersMenuOauthListMcUsersMenuOauthToAttach : mcUsers.getMcUsersMenuOauthList()) {
                mcUsersMenuOauthListMcUsersMenuOauthToAttach = em.getReference(mcUsersMenuOauthListMcUsersMenuOauthToAttach.getClass(), mcUsersMenuOauthListMcUsersMenuOauthToAttach.getMcUsersMenuOauthId());
                attachedMcUsersMenuOauthList.add(mcUsersMenuOauthListMcUsersMenuOauthToAttach);
            }
            mcUsers.setMcUsersMenuOauthList(attachedMcUsersMenuOauthList);
            List<McUsersItemMenu> attachedMcUsersItemMenuList = new ArrayList<McUsersItemMenu>();
            for (McUsersItemMenu mcUsersItemMenuListMcUsersItemMenuToAttach : mcUsers.getMcUsersItemMenuList()) {
                mcUsersItemMenuListMcUsersItemMenuToAttach = em.getReference(mcUsersItemMenuListMcUsersItemMenuToAttach.getClass(), mcUsersItemMenuListMcUsersItemMenuToAttach.getMcUsersItemMenuId());
                attachedMcUsersItemMenuList.add(mcUsersItemMenuListMcUsersItemMenuToAttach);
            }
            mcUsers.setMcUsersItemMenuList(attachedMcUsersItemMenuList);
            em.persist(mcUsers);
            if (mcUsersType != null) {
                mcUsersType.getMcUsersList().add(mcUsers);
                mcUsersType = em.merge(mcUsersType);
            }
            for (McSessionOauth mcSessionOauthListMcSessionOauth : mcUsers.getMcSessionOauthList()) {
                McUsers oldMcSessionOauthUsersIdOfMcSessionOauthListMcSessionOauth = mcSessionOauthListMcSessionOauth.getMcSessionOauthUsersId();
                mcSessionOauthListMcSessionOauth.setMcSessionOauthUsersId(mcUsers);
                mcSessionOauthListMcSessionOauth = em.merge(mcSessionOauthListMcSessionOauth);
                if (oldMcSessionOauthUsersIdOfMcSessionOauthListMcSessionOauth != null) {
                    oldMcSessionOauthUsersIdOfMcSessionOauthListMcSessionOauth.getMcSessionOauthList().remove(mcSessionOauthListMcSessionOauth);
                    oldMcSessionOauthUsersIdOfMcSessionOauthListMcSessionOauth = em.merge(oldMcSessionOauthUsersIdOfMcSessionOauthListMcSessionOauth);
                }
            }
            for (McUsersMenuOauth mcUsersMenuOauthListMcUsersMenuOauth : mcUsers.getMcUsersMenuOauthList()) {
                McUsers oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListMcUsersMenuOauth = mcUsersMenuOauthListMcUsersMenuOauth.getMcUsersMenuOauthUsersId();
                mcUsersMenuOauthListMcUsersMenuOauth.setMcUsersMenuOauthUsersId(mcUsers);
                mcUsersMenuOauthListMcUsersMenuOauth = em.merge(mcUsersMenuOauthListMcUsersMenuOauth);
                if (oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListMcUsersMenuOauth != null) {
                    oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListMcUsersMenuOauth.getMcUsersMenuOauthList().remove(mcUsersMenuOauthListMcUsersMenuOauth);
                    oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListMcUsersMenuOauth = em.merge(oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListMcUsersMenuOauth);
                }
            }
            for (McUsersItemMenu mcUsersItemMenuListMcUsersItemMenu : mcUsers.getMcUsersItemMenuList()) {
                McUsers oldMcMenuItemUsersIdOfMcUsersItemMenuListMcUsersItemMenu = mcUsersItemMenuListMcUsersItemMenu.getMcMenuItemUsersId();
                mcUsersItemMenuListMcUsersItemMenu.setMcMenuItemUsersId(mcUsers);
                mcUsersItemMenuListMcUsersItemMenu = em.merge(mcUsersItemMenuListMcUsersItemMenu);
                if (oldMcMenuItemUsersIdOfMcUsersItemMenuListMcUsersItemMenu != null) {
                    oldMcMenuItemUsersIdOfMcUsersItemMenuListMcUsersItemMenu.getMcUsersItemMenuList().remove(mcUsersItemMenuListMcUsersItemMenu);
                    oldMcMenuItemUsersIdOfMcUsersItemMenuListMcUsersItemMenu = em.merge(oldMcMenuItemUsersIdOfMcUsersItemMenuListMcUsersItemMenu);
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

    public void edit(McUsers mcUsers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McUsers persistentMcUsers = em.find(McUsers.class, mcUsers.getMcUsersId());
            McTypeOauth mcUsersTypeOld = persistentMcUsers.getMcUsersType();
            McTypeOauth mcUsersTypeNew = mcUsers.getMcUsersType();
            List<McSessionOauth> mcSessionOauthListOld = persistentMcUsers.getMcSessionOauthList();
            List<McSessionOauth> mcSessionOauthListNew = mcUsers.getMcSessionOauthList();
            List<McUsersMenuOauth> mcUsersMenuOauthListOld = persistentMcUsers.getMcUsersMenuOauthList();
            List<McUsersMenuOauth> mcUsersMenuOauthListNew = mcUsers.getMcUsersMenuOauthList();
            List<McUsersItemMenu> mcUsersItemMenuListOld = persistentMcUsers.getMcUsersItemMenuList();
            List<McUsersItemMenu> mcUsersItemMenuListNew = mcUsers.getMcUsersItemMenuList();
            if (mcUsersTypeNew != null) {
                mcUsersTypeNew = em.getReference(mcUsersTypeNew.getClass(), mcUsersTypeNew.getMcTypeOauthId());
                mcUsers.setMcUsersType(mcUsersTypeNew);
            }
            List<McSessionOauth> attachedMcSessionOauthListNew = new ArrayList<McSessionOauth>();
            for (McSessionOauth mcSessionOauthListNewMcSessionOauthToAttach : mcSessionOauthListNew) {
                mcSessionOauthListNewMcSessionOauthToAttach = em.getReference(mcSessionOauthListNewMcSessionOauthToAttach.getClass(), mcSessionOauthListNewMcSessionOauthToAttach.getMcSessionOauthId());
                attachedMcSessionOauthListNew.add(mcSessionOauthListNewMcSessionOauthToAttach);
            }
            mcSessionOauthListNew = attachedMcSessionOauthListNew;
            mcUsers.setMcSessionOauthList(mcSessionOauthListNew);
            List<McUsersMenuOauth> attachedMcUsersMenuOauthListNew = new ArrayList<McUsersMenuOauth>();
            for (McUsersMenuOauth mcUsersMenuOauthListNewMcUsersMenuOauthToAttach : mcUsersMenuOauthListNew) {
                mcUsersMenuOauthListNewMcUsersMenuOauthToAttach = em.getReference(mcUsersMenuOauthListNewMcUsersMenuOauthToAttach.getClass(), mcUsersMenuOauthListNewMcUsersMenuOauthToAttach.getMcUsersMenuOauthId());
                attachedMcUsersMenuOauthListNew.add(mcUsersMenuOauthListNewMcUsersMenuOauthToAttach);
            }
            mcUsersMenuOauthListNew = attachedMcUsersMenuOauthListNew;
            mcUsers.setMcUsersMenuOauthList(mcUsersMenuOauthListNew);
            List<McUsersItemMenu> attachedMcUsersItemMenuListNew = new ArrayList<McUsersItemMenu>();
            for (McUsersItemMenu mcUsersItemMenuListNewMcUsersItemMenuToAttach : mcUsersItemMenuListNew) {
                mcUsersItemMenuListNewMcUsersItemMenuToAttach = em.getReference(mcUsersItemMenuListNewMcUsersItemMenuToAttach.getClass(), mcUsersItemMenuListNewMcUsersItemMenuToAttach.getMcUsersItemMenuId());
                attachedMcUsersItemMenuListNew.add(mcUsersItemMenuListNewMcUsersItemMenuToAttach);
            }
            mcUsersItemMenuListNew = attachedMcUsersItemMenuListNew;
            mcUsers.setMcUsersItemMenuList(mcUsersItemMenuListNew);
            mcUsers = em.merge(mcUsers);
            if (mcUsersTypeOld != null && !mcUsersTypeOld.equals(mcUsersTypeNew)) {
                mcUsersTypeOld.getMcUsersList().remove(mcUsers);
                mcUsersTypeOld = em.merge(mcUsersTypeOld);
            }
            if (mcUsersTypeNew != null && !mcUsersTypeNew.equals(mcUsersTypeOld)) {
                mcUsersTypeNew.getMcUsersList().add(mcUsers);
                mcUsersTypeNew = em.merge(mcUsersTypeNew);
            }
            for (McSessionOauth mcSessionOauthListOldMcSessionOauth : mcSessionOauthListOld) {
                if (!mcSessionOauthListNew.contains(mcSessionOauthListOldMcSessionOauth)) {
                    mcSessionOauthListOldMcSessionOauth.setMcSessionOauthUsersId(null);
                    mcSessionOauthListOldMcSessionOauth = em.merge(mcSessionOauthListOldMcSessionOauth);
                }
            }
            for (McSessionOauth mcSessionOauthListNewMcSessionOauth : mcSessionOauthListNew) {
                if (!mcSessionOauthListOld.contains(mcSessionOauthListNewMcSessionOauth)) {
                    McUsers oldMcSessionOauthUsersIdOfMcSessionOauthListNewMcSessionOauth = mcSessionOauthListNewMcSessionOauth.getMcSessionOauthUsersId();
                    mcSessionOauthListNewMcSessionOauth.setMcSessionOauthUsersId(mcUsers);
                    mcSessionOauthListNewMcSessionOauth = em.merge(mcSessionOauthListNewMcSessionOauth);
                    if (oldMcSessionOauthUsersIdOfMcSessionOauthListNewMcSessionOauth != null && !oldMcSessionOauthUsersIdOfMcSessionOauthListNewMcSessionOauth.equals(mcUsers)) {
                        oldMcSessionOauthUsersIdOfMcSessionOauthListNewMcSessionOauth.getMcSessionOauthList().remove(mcSessionOauthListNewMcSessionOauth);
                        oldMcSessionOauthUsersIdOfMcSessionOauthListNewMcSessionOauth = em.merge(oldMcSessionOauthUsersIdOfMcSessionOauthListNewMcSessionOauth);
                    }
                }
            }
            for (McUsersMenuOauth mcUsersMenuOauthListOldMcUsersMenuOauth : mcUsersMenuOauthListOld) {
                if (!mcUsersMenuOauthListNew.contains(mcUsersMenuOauthListOldMcUsersMenuOauth)) {
                    mcUsersMenuOauthListOldMcUsersMenuOauth.setMcUsersMenuOauthUsersId(null);
                    mcUsersMenuOauthListOldMcUsersMenuOauth = em.merge(mcUsersMenuOauthListOldMcUsersMenuOauth);
                }
            }
            for (McUsersMenuOauth mcUsersMenuOauthListNewMcUsersMenuOauth : mcUsersMenuOauthListNew) {
                if (!mcUsersMenuOauthListOld.contains(mcUsersMenuOauthListNewMcUsersMenuOauth)) {
                    McUsers oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListNewMcUsersMenuOauth = mcUsersMenuOauthListNewMcUsersMenuOauth.getMcUsersMenuOauthUsersId();
                    mcUsersMenuOauthListNewMcUsersMenuOauth.setMcUsersMenuOauthUsersId(mcUsers);
                    mcUsersMenuOauthListNewMcUsersMenuOauth = em.merge(mcUsersMenuOauthListNewMcUsersMenuOauth);
                    if (oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListNewMcUsersMenuOauth != null && !oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListNewMcUsersMenuOauth.equals(mcUsers)) {
                        oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListNewMcUsersMenuOauth.getMcUsersMenuOauthList().remove(mcUsersMenuOauthListNewMcUsersMenuOauth);
                        oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListNewMcUsersMenuOauth = em.merge(oldMcUsersMenuOauthUsersIdOfMcUsersMenuOauthListNewMcUsersMenuOauth);
                    }
                }
            }
            for (McUsersItemMenu mcUsersItemMenuListOldMcUsersItemMenu : mcUsersItemMenuListOld) {
                if (!mcUsersItemMenuListNew.contains(mcUsersItemMenuListOldMcUsersItemMenu)) {
                    mcUsersItemMenuListOldMcUsersItemMenu.setMcMenuItemUsersId(null);
                    mcUsersItemMenuListOldMcUsersItemMenu = em.merge(mcUsersItemMenuListOldMcUsersItemMenu);
                }
            }
            for (McUsersItemMenu mcUsersItemMenuListNewMcUsersItemMenu : mcUsersItemMenuListNew) {
                if (!mcUsersItemMenuListOld.contains(mcUsersItemMenuListNewMcUsersItemMenu)) {
                    McUsers oldMcMenuItemUsersIdOfMcUsersItemMenuListNewMcUsersItemMenu = mcUsersItemMenuListNewMcUsersItemMenu.getMcMenuItemUsersId();
                    mcUsersItemMenuListNewMcUsersItemMenu.setMcMenuItemUsersId(mcUsers);
                    mcUsersItemMenuListNewMcUsersItemMenu = em.merge(mcUsersItemMenuListNewMcUsersItemMenu);
                    if (oldMcMenuItemUsersIdOfMcUsersItemMenuListNewMcUsersItemMenu != null && !oldMcMenuItemUsersIdOfMcUsersItemMenuListNewMcUsersItemMenu.equals(mcUsers)) {
                        oldMcMenuItemUsersIdOfMcUsersItemMenuListNewMcUsersItemMenu.getMcUsersItemMenuList().remove(mcUsersItemMenuListNewMcUsersItemMenu);
                        oldMcMenuItemUsersIdOfMcUsersItemMenuListNewMcUsersItemMenu = em.merge(oldMcMenuItemUsersIdOfMcUsersItemMenuListNewMcUsersItemMenu);
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
                Integer id = mcUsers.getMcUsersId();
                if (findMcUsers(id) == null) {
                    throw new NonexistentEntityException("The mcUsers with id " + id + " no longer exists.");
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
            McUsers mcUsers;
            try {
                mcUsers = em.getReference(McUsers.class, id);
                mcUsers.getMcUsersId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcUsers with id " + id + " no longer exists.", enfe);
            }
            McTypeOauth mcUsersType = mcUsers.getMcUsersType();
            if (mcUsersType != null) {
                mcUsersType.getMcUsersList().remove(mcUsers);
                mcUsersType = em.merge(mcUsersType);
            }
            List<McSessionOauth> mcSessionOauthList = mcUsers.getMcSessionOauthList();
            for (McSessionOauth mcSessionOauthListMcSessionOauth : mcSessionOauthList) {
                mcSessionOauthListMcSessionOauth.setMcSessionOauthUsersId(null);
                mcSessionOauthListMcSessionOauth = em.merge(mcSessionOauthListMcSessionOauth);
            }
            List<McUsersMenuOauth> mcUsersMenuOauthList = mcUsers.getMcUsersMenuOauthList();
            for (McUsersMenuOauth mcUsersMenuOauthListMcUsersMenuOauth : mcUsersMenuOauthList) {
                mcUsersMenuOauthListMcUsersMenuOauth.setMcUsersMenuOauthUsersId(null);
                mcUsersMenuOauthListMcUsersMenuOauth = em.merge(mcUsersMenuOauthListMcUsersMenuOauth);
            }
            List<McUsersItemMenu> mcUsersItemMenuList = mcUsers.getMcUsersItemMenuList();
            for (McUsersItemMenu mcUsersItemMenuListMcUsersItemMenu : mcUsersItemMenuList) {
                mcUsersItemMenuListMcUsersItemMenu.setMcMenuItemUsersId(null);
                mcUsersItemMenuListMcUsersItemMenu = em.merge(mcUsersItemMenuListMcUsersItemMenu);
            }
            em.remove(mcUsers);
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

    public List<McUsers> findMcUsersEntities() {
        return findMcUsersEntities(true, -1, -1);
    }

    public List<McUsers> findMcUsersEntities(int maxResults, int firstResult) {
        return findMcUsersEntities(false, maxResults, firstResult);
    }

    private List<McUsers> findMcUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McUsers.class));
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

    public McUsers findMcUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McUsers.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McUsers> rt = cq.from(McUsers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
