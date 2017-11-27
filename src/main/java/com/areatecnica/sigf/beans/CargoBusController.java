package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.BusController;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.controllers.CargoBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cargoBusController")
@ViewScoped
public class CargoBusController extends AbstractController<CargoBus> {

    @Inject
    private CargoBusFacade ejbFacade;
    @Inject
    private BusController cargoBusIdBusController;
    @Inject
    private TipoCargoController cargoBusIdTipoCargoController;

    /**
     * Initialize the concrete CargoBus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CargoBusController() {
        // Inform the Abstract parent controller of the concrete CargoBus Entity
        super(CargoBus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargoBusIdBusController.setSelected(null);
        cargoBusIdTipoCargoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of CargoLiquidacion entities
     * that are retrieved from CargoBus?cap_first and returns the navigation
     * outcome.
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
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoBusIdBus(ActionEvent event) {
        if (this.getSelected() != null && cargoBusIdBusController.getSelected() == null) {
            cargoBusIdBusController.setSelected(this.getSelected().getCargoBusIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCargo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoBusIdTipoCargo(ActionEvent event) {
        if (this.getSelected() != null && cargoBusIdTipoCargoController.getSelected() == null) {
            cargoBusIdTipoCargoController.setSelected(this.getSelected().getCargoBusIdTipo());
        }
    }
}
