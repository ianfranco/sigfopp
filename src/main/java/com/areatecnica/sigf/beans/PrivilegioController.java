package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.controllers.PrivilegioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "privilegioController")
@ViewScoped
public class PrivilegioController extends AbstractController<Privilegio> {

    @Inject
    private PrivilegioFacade ejbFacade;

    /**
     * Initialize the concrete Privilegio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PrivilegioController() {
        // Inform the Abstract parent controller of the concrete Privilegio Entity
        super(Privilegio.class);
    }
   

    /**
     * Sets the "items" attribute with a collection of Log entities that are
     * retrieved from Privilegio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Log page
     */
    public String navigateLogList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Log_items", this.getSelected().getLogList());
        }
        return "/log/index";
    }

}
