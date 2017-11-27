package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import com.areatecnica.sigf.controllers.RepresentanteEmpresaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "representanteEmpresaController")
@ViewScoped
public class RepresentanteEmpresaController extends AbstractController<RepresentanteEmpresa> {

    @Inject
    private RepresentanteEmpresaFacade ejbFacade;
    @Inject
    private EmpresaController representanteEmpresaIdEmpresaController;
    @Inject
    private RepresentanteLegalController representanteEmpresaIdRepresentanteLegalController;

    /**
     * Initialize the concrete RepresentanteEmpresa controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RepresentanteEmpresaController() {
        // Inform the Abstract parent controller of the concrete RepresentanteEmpresa Entity
        super(RepresentanteEmpresa.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        representanteEmpresaIdEmpresaController.setSelected(null);
        representanteEmpresaIdRepresentanteLegalController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRepresentanteEmpresaIdEmpresa(ActionEvent event) {
        if (this.getSelected() != null && representanteEmpresaIdEmpresaController.getSelected() == null) {
            representanteEmpresaIdEmpresaController.setSelected(this.getSelected().getRepresentanteEmpresaIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the RepresentanteLegal controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRepresentanteEmpresaIdRepresentanteLegal(ActionEvent event) {
        if (this.getSelected() != null && representanteEmpresaIdRepresentanteLegalController.getSelected() == null) {
            representanteEmpresaIdRepresentanteLegalController.setSelected(this.getSelected().getRepresentanteEmpresaIdRepresentanteLegal());
        }
    }
}
