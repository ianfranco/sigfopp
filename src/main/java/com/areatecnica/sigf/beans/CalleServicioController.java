package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.CalleController;
import com.areatecnica.sigf.beans.ServicioController;
import com.areatecnica.sigf.entities.CalleServicio;
import com.areatecnica.sigf.controllers.CalleServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "calleServicioController")
@ViewScoped
public class CalleServicioController extends AbstractController<CalleServicio> {

    @Inject
    private CalleServicioFacade ejbFacade;
    @Inject
    private CalleController calleServicioIdCalleController;
    @Inject
    private ServicioController calleServicioIdServicioController;

    /**
     * Initialize the concrete CalleServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CalleServicioController() {
        // Inform the Abstract parent controller of the concrete CalleServicio Entity
        super(CalleServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        calleServicioIdCalleController.setSelected(null);
        calleServicioIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Calle controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalleServicioIdCalle(ActionEvent event) {
        if (this.getSelected() != null && calleServicioIdCalleController.getSelected() == null) {
            calleServicioIdCalleController.setSelected(this.getSelected().getCalleServicioIdCalle());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalleServicioIdServicio(ActionEvent event) {
        if (this.getSelected() != null && calleServicioIdServicioController.getSelected() == null) {
            calleServicioIdServicioController.setSelected(this.getSelected().getCalleServicioIdServicio());
        }
    }
}
