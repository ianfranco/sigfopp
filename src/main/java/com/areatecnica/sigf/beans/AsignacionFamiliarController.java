package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.AsignacionFamiliar;
import com.areatecnica.sigf.controllers.AsignacionFamiliarFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "asignacionFamiliarController")
@ViewScoped
public class AsignacionFamiliarController extends AbstractController<AsignacionFamiliar> {

    @Inject
    private AsignacionFamiliarFacade ejbFacade;

    /**
     * Initialize the concrete AsignacionFamiliar controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AsignacionFamiliarController() {
        // Inform the Abstract parent controller of the concrete AsignacionFamiliar Entity
        super(AsignacionFamiliar.class);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from AsignacionFamiliar?cap_first and returns the
     * navigation outcome.
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
    public AsignacionFamiliar prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setAsignacionFamiliarIdCuenta(this.getUserCount());
        return this.getSelected();
    }

}
