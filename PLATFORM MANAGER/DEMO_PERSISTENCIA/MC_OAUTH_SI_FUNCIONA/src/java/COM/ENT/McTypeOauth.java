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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acespedesl
 */
@Entity
@Table(name = "mc_type_oauth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McTypeOauth.findAll", query = "SELECT m FROM McTypeOauth m")
    , @NamedQuery(name = "McTypeOauth.findByMcTypeOauthId", query = "SELECT m FROM McTypeOauth m WHERE m.mcTypeOauthId = :mcTypeOauthId")
    , @NamedQuery(name = "McTypeOauth.findByMcTypeOauthDescription", query = "SELECT m FROM McTypeOauth m WHERE m.mcTypeOauthDescription = :mcTypeOauthDescription")
    , @NamedQuery(name = "McTypeOauth.findByMcTypeOauthStatus", query = "SELECT m FROM McTypeOauth m WHERE m.mcTypeOauthStatus = :mcTypeOauthStatus")
    , @NamedQuery(name = "McTypeOauth.findByMcTypeOauthAlterUser", query = "SELECT m FROM McTypeOauth m WHERE m.mcTypeOauthAlterUser = :mcTypeOauthAlterUser")
    , @NamedQuery(name = "McTypeOauth.findByMcTypeOauthAlterDate", query = "SELECT m FROM McTypeOauth m WHERE m.mcTypeOauthAlterDate = :mcTypeOauthAlterDate")
    , @NamedQuery(name = "McTypeOauth.findByMcTypeOauthAlterNetwork", query = "SELECT m FROM McTypeOauth m WHERE m.mcTypeOauthAlterNetwork = :mcTypeOauthAlterNetwork")})
public class McTypeOauth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_type_oauth_id")
    private Integer mcTypeOauthId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mc_type_oauth_description")
    private String mcTypeOauthDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mc_type_oauth_status")
    private Character mcTypeOauthStatus;
    @Size(max = 100)
    @Column(name = "mc_type_oauth_alter_user")
    private String mcTypeOauthAlterUser;
    @Size(max = 100)
    @Column(name = "mc_type_oauth_alter_date")
    private String mcTypeOauthAlterDate;
    @Size(max = 100)
    @Column(name = "mc_type_oauth_alter_network")
    private String mcTypeOauthAlterNetwork;
    @OneToMany(mappedBy = "mcUsersType")
    private List<McUsers> mcUsersList;

    public McTypeOauth() {
    }

    public McTypeOauth(Integer mcTypeOauthId) {
        this.mcTypeOauthId = mcTypeOauthId;
    }

    public McTypeOauth(Integer mcTypeOauthId, String mcTypeOauthDescription, Character mcTypeOauthStatus) {
        this.mcTypeOauthId = mcTypeOauthId;
        this.mcTypeOauthDescription = mcTypeOauthDescription;
        this.mcTypeOauthStatus = mcTypeOauthStatus;
    }

    public Integer getMcTypeOauthId() {
        return mcTypeOauthId;
    }

    public void setMcTypeOauthId(Integer mcTypeOauthId) {
        this.mcTypeOauthId = mcTypeOauthId;
    }

    public String getMcTypeOauthDescription() {
        return mcTypeOauthDescription;
    }

    public void setMcTypeOauthDescription(String mcTypeOauthDescription) {
        this.mcTypeOauthDescription = mcTypeOauthDescription;
    }

    public Character getMcTypeOauthStatus() {
        return mcTypeOauthStatus;
    }

    public void setMcTypeOauthStatus(Character mcTypeOauthStatus) {
        this.mcTypeOauthStatus = mcTypeOauthStatus;
    }

    public String getMcTypeOauthAlterUser() {
        return mcTypeOauthAlterUser;
    }

    public void setMcTypeOauthAlterUser(String mcTypeOauthAlterUser) {
        this.mcTypeOauthAlterUser = mcTypeOauthAlterUser;
    }

    public String getMcTypeOauthAlterDate() {
        return mcTypeOauthAlterDate;
    }

    public void setMcTypeOauthAlterDate(String mcTypeOauthAlterDate) {
        this.mcTypeOauthAlterDate = mcTypeOauthAlterDate;
    }

    public String getMcTypeOauthAlterNetwork() {
        return mcTypeOauthAlterNetwork;
    }

    public void setMcTypeOauthAlterNetwork(String mcTypeOauthAlterNetwork) {
        this.mcTypeOauthAlterNetwork = mcTypeOauthAlterNetwork;
    }

    @XmlTransient
    public List<McUsers> getMcUsersList() {
        return mcUsersList;
    }

    public void setMcUsersList(List<McUsers> mcUsersList) {
        this.mcUsersList = mcUsersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcTypeOauthId != null ? mcTypeOauthId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McTypeOauth)) {
            return false;
        }
        McTypeOauth other = (McTypeOauth) object;
        if ((this.mcTypeOauthId == null && other.mcTypeOauthId != null) || (this.mcTypeOauthId != null && !this.mcTypeOauthId.equals(other.mcTypeOauthId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COM.ENT.McTypeOauth[ mcTypeOauthId=" + mcTypeOauthId + " ]";
    }
    
}
