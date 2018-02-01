/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.mariadb.manager.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acespedesl
 */
@Entity
@Table(name = "mc_users_menu_oauth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McUsersMenuOauth.findAll", query = "SELECT m FROM McUsersMenuOauth m")
    , @NamedQuery(name = "McUsersMenuOauth.findByMcUsersMenuOauthId", query = "SELECT m FROM McUsersMenuOauth m WHERE m.mcUsersMenuOauthId = :mcUsersMenuOauthId")})
public class McUsersMenuOauth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_users_menu_oauth_id")
    private Integer mcUsersMenuOauthId;
    @JoinColumn(name = "mc_users_menu_oauth_menu_id", referencedColumnName = "mc_menu_advanced_id")
    @ManyToOne
    private McMenuAdvanced mcUsersMenuOauthMenuId;
    @JoinColumn(name = "mc_users_menu_oauth_users_id", referencedColumnName = "mc_users_id")
    @ManyToOne
    private McUsers mcUsersMenuOauthUsersId;

    public McUsersMenuOauth() {
    }

    public McUsersMenuOauth(Integer mcUsersMenuOauthId) {
        this.mcUsersMenuOauthId = mcUsersMenuOauthId;
    }

    public Integer getMcUsersMenuOauthId() {
        return mcUsersMenuOauthId;
    }

    public void setMcUsersMenuOauthId(Integer mcUsersMenuOauthId) {
        this.mcUsersMenuOauthId = mcUsersMenuOauthId;
    }

    public McMenuAdvanced getMcUsersMenuOauthMenuId() {
        return mcUsersMenuOauthMenuId;
    }

    public void setMcUsersMenuOauthMenuId(McMenuAdvanced mcUsersMenuOauthMenuId) {
        this.mcUsersMenuOauthMenuId = mcUsersMenuOauthMenuId;
    }

    public McUsers getMcUsersMenuOauthUsersId() {
        return mcUsersMenuOauthUsersId;
    }

    public void setMcUsersMenuOauthUsersId(McUsers mcUsersMenuOauthUsersId) {
        this.mcUsersMenuOauthUsersId = mcUsersMenuOauthUsersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcUsersMenuOauthId != null ? mcUsersMenuOauthId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McUsersMenuOauth)) {
            return false;
        }
        McUsersMenuOauth other = (McUsersMenuOauth) object;
        if ((this.mcUsersMenuOauthId == null && other.mcUsersMenuOauthId != null) || (this.mcUsersMenuOauthId != null && !this.mcUsersMenuOauthId.equals(other.mcUsersMenuOauthId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mc.mariadb.manager.McUsersMenuOauth[ mcUsersMenuOauthId=" + mcUsersMenuOauthId + " ]";
    }
    
}
