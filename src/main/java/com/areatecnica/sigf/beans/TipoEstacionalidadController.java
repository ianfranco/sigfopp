package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoEstacionalidad;
import com.areatecnica.sigf.controllers.TipoEstacionalidadFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoEstacionalidadController")
@ViewScoped
public class TipoEstacionalidadController extends AbstractController<TipoEstacionalidad> {

    @Inject
    private TipoEstacionalidadFacade ejbFacade;

    /**
     * Initialize the concrete TipoEstacionalidad controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoEstacionalidadController() {
        // Inform the Abstract parent controller of the concrete TipoEstacionalidad Entity
        super(TipoEstacionalidad.class);
    }

    /**
     * Sets the "items" attribute with a collection of HorarioServicio entities
     * that are retrieved from TipoEstacionalidad?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for HorarioServicio page
     */
    public String navigateHorarioServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HorarioServicio_items", this.getSelected().getHorarioServicioList());
        }
        return "/horarioServicio/index";
    }

}
