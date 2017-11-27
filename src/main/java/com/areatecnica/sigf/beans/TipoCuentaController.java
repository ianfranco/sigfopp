package com.areatecnica.sigf.beans;


import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.controllers.TipoCuentaFacade;
import com.areatecnica.sigf.entities.TipoCuenta;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoCuentaController")
@ViewScoped
public class TipoCuentaController extends AbstractController<TipoCuenta> {

    @Inject
    private TipoCuentaFacade ejbFacade;
    
    /**
     * Initialize the concrete TipoCuenta controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCuentaController() {
        // Inform the Abstract parent controller of the concrete TipoCuenta Entity
        super(TipoCuenta.class);
    }

    /**
     * Sets the "items" attribute with a collection of ObservacionTipoCuenta
     * entities that are retrieved from TipoCuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ObservacionTipoCuenta page
     */
    public String navigateObservacionTipoCuentaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ObservacionTipoCuenta_items", this.getSelected().getObservacionTipoCuentaList());
        }
        return "//observacionTipoCuenta/index";
    }

    /**
     * Sets the "items" attribute with a collection of Cuenta entities that are
     * retrieved from TipoCuenta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Cuenta page
     */
    public String navigateCuentaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cuenta_items", this.getSelected().getCuentaList());
        }
        return "//cuenta/index";
    }

}
