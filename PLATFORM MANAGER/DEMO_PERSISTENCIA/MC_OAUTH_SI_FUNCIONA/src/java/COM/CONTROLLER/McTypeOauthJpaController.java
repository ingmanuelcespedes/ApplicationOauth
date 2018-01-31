/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.CONTROLLER;

import COM.CONTROLLER.exceptions.NonexistentEntityException;
import COM.CONTROLLER.exceptions.RollbackFailureException;
import COM.ENT.McTypeOauth;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import COM.ENT.McUsers;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McTypeOauthJpaController implements Serializable {

    public McTypeOauthJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McTypeOauth mcTypeOauth) throws RollbackFailureException, Exception {
        if (mcTypeOauth.getMcUsersList() == null) {
            mcTypeOauth.setMcUsersList(new ArrayList<McUsers>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<McUsers> attachedMcUsersList = new ArrayList<McUsers>();
            for (McUsers mcUsersListMcUsersToAttach : mcTypeOauth.getMcUsersList()) {
                mcUsersListMcUsersToAttach = em.getReference(mcUsersListMcUsersToAttach.getClass(), mcUsersListMcUsersToAttach.getMcUsersId());
                attachedMcUsersList.add(mcUsersListMcUsersToAttach);
            }
            mcTypeOauth.setMcUsersList(attachedMcUsersList);
            em.persist(mcTypeOauth);
            for (McUsers mcUsersListMcUsers : mcTypeOauth.getMcUsersList()) {
                McTypeOauth oldMcUsersTypeOfMcUsersListMcUsers = mcUsersListMcUsers.getMcUsersType();
                mcUsersListMcUsers.setMcUsersType(mcTypeOauth);
                mcUsersListMcUsers = em.merge(mcUsersListMcUsers);
                if (oldMcUsersTypeOfMcUsersListMcUsers != null) {
                    oldMcUsersTypeOfMcUsersListMcUsers.getMcUsersList().remove(mcUsersListMcUsers);
                    oldMcUsersTypeOfMcUsersListMcUsers = em.merge(oldMcUsersTypeOfMcUsersListMcUsers);
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

    public void edit(McTypeOauth mcTypeOauth) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McTypeOauth persistentMcTypeOauth = em.find(McTypeOauth.class, mcTypeOauth.getMcTypeOauthId());
            List<McUsers> mcUsersListOld = persistentMcTypeOauth.getMcUsersList();
            List<McUsers> mcUsersListNew = mcTypeOauth.getMcUsersList();
            List<McUsers> attachedMcUsersListNew = new ArrayList<McUsers>();
            for (McUsers mcUsersListNewMcUsersToAttach : mcUsersListNew) {
                mcUsersListNewMcUsersToAttach = em.getReference(mcUsersListNewMcUsersToAttach.getClass(), mcUsersListNewMcUsersToAttach.getMcUsersId());
                attachedMcUsersListNew.add(mcUsersListNewMcUsersToAttach);
            }
            mcUsersListNew = attachedMcUsersListNew;
            mcTypeOauth.setMcUsersList(mcUsersListNew);
            mcTypeOauth = em.merge(mcTypeOauth);
            for (McUsers mcUsersListOldMcUsers : mcUsersListOld) {
                if (!mcUsersListNew.contains(mcUsersListOldMcUsers)) {
                    mcUsersListOldMcUsers.setMcUsersType(null);
                    mcUsersListOldMcUsers = em.merge(mcUsersListOldMcUsers);
                }
            }
            for (McUsers mcUsersListNewMcUsers : mcUsersListNew) {
                if (!mcUsersListOld.contains(mcUsersListNewMcUsers)) {
                    McTypeOauth oldMcUsersTypeOfMcUsersListNewMcUsers = mcUsersListNewMcUsers.getMcUsersType();
                    mcUsersListNewMcUsers.setMcUsersType(mcTypeOauth);
                    mcUsersListNewMcUsers = em.merge(mcUsersListNewMcUsers);
                    if (oldMcUsersTypeOfMcUsersListNewMcUsers != null && !oldMcUsersTypeOfMcUsersListNewMcUsers.equals(mcTypeOauth)) {
                        oldMcUsersTypeOfMcUsersListNewMcUsers.getMcUsersList().remove(mcUsersListNewMcUsers);
                        oldMcUsersTypeOfMcUsersListNewMcUsers = em.merge(oldMcUsersTypeOfMcUsersListNewMcUsers);
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
                Integer id = mcTypeOauth.getMcTypeOauthId();
                if (findMcTypeOauth(id) == null) {
                    throw new NonexistentEntityException("The mcTypeOauth with id " + id + " no longer exists.");
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
            McTypeOauth mcTypeOauth;
            try {
                mcTypeOauth = em.getReference(McTypeOauth.class, id);
                mcTypeOauth.getMcTypeOauthId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcTypeOauth with id " + id + " no longer exists.", enfe);
            }
            List<McUsers> mcUsersList = mcTypeOauth.getMcUsersList();
            for (McUsers mcUsersListMcUsers : mcUsersList) {
                mcUsersListMcUsers.setMcUsersType(null);
                mcUsersListMcUsers = em.merge(mcUsersListMcUsers);
            }
            em.remove(mcTypeOauth);
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

    public List<McTypeOauth> findMcTypeOauthEntities() {
        return findMcTypeOauthEntities(true, -1, -1);
    }

    public List<McTypeOauth> findMcTypeOauthEntities(int maxResults, int firstResult) {
        return findMcTypeOauthEntities(false, maxResults, firstResult);
    }

    private List<McTypeOauth> findMcTypeOauthEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McTypeOauth.class));
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

    public McTypeOauth findMcTypeOauth(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McTypeOauth.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcTypeOauthCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McTypeOauth> rt = cq.from(McTypeOauth.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
