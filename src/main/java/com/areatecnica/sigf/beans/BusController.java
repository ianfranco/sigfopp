package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.controllers.BusFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.BusDataModel;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "busController")
@ViewScoped
public class BusController extends AbstractController<Bus> {

    @Inject
    private BusFacade ejbFacade;
    @Inject
    private EmpresaController busIdEmpresaController;
    @Inject
    private EstadoBusController busIdEstadoBusController;
    @Inject
    private FlotaController busIdFlotaController;
    @Inject
    private ModeloMarcaBusController busIdModeloController;
    @Inject
    private ProcesoRecaudacionController busIdProcesoRecaudacionController;
    @Inject
    private TerminalController busIdTerminalController;
    @Inject
    private UnidadNegocioController busIdUnidadNegocioController;

    private IBusDao busDao;
    
    private List<Bus> items;
    private BusDataModel model;
    
    /**
     * Initialize the concrete Bus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public BusController() {
        // Inform the Abstract parent controller of the concrete Bus Entity
        super(Bus.class);

        columnNames();
        this.model = new BusDataModel(this.getCurrentUser().getUsuarioIdTerminal().getBusList());
    }

    /**
     * @return the items
     */
    @Override
    public List<Bus> getItems() {        
        return this.getCurrentUser().getUsuarioIdTerminal().getBusList();
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public BusDataModel getModel() {
        return model;
    }

    public void setModel(BusDataModel model) {
        this.model = model;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        busIdEmpresaController.setSelected(null);
        busIdEstadoBusController.setSelected(null);
        busIdFlotaController.setSelected(null);
        busIdModeloController.setSelected(null);
        busIdProcesoRecaudacionController.setSelected(null);
        busIdTerminalController.setSelected(null);
        busIdUnidadNegocioController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of AbonoBus entities that
     * are retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for AbonoBus page
     */
    public String navigateAbonoBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AbonoBus_items", this.getSelected().getAbonoBusList());
        }
        return "/abonoBus/index";
    }

    /**
     * Sets the "items" attribute with a collection of CargoBus entities that
     * are retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for CargoBus page
     */
    public String navigateCargoBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoBus_items", this.getSelected().getCargoBusList());
        }
        return "/cargoBus/index";
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", this.getSelected().getDespachoList());
        }
        return "/despacho/index";
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdEmpresa(ActionEvent event) {
        if (this.getSelected() != null && busIdEmpresaController.getSelected() == null) {
            busIdEmpresaController.setSelected(this.getSelected().getBusIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the EstadoBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdEstadoBus(ActionEvent event) {
        if (this.getSelected() != null && busIdEstadoBusController.getSelected() == null) {
            busIdEstadoBusController.setSelected(this.getSelected().getBusIdEstadoBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Flota controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdFlota(ActionEvent event) {
        if (this.getSelected() != null && busIdFlotaController.getSelected() == null) {
            busIdFlotaController.setSelected(this.getSelected().getBusIdFlota());
        }
    }

    /**
     * Sets the "selected" attribute of the ModeloMarcaBus controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdModelo(ActionEvent event) {
        if (this.getSelected() != null && busIdModeloController.getSelected() == null) {
            busIdModeloController.setSelected(this.getSelected().getBusIdModelo());
        }
    }

    /**
     * Sets the "selected" attribute of the ProcesoRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdProcesoRecaudacion(ActionEvent event) {
        if (this.getSelected() != null && busIdProcesoRecaudacionController.getSelected() == null) {
            busIdProcesoRecaudacionController.setSelected(this.getSelected().getBusIdProcesoRecaudacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && busIdTerminalController.getSelected() == null) {
            busIdTerminalController.setSelected(this.getSelected().getBusIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareBusIdUnidadNegocio(ActionEvent event) {
        if (this.getSelected() != null && busIdUnidadNegocioController.getSelected() == null) {
            busIdUnidadNegocioController.setSelected(this.getSelected().getBusIdUnidadNegocio());
        }
    }

    /**
     * Sets the "items" attribute with a collection of EgresoBus entities that
     * are retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoBus page
     */
    public String navigateEgresoBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoBus_items", this.getSelected().getEgresoBusList());
        }
        return "/egresoBus/index";
    }

    /**
     * Sets the "items" attribute with a collection of Guia entities that are
     * retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Guia page
     */
    public String navigateGuiaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Guia_items", this.getSelected().getGuiaList());
        }
        return "/guia/index";
    }

    /**
     * Sets the "items" attribute with a collection of RegistroMinuto entities
     * that are retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for RegistroMinuto page
     */
    public String navigateRegistroMinutoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroMinuto_items", this.getSelected().getRegistroMinutoList());
        }
        return "/registroMinuto/index";
    }

    /**
     * Sets the "items" attribute with a collection of RegistroMinuto entities
     * that are retrieved from Bus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for RegistroMinuto page
     */
    public String navigateRegistroMinutoList1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroMinuto_items", this.getSelected().getRegistroMinutoList1());
        }
        return "/registroMinuto/index";
    }

    @Override
    public Bus prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        this.getSelected().setBusFechaRevisionTecnica(new Date());

        return this.getSelected();
    }

    public void findMaxByUnidad() {
        UnidadNegocio unidad = this.getSelected().getBusIdUnidadNegocio();
        this.busDao = new IBusDaoImpl();
        this.getSelected().setBusNumero(this.busDao.findMaxNumeroUnidad(unidad));
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        this.getItems().add(this.getSelected());
    }

    @Override
    public void delete(ActionEvent event) {
        super.delete(event); //To change body of generated methods, choose Tools | Templates.
        this.getItems().remove(this.getSelected());
    }

    
    
}
