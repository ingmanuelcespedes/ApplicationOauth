/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.CONTROLLER;

import COM.CONTROLLER.exceptions.NonexistentEntityException;
import COM.CONTROLLER.exceptions.RollbackFailureException;
import COM.ENT.McSessionOauth;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import COM.ENT.McUsers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author acespedesl
 */
public class McSessionOauthJpaController implements Serializable {

    public McSessionOauthJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(McSessionOauth mcSessionOauth) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McUsers mcSessionOauthUsersId = mcSessionOauth.getMcSessionOauthUsersId();
            if (mcSessionOauthUsersId != null) {
                mcSessionOauthUsersId = em.getReference(mcSessionOauthUsersId.getClass(), mcSessionOauthUsersId.getMcUsersId());
                mcSessionOauth.setMcSessionOauthUsersId(mcSessionOauthUsersId);
            }
            em.persist(mcSessionOauth);
            if (mcSessionOauthUsersId != null) {
                mcSessionOauthUsersId.getMcSessionOauthList().add(mcSessionOauth);
                mcSessionOauthUsersId = em.merge(mcSessionOauthUsersId);
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

    public void edit(McSessionOauth mcSessionOauth) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            McSessionOauth persistentMcSessionOauth = em.find(McSessionOauth.class, mcSessionOauth.getMcSessionOauthId());
            McUsers mcSessionOauthUsersIdOld = persistentMcSessionOauth.getMcSessionOauthUsersId();
            McUsers mcSessionOauthUsersIdNew = mcSessionOauth.getMcSessionOauthUsersId();
            if (mcSessionOauthUsersIdNew != null) {
                mcSessionOauthUsersIdNew = em.getReference(mcSessionOauthUsersIdNew.getClass(), mcSessionOauthUsersIdNew.getMcUsersId());
                mcSessionOauth.setMcSessionOauthUsersId(mcSessionOauthUsersIdNew);
            }
            mcSessionOauth = em.merge(mcSessionOauth);
            if (mcSessionOauthUsersIdOld != null && !mcSessionOauthUsersIdOld.equals(mcSessionOauthUsersIdNew)) {
                mcSessionOauthUsersIdOld.getMcSessionOauthList().remove(mcSessionOauth);
                mcSessionOauthUsersIdOld = em.merge(mcSessionOauthUsersIdOld);
            }
            if (mcSessionOauthUsersIdNew != null && !mcSessionOauthUsersIdNew.equals(mcSessionOauthUsersIdOld)) {
                mcSessionOauthUsersIdNew.getMcSessionOauthList().add(mcSessionOauth);
                mcSessionOauthUsersIdNew = em.merge(mcSessionOauthUsersIdNew);
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
                Integer id = mcSessionOauth.getMcSessionOauthId();
                if (findMcSessionOauth(id) == null) {
                    throw new NonexistentEntityException("The mcSessionOauth with id " + id + " no longer exists.");
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
            McSessionOauth mcSessionOauth;
            try {
                mcSessionOauth = em.getReference(McSessionOauth.class, id);
                mcSessionOauth.getMcSessionOauthId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mcSessionOauth with id " + id + " no longer exists.", enfe);
            }
            McUsers mcSessionOauthUsersId = mcSessionOauth.getMcSessionOauthUsersId();
            if (mcSessionOauthUsersId != null) {
                mcSessionOauthUsersId.getMcSessionOauthList().remove(mcSessionOauth);
                mcSessionOauthUsersId = em.merge(mcSessionOauthUsersId);
            }
            em.remove(mcSessionOauth);
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

    public List<McSessionOauth> findMcSessionOauthEntities() {
        return findMcSessionOauthEntities(true, -1, -1);
    }

    public List<McSessionOauth> findMcSessionOauthEntities(int maxResults, int firstResult) {
        return findMcSessionOauthEntities(false, maxResults, firstResult);
    }

    private List<McSessionOauth> findMcSessionOauthEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(McSessionOauth.class));
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

    public McSessionOauth findMcSessionOauth(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(McSessionOauth.class, id);
        } finally {
            em.close();
        }
    }

    public int getMcSessionOauthCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<McSessionOauth> rt = cq.from(McSessionOauth.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
