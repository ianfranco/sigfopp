package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.DepartamentoController;
import com.areatecnica.sigf.entities.GastoAdministracionMensual;
import com.areatecnica.sigf.controllers.GastoAdministracionMensualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "gastoAdministracionMensualController")
@ViewScoped
public class GastoAdministracionMensualController extends AbstractController<GastoAdministracionMensual> {

    @Inject
    private GastoAdministracionMensualFacade ejbFacade;
    @Inject
    private DepartamentoController gastoAdministracionMensualIdDepartamentoController;

    /**
     * Initialize the concrete GastoAdministracionMensual controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public GastoAdministracionMensualController() {
        // Inform the Abstract parent controller of the concrete GastoAdministracionMensual Entity
        super(GastoAdministracionMensual.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        gastoAdministracionMensualIdDepartamentoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGastoAdministracionMensualIdDepartamento(ActionEvent event) {
        if (this.getSelected() != null && gastoAdministracionMensualIdDepartamentoController.getSelected() == null) {
            gastoAdministracionMensualIdDepartamentoController.setSelected(this.getSelected().getGastoAdministracionMensualIdDepartamento());
        }
    }
}
