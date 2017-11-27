package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import com.areatecnica.sigf.controllers.ReconocimientoDeudaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "reconocimientoDeudaController")
@ViewScoped
public class ReconocimientoDeudaController extends AbstractController<ReconocimientoDeuda> {

    @Inject
    private ReconocimientoDeudaFacade ejbFacade;
    @Inject
    private EmpresaController reconocimientoDeudaIdEmpresaController;
    @Inject
    private TrabajadorController reconocimientoDeudaIdTrabajadorController;

    /**
     * Initialize the concrete ReconocimientoDeuda controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ReconocimientoDeudaController() {
        // Inform the Abstract parent controller of the concrete ReconocimientoDeuda Entity
        super(ReconocimientoDeuda.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        reconocimientoDeudaIdEmpresaController.setSelected(null);
        reconocimientoDeudaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareReconocimientoDeudaIdEmpresa(ActionEvent event) {
        if (this.getSelected() != null && reconocimientoDeudaIdEmpresaController.getSelected() == null) {
            reconocimientoDeudaIdEmpresaController.setSelected(this.getSelected().getReconocimientoDeudaIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareReconocimientoDeudaIdTrabajador(ActionEvent event) {
        if (this.getSelected() != null && reconocimientoDeudaIdTrabajadorController.getSelected() == null) {
            reconocimientoDeudaIdTrabajadorController.setSelected(this.getSelected().getReconocimientoDeudaIdTrabajador());
        }
    }
}
