package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.PeriodoFrecuencia;
import com.areatecnica.sigf.controllers.PeriodoFrecuenciaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "periodoFrecuenciaController")
@ViewScoped
public class PeriodoFrecuenciaController extends AbstractController<PeriodoFrecuencia> {

    @Inject
    private PeriodoFrecuenciaFacade ejbFacade;

    /**
     * Initialize the concrete PeriodoFrecuencia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PeriodoFrecuenciaController() {
        // Inform the Abstract parent controller of the concrete PeriodoFrecuencia Entity
        super(PeriodoFrecuencia.class);
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from PeriodoFrecuencia?cap_first and returns
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
