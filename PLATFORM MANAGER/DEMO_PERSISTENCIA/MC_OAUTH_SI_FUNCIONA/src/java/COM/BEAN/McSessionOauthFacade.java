/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.BEAN;

import COM.ENT.McSessionOauth;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author acespedesl
 */
@Stateless
public class McSessionOauthFacade extends AbstractFacade<McSessionOauth> {

    @PersistenceContext(unitName = "MC_OAUTH_V4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public McSessionOauthFacade() {
        super(McSessionOauth.class);
    }
    
}
