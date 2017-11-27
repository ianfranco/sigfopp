package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.CajaRecaudacionController;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.controllers.ResumenRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "resumenRecaudacionController")
@ViewScoped
public class ResumenRecaudacionController extends AbstractController<ResumenRecaudacion> {

    @Inject
    private ResumenRecaudacionFacade ejbFacade;
    @Inject
    private CajaRecaudacionController resumenRecaudacionIdCajaController;

    /**
     * Initialize the concrete ResumenRecaudacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ResumenRecaudacionController() {
        // Inform the Abstract parent controller of the concrete ResumenRecaudacion Entity
        super(ResumenRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        resumenRecaudacionIdCajaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleResumenRecaudacion
     * entities that are retrieved from ResumenRecaudacion?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for DetalleResumenRecaudacion page
     */
    public String navigateDetalleResumenRecaudacionList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleResumenRecaudacion_items", this.getSelected().getDetalleResumenRecaudacionList());
        }
        return "/detalleResumenRecaudacion/index";
    }

    /**
     * Sets the "selected" attribute of the CajaTerminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareResumenRecaudacionIdCaja(ActionEvent event) {
        if (this.getSelected() != null && resumenRecaudacionIdCajaController.getSelected() == null) {
            resumenRecaudacionIdCajaController.setSelected(this.getSelected().getResumenRecaudacionIdCaja());
        }
    }

    /**
     * Sets the "items" attribute with a collection of CtvResumen entities that
     * are retrieved from ResumenRecaudacion?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CtvResumen page
     */
    public String navigateCtvResumenList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CtvResumen_items", this.getSelected().getCtvResumenList());
        }
        return "/ctvResumen/index";
    }

}
