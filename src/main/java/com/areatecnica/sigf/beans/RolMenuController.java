package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.controllers.RolMenuFacade;
import com.areatecnica.sigf.entities.RolMenu;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "rolMenuController")
@ViewScoped
public class RolMenuController extends AbstractController<RolMenu> {

    @Inject
    private RolMenuFacade ejbFacade;
    @Inject
    private PrivilegioController rolPrivilegioIdPrivilegioController;
    @Inject
    private RolController rolPrivilegioIdRolController;

    /**
     * Initialize the concrete RolPrivilegio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RolMenuController() {
        // Inform the Abstract parent controller of the concrete RolPrivilegio Entity
        super(RolMenu.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        rolPrivilegioIdPrivilegioController.setSelected(null);
        rolPrivilegioIdRolController.setSelected(null);
    }

    
}
