package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.controllers.VentaBoletoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "ventaBoletoController")
@ViewScoped
public class VentaBoletoController extends AbstractController<VentaBoleto> {

    @Inject
    private VentaBoletoFacade ejbFacade;
    @Inject
    private GuiaController ventaBoletoIdGuiaController;
    @Inject
    private InventarioCajaController ventaBoletoIdInventarioCajaController;

    /**
     * Initialize the concrete VentaBoleto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public VentaBoletoController() {
        // Inform the Abstract parent controller of the concrete VentaBoleto Entity
        super(VentaBoleto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaBoletoIdGuiaController.setSelected(null);
        ventaBoletoIdInventarioCajaController.setSelected(null);
    }
    
    /**
     * Sets the "selected" attribute of the InventarioCaja controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdInventarioCaja(ActionEvent event) {
        if (this.getSelected() != null && ventaBoletoIdInventarioCajaController.getSelected() == null) {
            ventaBoletoIdInventarioCajaController.setSelected(this.getSelected().getVentaBoletoIdInventarioCaja());
        }
    }
}
