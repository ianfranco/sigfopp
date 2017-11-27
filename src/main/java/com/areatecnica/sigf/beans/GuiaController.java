package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.controllers.GuiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "guiaController")
@ViewScoped
public class GuiaController extends AbstractController<Guia> {

    @Inject
    private GuiaFacade ejbFacade;
    @Inject
    private BusController guiaIdBusController;
    @Inject
    private CajaRecaudacionController guiaIdCajaTerminalController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    /**
     * Initialize the concrete Guia controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public GuiaController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        guiaIdBusController.setSelected(null);
        guiaIdCajaTerminalController.setSelected(null);
        guiaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of SerieBoletoGuia entities
     * that are retrieved from Guia?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for SerieBoletoGuia page
     */
    public String navigateRegistroBoletoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SerieBoletoGuia_items", this.getSelected().getRegistroBoletoList());
        }
        return "/serieBoletoGuia/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdBus(ActionEvent event) {
        if (this.getSelected() != null && guiaIdBusController.getSelected() == null) {
            guiaIdBusController.setSelected(this.getSelected().getGuiaIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && guiaIdTrabajadorController.getSelected() == null) {
            guiaIdTrabajadorController.setSelected(this.getSelected().getGuiaIdTrabajador());
        }
    }

}
