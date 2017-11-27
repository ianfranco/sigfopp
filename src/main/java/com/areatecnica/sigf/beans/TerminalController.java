package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.controllers.TerminalFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "terminalController")
@ViewScoped
public class TerminalController extends AbstractController<Terminal> {

    @Inject
    private TerminalFacade ejbFacade;
    @Inject
    private ComunaController terminalIdComunaController;

    /**
     * Initialize the concrete Terminal controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TerminalController() {
        // Inform the Abstract parent controller of the concrete Terminal Entity
        super(Terminal.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Terminal.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        terminalIdComunaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of CajaTerminal entities
     * that are retrieved from Terminal?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CajaTerminal page
     */
    public String navigateCajaTerminalList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaTerminal_items", this.getSelected().getCajaRecaudacionList());
        }
        return "/cajaTerminal/index";
    }

    /**
     * Sets the "items" attribute with a collection of Surtidor entities that
     * are retrieved from Terminal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Surtidor page
     */
    public String navigateSurtidorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Surtidor_items", this.getSelected().getSurtidorList());
        }
        return "/surtidor/index";
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Terminal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", this.getSelected().getUsuarioList());
        }
        return "/usuario/index";
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from Terminal?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", this.getSelected().getLiquidacionSueldoList());
        }
        return "/liquidacionSueldo/index";
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from Terminal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", this.getSelected().getTrabajadorList());
        }
        return "/trabajador/index";
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from Terminal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "/bus/index";
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from Terminal?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", this.getSelected().getRelacionLaboralList());
        }
        return "/relacionLaboral/index";
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from Terminal?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", this.getSelected().getServicioList());
        }
        return "/servicio/index";
    }

    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTerminalIdComuna(ActionEvent event) {
        if (this.getSelected() != null && terminalIdComunaController.getSelected() == null) {
            terminalIdComunaController.setSelected(this.getSelected().getTerminalIdComuna());
        }
    }
   
    
    @Override
    public Terminal prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTerminalIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }
}
