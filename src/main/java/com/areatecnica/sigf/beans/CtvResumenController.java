package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.ResumenRecaudacionController;
import com.areatecnica.sigf.entities.CtvResumen;
import com.areatecnica.sigf.controllers.CtvResumenFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "ctvResumenController")
@ViewScoped
public class CtvResumenController extends AbstractController<CtvResumen> {

    @Inject
    private CtvResumenFacade ejbFacade;
    @Inject
    private ResumenRecaudacionController ctvResumenIdResumenRecaudacionController;

    /**
     * Initialize the concrete CtvResumen controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CtvResumenController() {
        // Inform the Abstract parent controller of the concrete CtvResumen Entity
        super(CtvResumen.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ctvResumenIdResumenRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleMetalicoCtv
     * entities that are retrieved from CtvResumen?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleMetalicoCtv page
     */
    public String navigateDetalleMetalicoCtvList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleMetalicoCtv_items", this.getSelected().getDetalleMetalicoCtvList());
        }
        return "/detalleMetalicoCtv/index";
    }

    /**
     * Sets the "items" attribute with a collection of DetalleBilletesCtv
     * entities that are retrieved from CtvResumen?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleBilletesCtv page
     */
    public String navigateDetalleBilletesCtvList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleBilletesCtv_items", this.getSelected().getDetalleBilletesCtvList());
        }
        return "/detalleBilletesCtv/index";
    }

    /**
     * Sets the "selected" attribute of the ResumenRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCtvResumenIdResumenRecaudacion(ActionEvent event) {
        if (this.getSelected() != null && ctvResumenIdResumenRecaudacionController.getSelected() == null) {
            ctvResumenIdResumenRecaudacionController.setSelected(this.getSelected().getCtvResumenIdResumenRecaudacion());
        }
    }
}
