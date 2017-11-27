package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.UnidadNegocioController;
import com.areatecnica.sigf.entities.ContratoUnidad;
import com.areatecnica.sigf.controllers.ContratoUnidadFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "contratoUnidadController")
@ViewScoped
public class ContratoUnidadController extends AbstractController<ContratoUnidad> {

    @Inject
    private ContratoUnidadFacade ejbFacade;
    @Inject
    private UnidadNegocioController contratoUnidadIdUnidadNegocioController;

    /**
     * Initialize the concrete ContratoUnidad controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ContratoUnidadController() {
        // Inform the Abstract parent controller of the concrete ContratoUnidad Entity
        super(ContratoUnidad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        contratoUnidadIdUnidadNegocioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareContratoUnidadIdUnidadNegocio(ActionEvent event) {
        if (this.getSelected() != null && contratoUnidadIdUnidadNegocioController.getSelected() == null) {
            contratoUnidadIdUnidadNegocioController.setSelected(this.getSelected().getContratoUnidadIdUnidadNegocio());
        }
    }
}
