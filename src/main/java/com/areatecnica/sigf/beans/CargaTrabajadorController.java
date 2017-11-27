package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.CargaTrabajador;
import com.areatecnica.sigf.controllers.CargaTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "cargaTrabajadorController")
@ViewScoped
public class CargaTrabajadorController extends AbstractController<CargaTrabajador> {

    @Inject
    private CargaTrabajadorFacade ejbFacade;
    @Inject
    private ParentescoCargaController cargaTrabajadorIdParentescoCargaController;
    @Inject
    private TipoCargaFamiliarController cargaTrabajadorIdTipoCargaController;
    @Inject
    private TrabajadorController cargaTrabajadorIdTrabajadorController;

    /**
     * Initialize the concrete CargaTrabajador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CargaTrabajadorController() {
        // Inform the Abstract parent controller of the concrete CargaTrabajador Entity
        super(CargaTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargaTrabajadorIdParentescoCargaController.setSelected(null);
        cargaTrabajadorIdTipoCargaController.setSelected(null);
        cargaTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the ParentescoCarga controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaTrabajadorIdParentescoCarga(ActionEvent event) {
        if (this.getSelected() != null && cargaTrabajadorIdParentescoCargaController.getSelected() == null) {
            cargaTrabajadorIdParentescoCargaController.setSelected(this.getSelected().getCargaTrabajadorIdParentesco());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCargaFamiliar controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaTrabajadorIdTipoCarga(ActionEvent event) {
        if (this.getSelected() != null && cargaTrabajadorIdTipoCargaController.getSelected() == null) {
            cargaTrabajadorIdTipoCargaController.setSelected(this.getSelected().getCargaTrabajadorIdTipo());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaTrabajadorIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && cargaTrabajadorIdTrabajadorController.getSelected() == null) {
            cargaTrabajadorIdTrabajadorController.setSelected(this.getSelected().getCargaTrabajadorIdTrabajador());
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargaRetroactiva entities
     * that are retrieved from CargaTrabajador?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CargaRetroactiva page
     */
    public String navigateCargaRetroactivaList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaRetroactiva_items", this.getSelected().getCargaRetroactivaList());
        }
        return "/cargaRetroactiva/index";
    }

}
