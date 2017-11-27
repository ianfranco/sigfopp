package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;
import com.areatecnica.sigf.controllers.FiniquitoRelacionLaboralFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "finiquitoRelacionLaboralController")
@ViewScoped
public class FiniquitoRelacionLaboralController extends AbstractController<FiniquitoRelacionLaboral> {

    @Inject
    private FiniquitoRelacionLaboralFacade ejbFacade;
    @Inject
    private CausalFiniquitoController finiquitoRelacionLaboralIdCausalFiniquitoController;
    @Inject
    private RelacionLaboralController finiquitoRelacionLaboralIdRelacionLaboralController;

    /**
     * Initialize the concrete FiniquitoRelacionLaboral controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public FiniquitoRelacionLaboralController() {
        // Inform the Abstract parent controller of the concrete FiniquitoRelacionLaboral Entity
        super(FiniquitoRelacionLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        finiquitoRelacionLaboralIdCausalFiniquitoController.setSelected(null);
        finiquitoRelacionLaboralIdRelacionLaboralController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CausalFiniquito controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFiniquitoRelacionLaboralIdCausalFiniquito(ActionEvent event) {
        if (this.getSelected() != null && finiquitoRelacionLaboralIdCausalFiniquitoController.getSelected() == null) {
            finiquitoRelacionLaboralIdCausalFiniquitoController.setSelected(this.getSelected().getFiniquitoRelacionLaboralIdCausalFiniquito());
        }
    }

    /**
     * Sets the "selected" attribute of the RelacionLaboral controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFiniquitoRelacionLaboralIdRelacionLaboral(ActionEvent event) {
        if (this.getSelected() != null && finiquitoRelacionLaboralIdRelacionLaboralController.getSelected() == null) {
            finiquitoRelacionLaboralIdRelacionLaboralController.setSelected(this.getSelected().getFiniquitoRelacionLaboralIdRelacionLaboral());
        }
    }
}
