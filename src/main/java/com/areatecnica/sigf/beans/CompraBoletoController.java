package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.AbstractController;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.controllers.CompraBoletoFacade;
import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.dao.impl.ICompraBoletoDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;

@Named(value = "compraBoletoController")
@ViewScoped
public class CompraBoletoController extends AbstractController<CompraBoleto> {

    @Inject
    private CompraBoletoFacade ejbFacade;
    
    private List<CompraBoleto> items;
    private ICompraBoletoDao compraBoletoDao;

    /**
     * Initialize the concrete CompraBoleto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        load();
    }

    public CompraBoletoController() {
        // Inform the Abstract parent controller of the concrete CompraBoleto Entity
        super(CompraBoleto.class);
    }

    /**
     * Sets the "items" attribute with a collection of DetalleCompraBoleto
     * entities that are retrieved from CompraBoleto?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DetalleCompraBoleto page
     */
    public String navigateDetalleCompraBoletoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DetalleCompraBoleto_items", this.getSelected().getDetalleCompraBoletoList());
        }
        return "/detalleCompraBoleto/index";
    }

    @Override
    public CompraBoleto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCompraBoletoIdCuenta(this.getUserCount());        
        return this.getSelected();
    }

    /**
     * @return the items
     */
    public List<CompraBoleto> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<CompraBoleto> items) {
        this.items = items;
    }

    private void load(){
        this.compraBoletoDao = new ICompraBoletoDaoImpl();
        this.setItems((List<CompraBoleto>) this.compraBoletoDao.findAllBy(this.getUserCount()));
    }
    
    

}
