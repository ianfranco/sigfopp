package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import com.areatecnica.sigf.controllers.DescuentoLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "descuentoLiquidacionController")
@ViewScoped
public class DescuentoLiquidacionController extends AbstractController<DescuentoLiquidacion> {

    @Inject
    private DescuentoLiquidacionFacade ejbFacade;
    @Inject
    private DescuentoTrabajadorController descuentoLiquidacionIdDescuentoTrabajadorController;
    @Inject
    private LiquidacionSueldoController descuentoLiquidacionIdLiquidacionSueldoController;

    /**
     * Initialize the concrete DescuentoLiquidacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DescuentoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete DescuentoLiquidacion Entity
        super(DescuentoLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        descuentoLiquidacionIdDescuentoTrabajadorController.setSelected(null);
        descuentoLiquidacionIdLiquidacionSueldoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the DescuentoTrabajador controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoLiquidacionIdDescuentoTrabajador(ActionEvent event) {
        if (this.getSelected() != null && descuentoLiquidacionIdDescuentoTrabajadorController.getSelected() == null) {
            descuentoLiquidacionIdDescuentoTrabajadorController.setSelected(this.getSelected().getDescuentoLiquidacionIdDescuentoTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionSueldo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoLiquidacionIdLiquidacionSueldo(ActionEvent event) {
        if (this.getSelected() != null && descuentoLiquidacionIdLiquidacionSueldoController.getSelected() == null) {
            descuentoLiquidacionIdLiquidacionSueldoController.setSelected(this.getSelected().getDescuentoLiquidacionIdLiquidacionSueldo());
        }
    }
}
