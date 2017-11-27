/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.recaudacion;

import com.areatecnica.sigf.beans.AbstractCrudController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.controllers.RecaudacionMinutoFacade;
import com.areatecnica.sigf.controllers.RegistroMinutoFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IRecaudacionMinutoDao;
import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.models.MinutosHelper;
import com.areatecnica.sigf.models.RecaudacionMinutoDataModel;
import com.areatecnica.sigf.models.RegistroMinutoDataModel;
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
public class RecaudacionMinutoController extends AbstractCrudController<RecaudacionMinuto> {

    /*Inyección de Controladores*/
    @Inject
    private RecaudacionMinutoFacade facade;
    @Inject
    private RegistroMinutoFacade registroMinutoFacade;
    @Inject
    private RecaudacionFacade recaudacionFacade;
    @Inject
    private TrabajadorController trabajadorController;

    //Listas Auxiliares
    /*Lista de cajas asociadas al usuario*/
    private List<CajaRecaudacion> cajaRecaudacionItems;
    /*Lista de buses permitidos de recaudar*/
    private List<Bus> busItems;
    /**/
    private List<RegistroMinuto> deudasItems;
    //Listas para manejar CRUD    
    private List<MinutosHelper> registroMinutoItems;
    private List<MinutosHelper> selectedMinutosHelper;

    private RecaudacionMinutoDataModel recaudacionMinutoDataModel;
    private RegistroMinutoDataModel deudasModel;

    /*Data Access Objects*/
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IRegistroMinutoDao registroMinutoDao;
    private IRecaudacionMinutoDao recaudacionDao;
    private IBusDao busDao;

    /*Objetos Auxiliares*/
    private Date fechaRecaudacion;
    private CurrentDate currentDate = new CurrentDate();
    private CajaRecaudacion cajaRecaudacion;
    private Recaudacion recaudacion;
    private RegistroMinuto registroMinuto;
    private Bus bus;

    /*Suma*/
    private int totalRecaudacion;
    private int totalCombustible;

    /*Otros*/
    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private boolean print;

    /**
     * Creates a new instance of RecaudacionMinutoController
     */
    public RecaudacionMinutoController() {
        super(RecaudacionMinuto.class);
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
    public RecaudacionMinuto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.setRecaudacion(new Recaudacion());
        this.getSelected().setRecaudacionMinutoIdRecaudacion(recaudacion);
        this.getRecaudacion().setRecaudacionFecha(fechaRecaudacion);
        this.getRecaudacion().setRecaudacionIdCaja(cajaRecaudacion);

        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null && this.selectedMinutosHelper != null) {
            int total = 0;
            int _minutos = 0;
            List<RecaudacionMinuto> listRecaudacionMinutos = new ArrayList<>();

            for (MinutosHelper m : this.getSelectedMinutosHelper()) {
                m.getRegistro().setRegistroMinutoRecaudado(Boolean.TRUE);
                this.registroMinutoFacade.edit(m.getRegistro());
                _minutos += m.getRegistro().getRegistroMinutoMonto();
                this.totalRecaudacion += m.getRegistro().getRegistroMinutoMonto();
                RecaudacionMinuto _rm = new RecaudacionMinuto();
                _rm.setRecaudacionMinutoIdRecaudacion(this.getRecaudacion());
                _rm.setRecaudacionMinutoIdRegistroMinuto(m.getRegistro());
                _rm.setRecaudacionMinutoMonto(m.getRegistro().getRegistroMinutoMonto());

                listRecaudacionMinutos.add(_rm);

            }

            this.getRecaudacion().setRecaudacionMinutoList(listRecaudacionMinutos);
            this.getRecaudacion().setRecaudacionHora(new Date());

            this.getRecaudacion().setRecaudacionTotal(_minutos);

            this.recaudacionFacade.create(this.getRecaudacion());
            load();

            this.selectedMinutosHelper = null;

            JsfUtil.addSuccessMessage("Se han recaudado minutos del Bus N°:" + this.bus.getBusNumero());
            
        }
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {
            RegistroMinuto registroMinuto = this.getSelected().getRecaudacionMinutoIdRegistroMinuto();
            registroMinuto.setRegistroMinutoRecaudado(Boolean.FALSE);

            this.registroMinutoFacade.edit(registroMinuto);
            Recaudacion _recaudacion = this.getSelected().getRecaudacionMinutoIdRecaudacion();

            System.err.println("cantidad de objetos recaudados:" + _recaudacion.getRecaudacionEgresoList().size());

            IRecaudacionMinutoDao dao = new IRecaudacionMinutoDaoImpl();

            /*this.getSelected().setRecaudacionMinutoIdRecaudacion(null);
            this.getSelected().setRecaudacionMinutoIdVentaCombustible(null);*/
            if (_recaudacion.getRecaudacionEgresoList().size() > 0) {

                int _auxTotal = _recaudacion.getRecaudacionTotal() - registroMinuto.getRegistroMinutoMonto();

                System.err.println("Recaudacion:" + _recaudacion.getRecaudacionTotal());
                System.err.println("Venta:" + registroMinuto.getRegistroMinutoMonto());
                System.err.println("Diferencia;: " + _auxTotal);
                _recaudacion.getRecaudacionMinutoList().remove(this.getSelected());

                RecaudacionMinuto r = this.getSelected();

                if (_auxTotal >= 0) {
                    _recaudacion.setRecaudacionTotal(_auxTotal);
                    _recaudacion.getRecaudacionMinutoList().remove(this.getSelected());
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
            this.recaudacionDao = new IRecaudacionMinutoDaoImpl();
            this.setItems(this.recaudacionDao.findByCajaDate(this.cajaRecaudacion, this.fechaRecaudacion));
            this.recaudacionMinutoDataModel = new RecaudacionMinutoDataModel(this.getItems());

            /*Cálculo del total de recaudación*/
            this.totalRecaudacion = 0;
            for (RecaudacionMinuto v : this.getItems()) {
                this.totalRecaudacion += v.getRecaudacionMinutoMonto();
            }

            this.busDao = new IBusDaoImpl();
            this.setBusItems(this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal()));

            loadDeudas();
        }
    }

    public void handleBoletaChange() {
        /*if (this.getRecaudacion().getRecaudacionIdentificador() > 0) {
            this.registroMinutoDao = new IRegistroMinutoDaoImpl();
            this.setVentaCombustible(this.registroMinutoDao.findByBoleta(this.getRecaudacion().getRecaudacionIdentificador()));

            if (this.getVentaCombustible() != null) {

                if (!this.getVentaCombustible().getVentaCombustibleRecaudado()) {
                    this.getRecaudacion().setRecaudacionIdBus(this.getVentaCombustible().getVentaCombustibleIdBus());
                    this.getRecaudacion().setRecaudacionTotal(getVentaCombustible().getVentaCombustibleTotal());
                    this.getSelected().setRecaudacionMinutoIdVentaCombustible(getVentaCombustible());
                    this.getSelected().setRecaudacionMinutoMonto(getVentaCombustible().getVentaCombustibleTotal());
                } else {
                    JsfUtil.addErrorMessage("La boleta ya fue recaudada");
                }

            } else {
                JsfUtil.addErrorMessage("No se encuentra el Folio ingresado");
            }
        }*/
    }

    public void loadDeudas() {
        this.registroMinutoDao = new IRegistroMinutoDaoImpl();
        this.setDeudasItems((List<RegistroMinuto>) this.registroMinutoDao.findByTerminalSinRecaudar(this.getCurrentUser().getUsuarioIdTerminal()));
        this.setDeudasModel(new RegistroMinutoDataModel(getDeudasItems()));
    }

    /**
     * @return the deudasItems
     */
    public List<RegistroMinuto> getDeudasItems() {
        return deudasItems;
    }

    /**
     * @param deudasItems the deudasItems to set
     */
    public void setDeudasItems(List<RegistroMinuto> deudasItems) {
        this.deudasItems = deudasItems;
    }

    /**
     * @return the deudasModel
     */
    public RegistroMinutoDataModel getDeudasModel() {
        return deudasModel;
    }

    /**
     * @param deudasModel the deudasModel to set
     */
    public void setDeudasModel(RegistroMinutoDataModel deudasModel) {
        this.deudasModel = deudasModel;
    }

    /**
     * @return the ventaCombustible
     */
    public RegistroMinuto getRegistroMinuto() {
        return registroMinuto;
    }

    /**
     * @param ventaCombustible the ventaCombustible to set
     */
    public void setRegistroMinuto(RegistroMinuto registroMinuto) {
        this.registroMinuto = registroMinuto;
    }

    /**
     * @return the recaudacionCombustibleDataModel
     */
    public RecaudacionMinutoDataModel getRecaudacionMinutoDataModel() {
        return recaudacionMinutoDataModel;
    }

    /**
     * @param recaudacionCombustibleDataModel the
     * recaudacionCombustibleDataModel to set
     */
    public void setRecaudacionMinutoDataModel(RecaudacionMinutoDataModel recaudacionCombustibleDataModel) {
        this.recaudacionMinutoDataModel = recaudacionCombustibleDataModel;
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
     * @return the bus
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * @param bus the bus to set
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    /**
     * @return the registroMinutoItems
     */
    public List<MinutosHelper> getRegistroMinutoItems() {
        return registroMinutoItems;
    }

    /**
     * @param registroMinutoItems the registroMinutoItems to set
     */
    public void setRegistroMinutoItems(List<MinutosHelper> registroMinutoItems) {
        this.registroMinutoItems = registroMinutoItems;
    }

    /**
     * @return the selectedMinutosHelper
     */
    public List<MinutosHelper> getSelectedMinutosHelper() {
        return selectedMinutosHelper;
    }

    /**
     * @param selectedMinutosHelper the selectedMinutosHelper to set
     */
    public void setSelectedMinutosHelper(List<MinutosHelper> selectedMinutosHelper) {
        this.selectedMinutosHelper = selectedMinutosHelper;
    }

    public int calculaTotal() {
        int total = 0;
        int _minutos = 0;
        int _petroleo = 0;

        if (this.getSelectedMinutosHelper() != null) {

            for (MinutosHelper m : this.getSelectedMinutosHelper()) {
                _minutos = _minutos + m.getRegistro().getRegistroMinutoMonto();
            }
        }

        total += _petroleo + _minutos;

        this.getSelected().setRecaudacionMinutoMonto(total);
        return total;
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getBus() != null) {
            this.setRegistroMinutoItems(null);
            this.setSelectedMinutosHelper(null);

            this.registroMinutoDao = new IRegistroMinutoDaoImpl();

            List<RegistroMinuto> minutosList = this.registroMinutoDao.findByBusSinRecaudar(this.bus);

            if (minutosList.isEmpty()) {
                //deshabilito el combobox
            } else {

                this.setRegistroMinutoItems(new ArrayList<>());
                //Si tiene más de una deuda de minutos, busco el total 
                if (minutosList.size() > 1) {

                    int total = 0;
                    for (RegistroMinuto m : minutosList) {
                        total = total + m.getRegistroMinutoMonto();
                        MinutosHelper minuto = new MinutosHelper();
                        minuto.setRegistro(m);
                        minuto.setObservacion("$ " + decimalFormat.format(m.getRegistroMinutoMonto()) + "   N° Bus: " + m.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + currentDate.format(m.getRegistroMinutoFechaMinuto()));
                        this.getRegistroMinutoItems().add(minuto);
                    }

                } else {
                    System.err.println("SÓLO UN MINUTO");
                    RegistroMinuto r = minutosList.get(0);
                    MinutosHelper minuto = new MinutosHelper();
                    minuto.setRegistro(r);
                    minuto.setObservacion("$ " + decimalFormat.format(r.getRegistroMinutoMonto()) + "   N° Bus: " + r.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + currentDate.format(r.getRegistroMinutoFechaMinuto()));
                    minuto.setTodos(Boolean.FALSE);
                    this.getRegistroMinutoItems().add(minuto);

                }
            }

        }

    }

    /**
     * @param value to format
     */
    public String getFormatValue(int value) {
        return decimalFormat.format(value);
    }

    public void exportPdf2(ActionEvent event) {

    }

}
