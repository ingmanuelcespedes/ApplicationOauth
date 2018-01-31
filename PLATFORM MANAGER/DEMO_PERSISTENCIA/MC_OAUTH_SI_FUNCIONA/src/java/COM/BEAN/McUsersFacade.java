/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.BEAN;

import COM.ENT.McUsers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author acespedesl
 */
@Stateless
public class McUsersFacade extends AbstractFacade<McUsers> {

    @PersistenceContext(unitName = "MC_OAUTH_V4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public McUsersFacade() {
        super(McUsers.class);
    }
    
}
