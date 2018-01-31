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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acespedesl
 */
@Entity
@Table(name = "mc_session_oauth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McSessionOauth.findAll", query = "SELECT m FROM McSessionOauth m")
    , @NamedQuery(name = "McSessionOauth.findByMcSessionOauthId", query = "SELECT m FROM McSessionOauth m WHERE m.mcSessionOauthId = :mcSessionOauthId")
    , @NamedQuery(name = "McSessionOauth.findByMcSessionOauthUserLogin", query = "SELECT m FROM McSessionOauth m WHERE m.mcSessionOauthUserLogin = :mcSessionOauthUserLogin")
    , @NamedQuery(name = "McSessionOauth.findByMcSessionOauthDateBegin", query = "SELECT m FROM McSessionOauth m WHERE m.mcSessionOauthDateBegin = :mcSessionOauthDateBegin")
    , @NamedQuery(name = "McSessionOauth.findByMcSessionOauthDateEnd", query = "SELECT m FROM McSessionOauth m WHERE m.mcSessionOauthDateEnd = :mcSessionOauthDateEnd")})
public class McSessionOauth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mc_session_oauth_id")
    private Integer mcSessionOauthId;
    @Size(max = 100)
    @Column(name = "mc_session_oauth_user_login")
    private String mcSessionOauthUserLogin;
    @Size(max = 20)
    @Column(name = "mc_session_oauth_date_begin")
    private String mcSessionOauthDateBegin;
    @Size(max = 20)
    @Column(name = "mc_session_oauth_date_end")
    private String mcSessionOauthDateEnd;
    @JoinColumn(name = "mc_session_oauth_users_id", referencedColumnName = "mc_users_id")
    @ManyToOne
    private McUsers mcSessionOauthUsersId;

    public McSessionOauth() {
    }

    public McSessionOauth(Integer mcSessionOauthId) {
        this.mcSessionOauthId = mcSessionOauthId;
    }

    public Integer getMcSessionOauthId() {
        return mcSessionOauthId;
    }

    public void setMcSessionOauthId(Integer mcSessionOauthId) {
        this.mcSessionOauthId = mcSessionOauthId;
    }

    public String getMcSessionOauthUserLogin() {
        return mcSessionOauthUserLogin;
    }

    public void setMcSessionOauthUserLogin(String mcSessionOauthUserLogin) {
        this.mcSessionOauthUserLogin = mcSessionOauthUserLogin;
    }

    public String getMcSessionOauthDateBegin() {
        return mcSessionOauthDateBegin;
    }

    public void setMcSessionOauthDateBegin(String mcSessionOauthDateBegin) {
        this.mcSessionOauthDateBegin = mcSessionOauthDateBegin;
    }

    public String getMcSessionOauthDateEnd() {
        return mcSessionOauthDateEnd;
    }

    public void setMcSessionOauthDateEnd(String mcSessionOauthDateEnd) {
        this.mcSessionOauthDateEnd = mcSessionOauthDateEnd;
    }

    public McUsers getMcSessionOauthUsersId() {
        return mcSessionOauthUsersId;
    }

    public void setMcSessionOauthUsersId(McUsers mcSessionOauthUsersId) {
        this.mcSessionOauthUsersId = mcSessionOauthUsersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcSessionOauthId != null ? mcSessionOauthId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McSessionOauth)) {
            return false;
        }
        McSessionOauth other = (McSessionOauth) object;
        if ((this.mcSessionOauthId == null && other.mcSessionOauthId != null) || (this.mcSessionOauthId != null && !this.mcSessionOauthId.equals(other.mcSessionOauthId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "COM.ENT.McSessionOauth[ mcSessionOauthId=" + mcSessionOauthId + " ]";
    }
    
}
