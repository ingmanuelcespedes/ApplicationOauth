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
import COM.ENT.McMenuAdvanced;
import COM.ENT.McUsers;
import COM.ENT.McUsersMenuOauth;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McUsersMenuOauthJpaController implements Serializable {

    public McUsersMenuOauthJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McUsersMenuOauth mcUsersMenuOauth) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McMenuAdvanced mcUsersMenuOauthMenuId = mcUsersMenuOauth.getMcUsersMenuOauthMenuId();
            if (mcUsersMenuOauthMenuId != null) {
                mcUsersMenuOauthMenuId = em.getReference(mcUsersMenuOauthMenuId.getClass(), mcUsersMenuOauthMenuId.getMcMenuAdvancedId());
                mcUsersMenuOauth.setMcUsersMenuOauthMenuId(mcUsersMenuOauthMenuId);
            }
            McUsers mcUsersMenuOauthUsersId = mcUsersMenuOauth.getMcUsersMenuOauthUsersId();
            if (mcUsersMenuOauthUsersId != null) {
                mcUsersMenuOauthUsersId = em.getReference(mcUsersMenuOauthUsersId.getClass(), mcUsersMenuOauthUsersId.getMcUsersId());
                mcUsersMenuOauth.setMcUsersMenuOauthUsersId(mcUsersMenuOauthUsersId);
            }
            em.persist(mcUsersMenuOauth);
            if (mcUsersMenuOauthMenuId != null) {
                mcUsersMenuOauthMenuId.getMcUsersMenuOauthList().add(mcUsersMenuOauth);
                mcUsersMenuOauthMenuId = em.merge(mcUsersMenuOauthMenuId);
            }
            if (mcUsersMenuOauthUsersId != null) {
                mcUsersMenuOauthUsersId.getMcUsersMenuOauthList().add(mcUsersMenuOauth);
                mcUsersMenuOauthUsersId = em.merge(mcUsersMenuOauthUsersId);
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

    public void edit(McUsersMenuOauth mcUsersMenuOauth) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McUsersMenuOauth persistentMcUsersMenuOauth = em.find(McUsersMenuOauth.class, mcUsersMenuOauth.getMcUsersMenuOauthId());
            McMenuAdvanced mcUsersMenuOauthMenuIdOld = persistentMcUsersMenuOauth.getMcUsersMenuOauthMenuId();
            McMenuAdvanced mcUsersMenuOauthMenuIdNew = mcUsersMenuOauth.getMcUsersMenuOauthMenuId();
            McUsers mcUsersMenuOauthUsersIdOld = persistentMcUsersMenuOauth.getMcUsersMenuOauthUsersId();
            McUsers mcUsersMenuOauthUsersIdNew = mcUsersMenuOauth.getMcUsersMenuOauthUsersId();
            if (mcUsersMenuOauthMenuIdNew != null) {
                mcUsersMenuOauthMenuIdNew = em.getReference(mcUsersMenuOauthMenuIdNew.getClass(), mcUsersMenuOauthMenuIdNew.getMcMenuAdvancedId());
                mcUsersMenuOauth.setMcUsersMenuOauthMenuId(mcUsersMenuOauthMenuIdNew);
            }
            if (mcUsersMenuOauthUsersIdNew != null) {
                mcUsersMenuOauthUsersIdNew = em.getReference(mcUsersMenuOauthUsersIdNew.getClass(), mcUsersMenuOauthUsersIdNew.getMcUsersId());
                mcUsersMenuOauth.setMcUsersMenuOauthUsersId(mcUsersMenuOauthUsersIdNew);
            }
            mcUsersMenuOauth = em.merge(mcUsersMenuOauth);
            if (mcUsersMenuOauthMenuIdOld != null && !mcUsersMenuOauthMenuIdOld.equals(mcUsersMenuOauthMenuIdNew)) {
                mcUsersMenuOauthMenuIdOld.getMcUsersMenuOauthList().remove(mcUsersMenuOauth);
                mcUsersMenuOauthMenuIdOld = em.merge(mcUsersMenuOauthMenuIdOld);
            }
            if (mcUsersMenuOauthMenuIdNew != null && !mcUsersMenuOauthMenuIdNew.equals(mcUsersMenuOauthMenuIdOld)) {
                mcUsersMenuOauthMenuIdNew.getMcUsersMenuOauthList().add(mcUsersMenuOauth);
                mcUsersMenuOauthMenuIdNew = em.merge(mcUsersMenuOauthMenuIdNew);
            }
            if (mcUsersMenuOauthUsersIdOld != null && !mcUsersMenuOauthUsersIdOld.equals(mcUsersMenuOauthUsersIdNew)) {
                mcUsersMenuOauthUsersIdOld.getMcUsersMenuOauthList().remove(mcUsersMenuOauth);
                mcUsersMenuOauthUsersIdOld = em.merge(mcUsersMenuOauthUsersIdOld);
            }
            if (mcUsersMenuOauthUsersIdNew != null && !mcUsersMenuOauthUsersIdNew.equals(mcUsersMenuOauthUsersIdOld)) {
                mcUsersMenuOauthUsersIdNew.getMcUsersMenuOauthList().add(mcUsersMenuOauth);
                mcUsersMenuOauthUsersIdNew = em.merge(mcUsersMenuOauthUsersIdNew);
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
                Integer id = mcUsersMenuOauth.getMcUsersMenuOauthId();
                if (findMcUsersMenuOauth(id) == null) {
                    throw new NonexistentEntityException("The mcUsersMenuOauth with id " + id + " no longer exists.");
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
            McUsersMenuOauth mcUsersMenuOauth;
            try {
                mcUsersMenuOauth = em.getReference(McUsersMenuOauth.class, id);
                mcUsersMenuOauth.getMcUsersMenuOauthId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcUsersMenuOauth with id " + id + " no longer exists.", enfe);
            }
            McMenuAdvanced mcUsersMenuOauthMenuId = mcUsersMenuOauth.getMcUsersMenuOauthMenuId();
            if (mcUsersMenuOauthMenuId != null) {
                mcUsersMenuOauthMenuId.getMcUsersMenuOauthList().remove(mcUsersMenuOauth);
                mcUsersMenuOauthMenuId = em.merge(mcUsersMenuOauthMenuId);
            }
            McUsers mcUsersMenuOauthUsersId = mcUsersMenuOauth.getMcUsersMenuOauthUsersId();
            if (mcUsersMenuOauthUsersId != null) {
                mcUsersMenuOauthUsersId.getMcUsersMenuOauthList().remove(mcUsersMenuOauth);
                mcUsersMenuOauthUsersId = em.merge(mcUsersMenuOauthUsersId);
            }
            em.remove(mcUsersMenuOauth);
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

    public List<McUsersMenuOauth> findMcUsersMenuOauthEntities() {
        return findMcUsersMenuOauthEntities(true, -1, -1);
    }

    public List<McUsersMenuOauth> findMcUsersMenuOauthEntities(int maxResults, int firstResult) {
        return findMcUsersMenuOauthEntities(false, maxResults, firstResult);
    }

    private List<McUsersMenuOauth> findMcUsersMenuOauthEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McUsersMenuOauth.class));
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

    public McUsersMenuOauth findMcUsersMenuOauth(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McUsersMenuOauth.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcUsersMenuOauthCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McUsersMenuOauth> rt = cq.from(McUsersMenuOauth.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
