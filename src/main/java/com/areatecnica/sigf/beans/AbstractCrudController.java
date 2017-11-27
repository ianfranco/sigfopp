package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.controllers.AbstractFacade;
import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.dao.IGenericDAO;
import com.areatecnica.sigf.dao.impl.GenericDAOImpl;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.util.ColumnModel;
import com.areatecnica.sigf.util.Functions;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import java.util.ResourceBundle;
import javax.ejb.EJBException;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.Visibility;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractCrudController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private AbstractFacade<T> ejbFacade;
    private Class<T> itemClass;
    private T selected;
    private List<T> filtered;
    private List<T> items;
    private LazyDataModel<T> lazyItems;
    private List<Boolean> listVisible;
    private List<Integer> listWidth;
    private List<String> searchColumnNameList;
    private List<String> columnNameList;
    private List<ColumnModel> columns;
    private List<String> manyToOneList;
    private IGenericDAO<T> dao;
    private Object paramItems;
    private Usuario currentUser;
    private Cuenta userCount;
    private Boolean limitedByCuenta;
    private String namedQuery;

    private enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractCrudController() {
    }

    public AbstractCrudController(Class<T> itemClass) {
        this.itemClass = itemClass;
        dao = new GenericDAOImpl<T>(itemClass);
        this.itemClass = itemClass;
        listVisible = new ArrayList<Boolean>();
        listWidth = new ArrayList<>();
        this.limitedByCuenta = Boolean.FALSE;
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        this.userCount = this.currentUser.getUsuarioIdCuenta();
    }

    /**
     * @return the currentUser
     */
    public Usuario getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the userCount
     */
    public Cuenta getUserCount() {
        return userCount;
    }

    /**
     * @param userCount the userCount to set
     */
    public void setUserCount(Cuenta userCount) {
        this.userCount = userCount;
    }

    /**
     * Initialize the concrete controller bean. This AbstractController requires
     * the EJB Facade object for most operations, and that task is performed by
     * the concrete controller bean.
     * <p>
     * In addition, each controller for an entity that has Many-To-One
     * relationships, needs to establish references to those entities'
     * controllers in order to display their information from a context menu.
     */
    public abstract void init();

    /**
     * @return the dao
     */
    public IGenericDAO<T> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(IGenericDAO<T> dao) {
        this.dao = dao;
    }

    /**
     * Retrieve the current EJB Facade object so that other beans in this
     * package can perform additional data layer tasks (e.g. additional queries)
     *
     * @return the concrete EJB Facade associated with the concrete controller
     * bean.
     */
    protected AbstractFacade<T> getFacade() {
        return ejbFacade;
    }

    /**
     * Sets the concrete EJB Facade object so that data layer actions can be
     * performed. This applies to all basic CRUD actions this controller
     * performs.
     *
     * @param ejbFacade the concrete EJB Facade to perform data layer actions
     * with
     */
    protected void setFacade(AbstractFacade<T> ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        this.selected = selected;
    }

    /**
     * @return the limitedByCuenta
     */
    public Boolean getLimitedByCuenta() {
        return limitedByCuenta;
    }

    /**
     * @param limitedByCuenta the limitedByCuenta to set
     */
    public void setLimitedByCuenta(Boolean limitedByCuenta) {
        this.limitedByCuenta = limitedByCuenta;
    }

    /**
     * @return the namedQuery
     */
    public String getNamedQuery() {
        return namedQuery;
    }

    /**
     * @param namedQuery the namedQuery to set
     */
    public void setNamedQuery(String namedQuery) {
        this.namedQuery = namedQuery;
    }

    /**
     *
     * @return
     */
    public List<T> getFiltered() {
        return filtered;
    }

    /**
     *
     * @param filtered
     */
    public void setFiltered(List<T> filtered) {
        this.filtered = filtered;
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    public void loadData() {
        if (items == null) {
            if (getLimitedByCuenta()) {
                items = this.ejbFacade.findAllByCuenta(userCount, getNamedQuery());
            } else {
                items = this.ejbFacade.findAll();
            }

        }
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     *
     * @return
     */
    public Class<T> getItemClass() {
        return itemClass;
    }

    /**
     *
     * @param itemClass
     */
    public void setItemClass(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     *
     * @return Entity-specific Lazy Data Model
     */
    /**
     *
     * @return
     */
    public LazyDataModel<T> getLazyList() {
        if (getLazyItems() == null) {
            setLazyItems(new LazyDataModel<T>() {
                private static final long serialVersionUID = 5340099026592784595L;
                List<T> result;

                @Override
                public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    if (getParamItems() != null) {
                        result = (List<T>) getParamItems();
                        lazyItems.setRowCount(result.size());
                    } else {
                        result = getDao().loadLazy(first, pageSize, sortField, sortOrder, filters, getSearchColumnNameList());
                        lazyItems.setRowCount(getDao().count(filters, getSearchColumnNameList()));
                    }
                    return result;
                }

                @Override
                public T getRowData(String rowKey) {
                    if (pkFieldType().contains("Short")) {
                        return rowKey.equals("null") ? null : (T) getDao().read(Short.parseShort(rowKey));
                    } else if (pkFieldType().contains("Integer")) {
                        return rowKey.equals("null") ? null : (T) getDao().read(Integer.parseInt(rowKey));
                    } else if (pkFieldType().contains("Long")) {
                        return rowKey.equals("null") ? null : (T) getDao().read(Long.parseLong(rowKey));
                    } else {
                        return null;
                    }
                }
            });
        }
        return getLazyItems();
    }

    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/Bundle").getString(itemClass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);
    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/Bundle").getString(itemClass.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
            setLazyItems(null); // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/Bundle").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
            setLazyItems(null); // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    this.ejbFacade.edit(selected);
                } else {
                    this.ejbFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();

            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            setItems((List<T>) paramItems);
            setLazyItems((LazyDataModel<T>) paramItems);
        }
    }

    private String pkFieldType() {

        for (Field field : itemClass.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.toString().contains("javax.persistence.Id")) {
                    return field.getType().toString();
                }
            }
        }
        return null;
    }

    /**
     *
     * @param e
     */
    public void onToggle(org.primefaces.event.ToggleEvent e) {
        try {
            listVisible.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
        } catch (IndexOutOfBoundsException i) {

        }
    }

    /**
     *
     * @param event
     */
    public void onResize(ColumnResizeEvent event) {
        try {
            String[] str = event.getColumn().getColumnKey().split(":");
            int colIndex = str.length;
            listWidth.set(Integer.parseInt(str[colIndex - 1]), event.getWidth());
        } catch (IndexOutOfBoundsException i) {

        }
    }

    /**
     *
     */
    public void columnNames() {
        setColumnNameList(new ArrayList<String>());
        searchColumnNameList = new ArrayList<>();
        setManyToOneList(new ArrayList<String>());
        columns = new ArrayList<>();

        for (Field field : this.getItemClass().getDeclaredFields()) {
            if (!field.getType().toString().contains("java.util.List")) {
                if (!field.getName().equals("serialVersionUID")) {

                    if (field.getType().toString().contains("com.areatecnica.sigf.entities")) {
                        getManyToOneList().add(field.getName());
                    } else {
                        getColumnNameList().add(field.getName());
                        searchColumnNameList.add(field.getName());
                    }
                    columns.add(new ColumnModel(Functions.firstUpperCase(field.getName()), field.getName(), Boolean.FALSE, false));
                }
            }
        }
        for (Field field : this.getItemClass().getSuperclass().getDeclaredFields()) {
            if (!field.getType().toString().contains("java.util.List")) {
                if (!field.getName().equals("serialVersionUID")) {
                    if (field.getType().toString().contains("com.areatecnica.sigf.entities")) {
                        getManyToOneList().add(field.getName());
                    } else {
                        getColumnNameList().add(field.getName());
                        searchColumnNameList.add(field.getName());
                    }
                }
            }
        }
    }

    /**
     * @return the columns
     */
    public List<ColumnModel> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    /**
     * @return the lazyItems
     */
    public LazyDataModel<T> getLazyItems() {
        return lazyItems;
    }

    /**
     * @param lazyItems the lazyItems to set
     */
    public void setLazyItems(LazyDataModel<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    /**
     * @return the listVisible
     */
    public List<Boolean> getListVisible() {
        return listVisible;
    }

    /**
     * @param listVisible the listVisible to set
     */
    public void setListVisible(List<Boolean> listVisible) {
        this.listVisible = listVisible;
    }

    /**
     * @return the listWidth
     */
    public List<Integer> getListWidth() {
        return listWidth;
    }

    /**
     * @param listWidth the listWidth to set
     */
    public void setListWidth(List<Integer> listWidth) {
        this.listWidth = listWidth;
    }

    /**
     * @return the paramItems
     */
    public Object getParamItems() {
        return paramItems;
    }

    /**
     * @param paramItems the paramItems to set
     */
    public void setParamItems(Object paramItems) {
        this.paramItems = paramItems;
    }

    /**
     * @return the searchColumnNameList
     */
    public List<String> getSearchColumnNameList() {
        return searchColumnNameList;
    }

    /**
     * @param searchColumnNameList the searchColumnNameList to set
     */
    public void setSearchColumnNameList(List<String> searchColumnNameList) {
        this.searchColumnNameList = searchColumnNameList;
    }

    /**
     * @return the columnNameList
     */
    public List<String> getColumnNameList() {
        return columnNameList;
    }

    /**
     * @param columnNameList the columnNameList to set
     */
    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    /**
     * @return the manyToOneList
     */
    public List<String> getManyToOneList() {
        return manyToOneList;
    }

    /**
     * @param manyToOneList the manyToOneList to set
     */
    public void setManyToOneList(List<String> manyToOneList) {
        this.manyToOneList = manyToOneList;
    }

}
