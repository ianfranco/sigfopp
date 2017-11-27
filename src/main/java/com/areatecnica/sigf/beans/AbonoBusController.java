package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.BusController;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.controllers.AbonoBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "abonoBusController")
@ViewScoped
public class AbonoBusController extends AbstractController<AbonoBus> {

    @Inject
    private AbonoBusFacade ejbFacade;
    @Inject
    private BusController abonoBusIdBusController;
    @Inject
    private TipoAbonoController abonoBusIdTipoAbonoController;

    /**
     * Initialize the concrete AbonoBus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AbonoBusController() {
        // Inform the Abstract parent controller of the concrete AbonoBus Entity
        super(AbonoBus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        abonoBusIdBusController.setSelected(null);
        abonoBusIdTipoAbonoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoBusIdBus(ActionEvent event) {
        if (this.getSelected() != null && abonoBusIdBusController.getSelected() == null) {
            abonoBusIdBusController.setSelected(this.getSelected().getAbonoBusIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoAbono controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoBusIdTipoAbono(ActionEvent event) {
        if (this.getSelected() != null && abonoBusIdTipoAbonoController.getSelected() == null) {
            abonoBusIdTipoAbonoController.setSelected(this.getSelected().getAbonoBusIdTipoAbono());
        }
    }

    /**
     * Sets the "items" attribute with a collection of AbonoLiquidacion entities
     * that are retrieved from AbonoBus?cap_first and returns the navigation
     * outcome.
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
