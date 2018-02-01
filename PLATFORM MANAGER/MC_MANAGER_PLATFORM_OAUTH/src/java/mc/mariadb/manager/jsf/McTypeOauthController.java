package mc.mariadb.manager.jsf;

import mc.mariadb.manager.entities.McTypeOauth;
import mc.mariadb.manager.jsf.util.JsfUtil;
import mc.mariadb.manager.jsf.util.JsfUtil.PersistAction;
import mc.mariadb.manager.bean.McTypeOauthFacade;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("mcTypeOauthController")
@SessionScoped
public class McTypeOauthController implements Serializable {

    @EJB
    private mc.mariadb.manager.bean.McTypeOauthFacade ejbFacade;
    private List<McTypeOauth> items = null;
    private McTypeOauth selected;

    public McTypeOauthController() {
    }

    public McTypeOauth getSelected() {
        return selected;
    }

    public void setSelected(McTypeOauth selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private McTypeOauthFacade getFacade() {
        return ejbFacade;
    }

    public McTypeOauth prepareCreate() {
        selected = new McTypeOauth();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("McTypeOauthCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("McTypeOauthUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("McTypeOauthDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<McTypeOauth> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    selected.setMcTypeOauthAlterDate(new SimpleDateFormat("dd-MM-YYYY hh:mm:ss").format(new java.util.Date()));
                    selected.setMcTypeOauthAlterNetwork(InetAddress.getLocalHost().getHostAddress());
                    selected.setMcTypeOauthAlterUser(ResourceBundle.getBundle("/Bundle").getString("UserMcSuperAdmin"));
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public McTypeOauth getMcTypeOauth(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<McTypeOauth> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<McTypeOauth> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = McTypeOauth.class)
    public static class McTypeOauthControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            McTypeOauthController controller = (McTypeOauthController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mcTypeOauthController");
            return controller.getMcTypeOauth(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof McTypeOauth) {
                McTypeOauth o = (McTypeOauth) object;
                return getStringKey(o.getMcTypeOauthId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), McTypeOauth.class.getName()});
                return null;
            }
        }

    }

}
