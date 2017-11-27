package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.controllers.HaberTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "haberTrabajadorController")
@ViewScoped
public class HaberTrabajadorController extends AbstractController<HaberTrabajador> {

    @Inject
    private HaberTrabajadorFacade ejbFacade;
    @Inject
    private TipoHaberTrabajadorController haberTrabajadorIdHaberController;
    @Inject
    private TrabajadorController haberTrabajadorIdTrabajadorController;

    /**
     * Initialize the concrete HaberTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public HaberTrabajadorController() {
        // Inform the Abstract parent controller of the concrete HaberTrabajador Entity
        super(HaberTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        haberTrabajadorIdHaberController.setSelected(null);
        haberTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoHaberTrabajador controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberTrabajadorIdHaber(ActionEvent event) {
        if (this.getSelected() != null && haberTrabajadorIdHaberController.getSelected() == null) {
            haberTrabajadorIdHaberController.setSelected(this.getSelected().getHaberTrabajadorIdHaber());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberTrabajadorIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && haberTrabajadorIdTrabajadorController.getSelected() == null) {
            haberTrabajadorIdTrabajadorController.setSelected(this.getSelected().getHaberTrabajadorIdTrabajador());
        }
    }

    /**
     * Sets the "items" attribute with a collection of HaberLiquidacion entities
     * that are retrieved from HaberTrabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for HaberLiquidacion page
     */
    public String navigateHaberLiquidacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberLiquidacion_items", this.getSelected().getHaberLiquidacionList());
        }
        return "/haberLiquidacion/index";
    }

}
