package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.MarcaBus;
import com.areatecnica.sigf.controllers.MarcaBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "marcaBusController")
@ViewScoped
public class MarcaBusController extends AbstractController<MarcaBus> {

    @Inject
    private MarcaBusFacade ejbFacade;

    /**
     * Initialize the concrete MarcaBus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public MarcaBusController() {
        // Inform the Abstract parent controller of the concrete MarcaBus Entity
        super(MarcaBus.class);
    }

    /**
     * Sets the "items" attribute with a collection of ModeloMarcaBus entities
     * that are retrieved from MarcaBus?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ModeloMarcaBus page
     */
    public String navigateModeloMarcaBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ModeloMarcaBus_items", this.getSelected().getModeloMarcaBusList());
        }
        return "/modeloMarcaBus/index";
    }
    
    public void resetParents(){
        
    }

    @Override
    public MarcaBus prepareCreate(ActionEvent event) {        
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        return this.getSelected();
    }

    
    
}
