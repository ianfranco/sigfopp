package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.ModeloMarcaBus;
import com.areatecnica.sigf.controllers.ModeloMarcaBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "modeloMarcaBusController")
@ViewScoped
public class ModeloMarcaBusController extends AbstractController<ModeloMarcaBus> {

    @Inject
    private ModeloMarcaBusFacade ejbFacade;
    @Inject
    private MarcaBusController modeloMarcaBusIdMarcaController;

    /**
     * Initialize the concrete ModeloMarcaBus controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ModeloMarcaBusController() {
        // Inform the Abstract parent controller of the concrete ModeloMarcaBus Entity
        super(ModeloMarcaBus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        modeloMarcaBusIdMarcaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the MarcaBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareModeloMarcaBusIdMarca(ActionEvent event) {
        if (this.getSelected() != null && modeloMarcaBusIdMarcaController.getSelected() == null) {
            modeloMarcaBusIdMarcaController.setSelected(this.getSelected().getModeloMarcaBusIdMarca());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from ModeloMarcaBus?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "/bus/index";
    }

    @Override
    public ModeloMarcaBus prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        return this.getSelected();
    }

}
