package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.controllers.InstitucionApvFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "institucionApvController")
@ViewScoped
public class InstitucionApvController extends AbstractController<InstitucionApv> {

    @Inject
    private InstitucionApvFacade ejbFacade;
    @Inject
    private TipoInstitucionApvController institucionApvIdTipoController;

    /**
     * Initialize the concrete InstitucionApv controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public InstitucionApvController() {
        // Inform the Abstract parent controller of the concrete InstitucionApv Entity
        super(InstitucionApv.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        institucionApvIdTipoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from InstitucionApv?cap_first and returns the navigation
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

    /**
     * Sets the "selected" attribute of the TipoInstitucionApv controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInstitucionApvIdTipo(ActionEvent event) {
        if (this.getSelected() != null && institucionApvIdTipoController.getSelected() == null) {
            institucionApvIdTipoController.setSelected(this.getSelected().getInstitucionApvIdTipo());
        }
    }

    @Override
    public InstitucionApv prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setInstitucionApvIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }
}
