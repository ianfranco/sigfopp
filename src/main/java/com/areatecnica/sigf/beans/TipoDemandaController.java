package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoDemanda;
import com.areatecnica.sigf.controllers.TipoDemandaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoDemandaController")
@ViewScoped
public class TipoDemandaController extends AbstractController<TipoDemanda> {

    @Inject
    private TipoDemandaFacade ejbFacade;

    /**
     * Initialize the concrete TipoDemanda controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoDemandaController() {
        // Inform the Abstract parent controller of the concrete TipoDemanda Entity
        super(TipoDemanda.class);
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from TipoDemanda?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FrecuenciaServicio page
     */
    public String navigateFrecuenciaServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FrecuenciaServicio_items", this.getSelected().getFrecuenciaServicioList());
        }
        return "/frecuenciaServicio/index";
    }

}
