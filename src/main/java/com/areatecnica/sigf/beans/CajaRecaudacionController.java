package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.controllers.CajaRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cajaTerminalController")
@ViewScoped
public class CajaRecaudacionController extends AbstractController<CajaRecaudacion> {

    @Inject
    private CajaRecaudacionFacade ejbFacade;
    @Inject
    private TerminalController cajaTerminalIdTerminalController;
    @Inject
    private UsuarioController cajaTerminalIdUsuarioController;

    /**
     * Initialize the concrete CajaTerminal controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CajaRecaudacionController() {
        // Inform the Abstract parent controller of the concrete CajaTerminal Entity
        super(CajaRecaudacion.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("CajaRecaudacion.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cajaTerminalIdTerminalController.setSelected(null);
        cajaTerminalIdUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaTerminalIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && cajaTerminalIdTerminalController.getSelected() == null) {
            cajaTerminalIdTerminalController.setSelected(this.getSelected().getCajaRecaudacionIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCajaTerminalIdUsuario(ActionEvent event) {
        if (this.getSelected() != null && cajaTerminalIdUsuarioController.getSelected() == null) {
            cajaTerminalIdUsuarioController.setSelected(this.getSelected().getCajaRecaudacionIdUsuario());
        }
    }

    /**
     * Sets the "items" attribute with a collection of ResumenRecaudacion
     * entities that are retrieved from CajaTerminal?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ResumenRecaudacion page
     */
    public String navigateResumenRecaudacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ResumenRecaudacion_items", this.getSelected().getResumenRecaudacionList());
        }
        return "/resumenRecaudacion/index";
    }
    
    /**
     * Sets the "items" attribute with a collection of EgresoCajaTerminal
     * entities that are retrieved from CajaTerminal?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for EgresoCajaTerminal page
     */
    public String navigateEgresoCajaTerminalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoCajaTerminal_items", this.getSelected().getEgresoCajaRecaudacionList());
        }
        return "/egresoCajaTerminal/index";
    }

    
    /**
     * Sets the "items" attribute with a collection of InventarioCaja entities
     * that are retrieved from CajaTerminal?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InventarioCaja page
     */
    public String navigateInventarioCajaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InventarioCaja_items", this.getSelected().getInventarioCajaList());
        }
        return "/inventarioCaja/index";
    }

    @Override
    public CajaRecaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        this.getSelected().setCajaRecaudacionIdCuenta(this.getUserCount());
        this.getSelected().setCajaRecaudacionActiva(Boolean.TRUE);
        return this.getSelected();
    }

}
