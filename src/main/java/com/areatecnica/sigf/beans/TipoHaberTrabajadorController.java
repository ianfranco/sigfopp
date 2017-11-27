package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.TipoHaberTrabajador;
import com.areatecnica.sigf.controllers.TipoHaberTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoHaberTrabajadorController")
@ViewScoped
public class TipoHaberTrabajadorController extends AbstractController<TipoHaberTrabajador> {

    @Inject
    private TipoHaberTrabajadorFacade ejbFacade;

    /**
     * Initialize the concrete TipoHaberTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoHaberTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoHaberTrabajador Entity
        super(TipoHaberTrabajador.class);
    }

    /**
     * Sets the "items" attribute with a collection of HaberTrabajador entities
     * that are retrieved from TipoHaberTrabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for HaberTrabajador page
     */
    public String navigateHaberTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberTrabajador_items", this.getSelected().getHaberTrabajadorList());
        }
        return "/haberTrabajador/index";
    }

    @Override
    public TipoHaberTrabajador prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoHaberTrabajadorIdCuenta(this.getUserCount());        
        return this.getSelected();
    }

}
