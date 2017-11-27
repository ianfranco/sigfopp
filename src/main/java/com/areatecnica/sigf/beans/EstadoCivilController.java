package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.EstadoCivil;
import com.areatecnica.sigf.controllers.EstadoCivilFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "estadoCivilController")
@ViewScoped
public class EstadoCivilController extends AbstractController<EstadoCivil> {

    @Inject
    private EstadoCivilFacade ejbFacade;

    /**
     * Initialize the concrete EstadoCivil controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EstadoCivilController() {
        // Inform the Abstract parent controller of the concrete EstadoCivil Entity
        super(EstadoCivil.class);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from EstadoCivil?cap_first and returns the navigation
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

}
