package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoCargaFamiliar;
import com.areatecnica.sigf.controllers.TipoCargaFamiliarFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoCargaFamiliarController")
@ViewScoped
public class TipoCargaFamiliarController extends AbstractController<TipoCargaFamiliar> {

    @Inject
    private TipoCargaFamiliarFacade ejbFacade;

    /**
     * Initialize the concrete TipoCargaFamiliar controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCargaFamiliarController() {
        // Inform the Abstract parent controller of the concrete TipoCargaFamiliar Entity
        super(TipoCargaFamiliar.class);
    }

    /**
     * Sets the "items" attribute with a collection of CargaTrabajador entities
     * that are retrieved from TipoCargaFamiliar?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CargaTrabajador page
     */
    public String navigateCargaTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaTrabajador_items", this.getSelected().getCargaTrabajadorList());
        }
        return "/cargaTrabajador/index";
    }

}
