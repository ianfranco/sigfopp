package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.controllers.EgresoCajaRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "egresoCajaTerminalController")
@ViewScoped
public class EgresoCajaTerminalController extends AbstractController<EgresoCajaRecaudacion> {

    @Inject
    private EgresoCajaRecaudacionFacade ejbFacade;
    @Inject
    private EgresoController egresoCajaTerminalIdEgresoController;
    @Inject
    private CajaRecaudacionController egresoCajaTerminalIdCajaTerminalController;

    /**
     * Initialize the concrete EgresoCajaTerminal controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EgresoCajaTerminalController() {
        // Inform the Abstract parent controller of the concrete EgresoCajaTerminal Entity
        super(EgresoCajaRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoCajaTerminalIdEgresoController.setSelected(null);
        egresoCajaTerminalIdCajaTerminalController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoCajaTerminalIdEgreso(ActionEvent event) {
        if (this.getSelected() != null && egresoCajaTerminalIdEgresoController.getSelected() == null) {
            egresoCajaTerminalIdEgresoController.setSelected(this.getSelected().getEgresoCajaRecaudacionIdEgreso());
        }
    }

    /**
     * Sets the "selected" attribute of the CajaTerminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoCajaTerminalIdCajaTerminal(ActionEvent event) {
        if (this.getSelected() != null && egresoCajaTerminalIdCajaTerminalController.getSelected() == null) {
            egresoCajaTerminalIdCajaTerminalController.setSelected(this.getSelected().getEgresoCajaRecaudacionIdCaja());
        }
    }
}
