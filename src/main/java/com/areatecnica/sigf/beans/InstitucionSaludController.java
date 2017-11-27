package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.InstitucionSalud;
import com.areatecnica.sigf.controllers.InstitucionSaludFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "institucionSaludController")
@ViewScoped
public class InstitucionSaludController extends AbstractController<InstitucionSalud> {

    @Inject
    private InstitucionSaludFacade ejbFacade;

    /**
     * Initialize the concrete InstitucionSalud controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public InstitucionSaludController() {
        // Inform the Abstract parent controller of the concrete InstitucionSalud Entity
        super(InstitucionSalud.class);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from InstitucionSalud?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", this.getSelected().getTrabajadorList());
        }
        return "/trabajador/index";
    }

    @Override
    public InstitucionSalud prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setInstitucionSaludIdCuenta(this.getUserCount());
        return this.getSelected();
    }

    public void resetParents(){
        
    }
    
}
