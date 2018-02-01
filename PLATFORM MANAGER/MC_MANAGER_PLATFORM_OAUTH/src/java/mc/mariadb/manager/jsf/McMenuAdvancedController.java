package mc.mariadb.manager.jsf;

import mc.mariadb.manager.entities.McMenuAdvanced;
import mc.mariadb.manager.jsf.util.JsfUtil;
import mc.mariadb.manager.jsf.util.JsfUtil.PersistAction;
import mc.mariadb.manager.bean.McMenuAdvancedFacade;

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

@Named("mcMenuAdvancedController")
@SessionScoped
public class McMenuAdvancedController implements Serializable {

    @EJB
    private mc.mariadb.manager.bean.McMenuAdvancedFacade ejbFacade;
    private List<McMenuAdvanced> items = null;
    private McMenuAdvanced selected;

    public McMenuAdvancedController() {
    }

    public McMenuAdvanced getSelected() {
        return selected;
    }

    public void setSelected(McMenuAdvanced selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private McMenuAdvancedFacade getFacade() {
        return ejbFacade;
    }

    public McMenuAdvanced prepareCreate() {
        selected = new McMenuAdvanced();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("McMenuAdvancedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("McMenuAdvancedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("McMenuAdvancedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<McMenuAdvanced> getItems() {
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
                    selected.setMcMenuAdvancedAlterDate(new SimpleDateFormat("dd-MM-YYYY hh:mm:ss").format(new java.util.Date()));
                    selected.setMcMenuAdvancdAlterNetwork(InetAddress.getLocalHost().getHostAddress());
                    selected.setMcMenuAdvancedAlterUser(ResourceBundle.getBundle("/Bundle").getString("UserMcSuperAdmin"));
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

    public McMenuAdvanced getMcMenuAdvanced(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<McMenuAdvanced> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<McMenuAdvanced> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = McMenuAdvanced.class)
    public static class McMenuAdvancedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            McMenuAdvancedController controller = (McMenuAdvancedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mcMenuAdvancedController");
            return controller.getMcMenuAdvanced(getKey(value));
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
            if (object instanceof McMenuAdvanced) {
                McMenuAdvanced o = (McMenuAdvanced) object;
                return getStringKey(o.getMcMenuAdvancedId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), McMenuAdvanced.class.getName()});
                return null;
            }
        }

    }

}
