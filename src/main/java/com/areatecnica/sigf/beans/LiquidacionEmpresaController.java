package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.EmpresaController;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.controllers.LiquidacionEmpresaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "liquidacionEmpresaController")
@ViewScoped
public class LiquidacionEmpresaController extends AbstractController<LiquidacionEmpresa> {

    @Inject
    private LiquidacionEmpresaFacade ejbFacade;
    @Inject
    private EmpresaController liquidacionEmpresaIdEmpresaController;

    /**
     * Initialize the concrete LiquidacionEmpresa controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public LiquidacionEmpresaController() {
        // Inform the Abstract parent controller of the concrete LiquidacionEmpresa Entity
        super(LiquidacionEmpresa.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        liquidacionEmpresaIdEmpresaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of CargoLiquidacion entities
     * that are retrieved from LiquidacionEmpresa?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CargoLiquidacion page
     */
    public String navigateCargoLiquidacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoLiquidacion_items", this.getSelected().getCargoLiquidacionList());
        }
        return "/cargoLiquidacion/index";
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLiquidacionEmpresaIdEmpresa(ActionEvent event) {
        if (this.getSelected() != null && liquidacionEmpresaIdEmpresaController.getSelected() == null) {
            liquidacionEmpresaIdEmpresaController.setSelected(this.getSelected().getLiquidacionEmpresaIdEmpresa());
        }
    }

    /**
     * Sets the "items" attribute with a collection of AbonoLiquidacion entities
     * that are retrieved from LiquidacionEmpresa?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for AbonoLiquidacion page
     */
    public String navigateAbonoLiquidacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AbonoLiquidacion_items", this.getSelected().getAbonoLiquidacionList());
        }
        return "/abonoLiquidacion/index";
    }

}
