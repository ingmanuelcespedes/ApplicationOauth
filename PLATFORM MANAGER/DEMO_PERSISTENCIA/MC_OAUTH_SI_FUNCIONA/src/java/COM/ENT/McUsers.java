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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "mc_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McUsers.findAll", query = "SELECT m FROM McUsers m")
    , @NamedQuery(name = "McUsers.findByMcUsersId", query = "SELECT m FROM McUsers m WHERE m.mcUsersId = :mcUsersId")
    , @NamedQuery(name = "McUsers.findByMcUsersName", query = "SELECT m FROM McUsers m WHERE m.mcUsersName = :mcUsersName")
    , @NamedQuery(name = "McUsers.findByMcUsersDescription", query = "SELECT m FROM McUsers m WHERE m.mcUsersDescription = :mcUsersDescription")
    , @NamedQuery(name = "McUsers.findByMcUsersBirthDate", query = "SELECT m FROM McUsers m WHERE m.mcUsersBirthDate = :mcUsersBirthDate")
    , @NamedQuery(name = "McUsers.findByMcUsersGender", query = "SELECT m FROM McUsers m WHERE m.mcUsersGender = :mcUsersGender")
    , @NamedQuery(name = "McUsers.findByMcUsersLogin", query = "SELECT m FROM McUsers m WHERE m.mcUsersLogin = :mcUsersLogin")
    , @NamedQuery(name = "McUsers.findByMcUsersPass", query = "SELECT m FROM McUsers m WHERE m.mcUsersPass = :mcUsersPass")
    , @NamedQuery(name = "McUsers.findByMcUsersAlterUser", query = "SELECT m FROM McUsers m WHERE m.mcUsersAlterUser = :mcUsersAlterUser")
    , @NamedQuery(name = "McUsers.findByMcUsersAlterDate", query = "SELECT m FROM McUsers m WHERE m.mcUsersAlterDate = :mcUsersAlterDate")
    , @NamedQuery(name = "McUsers.findByMcUsersAlterNetwork", query = "SELECT m FROM McUsers m WHERE m.mcUsersAlterNetwork = :mcUsersAlterNetwork")})
public class McUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_users_id")
    private Integer mcUsersId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mc_users_name")
    private String mcUsersName;
    @Size(max = 200)
    @Column(name = "mc_users_description")
    private String mcUsersDescription;
    @Size(max = 10)
    @Column(name = "mc_users_birth_date")
    private String mcUsersBirthDate;
    @Column(name = "mc_users_gender")
    private Character mcUsersGender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mc_users_login")
    private String mcUsersLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mc_users_pass")
    private String mcUsersPass;
    @Size(max = 100)
    @Column(name = "mc_users_alter_user")
    private String mcUsersAlterUser;
    @Size(max = 100)
    @Column(name = "mc_users_alter_date")
    private String mcUsersAlterDate;
    @Size(max = 100)
    @Column(name = "mc_users_alter_network")
    private String mcUsersAlterNetwork;
    @OneToMany(mappedBy = "mcSessionOauthUsersId")
    private List<McSessionOauth> mcSessionOauthList;
    @OneToMany(mappedBy = "mcUsersMenuOauthUsersId")
    private List<McUsersMenuOauth> mcUsersMenuOauthList;
    @JoinColumn(name = "mc_users_type", referencedColumnName = "mc_type_oauth_id")
    @ManyToOne
    private McTypeOauth mcUsersType;
    @OneToMany(mappedBy = "mcMenuItemUsersId")
    private List<McUsersItemMenu> mcUsersItemMenuList;

    public McUsers() {
    }

    public McUsers(Integer mcUsersId) {
        this.mcUsersId = mcUsersId;
    }

    public McUsers(Integer mcUsersId, String mcUsersName, String mcUsersLogin, String mcUsersPass) {
        this.mcUsersId = mcUsersId;
        this.mcUsersName = mcUsersName;
        this.mcUsersLogin = mcUsersLogin;
        this.mcUsersPass = mcUsersPass;
    }

    public Integer getMcUsersId() {
        return mcUsersId;
    }

    public void setMcUsersId(Integer mcUsersId) {
        this.mcUsersId = mcUsersId;
    }

    public String getMcUsersName() {
        return mcUsersName;
    }

    public void setMcUsersName(String mcUsersName) {
        this.mcUsersName = mcUsersName;
    }

    public String getMcUsersDescription() {
        return mcUsersDescription;
    }

    public void setMcUsersDescription(String mcUsersDescription) {
        this.mcUsersDescription = mcUsersDescription;
    }

    public String getMcUsersBirthDate() {
        return mcUsersBirthDate;
    }

    public void setMcUsersBirthDate(String mcUsersBirthDate) {
        this.mcUsersBirthDate = mcUsersBirthDate;
    }

    public Character getMcUsersGender() {
        return mcUsersGender;
    }

    public void setMcUsersGender(Character mcUsersGender) {
        this.mcUsersGender = mcUsersGender;
    }

    public String getMcUsersLogin() {
        return mcUsersLogin;
    }

    public void setMcUsersLogin(String mcUsersLogin) {
        this.mcUsersLogin = mcUsersLogin;
    }

    public String getMcUsersPass() {
        return mcUsersPass;
    }

    public void setMcUsersPass(String mcUsersPass) {
        this.mcUsersPass = mcUsersPass;
    }

    public String getMcUsersAlterUser() {
        return mcUsersAlterUser;
    }

    public void setMcUsersAlterUser(String mcUsersAlterUser) {
        this.mcUsersAlterUser = mcUsersAlterUser;
    }

    public String getMcUsersAlterDate() {
        return mcUsersAlterDate;
    }

    public void setMcUsersAlterDate(String mcUsersAlterDate) {
        this.mcUsersAlterDate = mcUsersAlterDate;
    }

    public String getMcUsersAlterNetwork() {
        return mcUsersAlterNetwork;
    }

    public void setMcUsersAlterNetwork(String mcUsersAlterNetwork) {
        this.mcUsersAlterNetwork = mcUsersAlterNetwork;
    }

    @XmlTransient
    public List<McSessionOauth> getMcSessionOauthList() {
        return mcSessionOauthList;
    }

    public void setMcSessionOauthList(List<McSessionOauth> mcSessionOauthList) {
        this.mcSessionOauthList = mcSessionOauthList;
    }

    @XmlTransient
    public List<McUsersMenuOauth> getMcUsersMenuOauthList() {
        return mcUsersMenuOauthList;
    }

    public void setMcUsersMenuOauthList(List<McUsersMenuOauth> mcUsersMenuOauthList) {
        this.mcUsersMenuOauthList = mcUsersMenuOauthList;
    }

    public McTypeOauth getMcUsersType() {
        return mcUsersType;
    }

    public void setMcUsersType(McTypeOauth mcUsersType) {
        this.mcUsersType = mcUsersType;
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
        hash += (mcUsersId != null ? mcUsersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McUsers)) {
            return false;
        }
        McUsers other = (McUsers) object;
        if ((this.mcUsersId == null && other.mcUsersId != null) || (this.mcUsersId != null && !this.mcUsersId.equals(other.mcUsersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COM.ENT.McUsers[ mcUsersId=" + mcUsersId + " ]";
    }
    
}
