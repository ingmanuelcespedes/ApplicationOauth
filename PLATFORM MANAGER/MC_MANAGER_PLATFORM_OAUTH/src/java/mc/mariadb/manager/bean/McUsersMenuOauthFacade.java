/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.mariadb.manager.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mc.mariadb.manager.entities.McUsersMenuOauth;

/**
 *
 * @author acespedesl
 */
@Stateless
public class McUsersMenuOauthFacade extends AbstractFacade<McUsersMenuOauth> {

    @PersistenceContext(unitName = "MC_MANAGER_PLATFORM_OAUTHPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public McUsersMenuOauthFacade() {
        super(McUsersMenuOauth.class);
    }
    
}
