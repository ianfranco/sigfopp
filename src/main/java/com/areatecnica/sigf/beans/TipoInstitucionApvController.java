package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.TipoInstitucionApv;
import com.areatecnica.sigf.controllers.TipoInstitucionApvFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tipoInstitucionApvController")
@ViewScoped
public class TipoInstitucionApvController extends AbstractController<TipoInstitucionApv> {

    @Inject
    private TipoInstitucionApvFacade ejbFacade;

    /**
     * Initialize the concrete TipoInstitucionApv controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoInstitucionApvController() {
        // Inform the Abstract parent controller of the concrete TipoInstitucionApv Entity
        super(TipoInstitucionApv.class);
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionApv entities
     * that are retrieved from TipoInstitucionApv?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for InstitucionApv page
     */
    public String navigateInstitucionApvList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionApv_items", this.getSelected().getInstitucionApvList());
        }
        return "/institucionApv/index";
    }

}
