package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TipoCuentaBancoController;
import com.areatecnica.sigf.beans.BancoController;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.controllers.CuentaBancariaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cuentaBancariaController")
@ViewScoped
public class CuentaBancariaController extends AbstractController<CuentaBancaria> {

    @Inject
    private CuentaBancariaFacade ejbFacade;
    @Inject
    private BancoController cuentaBancariaIdBancoController;
    @Inject
    private TipoCuentaBancoController cuentaBancariaIdTipoCuentaController;

    /**
     * Initialize the concrete CuentaBancaria controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CuentaBancariaController() {
        // Inform the Abstract parent controller of the concrete CuentaBancaria Entity
        super(CuentaBancaria.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaBancariaIdBancoController.setSelected(null);
        cuentaBancariaIdTipoCuentaController.setSelected(null);
    }

    

    /**
     * Sets the "selected" attribute of the Banco controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancariaIdBanco(ActionEvent event) {
        if (this.getSelected() != null && cuentaBancariaIdBancoController.getSelected() == null) {
            cuentaBancariaIdBancoController.setSelected(this.getSelected().getCuentaBancariaIdBanco());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCuentaBanco controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancariaIdTipoCuenta(ActionEvent event) {
        if (this.getSelected() != null && cuentaBancariaIdTipoCuentaController.getSelected() == null) {
            cuentaBancariaIdTipoCuentaController.setSelected(this.getSelected().getCuentaBancariaIdTipoCuenta());
        }
    }
    
}
