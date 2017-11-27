package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.SurtidorController;
import com.areatecnica.sigf.entities.NumeralSurtidor;
import com.areatecnica.sigf.controllers.NumeralSurtidorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "numeralSurtidorController")
@ViewScoped
public class NumeralSurtidorController extends AbstractController<NumeralSurtidor> {

    @Inject
    private NumeralSurtidorFacade ejbFacade;
    @Inject
    private SurtidorController numeralSurtidorIdSurtidorController;

    /**
     * Initialize the concrete NumeralSurtidor controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public NumeralSurtidorController() {
        // Inform the Abstract parent controller of the concrete NumeralSurtidor Entity
        super(NumeralSurtidor.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        numeralSurtidorIdSurtidorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Surtidor controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNumeralSurtidorIdSurtidor(ActionEvent event) {
        if (this.getSelected() != null && numeralSurtidorIdSurtidorController.getSelected() == null) {
            numeralSurtidorIdSurtidorController.setSelected(this.getSelected().getNumeralSurtidorIdSurtidor());
        }
    }
}
