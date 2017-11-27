package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.controllers.UnidadNegocioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "unidadNegocioController")
@ViewScoped
public class UnidadNegocioController extends AbstractController<UnidadNegocio> {

    @Inject
    private UnidadNegocioFacade ejbFacade;
    @Inject
    private OperadorTransporteController unidadNegocioIdOperadorTransporteController;
    @Inject
    private RegionController unidadNegocioIdRegionController;

    /**
     * Initialize the concrete UnidadNegocio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public UnidadNegocioController() {
        // Inform the Abstract parent controller of the concrete UnidadNegocio Entity
        super(UnidadNegocio.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("UnidadNegocio.findAllByCuenta");
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        unidadNegocioIdOperadorTransporteController.setSelected(null);
        unidadNegocioIdRegionController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of ContratoUnidad entities
     * that are retrieved from UnidadNegocio?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ContratoUnidad page
     */
    public String navigateContratoUnidadList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ContratoUnidad_items", this.getSelected().getContratoUnidadList());
        }
        return "/contratoUnidad/index";
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from UnidadNegocio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", this.getSelected().getBusList());
        }
        return "/bus/index";
    }

    /**
     * Sets the "items" attribute with a collection of ValorRolloUnidad entities
     * that are retrieved from UnidadNegocio?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ValorRolloUnidad page
     */
    public String navigateValorRolloUnidadList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ValorRolloUnidad_items", this.getSelected().getValorRolloUnidadList());
        }
        return "/valorRolloUnidad/index";
    }

    /**
     * Sets the "selected" attribute of the OperadorTransporte controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUnidadNegocioIdOperadorTransporte(ActionEvent event) {
        if (this.getSelected() != null && unidadNegocioIdOperadorTransporteController.getSelected() == null) {
            unidadNegocioIdOperadorTransporteController.setSelected(this.getSelected().getUnidadNegocioIdOperadorTransporte());
        }
    }

    /**
     * Sets the "selected" attribute of the Region controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUnidadNegocioIdRegion(ActionEvent event) {
        if (this.getSelected() != null && unidadNegocioIdRegionController.getSelected() == null) {
            unidadNegocioIdRegionController.setSelected(this.getSelected().getUnidadNegocioIdRegion());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from UnidadNegocio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", this.getSelected().getServicioList());
        }
        return "/servicio/index";
    }

    @Override
    public UnidadNegocio prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setUnidadNegocioIdCuenta(this.getUserCount());
        
        return this.getSelected();
    }

}
