package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.HaberLiquidacion;
import com.areatecnica.sigf.controllers.HaberLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "haberLiquidacionController")
@ViewScoped
public class HaberLiquidacionController extends AbstractController<HaberLiquidacion> {

    @Inject
    private HaberLiquidacionFacade ejbFacade;
    @Inject
    private HaberTrabajadorController haberLiquidacionIdHaberController;
    @Inject
    private LiquidacionSueldoController haberLiquidacionIdLiquidacionSueldoController;

    /**
     * Initialize the concrete HaberLiquidacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public HaberLiquidacionController() {
        // Inform the Abstract parent controller of the concrete HaberLiquidacion Entity
        super(HaberLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        haberLiquidacionIdHaberController.setSelected(null);
        haberLiquidacionIdLiquidacionSueldoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the HaberTrabajador controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberLiquidacionIdHaber(ActionEvent event) {
        if (this.getSelected() != null && haberLiquidacionIdHaberController.getSelected() == null) {
            haberLiquidacionIdHaberController.setSelected(this.getSelected().getHaberLiquidacionIdHaber());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionSueldo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberLiquidacionIdLiquidacionSueldo(ActionEvent event) {
        if (this.getSelected() != null && haberLiquidacionIdLiquidacionSueldoController.getSelected() == null) {
            haberLiquidacionIdLiquidacionSueldoController.setSelected(this.getSelected().getHaberLiquidacionIdLiquidacionSueldo());
        }
    }
}
