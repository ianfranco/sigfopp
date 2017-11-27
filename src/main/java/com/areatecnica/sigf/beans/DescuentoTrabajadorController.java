package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.DescuentoTrabajador;
import com.areatecnica.sigf.controllers.DescuentoTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "descuentoTrabajadorController")
@ViewScoped
public class DescuentoTrabajadorController extends AbstractController<DescuentoTrabajador> {

    @Inject
    private DescuentoTrabajadorFacade ejbFacade;
    @Inject
    private TipoDescuentoTrabajadorController descuentoTrabajadorIdDescuentoController;
    @Inject
    private TrabajadorController descuentoTrabajadorIdTrabajadorController;

    /**
     * Initialize the concrete DescuentoTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DescuentoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete DescuentoTrabajador Entity
        super(DescuentoTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        descuentoTrabajadorIdDescuentoController.setSelected(null);
        descuentoTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoLiquidacion
     * entities that are retrieved from DescuentoTrabajador?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for DescuentoLiquidacion page
     */
    public String navigateDescuentoLiquidacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoLiquidacion_items", this.getSelected().getDescuentoLiquidacionList());
        }
        return "/descuentoLiquidacion/index";
    }

    /**
     * Sets the "selected" attribute of the TipoDescuentoTrabajador controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoTrabajadorIdDescuento(ActionEvent event) {
        if (this.getSelected() != null && descuentoTrabajadorIdDescuentoController.getSelected() == null) {
            descuentoTrabajadorIdDescuentoController.setSelected(this.getSelected().getDescuentoTrabajadorIdDescuento());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoTrabajadorIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && descuentoTrabajadorIdTrabajadorController.getSelected() == null) {
            descuentoTrabajadorIdTrabajadorController.setSelected(this.getSelected().getDescuentoTrabajadorIdTrabajador());
        }
    }
}
