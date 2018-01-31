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
@Table(name = "mc_menu_advanced")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McMenuAdvanced.findAll", query = "SELECT m FROM McMenuAdvanced m")
    , @NamedQuery(name = "McMenuAdvanced.findByMcMenuAdvancedId", query = "SELECT m FROM McMenuAdvanced m WHERE m.mcMenuAdvancedId = :mcMenuAdvancedId")
    , @NamedQuery(name = "McMenuAdvanced.findByMcMenuAdvancedName", query = "SELECT m FROM McMenuAdvanced m WHERE m.mcMenuAdvancedName = :mcMenuAdvancedName")
    , @NamedQuery(name = "McMenuAdvanced.findByMcMenuAdvancedStatus", query = "SELECT m FROM McMenuAdvanced m WHERE m.mcMenuAdvancedStatus = :mcMenuAdvancedStatus")
    , @NamedQuery(name = "McMenuAdvanced.findByMcMenuAdvancedAlterUser", query = "SELECT m FROM McMenuAdvanced m WHERE m.mcMenuAdvancedAlterUser = :mcMenuAdvancedAlterUser")
    , @NamedQuery(name = "McMenuAdvanced.findByMcMenuAdvancedAlterDate", query = "SELECT m FROM McMenuAdvanced m WHERE m.mcMenuAdvancedAlterDate = :mcMenuAdvancedAlterDate")
    , @NamedQuery(name = "McMenuAdvanced.findByMcMenuAdvancdAlterNetwork", query = "SELECT m FROM McMenuAdvanced m WHERE m.mcMenuAdvancdAlterNetwork = :mcMenuAdvancdAlterNetwork")})
public class McMenuAdvanced implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_menu_advanced_id")
    private Integer mcMenuAdvancedId;
    @Size(max = 100)
    @Column(name = "mc_menu_advanced_name")
    private String mcMenuAdvancedName;
    @Column(name = "mc_menu_advanced_status")
    private Character mcMenuAdvancedStatus;
    @Size(max = 100)
    @Column(name = "mc_menu_advanced_alter_user")
    private String mcMenuAdvancedAlterUser;
    @Size(max = 100)
    @Column(name = "mc_menu_advanced_alter_date")
    private String mcMenuAdvancedAlterDate;
    @Size(max = 100)
    @Column(name = "mc_menu_advancd_alter_network")
    private String mcMenuAdvancdAlterNetwork;
    @OneToMany(mappedBy = "mcUsersMenuOauthMenuId")
    private List<McUsersMenuOauth> mcUsersMenuOauthList;

    public McMenuAdvanced() {
    }

    public McMenuAdvanced(Integer mcMenuAdvancedId) {
        this.mcMenuAdvancedId = mcMenuAdvancedId;
    }

    public Integer getMcMenuAdvancedId() {
        return mcMenuAdvancedId;
    }

    public void setMcMenuAdvancedId(Integer mcMenuAdvancedId) {
        this.mcMenuAdvancedId = mcMenuAdvancedId;
    }

    public String getMcMenuAdvancedName() {
        return mcMenuAdvancedName;
    }

    public void setMcMenuAdvancedName(String mcMenuAdvancedName) {
        this.mcMenuAdvancedName = mcMenuAdvancedName;
    }

    public Character getMcMenuAdvancedStatus() {
        return mcMenuAdvancedStatus;
    }

    public void setMcMenuAdvancedStatus(Character mcMenuAdvancedStatus) {
        this.mcMenuAdvancedStatus = mcMenuAdvancedStatus;
    }

    public String getMcMenuAdvancedAlterUser() {
        return mcMenuAdvancedAlterUser;
    }

    public void setMcMenuAdvancedAlterUser(String mcMenuAdvancedAlterUser) {
        this.mcMenuAdvancedAlterUser = mcMenuAdvancedAlterUser;
    }

    public String getMcMenuAdvancedAlterDate() {
        return mcMenuAdvancedAlterDate;
    }

    public void setMcMenuAdvancedAlterDate(String mcMenuAdvancedAlterDate) {
        this.mcMenuAdvancedAlterDate = mcMenuAdvancedAlterDate;
    }

    public String getMcMenuAdvancdAlterNetwork() {
        return mcMenuAdvancdAlterNetwork;
    }

    public void setMcMenuAdvancdAlterNetwork(String mcMenuAdvancdAlterNetwork) {
        this.mcMenuAdvancdAlterNetwork = mcMenuAdvancdAlterNetwork;
    }

    @XmlTransient
    public List<McUsersMenuOauth> getMcUsersMenuOauthList() {
        return mcUsersMenuOauthList;
    }

    public void setMcUsersMenuOauthList(List<McUsersMenuOauth> mcUsersMenuOauthList) {
        this.mcUsersMenuOauthList = mcUsersMenuOauthList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcMenuAdvancedId != null ? mcMenuAdvancedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McMenuAdvanced)) {
            return false;
        }
        McMenuAdvanced other = (McMenuAdvanced) object;
        if ((this.mcMenuAdvancedId == null && other.mcMenuAdvancedId != null) || (this.mcMenuAdvancedId != null && !this.mcMenuAdvancedId.equals(other.mcMenuAdvancedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COM.ENT.McMenuAdvanced[ mcMenuAdvancedId=" + mcMenuAdvancedId + " ]";
    }
    
}
