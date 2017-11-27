package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.EstadoBus;
import com.areatecnica.sigf.controllers.EstadoBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estadoBusController")
@ViewScoped
public class EstadoBusController extends AbstractController<EstadoBus> {

    @Inject
    private EstadoBusFacade ejbFacade;

    /**
     * Initialize the concrete EstadoBus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EstadoBusController() {
        // Inform the Abstract parent controller of the concrete EstadoBus Entity
        super(EstadoBus.class);
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from EstadoBus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "/bus/index";
    }

    public void resetParents() {

    }

    @Override
    public EstadoBus prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        return this.getSelected();
    }

}
