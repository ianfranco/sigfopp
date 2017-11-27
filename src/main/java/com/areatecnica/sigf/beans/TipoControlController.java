package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoControl;
import com.areatecnica.sigf.controllers.TipoControlFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoControlController")
@ViewScoped
public class TipoControlController extends AbstractController<TipoControl> {

    @Inject
    private TipoControlFacade ejbFacade;

    /**
     * Initialize the concrete TipoControl controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoControlController() {
        // Inform the Abstract parent controller of the concrete TipoControl Entity
        super(TipoControl.class);
    }

    /**
     * Sets the "items" attribute with a collection of Control entities that are
     * retrieved from TipoControl?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Control page
     */
    public String navigateControlList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Control_items", this.getSelected().getControlList());
        }
        return "/control/index";
    }

}
