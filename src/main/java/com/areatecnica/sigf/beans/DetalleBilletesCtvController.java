package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.DetalleBilletesCtv;
import com.areatecnica.sigf.controllers.DetalleBilletesCtvFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "detalleBilletesCtvController")
@ViewScoped
public class DetalleBilletesCtvController extends AbstractController<DetalleBilletesCtv> {

    @Inject
    private DetalleBilletesCtvFacade ejbFacade;
    @Inject
    private CtvResumenController detalleBilletesCtvIdCtvResumenController;

    /**
     * Initialize the concrete DetalleBilletesCtv controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DetalleBilletesCtvController() {
        // Inform the Abstract parent controller of the concrete DetalleBilletesCtv Entity
        super(DetalleBilletesCtv.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleBilletesCtvIdCtvResumenController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CtvResumen controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleBilletesCtvIdCtvResumen(ActionEvent event) {
        if (this.getSelected() != null && detalleBilletesCtvIdCtvResumenController.getSelected() == null) {
            detalleBilletesCtvIdCtvResumenController.setSelected(this.getSelected().getDetalleBilletesCtvIdCtvResumen());
        }
    }
}
