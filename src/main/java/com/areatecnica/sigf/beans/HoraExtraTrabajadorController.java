package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.HoraExtraTrabajador;
import com.areatecnica.sigf.controllers.HoraExtraTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "horaExtraTrabajadorController")
@ViewScoped
public class HoraExtraTrabajadorController extends AbstractController<HoraExtraTrabajador> {

    @Inject
    private HoraExtraTrabajadorFacade ejbFacade;
    @Inject
    private TrabajadorController horaExtraTrabajadorIdTrabajadorController;

    /**
     * Initialize the concrete HoraExtraTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public HoraExtraTrabajadorController() {
        // Inform the Abstract parent controller of the concrete HoraExtraTrabajador Entity
        super(HoraExtraTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        horaExtraTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHoraExtraTrabajadorIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && horaExtraTrabajadorIdTrabajadorController.getSelected() == null) {
            horaExtraTrabajadorIdTrabajadorController.setSelected(this.getSelected().getHoraExtraTrabajadorIdTrabajador());
        }
    }
}
