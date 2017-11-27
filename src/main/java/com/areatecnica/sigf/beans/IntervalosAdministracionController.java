package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.IntervalosAdministracion;
import com.areatecnica.sigf.controllers.IntervalosAdministracionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "intervalosAdministracionController")
@ViewScoped
public class IntervalosAdministracionController extends AbstractController<IntervalosAdministracion> {

    @Inject
    private IntervalosAdministracionFacade ejbFacade;

    /**
     * Initialize the concrete IntervalosAdministracion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public IntervalosAdministracionController() {
        // Inform the Abstract parent controller of the concrete IntervalosAdministracion Entity
        super(IntervalosAdministracion.class);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleIntervalosMensual
     * entities that are retrieved from IntervalosAdministracion?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for DetalleIntervalosMensual page
     */
    public String navigateDetalleIntervalosMensualList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleIntervalosMensual_items", this.getSelected().getDetalleIntervalosMensualList());
        }
        return "/detalleIntervalosMensual/index";
    }

    @Override
    public IntervalosAdministracion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setIntervalosAdministracionIdCuenta(this.getUserCount());
        return this.getSelected();
    }
    
    public void resetParents(){
        
    }

}
