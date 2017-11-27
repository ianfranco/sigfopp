package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TipoContratoController;
import com.areatecnica.sigf.beans.TipoTrabajadorController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.TerminalController;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.controllers.RelacionLaboralFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "relacionLaboralController")
@ViewScoped
public class RelacionLaboralController extends AbstractController<RelacionLaboral> {

    @Inject
    private RelacionLaboralFacade ejbFacade;
    @Inject
    private TerminalController relacionLaboralIdTerminalController;
    @Inject
    private TipoContratoController relacionLaboralIdTipoContratoController;
    @Inject
    private EmpresaController relacionLaboralIdEmpresaController;
    @Inject
    private OperadorTransporteController relacionLaboralIdOperadorController;
    @Inject
    private TrabajadorController relacionLaboralIdTrabajadorController;
    @Inject
    private TipoTrabajadorController relacionLaboralIdTipoTrabajadorController;

    /**
     * Initialize the concrete RelacionLaboral controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RelacionLaboralController() {
        // Inform the Abstract parent controller of the concrete RelacionLaboral Entity
        super(RelacionLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        relacionLaboralIdTerminalController.setSelected(null);
        relacionLaboralIdTipoContratoController.setSelected(null);
        relacionLaboralIdEmpresaController.setSelected(null);
        relacionLaboralIdOperadorController.setSelected(null);
        relacionLaboralIdTrabajadorController.setSelected(null);
        relacionLaboralIdTipoTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTerminal(ActionEvent event) {
        if (this.getSelected() != null && relacionLaboralIdTerminalController.getSelected() == null) {
            relacionLaboralIdTerminalController.setSelected(this.getSelected().getRelacionLaboralIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoContrato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTipoContrato(ActionEvent event) {
        if (this.getSelected() != null && relacionLaboralIdTipoContratoController.getSelected() == null) {
            relacionLaboralIdTipoContratoController.setSelected(this.getSelected().getRelacionLaboralIdTipoContrato());
        }
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdEmpresa(ActionEvent event) {
        if (this.getSelected() != null && relacionLaboralIdEmpresaController.getSelected() == null) {
            relacionLaboralIdEmpresaController.setSelected(this.getSelected().getRelacionLaboralIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the OperadorTransporte controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdOperador(ActionEvent event) {
        if (this.getSelected() != null && relacionLaboralIdOperadorController.getSelected() == null) {
            relacionLaboralIdOperadorController.setSelected(this.getSelected().getRelacionLaboralIdOperador());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && relacionLaboralIdTrabajadorController.getSelected() == null) {
            relacionLaboralIdTrabajadorController.setSelected(this.getSelected().getRelacionLaboralIdTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoTrabajador controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRelacionLaboralIdTipoTrabajador(ActionEvent event) {
        if (this.getSelected() != null && relacionLaboralIdTipoTrabajadorController.getSelected() == null) {
            relacionLaboralIdTipoTrabajadorController.setSelected(this.getSelected().getRelacionLaboralIdTipoTrabajador());
        }
    }

    /**
     * Sets the "items" attribute with a collection of FiniquitoRelacionLaboral
     * entities that are retrieved from RelacionLaboral?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for FiniquitoRelacionLaboral page
     */
    public String navigateFiniquitoRelacionLaboralList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FiniquitoRelacionLaboral_items", this.getSelected().getFiniquitoRelacionLaboralList());
        }
        return "/finiquitoRelacionLaboral/index";
    }

}
