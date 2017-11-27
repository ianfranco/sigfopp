package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.LicenciaMedica;
import com.areatecnica.sigf.controllers.LicenciaMedicaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "licenciaMedicaController")
@ViewScoped
public class LicenciaMedicaController extends AbstractController<LicenciaMedica> {

    @Inject
    private LicenciaMedicaFacade ejbFacade;
    @Inject
    private TrabajadorController licenciaMedicaIdTrabajadorController;

    /**
     * Initialize the concrete LicenciaMedica controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public LicenciaMedicaController() {
        // Inform the Abstract parent controller of the concrete LicenciaMedica Entity
        super(LicenciaMedica.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        licenciaMedicaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLicenciaMedicaIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && licenciaMedicaIdTrabajadorController.getSelected() == null) {
            licenciaMedicaIdTrabajadorController.setSelected(this.getSelected().getLicenciaMedicaIdTrabajador());
        }
    }
}
