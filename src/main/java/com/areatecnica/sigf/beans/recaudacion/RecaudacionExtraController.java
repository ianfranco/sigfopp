/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.recaudacion;

import com.areatecnica.sigf.beans.AbstractCrudController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.EstadoBusFacade;
import com.areatecnica.sigf.controllers.RecaudacionExtraFacade;
import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IEstadoBusDao;
import com.areatecnica.sigf.dao.IRecaudacionCombustibleDao;
import com.areatecnica.sigf.dao.IRecaudacionExtraDao;
import com.areatecnica.sigf.dao.ITrabajadorDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IEstadoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionCombustibleDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionExtraDaoImpl;
import com.areatecnica.sigf.dao.impl.ITrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.EstadoBus;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionEgreso;
import com.areatecnica.sigf.entities.RecaudacionExtra;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.models.RecaudacionExtraDataModel;
import com.areatecnica.sigf.util.CurrentDate;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author ianfr
 */
public class RecaudacionExtraController extends AbstractCrudController<RecaudacionExtra> {

    /*Inyección de Controladores*/
    @Inject
    private RecaudacionExtraFacade facade;
    @Inject
    private RecaudacionFacade recaudacionFacade;
    @Inject
    private EstadoBusFacade estadoBusFacade;
    @Inject
    private TrabajadorController trabajadorController;

    //Listas Auxiliares
    /*Lista de cajas asociadas al usuario*/
    private List<CajaRecaudacion> cajaRecaudacionItems;
    /*Lista de buses permitidos de recaudar*/

    private RecaudacionExtraDataModel recaudacionExtraDataModel;

    /*Data Access Objects*/
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IRecaudacionExtraDao recaudacionDao;
    private IBusDao busDao;
    private ITrabajadorDao trabajadorDao;
    private IEstadoBusDao estadoBusDao;

    /*Objetos Auxiliares*/
    private Date fechaRecaudacion;
    private CurrentDate currentDate = new CurrentDate();
    private CajaRecaudacion cajaRecaudacion;
    private Recaudacion recaudacion;
    private Bus bus;
    private Trabajador trabajador;
    private EstadoBus estadoBus;

    /*Suma*/
    private int totalRecaudacion;
    private int totalCombustible;

    /*Otros*/
    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private boolean print;

    /**
     * Creates a new instance of RecaudacionCombustibleController
     */
    public RecaudacionExtraController() {
        super(RecaudacionExtra.class);
    }

    @Override
    @PostConstruct
    public void init() {
        super.setFacade(facade);
        this.setFechaRecaudacion(getCurrentDate().date());

        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.setCajaRecaudacionItems((List<CajaRecaudacion>) this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser()));

        if (this.getCajaRecaudacionItems().size() == 1) {
            this.setCajaRecaudacion(this.getCajaRecaudacionItems().get(0));
        }

        this.estadoBusDao = new IEstadoBusDaoImpl();
        this.estadoBus = this.estadoBusFacade.find(new Integer(5));

        System.err.println("ESTOADO BUS:" + this.estadoBus.getEstadoBusNombre());

        this.busDao = new IBusDaoImpl();
        this.bus = this.busDao.findDefaultBus(estadoBus);

        if (this.bus == null) {
            JsfUtil.addErrorMessage("No está ingresado el bus por defecto");
            System.err.println("EL BUS ES NULO");
        }

        this.trabajadorDao = new ITrabajadorDaoImpl();
        this.trabajador = this.trabajadorDao.findByDefaultTrabajador(1, this.getCurrentUser().getUsuarioIdCuenta());

        this.setTotalRecaudacion(0);
        this.setTotalCombustible(0);

        this.setItems(new ArrayList<>());
    }

    @Override
    public RecaudacionExtra prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.setRecaudacion(new Recaudacion());
        this.getSelected().setRecaudacionExtraIdRecaudacion(recaudacion);

        this.getRecaudacion().setRecaudacionFecha(fechaRecaudacion);
        this.getRecaudacion().setRecaudacionIdCaja(cajaRecaudacion);
        this.getRecaudacion().setRecaudacionIdBus(bus);
        this.getRecaudacion().setRecaudacionIdTrabajador(trabajador);

        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null) {
            int total = 0;
            List<RecaudacionExtra> _recaudacionExtraList = new ArrayList<>();

            RecaudacionExtra _recaudacionExtra = this.getSelected();
            _recaudacionExtra.setRecaudacionExtraIdRecaudacion(this.getRecaudacion());

            _recaudacionExtraList.add(_recaudacionExtra);

            this.getRecaudacion().setRecaudacionIdCaja(this.cajaRecaudacion);
            this.getRecaudacion().setRecaudacionFecha(this.fechaRecaudacion);
            this.getRecaudacion().setRecaudacionHora(new Date());
            this.getRecaudacion().setRecaudacionTotal(_recaudacionExtra.getRecaudacionExtraMonto());
            this.getRecaudacion().setRecaudacionIdBus(this.bus);
            this.getRecaudacion().setRecaudacionIdTrabajador(this.trabajador);

            this.getRecaudacion().setRecaudacionExtraList(_recaudacionExtraList);

            this.recaudacionFacade.create(this.getRecaudacion());

            //this.facade.create(this.getSelected());
            //this.getItems().add(_recaudacionExtra);
            JsfUtil.addSuccessMessage("Se ha registra el ingreso extra");
            this.load();
        }
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
        this.load();
    }

    @Override
    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {

            Recaudacion _recaudacion = this.getSelected().getRecaudacionExtraIdRecaudacion();

            this.recaudacionFacade.remove(_recaudacion);
            load();
            JsfUtil.addSuccessMessage("Se ha cancelado la recaudación");

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
        }
    }

    public void load() {
        if (this.cajaRecaudacion != null && this.fechaRecaudacion != null) {
            /*Creación de Modelo de Datos para la recaudación*/
            this.recaudacionDao = new IRecaudacionExtraDaoImpl();
            this.setItems(this.recaudacionDao.findByCajaDate(this.cajaRecaudacion, this.fechaRecaudacion));
            this.recaudacionExtraDataModel = new RecaudacionExtraDataModel(this.getItems());

            /*Cálculo del total de recaudación*/
            this.totalRecaudacion = 0;
            for (RecaudacionExtra v : this.getItems()) {
                this.totalRecaudacion += v.getRecaudacionExtraMonto();
            }

        }
    }

    /**
     * @return the recaudacionExtraDataModel
     */
    public RecaudacionExtraDataModel getRecaudacionExtraDataModel() {
        return recaudacionExtraDataModel;
    }

    /**
     * @param recaudacionCombustibleDataModel the recaudacionExtraDataModel to
     * set
     */
    public void setRecaudacionExtraDataModel(RecaudacionExtraDataModel recaudacionCombustibleDataModel) {
        this.recaudacionExtraDataModel = recaudacionCombustibleDataModel;
    }

    /**
     * @return the fechaRecaudacion
     */
    public Date getFechaRecaudacion() {
        return fechaRecaudacion;
    }

    /**
     * @param fechaRecaudacion the fechaRecaudacion to set
     */
    public void setFechaRecaudacion(Date fechaRecaudacion) {
        this.fechaRecaudacion = fechaRecaudacion;
    }

    /**
     * @return the currentDate
     */
    public CurrentDate getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(CurrentDate currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the cajaRecaudacion
     */
    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    /**
     * @param cajaRecaudacion the cajaRecaudacion to set
     */
    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    /**
     * @return the totalRecaudacion
     */
    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    /**
     * @param totalRecaudacion the totalRecaudacion to set
     */
    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    /**
     * @return the print
     */
    public boolean getPrint() {
        return print;
    }

    /**
     * @param print the totalRecaudacion to set
     */
    public void setPrint(boolean print) {
        this.print = print;
    }

    /**
     * @return the totalCombustible
     */
    public int getTotalCombustible() {
        return totalCombustible;
    }

    /**
     * @param totalCombustible the totalCombustible to set
     */
    public void setTotalCombustible(int totalCombustible) {
        this.totalCombustible = totalCombustible;
    }

    /**
     * @return the recaudacion
     */
    public Recaudacion getRecaudacion() {
        return recaudacion;
    }

    /**
     * @param recaudacion the totalCombustible to set
     */
    public void setRecaudacion(Recaudacion recaudacion) {
        this.recaudacion = recaudacion;
    }

    /**
     * @return the cajaRecaudacionItems
     */
    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    /**
     * @param cajaRecaudacionItems the cajaRecaudacionItems to set
     */
    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    /**
     * @param value to format
     */
    public String getFormatValue(int value) {
        return decimalFormat.format(value);
    }

    public void exportPdf2(ActionEvent e) {

    }

}
