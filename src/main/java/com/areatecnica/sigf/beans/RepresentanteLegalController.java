package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.RepresentanteLegal;
import com.areatecnica.sigf.controllers.RepresentanteLegalFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "representanteLegalController")
@ViewScoped
public class RepresentanteLegalController extends AbstractController<RepresentanteLegal> {

    @Inject
    private RepresentanteLegalFacade ejbFacade;

    /**
     * Initialize the concrete RepresentanteLegal controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RepresentanteLegalController() {
        // Inform the Abstract parent controller of the concrete RepresentanteLegal Entity
        super(RepresentanteLegal.class);
    }

    /**
     * Sets the "items" attribute with a collection of RepresentanteEmpresa
     * entities that are retrieved from RepresentanteLegal?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for RepresentanteEmpresa page
     */
    public String navigateRepresentanteEmpresaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RepresentanteEmpresa_items", this.getSelected().getRepresentanteEmpresaList());
        }
        return "/representanteEmpresa/index";
    }

    @Override
    public RepresentanteLegal prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setRepresentanteLegalIdCuenta(this.getUserCount());
                
        return this.getSelected();
    }

}
