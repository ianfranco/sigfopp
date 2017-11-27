package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.MonedaPactadaInstitucionSaludController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;
import com.areatecnica.sigf.controllers.TrabajadorAdicionalSaludFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "trabajadorAdicionalSaludController")
@ViewScoped
public class TrabajadorAdicionalSaludController extends AbstractController<TrabajadorAdicionalSalud> {

    @Inject
    private TrabajadorAdicionalSaludFacade ejbFacade;
    @Inject
    private MonedaPactadaInstitucionSaludController trabajadorAdicionalSaludIdMonedaController;
    @Inject
    private TrabajadorController trabajadorAdicionalSaludIdTrabajadorController;

    /**
     * Initialize the concrete TrabajadorAdicionalSalud controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TrabajadorAdicionalSaludController() {
        // Inform the Abstract parent controller of the concrete TrabajadorAdicionalSalud Entity
        super(TrabajadorAdicionalSalud.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        trabajadorAdicionalSaludIdMonedaController.setSelected(null);
        trabajadorAdicionalSaludIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the MonedaPactadaInstitucionSalud
     * controller in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorAdicionalSaludIdMoneda(ActionEvent event) {
        if (this.getSelected() != null && trabajadorAdicionalSaludIdMonedaController.getSelected() == null) {
            trabajadorAdicionalSaludIdMonedaController.setSelected(this.getSelected().getTrabajadorAdicionalSaludIdMoneda());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorAdicionalSaludIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && trabajadorAdicionalSaludIdTrabajadorController.getSelected() == null) {
            trabajadorAdicionalSaludIdTrabajadorController.setSelected(this.getSelected().getTrabajadorAdicionalSaludIdTrabajador());
        }
    }
}
