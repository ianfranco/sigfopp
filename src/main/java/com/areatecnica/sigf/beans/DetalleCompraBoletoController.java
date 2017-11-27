package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.BoletoController;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.controllers.DetalleCompraBoletoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "detalleCompraBoletoController")
@ViewScoped
public class DetalleCompraBoletoController extends AbstractController<DetalleCompraBoleto> {

    @Inject
    private DetalleCompraBoletoFacade ejbFacade;
    @Inject
    private BoletoController detalleCompraBoletoIdBoletoController;
    @Inject
    private CompraBoletoController detalleCompraBoletoIdCompraBoletoController;

    /**
     * Initialize the concrete DetalleCompraBoleto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DetalleCompraBoletoController() {
        // Inform the Abstract parent controller of the concrete DetalleCompraBoleto Entity
        super(DetalleCompraBoleto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleCompraBoletoIdBoletoController.setSelected(null);
        detalleCompraBoletoIdCompraBoletoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleCompraBoletoIdBoleto(ActionEvent event) {
        if (this.getSelected() != null && detalleCompraBoletoIdBoletoController.getSelected() == null) {
            detalleCompraBoletoIdBoletoController.setSelected(this.getSelected().getDetalleCompraBoletoIdBoleto());
        }
    }

    /**
     * Sets the "selected" attribute of the CompraBoleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleCompraBoletoIdCompraBoleto(ActionEvent event) {
        if (this.getSelected() != null && detalleCompraBoletoIdCompraBoletoController.getSelected() == null) {
            detalleCompraBoletoIdCompraBoletoController.setSelected(this.getSelected().getDetalleCompraBoletoIdCompraBoleto());
        }
    }
}
