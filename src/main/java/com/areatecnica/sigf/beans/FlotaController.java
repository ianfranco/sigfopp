package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.controllers.FlotaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "flotaController")
@ViewScoped
public class FlotaController extends AbstractController<Flota> {

    @Inject
    private FlotaFacade ejbFacade;

    /**
     * Initialize the concrete Flota controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public FlotaController() {
        // Inform the Abstract parent controller of the concrete Flota Entity
        super(Flota.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Flota.findAllByCuenta");
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from Flota?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of EgresoFlota entities that
     * are retrieved from Flota?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for EgresoFlota page
     */
    public String navigateEgresoFlotaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EgresoFlota_items", this.getSelected().getEgresoFlotaList());
        }
        return "/egresoFlota/index";
    }

    public void resetParents() {

    }

    @Override
    public Flota prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setFlotaIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
