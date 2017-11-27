package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.ControlAsistencia;
import com.areatecnica.sigf.controllers.ControlAsistenciaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "controlAsistenciaController")
@ViewScoped
public class ControlAsistenciaController extends AbstractController<ControlAsistencia> {

    @Inject
    private ControlAsistenciaFacade ejbFacade;
    @Inject
    private TrabajadorController controlAsistenciaIdTrabajadorController;

    /**
     * Initialize the concrete ControlAsistencia controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ControlAsistenciaController() {
        // Inform the Abstract parent controller of the concrete ControlAsistencia Entity
        super(ControlAsistencia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlAsistenciaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlAsistenciaIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && controlAsistenciaIdTrabajadorController.getSelected() == null) {
            controlAsistenciaIdTrabajadorController.setSelected(this.getSelected().getControlAsistenciaIdTrabajador());
        }
    }
}
