package mc.mariadb.manager.jsf;

import mc.mariadb.manager.entities.McUsers;
import mc.mariadb.manager.jsf.util.JsfUtil;
import mc.mariadb.manager.jsf.util.JsfUtil.PersistAction;
import mc.mariadb.manager.bean.McUsersFacade;

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

@Named("mcUsersController")
@SessionScoped
public class McUsersController implements Serializable {

    @EJB
    private mc.mariadb.manager.bean.McUsersFacade ejbFacade;
    private List<McUsers> items = null;
    private McUsers selected;

    public McUsersController() {
    }

    public McUsers getSelected() {
        return selected;
    }

    public void setSelected(McUsers selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private McUsersFacade getFacade() {
        return ejbFacade;
    }

    public McUsers prepareCreate() {
        selected = new McUsers();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("McUsersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("McUsersUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("McUsersDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<McUsers> getItems() {
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
                    selected.setMcUsersAlterDate(new SimpleDateFormat("dd-MM-YYYY hh:mm:ss").format(new java.util.Date()));
                    selected.setMcUsersAlterNetwork(InetAddress.getLocalHost().getHostAddress());
                    selected.setMcUsersAlterUser(ResourceBundle.getBundle("/Bundle").getString("UserMcSuperAdmin"));
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

    public McUsers getMcUsers(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<McUsers> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<McUsers> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = McUsers.class)
    public static class McUsersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            McUsersController controller = (McUsersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mcUsersController");
            return controller.getMcUsers(getKey(value));
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
            if (object instanceof McUsers) {
                McUsers o = (McUsers) object;
                return getStringKey(o.getMcUsersId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), McUsers.class.getName()});
                return null;
            }
        }

    }

}
