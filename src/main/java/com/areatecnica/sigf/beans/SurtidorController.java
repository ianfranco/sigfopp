package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Surtidor;
import com.areatecnica.sigf.controllers.SurtidorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "surtidorController")
@ViewScoped
public class SurtidorController extends AbstractController<Surtidor> {

    @Inject
    private SurtidorFacade ejbFacade;
    @Inject
    private TerminalController surtidorIdTerminalController;

    /**
     * Initialize the concrete Surtidor controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public SurtidorController() {
        // Inform the Abstract parent controller of the concrete Surtidor Entity
        super(Surtidor.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        surtidorIdTerminalController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSurtidorIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && surtidorIdTerminalController.getSelected() == null) {
            surtidorIdTerminalController.setSelected(this.getSelected().getSurtidorIdTerminal());
        }
    }

    /**
     * Sets the "items" attribute with a collection of VentaCombustible entities
     * that are retrieved from Surtidor?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for VentaCombustible page
     */
    public String navigateVentaCombustibleList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaCombustible_items", this.getSelected().getVentaCombustibleList());
        }
        return "/ventaCombustible/index";
    }

    /**
     * Sets the "items" attribute with a collection of NumeralSurtidor entities
     * that are retrieved from Surtidor?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for NumeralSurtidor page
     */
    public String navigateNumeralSurtidorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("NumeralSurtidor_items", this.getSelected().getNumeralSurtidorList());
        }
        return "/numeralSurtidor/index";
    }

    @Override
    public Surtidor prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        return this.getSelected();
    }
    
}
