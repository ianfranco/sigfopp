package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.controllers.TipoCargoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoCargoController")
@ViewScoped
public class TipoCargoController extends AbstractController<TipoCargo> {

    @Inject
    private TipoCargoFacade ejbFacade;

    /**
     * Initialize the concrete TipoCargo controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCargoController() {
        // Inform the Abstract parent controller of the concrete TipoCargo Entity
        super(TipoCargo.class);
    }

    /**
     * Sets the "items" attribute with a collection of CargoBus entities that
     * are retrieved from TipoCargo?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargoBus page
     */
    public String navigateCargoBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargoBus_items", this.getSelected().getCargoBusList());
        }
        return "/cargoBus/index";
    }

    @Override
    public TipoCargo prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoCargoIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
