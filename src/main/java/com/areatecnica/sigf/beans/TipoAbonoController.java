package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.controllers.TipoAbonoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoAbonoController")
@ViewScoped
public class TipoAbonoController extends AbstractController<TipoAbono> {

    @Inject
    private TipoAbonoFacade ejbFacade;

    /**
     * Initialize the concrete TipoAbono controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoAbonoController() {
        // Inform the Abstract parent controller of the concrete TipoAbono Entity
        super(TipoAbono.class);
    }

    /**
     * Sets the "items" attribute with a collection of AbonoBus entities that
     * are retrieved from TipoAbono?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for AbonoBus page
     */
    public String navigateAbonoBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AbonoBus_items", this.getSelected().getAbonoBusList());
        }
        return "/abonoBus/index";
    }

    @Override
    public TipoAbono prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoAbonoIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
