package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.AbonoBusController;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.controllers.AbonoLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "abonoLiquidacionController")
@ViewScoped
public class AbonoLiquidacionController extends AbstractController<AbonoLiquidacion> {

    @Inject
    private AbonoLiquidacionFacade ejbFacade;
    @Inject
    private AbonoBusController abonoLiquidacionIdAbonoController;
    @Inject
    private LiquidacionEmpresaController abonoLiquidacionIdLiquidacionEmpresaController;

    /**
     * Initialize the concrete AbonoLiquidacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AbonoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete AbonoLiquidacion Entity
        super(AbonoLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        abonoLiquidacionIdAbonoController.setSelected(null);
        abonoLiquidacionIdLiquidacionEmpresaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the AbonoBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoLiquidacionIdAbono(ActionEvent event) {
        if (this.getSelected() != null && abonoLiquidacionIdAbonoController.getSelected() == null) {
            abonoLiquidacionIdAbonoController.setSelected(this.getSelected().getAbonoLiquidacionIdAbono());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionEmpresa controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoLiquidacionIdLiquidacionEmpresa(ActionEvent event) {
        if (this.getSelected() != null && abonoLiquidacionIdLiquidacionEmpresaController.getSelected() == null) {
            abonoLiquidacionIdLiquidacionEmpresaController.setSelected(this.getSelected().getAbonoLiquidacionIdLiquidacionEmpresa());
        }
    }
}
