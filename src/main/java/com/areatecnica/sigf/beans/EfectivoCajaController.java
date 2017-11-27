package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.MetodoPagoController;
import com.areatecnica.sigf.entities.EfectivoCaja;
import com.areatecnica.sigf.controllers.EfectivoCajaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "efectivoCajaController")
@ViewScoped
public class EfectivoCajaController extends AbstractController<EfectivoCaja> {

    @Inject
    private EfectivoCajaFacade ejbFacade;
    @Inject
    private MetodoPagoController efectivoCajaIdMetodoPagoController;

    /**
     * Initialize the concrete EfectivoCaja controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EfectivoCajaController() {
        // Inform the Abstract parent controller of the concrete EfectivoCaja Entity
        super(EfectivoCaja.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        efectivoCajaIdMetodoPagoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleResumenRecaudacion
     * entities that are retrieved from EfectivoCaja?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleResumenRecaudacion page
     */
    public String navigateDetalleResumenRecaudacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleResumenRecaudacion_items", this.getSelected().getDetalleResumenRecaudacionList());
        }
        return "/detalleResumenRecaudacion/index";
    }

    /**
     * Sets the "items" attribute with a collection of DetalleMetalicoCtv
     * entities that are retrieved from EfectivoCaja?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleMetalicoCtv page
     */
    public String navigateDetalleMetalicoCtvList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleMetalicoCtv_items", this.getSelected().getDetalleMetalicoCtvList());
        }
        return "/detalleMetalicoCtv/index";
    }

    /**
     * Sets the "selected" attribute of the MetodoPago controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEfectivoCajaIdMetodoPago(ActionEvent event) {
        if (this.getSelected() != null && efectivoCajaIdMetodoPagoController.getSelected() == null) {
            efectivoCajaIdMetodoPagoController.setSelected(this.getSelected().getEfectivoCajaIdMetodoPago());
        }
    }
}
