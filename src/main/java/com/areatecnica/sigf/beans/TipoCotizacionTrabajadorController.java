package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoCotizacionTrabajador;
import com.areatecnica.sigf.controllers.TipoCotizacionTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoCotizacionTrabajadorController")
@ViewScoped
public class TipoCotizacionTrabajadorController extends AbstractController<TipoCotizacionTrabajador> {

    @Inject
    private TipoCotizacionTrabajadorFacade ejbFacade;

    /**
     * Initialize the concrete TipoCotizacionTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCotizacionTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoCotizacionTrabajador Entity
        super(TipoCotizacionTrabajador.class);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from TipoCotizacionTrabajador?cap_first and returns the
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

}
