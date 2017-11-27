package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.MetodoPago;
import com.areatecnica.sigf.controllers.MetodoPagoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "metodoPagoController")
@ViewScoped
public class MetodoPagoController extends AbstractController<MetodoPago> {

    @Inject
    private MetodoPagoFacade ejbFacade;

    /**
     * Initialize the concrete MetodoPago controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public MetodoPagoController() {
        // Inform the Abstract parent controller of the concrete MetodoPago Entity
        super(MetodoPago.class);
    }

    /**
     * Sets the "items" attribute with a collection of EfectivoCaja entities
     * that are retrieved from MetodoPago?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EfectivoCaja page
     */
    public String navigateEfectivoCajaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EfectivoCaja_items", this.getSelected().getEfectivoCajaList());
        }
        return "/efectivoCaja/index";
    }

}
