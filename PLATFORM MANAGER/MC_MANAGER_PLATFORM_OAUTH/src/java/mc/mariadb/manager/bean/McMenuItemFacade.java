/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.mariadb.manager.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mc.mariadb.manager.entities.McMenuItem;

/**
 *
 * @author acespedesl
 */
@Stateless
public class McMenuItemFacade extends AbstractFacade<McMenuItem> {

    @PersistenceContext(unitName = "MC_MANAGER_PLATFORM_OAUTHPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public McMenuItemFacade() {
        super(McMenuItem.class);
    }
    
}
