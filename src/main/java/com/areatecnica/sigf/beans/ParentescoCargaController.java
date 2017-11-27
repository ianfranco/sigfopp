package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.ParentescoCarga;
import com.areatecnica.sigf.controllers.ParentescoCargaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "parentescoCargaController")
@ViewScoped
public class ParentescoCargaController extends AbstractController<ParentescoCarga> {

    @Inject
    private ParentescoCargaFacade ejbFacade;

    /**
     * Initialize the concrete ParentescoCarga controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ParentescoCargaController() {
        // Inform the Abstract parent controller of the concrete ParentescoCarga Entity
        super(ParentescoCarga.class);
    }

    /**
     * Sets the "items" attribute with a collection of CargaTrabajador entities
     * that are retrieved from ParentescoCarga?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CargaTrabajador page
     */
    public String navigateCargaTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaTrabajador_items", this.getSelected().getCargaTrabajadorList());
        }
        return "/cargaTrabajador/index";
    }

}
