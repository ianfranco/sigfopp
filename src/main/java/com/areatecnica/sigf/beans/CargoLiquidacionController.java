package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.controllers.CargoLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cargoLiquidacionController")
@ViewScoped
public class CargoLiquidacionController extends AbstractController<CargoLiquidacion> {

    @Inject
    private CargoLiquidacionFacade ejbFacade;
    @Inject
    private CargoBusController cargoLiquidacionIdCargoController;
    @Inject
    private LiquidacionEmpresaController cargoLiquidacionIdLiquidacionEmpresaController;

    /**
     * Initialize the concrete CargoLiquidacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CargoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete CargoLiquidacion Entity
        super(CargoLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargoLiquidacionIdCargoController.setSelected(null);
        cargoLiquidacionIdLiquidacionEmpresaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CargoBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoLiquidacionIdCargo(ActionEvent event) {
        if (this.getSelected() != null && cargoLiquidacionIdCargoController.getSelected() == null) {
            cargoLiquidacionIdCargoController.setSelected(this.getSelected().getCargoLiquidacionIdCargo());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionEmpresa controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoLiquidacionIdLiquidacionEmpresa(ActionEvent event) {
        if (this.getSelected() != null && cargoLiquidacionIdLiquidacionEmpresaController.getSelected() == null) {
            cargoLiquidacionIdLiquidacionEmpresaController.setSelected(this.getSelected().getCargoLiquidacionIdLiquidacion());
        }
    }
}
