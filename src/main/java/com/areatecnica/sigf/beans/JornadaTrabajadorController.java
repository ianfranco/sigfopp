package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.JornadaTrabajador;
import com.areatecnica.sigf.controllers.JornadaTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "jornadaTrabajadorController")
@ViewScoped
public class JornadaTrabajadorController extends AbstractController<JornadaTrabajador> {

    @Inject
    private JornadaTrabajadorFacade ejbFacade;
    @Inject
    private JornadaLaboralController jornadaTrabajadorIdJornadaLaboralController;
    @Inject
    private TrabajadorController jornadaTrabajadorIdTrabajadorController;

    /**
     * Initialize the concrete JornadaTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public JornadaTrabajadorController() {
        // Inform the Abstract parent controller of the concrete JornadaTrabajador Entity
        super(JornadaTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        jornadaTrabajadorIdJornadaLaboralController.setSelected(null);
        jornadaTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the JornadaLaboral controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaTrabajadorIdJornadaLaboral(ActionEvent event) {
        if (this.getSelected() != null && jornadaTrabajadorIdJornadaLaboralController.getSelected() == null) {
            jornadaTrabajadorIdJornadaLaboralController.setSelected(this.getSelected().getJornadaTrabajadorIdJornadaLaboral());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaTrabajadorIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && jornadaTrabajadorIdTrabajadorController.getSelected() == null) {
            jornadaTrabajadorIdTrabajadorController.setSelected(this.getSelected().getJornadaTrabajadorIdTrabajador());
        }
    }
}
