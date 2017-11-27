package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.CentroCosto;
import com.areatecnica.sigf.controllers.CentroCostoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "centroCostoController")
@ViewScoped
public class CentroCostoController extends AbstractController<CentroCosto> {

    @Inject
    private CentroCostoFacade ejbFacade;

    /**
     * Initialize the concrete CentroCosto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CentroCostoController() {
        // Inform the Abstract parent controller of the concrete CentroCosto Entity
        super(CentroCosto.class);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from CentroCosto?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", this.getSelected().getTrabajadorList());
        }
        return "/trabajador/index";
    }

    @Override
    public CentroCosto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCentroCostoIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
