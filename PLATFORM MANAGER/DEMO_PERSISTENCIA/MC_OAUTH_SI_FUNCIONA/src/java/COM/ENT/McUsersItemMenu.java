/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.ENT;

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
@Table(name = "mc_users_item_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McUsersItemMenu.findAll", query = "SELECT m FROM McUsersItemMenu m")
    , @NamedQuery(name = "McUsersItemMenu.findByMcUsersItemMenuId", query = "SELECT m FROM McUsersItemMenu m WHERE m.mcUsersItemMenuId = :mcUsersItemMenuId")})
public class McUsersItemMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_users_item_menu_id")
    private Integer mcUsersItemMenuId;
    @JoinColumn(name = "mc_menu_item_menu_item_id", referencedColumnName = "mc_menu_item_id")
    @ManyToOne
    private McMenuItem mcMenuItemMenuItemId;
    @JoinColumn(name = "mc_menu_item_users_id", referencedColumnName = "mc_users_id")
    @ManyToOne
    private McUsers mcMenuItemUsersId;

    public McUsersItemMenu() {
    }

    public McUsersItemMenu(Integer mcUsersItemMenuId) {
        this.mcUsersItemMenuId = mcUsersItemMenuId;
    }

    public Integer getMcUsersItemMenuId() {
        return mcUsersItemMenuId;
    }

    public void setMcUsersItemMenuId(Integer mcUsersItemMenuId) {
        this.mcUsersItemMenuId = mcUsersItemMenuId;
    }

    public McMenuItem getMcMenuItemMenuItemId() {
        return mcMenuItemMenuItemId;
    }

    public void setMcMenuItemMenuItemId(McMenuItem mcMenuItemMenuItemId) {
        this.mcMenuItemMenuItemId = mcMenuItemMenuItemId;
    }

    public McUsers getMcMenuItemUsersId() {
        return mcMenuItemUsersId;
    }

    public void setMcMenuItemUsersId(McUsers mcMenuItemUsersId) {
        this.mcMenuItemUsersId = mcMenuItemUsersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcUsersItemMenuId != null ? mcUsersItemMenuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McUsersItemMenu)) {
            return false;
        }
        McUsersItemMenu other = (McUsersItemMenu) object;
        if ((this.mcUsersItemMenuId == null && other.mcUsersItemMenuId != null) || (this.mcUsersItemMenuId != null && !this.mcUsersItemMenuId.equals(other.mcUsersItemMenuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COM.ENT.McUsersItemMenu[ mcUsersItemMenuId=" + mcUsersItemMenuId + " ]";
    }
    
}
