package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Servicio;
import com.areatecnica.sigf.controllers.ServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "servicioController")
@ViewScoped
public class ServicioController extends AbstractController<Servicio> {

    @Inject
    private ServicioFacade ejbFacade;
    @Inject
    private TerminalController servicioIdTerminalController;
    @Inject
    private UnidadNegocioController servicioIdUnidadController;

    /**
     * Initialize the concrete Servicio controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ServicioController() {
        // Inform the Abstract parent controller of the concrete Servicio Entity
        super(Servicio.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Servicio.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        servicioIdTerminalController.setSelected(null);
        servicioIdUnidadController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of CalleServicio entities
     * that are retrieved from Servicio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CalleServicio page
     */
    public String navigateCalleServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CalleServicio_items", this.getSelected().getCalleServicioList());
        }
        return "/calleServicio/index";
    }

    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Servicio?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from Servicio?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FrecuenciaServicio page
     */
    public String navigateFrecuenciaServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FrecuenciaServicio_items", this.getSelected().getFrecuenciaServicioList());
        }
        return "/frecuenciaServicio/index";
    }

    /**
     * Sets the "items" attribute with a collection of ControlServicio entities
     * that are retrieved from Servicio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ControlServicio page
     */
    public String navigateControlServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlServicio_items", this.getSelected().getControlServicioList());
        }
        return "/controlServicio/index";
    }


    /**
     * Sets the "items" attribute with a collection of HorarioServicio entities
     * that are retrieved from Servicio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HorarioServicio page
     */
    public String navigateHorarioServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HorarioServicio_items", this.getSelected().getHorarioServicioList());
        }
        return "/horarioServicio/index";
    }

    
    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && servicioIdTerminalController.getSelected() == null) {
            servicioIdTerminalController.setSelected(this.getSelected().getServicioIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareServicioIdUnidad(ActionEvent event) {
        if (this.getSelected() != null && servicioIdUnidadController.getSelected() == null) {
            servicioIdUnidadController.setSelected(this.getSelected().getServicioIdUnidad());
        }
    }

    @Override
    public Servicio prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setServicioIdCuenta(this.getUserCount());
        
        return getSelected();
    }
    
    
}
