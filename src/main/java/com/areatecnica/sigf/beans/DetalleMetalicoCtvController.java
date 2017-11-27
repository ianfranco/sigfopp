package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.EfectivoCajaController;
import com.areatecnica.sigf.entities.DetalleMetalicoCtv;
import com.areatecnica.sigf.controllers.DetalleMetalicoCtvFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "detalleMetalicoCtvController")
@ViewScoped
public class DetalleMetalicoCtvController extends AbstractController<DetalleMetalicoCtv> {

    @Inject
    private DetalleMetalicoCtvFacade ejbFacade;
    @Inject
    private CtvResumenController detalleMetalicoCtvIdCtvResumenController;
    @Inject
    private EfectivoCajaController detalleMetalicoCtvIdEfectivoCajaController;

    /**
     * Initialize the concrete DetalleMetalicoCtv controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DetalleMetalicoCtvController() {
        // Inform the Abstract parent controller of the concrete DetalleMetalicoCtv Entity
        super(DetalleMetalicoCtv.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleMetalicoCtvIdCtvResumenController.setSelected(null);
        detalleMetalicoCtvIdEfectivoCajaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CtvResumen controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleMetalicoCtvIdCtvResumen(ActionEvent event) {
        if (this.getSelected() != null && detalleMetalicoCtvIdCtvResumenController.getSelected() == null) {
            detalleMetalicoCtvIdCtvResumenController.setSelected(this.getSelected().getDetalleMetalicoCtvIdCtvResumen());
        }
    }

    /**
     * Sets the "selected" attribute of the EfectivoCaja controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleMetalicoCtvIdEfectivoCaja(ActionEvent event) {
        if (this.getSelected() != null && detalleMetalicoCtvIdEfectivoCajaController.getSelected() == null) {
            detalleMetalicoCtvIdEfectivoCajaController.setSelected(this.getSelected().getDetalleMetalicoCtvIdEfectivoCaja());
        }
    }
}
