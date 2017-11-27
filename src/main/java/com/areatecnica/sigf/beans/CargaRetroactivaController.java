package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.CargaRetroactiva;
import com.areatecnica.sigf.controllers.CargaRetroactivaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cargaRetroactivaController")
@ViewScoped
public class CargaRetroactivaController extends AbstractController<CargaRetroactiva> {

    @Inject
    private CargaRetroactivaFacade ejbFacade;
    @Inject
    private CargaTrabajadorController cargaRetroactivaIdCargaTrabajadorController;
    @Inject
    private TrabajadorController cargaRetroactivaIdTrabajadorController;

    /**
     * Initialize the concrete CargaRetroactiva controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CargaRetroactivaController() {
        // Inform the Abstract parent controller of the concrete CargaRetroactiva Entity
        super(CargaRetroactiva.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargaRetroactivaIdCargaTrabajadorController.setSelected(null);
        cargaRetroactivaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CargaTrabajador controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaRetroactivaIdCargaTrabajador(ActionEvent event) {
        if (this.getSelected() != null && cargaRetroactivaIdCargaTrabajadorController.getSelected() == null) {
            cargaRetroactivaIdCargaTrabajadorController.setSelected(this.getSelected().getCargaRetroactivaIdCargaTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaRetroactivaIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && cargaRetroactivaIdTrabajadorController.getSelected() == null) {
            cargaRetroactivaIdTrabajadorController.setSelected(this.getSelected().getCargaRetroactivaIdTrabajador());
        }
    }
}
