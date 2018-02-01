package mc.mariadb.manager.jsf;

import mc.mariadb.manager.entities.McUsersItemMenu;
import mc.mariadb.manager.jsf.util.JsfUtil;
import mc.mariadb.manager.jsf.util.JsfUtil.PersistAction;
import mc.mariadb.manager.bean.McUsersItemMenuFacade;

import java.io.Serializable;
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

@Named("mcUsersItemMenuController")
@SessionScoped
public class McUsersItemMenuController implements Serializable {

    @EJB
    private mc.mariadb.manager.bean.McUsersItemMenuFacade ejbFacade;
    private List<McUsersItemMenu> items = null;
    private McUsersItemMenu selected;

    public McUsersItemMenuController() {
    }

    public McUsersItemMenu getSelected() {
        return selected;
    }

    public void setSelected(McUsersItemMenu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private McUsersItemMenuFacade getFacade() {
        return ejbFacade;
    }

    public McUsersItemMenu prepareCreate() {
        selected = new McUsersItemMenu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("McUsersItemMenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("McUsersItemMenuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("McUsersItemMenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<McUsersItemMenu> getItems() {
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

    public McUsersItemMenu getMcUsersItemMenu(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<McUsersItemMenu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<McUsersItemMenu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = McUsersItemMenu.class)
    public static class McUsersItemMenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            McUsersItemMenuController controller = (McUsersItemMenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mcUsersItemMenuController");
            return controller.getMcUsersItemMenu(getKey(value));
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
            if (object instanceof McUsersItemMenu) {
                McUsersItemMenu o = (McUsersItemMenu) object;
                return getStringKey(o.getMcUsersItemMenuId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), McUsersItemMenu.class.getName()});
                return null;
            }
        }

    }

}
