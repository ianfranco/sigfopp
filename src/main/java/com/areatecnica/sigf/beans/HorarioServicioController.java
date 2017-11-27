package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.ServicioController;
import com.areatecnica.sigf.entities.HorarioServicio;
import com.areatecnica.sigf.controllers.HorarioServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "horarioServicioController")
@ViewScoped
public class HorarioServicioController extends AbstractController<HorarioServicio> {

    @Inject
    private HorarioServicioFacade ejbFacade;
    @Inject
    private ServicioController horarioServicioIdServicioController;
    @Inject
    private TipoEstacionalidadController horarioServicioIdTipoEstacionalidadController;

    /**
     * Initialize the concrete HorarioServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public HorarioServicioController() {
        // Inform the Abstract parent controller of the concrete HorarioServicio Entity
        super(HorarioServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        horarioServicioIdServicioController.setSelected(null);
        horarioServicioIdTipoEstacionalidadController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of ControlHorarioServicio
     * entities that are retrieved from HorarioServicio?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for ControlHorarioServicio page
     */
    public String navigateControlHorarioServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlHorarioServicio_items", this.getSelected().getControlHorarioServicioList());
        }
        return "/controlHorarioServicio/index";
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHorarioServicioIdServicio(ActionEvent event) {
        if (this.getSelected() != null && horarioServicioIdServicioController.getSelected() == null) {
            horarioServicioIdServicioController.setSelected(this.getSelected().getHorarioServicioIdServicio());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoEstacionalidad controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHorarioServicioIdTipoEstacionalidad(ActionEvent event) {
        if (this.getSelected() != null && horarioServicioIdTipoEstacionalidadController.getSelected() == null) {
            horarioServicioIdTipoEstacionalidadController.setSelected(this.getSelected().getHorarioServicioIdTipoEstacionalidad());
        }
    }
}
