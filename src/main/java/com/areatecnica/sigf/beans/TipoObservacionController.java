package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoObservacion;
import com.areatecnica.sigf.controllers.TipoObservacionFacade;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoObservacionController")
@ViewScoped
public class TipoObservacionController extends AbstractController<TipoObservacion> {

    @Inject
    private TipoObservacionFacade ejbFacade;

    /**
     * Initialize the concrete TipoObservacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoObservacionController() {
        // Inform the Abstract parent controller of the concrete TipoObservacion Entity
        super(TipoObservacion.class);
    }

    /**
     * Sets the "items" attribute with a collection of ObservacionTrabajador
     * entities that are retrieved from TipoObservacion?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for ObservacionTrabajador page
     */
    public String navigateObservacionTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ObservacionTrabajador_items", this.getSelected().getObservacionTrabajadorList());
        }
        return "/observacionTrabajador/index";
    }

    @Override
    public TipoObservacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoObservacionIdCuenta(this.getUserCount());        
        return this.getSelected();
    }

}
