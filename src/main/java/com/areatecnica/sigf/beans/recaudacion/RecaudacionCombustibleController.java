/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.recaudacion;

import com.areatecnica.sigf.beans.AbstractCrudController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.RecaudacionCombustibleFacade;
import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IRecaudacionCombustibleDao;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionCombustibleDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.RecaudacionEgreso;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.models.RecaudacionCombustibleDataModel;
import com.areatecnica.sigf.models.VentaCombustibleModel;
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
public class RecaudacionCombustibleController extends AbstractCrudController<RecaudacionCombustible> {

    /*Inyección de Controladores*/
    @Inject
    private RecaudacionCombustibleFacade facade;
    @Inject
    private VentaCombustibleFacade ventaCombustibleFacade;
    @Inject
    private RecaudacionFacade recaudacionFacade;
    @Inject
    private TrabajadorController trabajadorController;
    @Inject
    private RecaudacionMinutoController recaudacionMinutoController;
    @Inject
    private RecaudacionBoletoController recaudacionBoletoController;

    //Listas Auxiliares
    /*Lista de cajas asociadas al usuario*/
    private List<CajaRecaudacion> cajaRecaudacionItems;
    /*Lista de buses permitidos de recaudar*/
    private List<Bus> busItems;
    /**/
    private List<VentaCombustible> deudasItems;

    private RecaudacionCombustibleDataModel recaudacionCombustibleDataModel;
    private VentaCombustibleModel deudasModel;

    /*Data Access Objects*/
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IVentaCombustibleDao ventaCombustibleDao;
    private IRecaudacionCombustibleDao recaudacionDao;

    /*Objetos Auxiliares*/
    private Date fechaRecaudacion;
    private CurrentDate currentDate = new CurrentDate();
    private CajaRecaudacion cajaRecaudacion;
    private Recaudacion recaudacion;
    private VentaCombustible ventaCombustible;

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
    public RecaudacionCombustibleController() {
        super(RecaudacionCombustible.class);
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

        this.setTotalRecaudacion(0);
        this.setTotalCombustible(0);

        this.setItems(new ArrayList<>());
    }

    @Override
    public RecaudacionCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.setRecaudacion(new Recaudacion());
        this.getSelected().setRecaudacionCombustibleIdRecaudacion(recaudacion);
        this.getRecaudacion().setRecaudacionFecha(fechaRecaudacion);
        this.getRecaudacion().setRecaudacionIdCaja(cajaRecaudacion);

        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null && this.ventaCombustible != null) {
            int total = 0;
            List<RecaudacionCombustible> _recaudacionCombusteList = new ArrayList<>();

            RecaudacionCombustible recaudacionCombustible = new RecaudacionCombustible();
            recaudacionCombustible.setRecaudacionCombustibleIdRecaudacion(this.getRecaudacion());
            recaudacionCombustible.setRecaudacionCombustibleIdVentaCombustible(this.ventaCombustible);
            recaudacionCombustible.setRecaudacionCombustibleMonto(this.ventaCombustible.getVentaCombustibleTotal());

            this.ventaCombustible.setVentaCombustibleRecaudado(Boolean.TRUE);

            this.ventaCombustibleFacade.edit(this.ventaCombustible);

            _recaudacionCombusteList.add(recaudacionCombustible);

            this.getRecaudacion().setRecaudacionIdCaja(this.cajaRecaudacion);
            this.getRecaudacion().setRecaudacionFecha(this.fechaRecaudacion);
            this.getRecaudacion().setRecaudacionHora(new Date());
            this.getRecaudacion().setRecaudacionTotal(total);

            this.getRecaudacion().setRecaudacionCombustibleList(_recaudacionCombusteList);

            this.recaudacionFacade.create(this.getRecaudacion());

            //this.facade.create(this.getSelected());
            this.getItems().add(recaudacionCombustible);

            JsfUtil.addSuccessMessage("Se ha recaudado la boleta N°" + this.getSelected().getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleNumeroBoleta() + " por un monto de: $ " + getFormatValue(this.getSelected().getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleTotal()));

            this.totalRecaudacion += this.ventaCombustible.getVentaCombustibleTotal();
        }
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {
            VentaCombustible ventaCombustible = this.getSelected().getRecaudacionCombustibleIdVentaCombustible();
            ventaCombustible.setVentaCombustibleRecaudado(Boolean.FALSE);

            this.ventaCombustibleFacade.edit(ventaCombustible);
            Recaudacion _recaudacion = this.getSelected().getRecaudacionCombustibleIdRecaudacion();

            System.err.println("cantidad de objetos recaudados:" + _recaudacion.getRecaudacionEgresoList().size());

            IRecaudacionCombustibleDao dao = new IRecaudacionCombustibleDaoImpl();

            /*this.getSelected().setRecaudacionCombustibleIdRecaudacion(null);
            this.getSelected().setRecaudacionCombustibleIdVentaCombustible(null);*/
            if (_recaudacion.getRecaudacionEgresoList().size() > 0) {

                int _auxTotal = _recaudacion.getRecaudacionTotal() - ventaCombustible.getVentaCombustibleTotal();

                System.err.println("Recaudacion:" + _recaudacion.getRecaudacionTotal());
                System.err.println("Venta:" + ventaCombustible.getVentaCombustibleTotal());
                System.err.println("Diferencia;: " + _auxTotal);
                _recaudacion.getRecaudacionCombustibleList().remove(this.getSelected());

                RecaudacionCombustible r = this.getSelected();

                if (_auxTotal >= 0) {
                    _recaudacion.setRecaudacionTotal(_auxTotal);
                    _recaudacion.getRecaudacionCombustibleList().remove(this.getSelected());
                    this.recaudacionFacade.edit(_recaudacion);
                }

                this.facade.remove(this.getSelected());
                this.setSelected(null);
            } else {

                this.recaudacionFacade.remove(_recaudacion);
            }

            load();

            JsfUtil.addSuccessMessage("Se ha cancelado la recaudación");

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
        }
    }

    public void load() {
        if (this.cajaRecaudacion != null && this.fechaRecaudacion != null) {
            /*Creación de Modelo de Datos para la recaudación*/
            this.recaudacionDao = new IRecaudacionCombustibleDaoImpl();
            this.setItems(this.recaudacionDao.findByCajaDate(this.cajaRecaudacion, this.fechaRecaudacion));
            this.recaudacionCombustibleDataModel = new RecaudacionCombustibleDataModel(this.getItems());

            /*Cálculo del total de recaudación*/
            this.totalRecaudacion = 0;
            for (RecaudacionCombustible v : this.getItems()) {
                this.totalRecaudacion += v.getRecaudacionCombustibleMonto();
            }

            loadDeudas();
        }
    }

    public void handleBoletaChange() {
        if (this.getRecaudacion().getRecaudacionIdentificador() > 0) {
            this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
            this.setVentaCombustible(this.ventaCombustibleDao.findByBoleta(this.getRecaudacion().getRecaudacionIdentificador()));

            if (this.getVentaCombustible() != null) {

                if (!this.getVentaCombustible().getVentaCombustibleRecaudado()) {
                    this.getRecaudacion().setRecaudacionIdBus(this.getVentaCombustible().getVentaCombustibleIdBus());
                    this.getRecaudacion().setRecaudacionTotal(getVentaCombustible().getVentaCombustibleTotal());
                    this.getSelected().setRecaudacionCombustibleIdVentaCombustible(getVentaCombustible());
                    this.getSelected().setRecaudacionCombustibleMonto(getVentaCombustible().getVentaCombustibleTotal());
                } else {
                    JsfUtil.addErrorMessage("La boleta ya fue recaudada");
                }

            } else {
                JsfUtil.addErrorMessage("No se encuentra el Folio ingresado");
            }
        }
    }

    public void loadDeudas() {
        this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
        this.setDeudasItems((List<VentaCombustible>) this.ventaCombustibleDao.findByTerminalSinRecaudar(this.getCurrentUser().getUsuarioIdTerminal(), Boolean.FALSE));
        this.setDeudasModel(new VentaCombustibleModel(getDeudasItems()));
    }

    /**
     * @return the deudasItems
     */
    public List<VentaCombustible> getDeudasItems() {
        return deudasItems;
    }

    /**
     * @param deudasItems the deudasItems to set
     */
    public void setDeudasItems(List<VentaCombustible> deudasItems) {
        this.deudasItems = deudasItems;
    }

    /**
     * @return the deudasModel
     */
    public VentaCombustibleModel getDeudasModel() {
        return deudasModel;
    }

    /**
     * @param deudasModel the deudasModel to set
     */
    public void setDeudasModel(VentaCombustibleModel deudasModel) {
        this.deudasModel = deudasModel;
    }

    /**
     * @return the ventaCombustible
     */
    public VentaCombustible getVentaCombustible() {
        return ventaCombustible;
    }

    /**
     * @param ventaCombustible the ventaCombustible to set
     */
    public void setVentaCombustible(VentaCombustible ventaCombustible) {
        this.ventaCombustible = ventaCombustible;
    }

    /**
     * @return the recaudacionCombustibleDataModel
     */
    public RecaudacionCombustibleDataModel getRecaudacionCombustibleDataModel() {
        return recaudacionCombustibleDataModel;
    }

    /**
     * @param recaudacionCombustibleDataModel the
     * recaudacionCombustibleDataModel to set
     */
    public void setRecaudacionCombustibleDataModel(RecaudacionCombustibleDataModel recaudacionCombustibleDataModel) {
        this.recaudacionCombustibleDataModel = recaudacionCombustibleDataModel;
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
     * @param totalRecaudacion the totalRecaudacion to set
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
     * @return the busItems
     */
    public List<Bus> getBusItems() {
        return busItems;
    }

    /**
     * @param busItems the busItems to set
     */
    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    /**
     * @param value to format
     */
    public String getFormatValue(int value) {
        return decimalFormat.format(value);
    }

}
