package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.IntervalosAdministracionController;
import com.areatecnica.sigf.beans.AdministracionMensualController;
import com.areatecnica.sigf.entities.DetalleIntervalosMensual;
import com.areatecnica.sigf.controllers.DetalleIntervalosMensualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "detalleIntervalosMensualController")
@ViewScoped
public class DetalleIntervalosMensualController extends AbstractController<DetalleIntervalosMensual> {

    @Inject
    private DetalleIntervalosMensualFacade ejbFacade;
    @Inject
    private AdministracionMensualController detalleIntervalosMensualIdAdministracionController;
    @Inject
    private IntervalosAdministracionController detalleIntervalosMensualIdIntervaloAdministracionController;

    /**
     * Initialize the concrete DetalleIntervalosMensual controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DetalleIntervalosMensualController() {
        // Inform the Abstract parent controller of the concrete DetalleIntervalosMensual Entity
        super(DetalleIntervalosMensual.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleIntervalosMensualIdAdministracionController.setSelected(null);
        detalleIntervalosMensualIdIntervaloAdministracionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the AdministracionMensual controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleIntervalosMensualIdAdministracion(ActionEvent event) {
        if (this.getSelected() != null && detalleIntervalosMensualIdAdministracionController.getSelected() == null) {
            detalleIntervalosMensualIdAdministracionController.setSelected(this.getSelected().getDetalleIntervalosMensualIdAdministracion());
        }
    }

    /**
     * Sets the "selected" attribute of the IntervalosAdministracion controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleIntervalosMensualIdIntervaloAdministracion(ActionEvent event) {
        if (this.getSelected() != null && detalleIntervalosMensualIdIntervaloAdministracionController.getSelected() == null) {
            detalleIntervalosMensualIdIntervaloAdministracionController.setSelected(this.getSelected().getDetalleIntervalosMensualIdIntervaloAdministracion());
        }
    }
}
