/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.CONTROLLER;

import COM.CONTROLLER.exceptions.NonexistentEntityException;
import COM.CONTROLLER.exceptions.RollbackFailureException;
import COM.ENT.McMenuAdvanced;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import COM.ENT.McUsersMenuOauth;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McMenuAdvancedJpaController implements Serializable {

    public McMenuAdvancedJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McMenuAdvanced mcMenuAdvanced) throws RollbackFailureException, Exception {
        if (mcMenuAdvanced.getMcUsersMenuOauthList() == null) {
            mcMenuAdvanced.setMcUsersMenuOauthList(new ArrayList<McUsersMenuOauth>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<McUsersMenuOauth> attachedMcUsersMenuOauthList = new ArrayList<McUsersMenuOauth>();
            for (McUsersMenuOauth mcUsersMenuOauthListMcUsersMenuOauthToAttach : mcMenuAdvanced.getMcUsersMenuOauthList()) {
                mcUsersMenuOauthListMcUsersMenuOauthToAttach = em.getReference(mcUsersMenuOauthListMcUsersMenuOauthToAttach.getClass(), mcUsersMenuOauthListMcUsersMenuOauthToAttach.getMcUsersMenuOauthId());
                attachedMcUsersMenuOauthList.add(mcUsersMenuOauthListMcUsersMenuOauthToAttach);
            }
            mcMenuAdvanced.setMcUsersMenuOauthList(attachedMcUsersMenuOauthList);
            em.persist(mcMenuAdvanced);
            for (McUsersMenuOauth mcUsersMenuOauthListMcUsersMenuOauth : mcMenuAdvanced.getMcUsersMenuOauthList()) {
                McMenuAdvanced oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListMcUsersMenuOauth = mcUsersMenuOauthListMcUsersMenuOauth.getMcUsersMenuOauthMenuId();
                mcUsersMenuOauthListMcUsersMenuOauth.setMcUsersMenuOauthMenuId(mcMenuAdvanced);
                mcUsersMenuOauthListMcUsersMenuOauth = em.merge(mcUsersMenuOauthListMcUsersMenuOauth);
                if (oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListMcUsersMenuOauth != null) {
                    oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListMcUsersMenuOauth.getMcUsersMenuOauthList().remove(mcUsersMenuOauthListMcUsersMenuOauth);
                    oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListMcUsersMenuOauth = em.merge(oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListMcUsersMenuOauth);
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

    public void edit(McMenuAdvanced mcMenuAdvanced) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McMenuAdvanced persistentMcMenuAdvanced = em.find(McMenuAdvanced.class, mcMenuAdvanced.getMcMenuAdvancedId());
            List<McUsersMenuOauth> mcUsersMenuOauthListOld = persistentMcMenuAdvanced.getMcUsersMenuOauthList();
            List<McUsersMenuOauth> mcUsersMenuOauthListNew = mcMenuAdvanced.getMcUsersMenuOauthList();
            List<McUsersMenuOauth> attachedMcUsersMenuOauthListNew = new ArrayList<McUsersMenuOauth>();
            for (McUsersMenuOauth mcUsersMenuOauthListNewMcUsersMenuOauthToAttach : mcUsersMenuOauthListNew) {
                mcUsersMenuOauthListNewMcUsersMenuOauthToAttach = em.getReference(mcUsersMenuOauthListNewMcUsersMenuOauthToAttach.getClass(), mcUsersMenuOauthListNewMcUsersMenuOauthToAttach.getMcUsersMenuOauthId());
                attachedMcUsersMenuOauthListNew.add(mcUsersMenuOauthListNewMcUsersMenuOauthToAttach);
            }
            mcUsersMenuOauthListNew = attachedMcUsersMenuOauthListNew;
            mcMenuAdvanced.setMcUsersMenuOauthList(mcUsersMenuOauthListNew);
            mcMenuAdvanced = em.merge(mcMenuAdvanced);
            for (McUsersMenuOauth mcUsersMenuOauthListOldMcUsersMenuOauth : mcUsersMenuOauthListOld) {
                if (!mcUsersMenuOauthListNew.contains(mcUsersMenuOauthListOldMcUsersMenuOauth)) {
                    mcUsersMenuOauthListOldMcUsersMenuOauth.setMcUsersMenuOauthMenuId(null);
                    mcUsersMenuOauthListOldMcUsersMenuOauth = em.merge(mcUsersMenuOauthListOldMcUsersMenuOauth);
                }
            }
            for (McUsersMenuOauth mcUsersMenuOauthListNewMcUsersMenuOauth : mcUsersMenuOauthListNew) {
                if (!mcUsersMenuOauthListOld.contains(mcUsersMenuOauthListNewMcUsersMenuOauth)) {
                    McMenuAdvanced oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListNewMcUsersMenuOauth = mcUsersMenuOauthListNewMcUsersMenuOauth.getMcUsersMenuOauthMenuId();
                    mcUsersMenuOauthListNewMcUsersMenuOauth.setMcUsersMenuOauthMenuId(mcMenuAdvanced);
                    mcUsersMenuOauthListNewMcUsersMenuOauth = em.merge(mcUsersMenuOauthListNewMcUsersMenuOauth);
                    if (oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListNewMcUsersMenuOauth != null && !oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListNewMcUsersMenuOauth.equals(mcMenuAdvanced)) {
                        oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListNewMcUsersMenuOauth.getMcUsersMenuOauthList().remove(mcUsersMenuOauthListNewMcUsersMenuOauth);
                        oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListNewMcUsersMenuOauth = em.merge(oldMcUsersMenuOauthMenuIdOfMcUsersMenuOauthListNewMcUsersMenuOauth);
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
                Integer id = mcMenuAdvanced.getMcMenuAdvancedId();
                if (findMcMenuAdvanced(id) == null) {
                    throw new NonexistentEntityException("The mcMenuAdvanced with id " + id + " no longer exists.");
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
            McMenuAdvanced mcMenuAdvanced;
            try {
                mcMenuAdvanced = em.getReference(McMenuAdvanced.class, id);
                mcMenuAdvanced.getMcMenuAdvancedId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcMenuAdvanced with id " + id + " no longer exists.", enfe);
            }
            List<McUsersMenuOauth> mcUsersMenuOauthList = mcMenuAdvanced.getMcUsersMenuOauthList();
            for (McUsersMenuOauth mcUsersMenuOauthListMcUsersMenuOauth : mcUsersMenuOauthList) {
                mcUsersMenuOauthListMcUsersMenuOauth.setMcUsersMenuOauthMenuId(null);
                mcUsersMenuOauthListMcUsersMenuOauth = em.merge(mcUsersMenuOauthListMcUsersMenuOauth);
            }
            em.remove(mcMenuAdvanced);
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

    public List<McMenuAdvanced> findMcMenuAdvancedEntities() {
        return findMcMenuAdvancedEntities(true, -1, -1);
    }

    public List<McMenuAdvanced> findMcMenuAdvancedEntities(int maxResults, int firstResult) {
        return findMcMenuAdvancedEntities(false, maxResults, firstResult);
    }

    private List<McMenuAdvanced> findMcMenuAdvancedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McMenuAdvanced.class));
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

    public McMenuAdvanced findMcMenuAdvanced(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McMenuAdvanced.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcMenuAdvancedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McMenuAdvanced> rt = cq.from(McMenuAdvanced.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
