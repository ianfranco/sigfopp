package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.controllers.PrecioCombustibleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "precioCombustibleController")
@ViewScoped
public class PrecioCombustibleController extends AbstractController<PrecioCombustible> {

    @Inject
    private PrecioCombustibleFacade ejbFacade;
    @Inject
    private TipoCombustibleController precioCombustibleIdTipoCombustibleController;

    /**
     * Initialize the concrete PrecioCombustible controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PrecioCombustibleController() {
        // Inform the Abstract parent controller of the concrete PrecioCombustible Entity
        super(PrecioCombustible.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("PrecioCombustible.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        precioCombustibleIdTipoCombustibleController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePrecioCombustibleIdTipoCombustible(ActionEvent event) {
        if (this.getSelected() != null && precioCombustibleIdTipoCombustibleController.getSelected() == null) {
            precioCombustibleIdTipoCombustibleController.setSelected(this.getSelected().getPrecioCombustibleIdTipoCombustible());
        }
    }

    @Override
    public PrecioCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setPrecioCombustibleIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }
}
