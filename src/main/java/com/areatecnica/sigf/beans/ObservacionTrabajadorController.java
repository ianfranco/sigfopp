package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TipoObservacionController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.ObservacionTrabajador;
import com.areatecnica.sigf.controllers.ObservacionTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "observacionTrabajadorController")
@ViewScoped
public class ObservacionTrabajadorController extends AbstractController<ObservacionTrabajador> {

    @Inject
    private ObservacionTrabajadorFacade ejbFacade;
    @Inject
    private TipoObservacionController observacionTrabajadorIdTipoObservacionController;
    @Inject
    private TrabajadorController observacionTrabajadorIdTrabajadorController;

    /**
     * Initialize the concrete ObservacionTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ObservacionTrabajadorController() {
        // Inform the Abstract parent controller of the concrete ObservacionTrabajador Entity
        super(ObservacionTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        observacionTrabajadorIdTipoObservacionController.setSelected(null);
        observacionTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoObservacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareObservacionTrabajadorIdTipoObservacion(ActionEvent event) {
        if (this.getSelected() != null && observacionTrabajadorIdTipoObservacionController.getSelected() == null) {
            observacionTrabajadorIdTipoObservacionController.setSelected(this.getSelected().getObservacionTrabajadorIdTipoObservacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareObservacionTrabajadorIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && observacionTrabajadorIdTrabajadorController.getSelected() == null) {
            observacionTrabajadorIdTrabajadorController.setSelected(this.getSelected().getObservacionTrabajadorIdTrabajador());
        }
    }
}
