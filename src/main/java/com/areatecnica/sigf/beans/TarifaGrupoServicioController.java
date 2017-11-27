package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.GrupoServicioController;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import com.areatecnica.sigf.controllers.TarifaGrupoServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "tarifaServicioController")
@ViewScoped
public class TarifaGrupoServicioController extends AbstractController<TarifaGrupoServicio> {

    @Inject
    private TarifaGrupoServicioFacade ejbFacade;
    @Inject
    private BoletoController tarifaServicioIdBoletoController;
    @Inject
    private GrupoServicioController tarifaServicioIdServicioController;

    /**
     * Initialize the concrete TarifaServicio controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TarifaGrupoServicioController() {
        // Inform the Abstract parent controller of the concrete TarifaServicio Entity
        super(TarifaGrupoServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tarifaServicioIdBoletoController.setSelected(null);
        tarifaServicioIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTarifaServicioIdBoleto(ActionEvent event) {
        if (this.getSelected() != null && tarifaServicioIdBoletoController.getSelected() == null) {
            tarifaServicioIdBoletoController.setSelected(this.getSelected().getTarifaGrupoServicioIdBoleto());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTarifaServicioIdGrupo(ActionEvent event) {
        if (this.getSelected() != null && tarifaServicioIdServicioController.getSelected() == null) {
            tarifaServicioIdServicioController.setSelected(this.getSelected().getTarifaGrupoServicioIdGrupo());
        }
    }
}
