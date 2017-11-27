package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.CajaCompensacion;
import com.areatecnica.sigf.controllers.CajaCompensacionFacade;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cajaCompensacionController")
@ViewScoped
public class CajaCompensacionController extends AbstractController<CajaCompensacion> {

    @Inject
    private CajaCompensacionFacade ejbFacade;

    /**
     * Initialize the concrete CajaCompensacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CajaCompensacionController() {
        // Inform the Abstract parent controller of the concrete CajaCompensacion Entity
        super(CajaCompensacion.class);
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from CajaCompensacion?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", this.getSelected().getEmpresaList());
        }
        return "/empresa/index";
    }

    @Override
    public CajaCompensacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCajaCompensacionIdCuenta(this.getUserCount());        
        return this.getSelected();
    }

    public void resetParents(){
        
    }
    
}
