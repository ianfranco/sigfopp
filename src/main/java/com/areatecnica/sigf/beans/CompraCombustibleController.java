package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.CompraCombustible;
import com.areatecnica.sigf.controllers.CompraCombustibleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "compraCombustibleController")
@ViewScoped
public class CompraCombustibleController extends AbstractController<CompraCombustible> {

    @Inject
    private CompraCombustibleFacade ejbFacade;
    @Inject
    private TipoCombustibleController compraCombustibleIdTipoController;

    /**
     * Initialize the concrete CompraCombustible controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CompraCombustibleController() {
        // Inform the Abstract parent controller of the concrete CompraCombustible Entity
        super(CompraCombustible.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        compraCombustibleIdTipoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraCombustibleIdTipo(ActionEvent event) {
        if (this.getSelected() != null && compraCombustibleIdTipoController.getSelected() == null) {
            compraCombustibleIdTipoController.setSelected(this.getSelected().getCompraCombustibleIdTipo());
        }
    }

    @Override
    public CompraCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCompraCombustibleIdCuenta(this.getUserCount());
        return this.getSelected();
    }
}
