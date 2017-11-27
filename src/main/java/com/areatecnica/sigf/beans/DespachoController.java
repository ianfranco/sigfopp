package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.ServicioController;
import com.areatecnica.sigf.beans.UsuarioController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.BusController;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.controllers.DespachoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "despachoController")
@ViewScoped
public class DespachoController extends AbstractController<Despacho> {

    @Inject
    private DespachoFacade ejbFacade;
    @Inject
    private BusController despachoIdBusController;
    @Inject
    private TrabajadorController despachoIdTrabajadorController;
    @Inject
    private UsuarioController despachoIdInspectorController;
    @Inject
    private ServicioController despachoIdServicioController;

    /**
     * Initialize the concrete Despacho controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DespachoController() {
        // Inform the Abstract parent controller of the concrete Despacho Entity
        super(Despacho.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        despachoIdBusController.setSelected(null);
        despachoIdTrabajadorController.setSelected(null);
        despachoIdInspectorController.setSelected(null);
        despachoIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdBus(ActionEvent event) {
        if (this.getSelected() != null && despachoIdBusController.getSelected() == null) {
            despachoIdBusController.setSelected(this.getSelected().getDespachoIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && despachoIdTrabajadorController.getSelected() == null) {
            despachoIdTrabajadorController.setSelected(this.getSelected().getDespachoIdTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdInspector(ActionEvent event) {
        if (this.getSelected() != null && despachoIdInspectorController.getSelected() == null) {
            despachoIdInspectorController.setSelected(this.getSelected().getDespachoIdInspector());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoIdServicio(ActionEvent event) {
        if (this.getSelected() != null && despachoIdServicioController.getSelected() == null) {
            despachoIdServicioController.setSelected(this.getSelected().getDespachoIdServicio());
        }
    }
}
