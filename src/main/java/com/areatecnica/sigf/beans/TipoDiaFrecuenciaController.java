package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoDiaFrecuencia;
import com.areatecnica.sigf.controllers.TipoDiaFrecuenciaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoDiaFrecuenciaController")
@ViewScoped
public class TipoDiaFrecuenciaController extends AbstractController<TipoDiaFrecuencia> {

    @Inject
    private TipoDiaFrecuenciaFacade ejbFacade;

    /**
     * Initialize the concrete TipoDiaFrecuencia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoDiaFrecuenciaController() {
        // Inform the Abstract parent controller of the concrete TipoDiaFrecuencia Entity
        super(TipoDiaFrecuencia.class);
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from TipoDiaFrecuencia?cap_first and returns
     * the navigation outcome.
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
