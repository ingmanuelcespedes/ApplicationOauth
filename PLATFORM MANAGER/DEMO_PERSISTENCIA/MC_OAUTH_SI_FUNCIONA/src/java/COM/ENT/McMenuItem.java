/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COM.ENT;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acespedesl
 */
@Entity
@Table(name = "mc_menu_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McMenuItem.findAll", query = "SELECT m FROM McMenuItem m")
    , @NamedQuery(name = "McMenuItem.findByMcMenuItemId", query = "SELECT m FROM McMenuItem m WHERE m.mcMenuItemId = :mcMenuItemId")
    , @NamedQuery(name = "McMenuItem.findByMcMenuItemName", query = "SELECT m FROM McMenuItem m WHERE m.mcMenuItemName = :mcMenuItemName")
    , @NamedQuery(name = "McMenuItem.findByMcMenuItemStatus", query = "SELECT m FROM McMenuItem m WHERE m.mcMenuItemStatus = :mcMenuItemStatus")
    , @NamedQuery(name = "McMenuItem.findByMcMenuItemAlterUser", query = "SELECT m FROM McMenuItem m WHERE m.mcMenuItemAlterUser = :mcMenuItemAlterUser")
    , @NamedQuery(name = "McMenuItem.findByMcMenuItemAlterDate", query = "SELECT m FROM McMenuItem m WHERE m.mcMenuItemAlterDate = :mcMenuItemAlterDate")
    , @NamedQuery(name = "McMenuItem.findByMcMenuItemAlterNetwork", query = "SELECT m FROM McMenuItem m WHERE m.mcMenuItemAlterNetwork = :mcMenuItemAlterNetwork")})
public class McMenuItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_menu_item_id")
    private Integer mcMenuItemId;
    @Size(max = 100)
    @Column(name = "mc_menu_item_name")
    private String mcMenuItemName;
    @Column(name = "mc_menu_item_status")
    private Character mcMenuItemStatus;
    @Size(max = 100)
    @Column(name = "mc_menu_item_alter_user")
    private String mcMenuItemAlterUser;
    @Size(max = 100)
    @Column(name = "mc_menu_item_alter_date")
    private String mcMenuItemAlterDate;
    @Size(max = 100)
    @Column(name = "mc_menu_item_alter_network")
    private String mcMenuItemAlterNetwork;
    @OneToMany(mappedBy = "mcMenuItemMenuItemId")
    private List<McUsersItemMenu> mcUsersItemMenuList;

    public McMenuItem() {
    }

    public McMenuItem(Integer mcMenuItemId) {
        this.mcMenuItemId = mcMenuItemId;
    }

    public Integer getMcMenuItemId() {
        return mcMenuItemId;
    }

    public void setMcMenuItemId(Integer mcMenuItemId) {
        this.mcMenuItemId = mcMenuItemId;
    }

    public String getMcMenuItemName() {
        return mcMenuItemName;
    }

    public void setMcMenuItemName(String mcMenuItemName) {
        this.mcMenuItemName = mcMenuItemName;
    }

    public Character getMcMenuItemStatus() {
        return mcMenuItemStatus;
    }

    public void setMcMenuItemStatus(Character mcMenuItemStatus) {
        this.mcMenuItemStatus = mcMenuItemStatus;
    }

    public String getMcMenuItemAlterUser() {
        return mcMenuItemAlterUser;
    }

    public void setMcMenuItemAlterUser(String mcMenuItemAlterUser) {
        this.mcMenuItemAlterUser = mcMenuItemAlterUser;
    }

    public String getMcMenuItemAlterDate() {
        return mcMenuItemAlterDate;
    }

    public void setMcMenuItemAlterDate(String mcMenuItemAlterDate) {
        this.mcMenuItemAlterDate = mcMenuItemAlterDate;
    }

    public String getMcMenuItemAlterNetwork() {
        return mcMenuItemAlterNetwork;
    }

    public void setMcMenuItemAlterNetwork(String mcMenuItemAlterNetwork) {
        this.mcMenuItemAlterNetwork = mcMenuItemAlterNetwork;
    }

    @XmlTransient
    public List<McUsersItemMenu> getMcUsersItemMenuList() {
        return mcUsersItemMenuList;
    }

    public void setMcUsersItemMenuList(List<McUsersItemMenu> mcUsersItemMenuList) {
        this.mcUsersItemMenuList = mcUsersItemMenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcMenuItemId != null ? mcMenuItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McMenuItem)) {
            return false;
        }
        McMenuItem other = (McMenuItem) object;
        if ((this.mcMenuItemId == null && other.mcMenuItemId != null) || (this.mcMenuItemId != null && !this.mcMenuItemId.equals(other.mcMenuItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COM.ENT.McMenuItem[ mcMenuItemId=" + mcMenuItemId + " ]";
    }
    
}
