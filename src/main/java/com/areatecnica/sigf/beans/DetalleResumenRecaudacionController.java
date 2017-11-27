package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.EfectivoCajaController;
import com.areatecnica.sigf.beans.ResumenRecaudacionController;
import com.areatecnica.sigf.entities.DetalleResumenRecaudacion;
import com.areatecnica.sigf.controllers.DetalleResumenRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "detalleResumenRecaudacionController")
@ViewScoped
public class DetalleResumenRecaudacionController extends AbstractController<DetalleResumenRecaudacion> {

    @Inject
    private DetalleResumenRecaudacionFacade ejbFacade;
    @Inject
    private EfectivoCajaController detalleResumenRecaudacionIdEfectivoCajaController;
    @Inject
    private ResumenRecaudacionController detalleResumenRecaudacionIdResumenController;

    /**
     * Initialize the concrete DetalleResumenRecaudacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DetalleResumenRecaudacionController() {
        // Inform the Abstract parent controller of the concrete DetalleResumenRecaudacion Entity
        super(DetalleResumenRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleResumenRecaudacionIdEfectivoCajaController.setSelected(null);
        detalleResumenRecaudacionIdResumenController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the EfectivoCaja controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleResumenRecaudacionIdEfectivoCaja(ActionEvent event) {
        if (this.getSelected() != null && detalleResumenRecaudacionIdEfectivoCajaController.getSelected() == null) {
            detalleResumenRecaudacionIdEfectivoCajaController.setSelected(this.getSelected().getDetalleResumenRecaudacionIdEfectivoCaja());
        }
    }

    /**
     * Sets the "selected" attribute of the ResumenRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleResumenRecaudacionIdResumen(ActionEvent event) {
        if (this.getSelected() != null && detalleResumenRecaudacionIdResumenController.getSelected() == null) {
            detalleResumenRecaudacionIdResumenController.setSelected(this.getSelected().getDetalleResumenRecaudacionIdResumen());
        }
    }
}
