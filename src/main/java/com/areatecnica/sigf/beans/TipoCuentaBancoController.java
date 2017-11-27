package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoCuentaBanco;
import com.areatecnica.sigf.controllers.TipoCuentaBancoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoCuentaBancoController")
@ViewScoped
public class TipoCuentaBancoController extends AbstractController<TipoCuentaBanco> {

    @Inject
    private TipoCuentaBancoFacade ejbFacade;

    /**
     * Initialize the concrete TipoCuentaBanco controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCuentaBancoController() {
        // Inform the Abstract parent controller of the concrete TipoCuentaBanco Entity
        super(TipoCuentaBanco.class);
    }

    /**
     * Sets the "items" attribute with a collection of CuentaBancaria entities
     * that are retrieved from TipoCuentaBanco?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CuentaBancaria page
     */
    public String navigateCuentaBancariaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancaria_items", this.getSelected().getCuentaBancariaList());
        }
        return "/cuentaBancaria/index";
    }

}
