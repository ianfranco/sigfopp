package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.InstitucionPrevision;
import com.areatecnica.sigf.controllers.InstitucionPrevisionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "institucionPrevisionController")
@ViewScoped
public class InstitucionPrevisionController extends AbstractController<InstitucionPrevision> {

    @Inject
    private InstitucionPrevisionFacade ejbFacade;

    /**
     * Initialize the concrete InstitucionPrevision controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public InstitucionPrevisionController() {
        // Inform the Abstract parent controller of the concrete InstitucionPrevision Entity
        super(InstitucionPrevision.class);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from InstitucionPrevision?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", this.getSelected().getTrabajadorList());
        }
        return "/trabajador/index";
    }
    
    public void resetParents(){
        
    }

    @Override
    public InstitucionPrevision prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setInstitucionPrevisionIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
