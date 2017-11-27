package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.HorarioServicioController;
import com.areatecnica.sigf.beans.ControlServicioController;
import com.areatecnica.sigf.entities.ControlHorarioServicio;
import com.areatecnica.sigf.controllers.ControlHorarioServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "controlHorarioServicioController")
@ViewScoped
public class ControlHorarioServicioController extends AbstractController<ControlHorarioServicio> {

    @Inject
    private ControlHorarioServicioFacade ejbFacade;
    @Inject
    private ControlServicioController controlHorarioServicioIdControlServicioController;
    @Inject
    private HorarioServicioController controlHorarioServicioIdHorarioServicioController;

    /**
     * Initialize the concrete ControlHorarioServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ControlHorarioServicioController() {
        // Inform the Abstract parent controller of the concrete ControlHorarioServicio Entity
        super(ControlHorarioServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlHorarioServicioIdControlServicioController.setSelected(null);
        controlHorarioServicioIdHorarioServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the ControlServicio controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlHorarioServicioIdControlServicio(ActionEvent event) {
        if (this.getSelected() != null && controlHorarioServicioIdControlServicioController.getSelected() == null) {
            controlHorarioServicioIdControlServicioController.setSelected(this.getSelected().getControlHorarioServicioIdControl());
        }
    }

    /**
     * Sets the "selected" attribute of the HorarioServicio controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlHorarioServicioIdHorarioServicio(ActionEvent event) {
        if (this.getSelected() != null && controlHorarioServicioIdHorarioServicioController.getSelected() == null) {
            controlHorarioServicioIdHorarioServicioController.setSelected(this.getSelected().getControlHorarioServicioIdHorario());
        }
    }
}
