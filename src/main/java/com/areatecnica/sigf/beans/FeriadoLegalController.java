package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.FeriadoLegal;
import com.areatecnica.sigf.controllers.FeriadoLegalFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "feriadoLegalController")
@ViewScoped
public class FeriadoLegalController extends AbstractController<FeriadoLegal> {

    @Inject
    private FeriadoLegalFacade ejbFacade;
    @Inject
    private TrabajadorController feriadoLegalIdTrabajadorController;

    /**
     * Initialize the concrete FeriadoLegal controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public FeriadoLegalController() {
        // Inform the Abstract parent controller of the concrete FeriadoLegal Entity
        super(FeriadoLegal.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        feriadoLegalIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFeriadoLegalIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && feriadoLegalIdTrabajadorController.getSelected() == null) {
            feriadoLegalIdTrabajadorController.setSelected(this.getSelected().getFeriadoLegalIdTrabajador());
        }
    }
}
