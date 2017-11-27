package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.ValorRolloUnidad;
import com.areatecnica.sigf.controllers.ValorRolloUnidadFacade;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "valorRolloUnidadController")
@ViewScoped
public class ValorRolloUnidadController extends AbstractController<ValorRolloUnidad> {

    @Inject
    private ValorRolloUnidadFacade ejbFacade;
    @Inject
    private UnidadNegocioController valorRolloUnidadIdUnidadController;

    /**
     * Initialize the concrete ValorRolloUnidad controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ValorRolloUnidadController() {
        // Inform the Abstract parent controller of the concrete ValorRolloUnidad Entity
        super(ValorRolloUnidad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        valorRolloUnidadIdUnidadController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareValorRolloUnidadIdUnidad(ActionEvent event) {
        if (this.getSelected() != null && valorRolloUnidadIdUnidadController.getSelected() == null) {
            valorRolloUnidadIdUnidadController.setSelected(this.getSelected().getValorRolloUnidadIdUnidad());
        }
    }

    @Override
    public ValorRolloUnidad prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.        
        return this.getSelected();
    }
    
    
}
