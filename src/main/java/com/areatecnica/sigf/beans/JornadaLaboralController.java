package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.JornadaLaboral;
import com.areatecnica.sigf.controllers.JornadaLaboralFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "jornadaLaboralController")
@ViewScoped
public class JornadaLaboralController extends AbstractController<JornadaLaboral> {

    @Inject
    private JornadaLaboralFacade ejbFacade;
    @Inject
    private HorarioJornadaController jornadaLaboralIdHorarioJornadaController;

    /**
     * Initialize the concrete JornadaLaboral controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public JornadaLaboralController() {
        // Inform the Abstract parent controller of the concrete JornadaLaboral Entity
        super(JornadaLaboral.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        jornadaLaboralIdHorarioJornadaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the HorarioJornada controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaLaboralIdHorarioJornada(ActionEvent event) {
        if (this.getSelected() != null && jornadaLaboralIdHorarioJornadaController.getSelected() == null) {
            jornadaLaboralIdHorarioJornadaController.setSelected(this.getSelected().getJornadaLaboralIdHorarioJornada());
        }
    }

    /**
     * Sets the "items" attribute with a collection of JornadaTrabajador
     * entities that are retrieved from JornadaLaboral?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for JornadaTrabajador page
     */
    public String navigateJornadaTrabajadorList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("JornadaTrabajador_items", this.getSelected().getJornadaTrabajadorList());
        }
        return "/jornadaTrabajador/index";
    }

}
