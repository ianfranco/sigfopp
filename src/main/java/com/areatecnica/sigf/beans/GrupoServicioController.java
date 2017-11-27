package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.controllers.GrupoServicioFacade;
import com.areatecnica.sigf.entities.GrupoServicio;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "grupoServicioController")
@ViewScoped
public class GrupoServicioController extends AbstractController<GrupoServicio> {

    @Inject
    private GrupoServicioFacade ejbFacade;
    @Inject
    private CuentaController grupoServicioIdCuentaController;

    /**
     * Initialize the concrete GrupoServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public GrupoServicioController() {
        // Inform the Abstract parent controller of the concrete GrupoServicio Entity
        super(GrupoServicio.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("GrupoServicio.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        grupoServicioIdCuentaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from GrupoServicio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "//bus/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGrupoServicioIdCuenta(ActionEvent event) {
        if (this.getSelected() != null && grupoServicioIdCuentaController.getSelected() == null) {
            grupoServicioIdCuentaController.setSelected(this.getSelected().getGrupoServicioIdCuenta());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from GrupoServicio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", this.getSelected().getServicioList());
        }
        return "//servicio/index";
    }

    /**
     * Sets the "items" attribute with a collection of TarifaGrupoServicio
     * entities that are retrieved from GrupoServicio?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for TarifaGrupoServicio page
     */
    public String navigateTarifaGrupoServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TarifaGrupoServicio_items", this.getSelected().getTarifaGrupoServicioList());
        }
        return "//tarifaGrupoServicio/index";
    }

    @Override
    public GrupoServicio prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        this.getSelected().setGrupoServicioIdCuenta(this.getUserCount());
        this.getSelected().setGrupoServicioAccesoInspector(Boolean.TRUE);
        return this.getSelected();
    }

}
