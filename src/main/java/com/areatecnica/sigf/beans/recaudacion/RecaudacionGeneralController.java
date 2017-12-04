/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.recaudacion;

import com.areatecnica.sigf.beans.AbstractCrudController;
import com.areatecnica.sigf.beans.RegistroMinutoController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.controllers.RecaudacionBoletoFacade;
import com.areatecnica.sigf.controllers.RecaudacionCombustibleFacade;
import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.controllers.RecaudacionMinutoFacade;
import com.areatecnica.sigf.controllers.RegistroMinutoFacade;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IEgresoCajaRecaudacionDao;
import com.areatecnica.sigf.dao.IRecaudacionDao;
import com.areatecnica.sigf.dao.IRecaudacionExtraDao;
import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IEgresoCajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionExtraDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionBoleto;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.RecaudacionEgreso;
import com.areatecnica.sigf.entities.RecaudacionExtra;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.models.MinutosHelper;
import com.areatecnica.sigf.models.PetroleoHelper;
import com.areatecnica.sigf.models.RecaudacionDataModel;
import com.areatecnica.sigf.util.CurrentDate;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocFont;
import net.sf.dynamicreports.adhoc.configuration.AdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import com.areatecnica.sigf.reports.Templates;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ianfr
 */
/**
 * * INICIO OBSERVACIONES
 *
 * 1. Comprobar estado de buses 2. Método que devuelva un valor formateado
 *
 *
 *
 * FIN OBSERVACIONES ***
 */
public class RecaudacionGeneralController extends AbstractCrudController<Recaudacion> implements IProcess {

    /*Inyección de Controladores*/
    @Inject
    private RecaudacionFacade facade;
    @Inject
    private VentaCombustibleFacade ventaCombustibleFacade;
    @Inject
    private RegistroMinutoFacade registroMinutoFacade;
    @Inject
    private RecaudacionCombustibleFacade recaudacionCombustibleFacade;
    @Inject
    private RecaudacionMinutoFacade recaudacionMinutoFacade;
    @Inject
    private RecaudacionBoletoFacade recaudacionBoletoFacade;
    @Inject
    private TrabajadorController trabajadorController;
    @Inject
    private RecaudacionMinutoController recaudacionMinutoController;
    @Inject
    private RecaudacionCombustibleController recaudacionCombustibleController;
    @Inject
    private RecaudacionBoletoController recaudacionBoletoController;

    /*Listas para llenar las tablas*/
    private List<Recaudacion> recaudacionGuias;
    private List<RecaudacionMinuto> recaudacionMinutoItems;
    private List<RecaudacionCombustible> recaudacionCombustibleItems;
    private List<RecaudacionBoleto> recaudacionBoletoItems;
    private List<LinkedHashMap> mapsItems;
    private List<Column> columnModelItems;
    private Map table;

    private RecaudacionDataModel recaudacionDataModel;

    //Listas auxiliares para tabla general
    /*Lista de todos los egresos en caja*/
    private List<Egreso> egresoItems;
    /*Auxiliar al registro de egresos x recaudación*/
    private List<RecaudacionEgreso> recaudacionEgresoItems;
    /*Lista de cajas asociadas al usuario*/
    private List<CajaRecaudacion> cajaRecaudacionItems;
    /*Lista de buses permitidos de recaudar*/
    private List<Bus> busItems;
    /*Listas para totales y resultados*/
    private LinkedHashMap totals;
    private List<String> resultsHeader;
    private List<String> resultsTotals;

    //Listas para manejar CRUD    
    private List<MinutosHelper> registroMinutoItems;
    private List<MinutosHelper> selectedMinutosHelper;
    private List<PetroleoHelper> ventaCombustibleItems;
    private List<PetroleoHelper> selectedPetroleoHelper;

    /*Data Access Objects*/
    private IRecaudacionDao recaudacionDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IEgresoCajaRecaudacionDao egresoCajaDao;
    private IRegistroMinutoDao registroMinutoDao;
    private IVentaCombustibleDao ventaCombustibleDao;
    private IBusDao busDao;

    /*Objetos Auxiliares*/
    private RecaudacionCombustible recaudacionCombustible;
    private RecaudacionMinuto recaudacionMinuto;
    private RecaudacionBoleto recaudacionBoleto;
    private Date fechaRecaudacion;
    private CurrentDate currentDate = new CurrentDate();
    private CajaRecaudacion cajaRecaudacion;


    /*Suma*/
    private int totalRecaudacion;
    private int totalResumenRecaudacion;
    private int totalMinutos;
    private int totalCombustible;
    private int totalRecaudacionCombustible;
    private int totalRecaudacionMinuto;


    /*Otros*/
    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private boolean print;

    private ServletOutputStream servletOutputStream;

    /**
     * Creates a new instance of RecaudacionGeneralController
     */
    public RecaudacionGeneralController() {
        super(Recaudacion.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(facade);
        
        CurrentDate cu = new CurrentDate(4, 10, 2017);
        
        this.setFechaRecaudacion(cu.date());

        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.setCajaRecaudacionItems((List<CajaRecaudacion>) this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser()));

        if (this.getCajaRecaudacionItems().size() == 1) {
            this.setCajaRecaudacion(this.getCajaRecaudacionItems().get(0));
        }

        this.setTotalRecaudacion(0);
        this.setTotalCombustible(0);
        this.setTotalMinutos(0);

        this.recaudacionCombustibleController = new RecaudacionCombustibleController();
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

    public List<Column> getColumnModelItems() {
        return columnModelItems;
    }

    public void setColumnModelItems(List<Column> columnModelItems) {
        this.columnModelItems = columnModelItems;
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

    /**
     * @return the ventaCombustibleItems
     */
    public List<PetroleoHelper> getVentaCombustibleItems() {
        return ventaCombustibleItems;
    }

    /**
     * @param ventaCombustibleItems the ventaCombustibleItems to set
     */
    public void setVentaCombustibleItems(List<PetroleoHelper> ventaCombustibleItems) {
        this.ventaCombustibleItems = ventaCombustibleItems;
    }

    /**
     * @return the selectedPetroleoHelper
     */
    public List<PetroleoHelper> getSelectedPetroleoHelper() {
        return selectedPetroleoHelper;
    }

    /**
     * @param selectedPetroleoHelper the selectedPetroleoHelper to set
     */
    public void setSelectedPetroleoHelper(List<PetroleoHelper> selectedPetroleoHelper) {
        this.selectedPetroleoHelper = selectedPetroleoHelper;
    }

    /**
     * @return the recaudacionGuias
     */
    public List<Recaudacion> getRecaudacionGuias() {
        return recaudacionGuias;
    }

    /**
     * @param recaudacionGuias the recaudacionGuias to set
     */
    public void setRecaudacionGuias(List<Recaudacion> recaudacionGuias) {
        this.recaudacionGuias = recaudacionGuias;
    }

    /**
     * @return the recaudacionMinutoItems
     */
    public List<RecaudacionMinuto> getRecaudacionMinutoItems() {
        return recaudacionMinutoItems;
    }

    /**
     * @param recaudacionMinutoItems the recaudacionMinutoItems to set
     */
    public void setRecaudacionMinutoItems(List<RecaudacionMinuto> recaudacionMinutoItems) {
        this.recaudacionMinutoItems = recaudacionMinutoItems;
    }

    /**
     * @return the recaudacionCombustibleItems
     */
    public List<RecaudacionCombustible> getRecaudacionCombustibleItems() {
        return recaudacionCombustibleItems;
    }

    /**
     * @param recaudacionCombustibleItems the recaudacionCombustibleItems to set
     */
    public void setRecaudacionCombustibleItems(List<RecaudacionCombustible> recaudacionCombustibleItems) {
        this.recaudacionCombustibleItems = recaudacionCombustibleItems;
    }

    /**
     * @return the recaudacionBoletoItems
     */
    public List<RecaudacionBoleto> getRecaudacionBoletoItems() {
        return recaudacionBoletoItems;
    }

    /**
     * @param recaudacionBoletoItems the recaudacionBoletoItems to set
     */
    public void setRecaudacionBoletoItems(List<RecaudacionBoleto> recaudacionBoletoItems) {
        this.recaudacionBoletoItems = recaudacionBoletoItems;
    }

    /**
     * @return the mapsItems
     */
    public List<LinkedHashMap> getMapsItems() {
        return mapsItems;
    }

    /**
     * @param mapsItems the mapsItems to set
     */
    public void setMapsItems(List<LinkedHashMap> mapsItems) {
        this.mapsItems = mapsItems;
    }

    /**
     * @return the recaudacionDataModel
     */
    public RecaudacionDataModel getRecaudacionDataModel() {
        return recaudacionDataModel;
    }

    /**
     * @param recaudacionDataModel the recaudacionDataModel to set
     */
    public void setRecaudacionDataModel(RecaudacionDataModel recaudacionDataModel) {
        this.recaudacionDataModel = recaudacionDataModel;
    }

    /**
     * @return the egresoItems
     */
    public List<Egreso> getEgresoItems() {
        return egresoItems;
    }

    /**
     * @param egresoItems the egresoItems to set
     */
    public void setEgresoItems(List<Egreso> egresoItems) {
        this.egresoItems = egresoItems;
    }

    /**
     * @return the recaudacionEgresoItems
     */
    public List<RecaudacionEgreso> getRecaudacionEgresoItems() {
        return recaudacionEgresoItems;
    }

    /**
     * @param recaudacionEgresoItems the recaudacionEgresoItems to set
     */
    public void setRecaudacionEgresoItems(List<RecaudacionEgreso> recaudacionEgresoItems) {
        this.recaudacionEgresoItems = recaudacionEgresoItems;
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
     * @return the totals
     */
    public Map getTable() {
        return table;
    }

    /**
     * @param totals the totals to set
     */
    public void setTable(Map totals) {
        this.table = table;
    }

    /**
     * @return the totals
     */
    public LinkedHashMap getTotals() {
        return totals;
    }

    /**
     * @param totals the totals to set
     */
    public void setTotals(LinkedHashMap totals) {
        this.totals = totals;
    }

    /**
     * @return the resultsHeader
     */
    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    /**
     * @param resultsHeader the resultsHeader to set
     */
    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    /**
     * @return the resultsTotals
     */
    public List<String> getResultsTotals() {
        return resultsTotals;
    }

    /**
     * @param resultsTotals the resultsTotals to set
     */
    public void setResultsTotals(List<String> resultsTotals) {
        this.resultsTotals = resultsTotals;
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
     * @return the totalResumenRecaudacion
     */
    public int getTotalResumenRecaudacion() {
        return totalResumenRecaudacion;
    }

    /**
     * @param totalResumenRecaudacion the totalRecaudacion to set
     */
    public void setTotalResumenRecaudacion(int totalResumenRecaudacion) {
        this.totalResumenRecaudacion = totalResumenRecaudacion;
    }

    /**
     * @return the totalMinutos
     */
    public int getTotalMinutos() {
        return totalMinutos;
    }

    /**
     * @param totalMinutos the totalMinutos to set
     */
    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
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
     * @return the totalRecaudacionCombustible
     */
    public int getTotalRecaudacionCombustible() {
        return totalRecaudacionCombustible;
    }

    /**
     * @param totalRecaudacionCombustible the totalRecaudacionCombustible to set
     */
    public void setTotalRecaudacionCombustible(int totalRecaudacionCombustible) {
        this.totalRecaudacionCombustible = totalRecaudacionCombustible;
    }

    /**
     * @return the totalRecaudacionMinuto
     */
    public int getTotalRecaudacionMinuto() {
        return totalRecaudacionMinuto;
    }

    /**
     * @param totalRecaudacionMinuto the totalRecaudacionMinuto to set
     */
    public void setTotalRecaudacionMinuto(int totalRecaudacionMinuto) {
        this.totalRecaudacionMinuto = totalRecaudacionMinuto;
    }

    /**
     * @return the decimalFormat
     */
    public static DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * @return the recaudacionCombustible
     */
    public RecaudacionCombustible getRecaudacionCombustible() {
        return recaudacionCombustible;
    }

    /**
     * @param recaudacionCombustible the recaudacionCombustible to set
     */
    public void setRecaudacionCombustible(RecaudacionCombustible recaudacionCombustible) {
        this.recaudacionCombustible = recaudacionCombustible;
    }

    /**
     * @return the recaudacionMinuto
     */
    public RecaudacionMinuto getRecaudacionMinuto() {
        return recaudacionMinuto;
    }

    /**
     * @param recaudacionMinuto the recaudacionMinuto to set
     */
    public void setRecaudacionMinuto(RecaudacionMinuto recaudacionMinuto) {
        this.recaudacionMinuto = recaudacionMinuto;
    }

    /**
     * @return the recaudacionBoleto
     */
    public RecaudacionBoleto getRecaudacionBoleto() {
        return recaudacionBoleto;
    }

    /**
     * @param recaudacionBoleto the recaudacionBoleto to set
     */
    public void setRecaudacionBoleto(RecaudacionBoleto recaudacionBoleto) {
        this.recaudacionBoleto = recaudacionBoleto;
    }

    public String getFormatFecha(Date fecha) {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    /**
     * @param print the print to set
     */
    public void setPrint(boolean print) {
        this.print = print;
    }

    /**
     * @return the print
     */
    public boolean getPrint() {
        return print;
    }

    @Override
    public void load() {
        /* Lista de objetos recaudación*/
        this.setMapsItems(new ArrayList<>());
        this.setRecaudacionGuias(new ArrayList<>());
        this.setRecaudacionCombustibleItems(new ArrayList<>());
        this.setRecaudacionMinutoItems(new ArrayList<>());
        /* Carga información de egresos*/
        this.setEgresoItems(new ArrayList<>());
        /* Inicio de Totals*/
        this.setTotals(new LinkedHashMap());

        this.totalRecaudacion = 0;
        this.totalMinutos = 0;
        this.totalCombustible = 0;
        this.resultsTotals = new ArrayList<>();
        /*Carga de información de los buses que hay en el terminal*/
        this.busDao = new IBusDaoImpl();
        this.setBusItems(this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal()));

        /* Inicio de headers*/
        this.setResultsHeader(new ArrayList<>());

        LinkedHashMap _defaultLink = new LinkedHashMap();

        this.egresoCajaDao = new IEgresoCajaRecaudacionDaoImpl();

        List<EgresoCajaRecaudacion> auxEgreso = this.egresoCajaDao.findAllByCajaRecaudacion(cajaRecaudacion);

        for (EgresoCajaRecaudacion ecr : auxEgreso) {

            System.err.println("CANTIDAD DE EGRESOS: " + this.getCajaRecaudacion().getEgresoCajaRecaudacionList());

            Egreso egreso = ecr.getEgresoCajaRecaudacionIdEgreso();
            egreso.setEgresoValorDefecto(ecr.getEgresoCajaRecaudacionValorDefecto());
            egreso.setEgresoPorcentaje(ecr.getEgresoCajaRecaudacionPorcentaje());

            _defaultLink.put(egreso.getEgresoNombre(), 0);
            this.getEgresoItems().add(egreso);
            this.getResultsHeader().add(egreso.getEgresoNombre());
        }

        this.recaudacionDao = new IRecaudacionDaoImpl();
        this.setItems(this.recaudacionDao.findByCajaFechaRecaudacion(getCajaRecaudacion(), getFechaRecaudacion()));

        /*Si existen objetos de recaudación para la caja seleccionada a esa fecha*/
        if (!getItems().isEmpty()) {

            for (Recaudacion r : this.getItems()) {
                int _totalRecaudacion = 0;
                LinkedHashMap auxLink = new LinkedHashMap();

                if (!r.getRecaudacionEgresoList().isEmpty()) {
                    for (RecaudacionEgreso re : r.getRecaudacionEgresoList()) {
                        System.err.println("Nombre Egreso:" + re.getRecaudacionEgresoIdEgreso().getEgresoNombre() + " valor:" + re.getRecaudacionEgresoMonto());

                        _totalRecaudacion += re.getRecaudacionEgresoMonto();
                        String key = re.getRecaudacionEgresoIdEgreso().getEgresoNombre();
                        //this.getResultsHeader().add(String.valueOf(eg.getRecaudacionEgresoIdEgreso().getEgresoId()));
                        if (getTotals().containsKey(key)) {
                            int aux = (int) getTotals().get(key);
                            aux += re.getRecaudacionEgresoMonto();
                            getTotals().put(key, aux);
                        } else {
                            getTotals().put(key, re.getRecaudacionEgresoMonto());
                        }

                        auxLink.put(re.getRecaudacionEgresoIdEgreso().getEgresoNombre(), re.getRecaudacionEgresoMonto());
                    }
                    r.setLink(auxLink);
                    this.getMapsItems().add(r.getLink());

                } else {
                    r.setLink(_defaultLink);
                    this.getMapsItems().add(r.getLink());
                }

                /*Carga información de combustible por cada recaudación*/
                if (!r.getRecaudacionCombustibleList().isEmpty()) {
                    int _auxCombustible = 0;

                    for (RecaudacionCombustible c : r.getRecaudacionCombustibleList()) {
                        _auxCombustible += c.getRecaudacionCombustibleMonto();
                    }
                    r.setTotalCombustible(_auxCombustible);
                    this.setTotalCombustible(this.getTotalCombustible() + _auxCombustible);
                    this.recaudacionCombustibleItems.addAll(r.getRecaudacionCombustibleList());
                }

                /*Carga información de minutos por cada recaudación*/
                if (!r.getRecaudacionMinutoList().isEmpty()) {
                    int _auxMinutos = 0;

                    for (RecaudacionMinuto m : r.getRecaudacionMinutoList()) {
                        _auxMinutos += m.getRecaudacionMinutoMonto();
                    }
                    r.setTotalMinutos(_auxMinutos);
                    this.setTotalMinutos(this.getTotalMinutos() + _auxMinutos);
                    this.recaudacionMinutoItems.addAll(r.getRecaudacionMinutoList());
                }

                r.setRecaudacionTotal(_totalRecaudacion);

                totalRecaudacion += r.getRecaudacionTotal();

                if (r.getRecaudacionIdBus().getBusIdUnidadNegocio().getUnidadNegocioIdOperadorTransporte().getOperadorTransporteRecaudaGuias() && r.getRecaudacionTotal()>0 ) {
                    this.getRecaudacionGuias().add(r);
                }

            }

            /*Si no existen objetos de recaudación para la caja seleccionada a esa fecha*/
        } else {
            LinkedHashMap auxLink = new LinkedHashMap();

            for (Egreso eg : this.getEgresoItems()) {
                if (eg.getEgresoObligatorio()) {
                    this.getTotals().put(eg.getEgresoNombre(), 0);
                    this.getResultsTotals().add("0");
                    this.getResultsHeader().add(eg.getEgresoNombre());
                    auxLink.put(eg.getEgresoNombre(), 0);
                }
            }

            this.getMapsItems().add(auxLink);
        }

        this.setRecaudacionDataModel(new RecaudacionDataModel(getRecaudacionGuias()));

        System.err.println("total de elementos en el array de valores:" + this.totals.size());

        this.columnModelItems = new ArrayList<>();
        if (this.totals.size() > 0) {
            for (Object i : totals.values()) {
                resultsTotals.add(decimalFormat.format((int) i));

            }
        } else {
            for (Egreso eg : this.getEgresoItems()) {
                if (eg.getEgresoObligatorio()) {
                    this.getResultsTotals().add("0");
                }
            }
        }

        //verificar totales
        System.err.println("TAMAÑO DE HEADER:" + this.getResultsHeader().size());
        System.err.println("TAMAÑO DE footer:" + this.getResultsTotals().size());

        this.table = new HashMap();

        resumen();
    }

    public void loadGuia() {
        if (this.getSelected() != null) {
            //this.guiaLink = this.getSelected().getLink();
            this.recaudacionEgresoItems = this.getSelected().getRecaudacionEgresoList();
            //this.porcentajesList = new ArrayList<PorcentajeHelper>();

            for (RecaudacionEgreso eg : this.recaudacionEgresoItems) {
                if (this.totals.containsKey(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre())) {
                    int val = (int) this.totals.get(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre());
                    val -= eg.getRecaudacionEgresoMonto();
                    this.totals.put(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre(), val);
                }
            }
        }
    }

    @Override
    public Recaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.        
        this.setRecaudacionEgresoItems(egresosRecaudacion(this.getSelected()));
        this.getSelected().setRecaudacionFecha(fechaRecaudacion);
        this.getSelected().setRecaudacionIdCaja(cajaRecaudacion);
        return this.getSelected();
    }
    
    

    @Override
    public void saveNew(ActionEvent event) {
        int _petroleo = 0;
        /*INFORMACIÓN DE RECAUDACION DE COMBUSTIBLE*/
        if (this.getSelectedPetroleoHelper() != null) {

            List<RecaudacionCombustible> listRecaudacionCombustible = new ArrayList<RecaudacionCombustible>();

            for (PetroleoHelper p : this.getSelectedPetroleoHelper()) {
                VentaCombustible venta = p.getVentaCombustible();
                venta.setVentaCombustibleRecaudado(true);
                _petroleo += venta.getVentaCombustibleTotal();

                RecaudacionCombustible recaudacionCombustible = new RecaudacionCombustible();
                recaudacionCombustible.setRecaudacionCombustibleIdRecaudacion(this.getSelected());
                recaudacionCombustible.setRecaudacionCombustibleIdVentaCombustible(venta);
                recaudacionCombustible.setRecaudacionCombustibleMonto(venta.getVentaCombustibleTotal());

                listRecaudacionCombustible.add(recaudacionCombustible);

                this.ventaCombustibleFacade.edit(venta);
            }

            this.getSelected().setTotalCombustible(_petroleo);
            System.err.println("VALOR petroleo:" + this.getSelected().getTotalCombustible());
            this.setTotalCombustible(this.getTotalCombustible() + _petroleo);
            this.getSelected().setRecaudacionCombustibleList(listRecaudacionCombustible);

            this.setVentaCombustibleItems(null);

        }

        int _minutos = 0;
        /*INFORMACIÓN DE RECAUDACIÓN DE MINUTOS*/
        if (this.getSelectedMinutosHelper() != null) {

            List<RecaudacionMinuto> listRecaudacionMinutos = new ArrayList<>();

            for (MinutosHelper m : this.getSelectedMinutosHelper()) {
                m.getRegistro().setRegistroMinutoRecaudado(Boolean.TRUE);
                this.registroMinutoFacade.edit(m.getRegistro());
                _minutos += m.getRegistro().getRegistroMinutoMonto();
                RecaudacionMinuto _rm = new RecaudacionMinuto();
                _rm.setRecaudacionMinutoIdRecaudacion(this.getSelected());
                _rm.setRecaudacionMinutoIdRegistroMinuto(m.getRegistro());
                _rm.setRecaudacionMinutoMonto(m.getRegistro().getRegistroMinutoMonto());

                listRecaudacionMinutos.add(_rm);

            }

            this.getSelected().setRecaudacionMinutoList(listRecaudacionMinutos);
            this.getSelected().setTotalMinutos(_minutos);
            System.err.println("VALOR minutos:" + this.getSelected().getTotalMinutos());
            this.setTotalMinutos(this.getTotalMinutos() + _minutos);
            this.setRegistroMinutoItems(null);

        }

        this.getSelected().setRecaudacionHora(new Date());
        this.getSelected().setRecaudacionEgresoList(recaudacionEgresoItems);

        this.facade.create(this.getSelected());

        LinkedHashMap auxLink = new LinkedHashMap();
        int _totalRecaudacion = 0;
        if (!this.getSelected().getRecaudacionEgresoList().isEmpty()) {

            for (RecaudacionEgreso eg : this.getSelected().getRecaudacionEgresoList()) {
                _totalRecaudacion += eg.getRecaudacionEgresoMonto();
                String key = eg.getRecaudacionEgresoIdEgreso().getEgresoNombre();
                //this.getResultsHeader().add(String.valueOf(eg.getRecaudacionEgresoIdEgreso().getEgresoId()));
                if (getTotals().containsKey(key)) {
                    int aux = (int) getTotals().get(key);
                    aux += eg.getRecaudacionEgresoMonto();
                    getTotals().put(key, aux);
                } else {
                    getTotals().put(key, eg.getRecaudacionEgresoMonto());
                }
                auxLink.put(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre(), eg.getRecaudacionEgresoMonto());
            }

            this.getSelected().setLink(auxLink);
            //RecaudacionBuilder builder = new RecaudacionBuilder(this.getSelected());

            _totalRecaudacion += _minutos + _petroleo;

            this.getSelected().setRecaudacionTotal(_totalRecaudacion);
            this.totalRecaudacion = this.totalRecaudacion + _totalRecaudacion;
            this.getSelected().setTotalMinutos(_minutos);
            this.getSelected().setTotalCombustible(_petroleo);

            System.err.println("minutos:" + this.getSelected().getTotalMinutos() + " petroleo" + this.getSelected().getTotalCombustible() + " recaudacion" + this.getSelected().getRecaudacionTotal());
            System.err.println("minutos:" + _minutos + " petroleo" + _petroleo + " recaudacion" + _totalRecaudacion);

            //this.getSelected().setLink(builder.getLink());
            this.getMapsItems().add(this.getSelected().getLink());

        }

        /*for (RecaudacionEgreso eg : this.getSelected().getRecaudacionEgresoList()) {
            if (eg.getRecaudacionEgresoIdEgreso().getEgresoObligatorio()) {
                String key = eg.getRecaudacionEgresoIdEgreso().getEgresoNombre();

                if (totals.containsKey(key)) {
                    int aux = (int) totals.get(key);
                    aux += eg.getRecaudacionEgresoMonto();
                    totals.put(key, aux);
                } else {
                    totals.put(key, eg.getRecaudacionEgresoMonto());
                }
                auxLink.put(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre(), eg.getRecaudacionEgresoMonto());
            }
        }
        this.getSelected().setLink(auxLink);
        this.mapsItems.add(this.getSelected().getLink());
         */
        this.resultsTotals = new ArrayList<>();

        totals.values().stream().forEach((i) -> {
            this.resultsTotals.add(decimalFormat.format((int) i));
        });

        //dudas acá
        /*this.egresosResumenList.stream().forEach((eg) -> {
            if (totals.containsKey(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre())) {
                eg.setEgresoResumenRecaudacionTotal((int) totals.get(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre()));
            }
        });*/
        this.getItems().add(this.getSelected());

        JsfUtil.addSuccessMessage("Se ha ingresado la Guia N°" + this.getSelected().getRecaudacionIdentificador());

        //mostar resumen total acá
        this.setSelectedPetroleoHelper(null);
        this.setSelectedMinutosHelper(null);
        this.recaudacionEgresoItems = null;

        this.setTotalRecaudacionMinuto(0);
        this.setTotalRecaudacionCombustible(0);
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {

            /*INFORMACIÓN DE VENTAS DE COMBUSTIBLES*/
            if (this.getSelected().getRecaudacionCombustibleList() != null) {

                for (RecaudacionCombustible p : this.getSelected().getRecaudacionCombustibleList()) {
                    VentaCombustible venta = p.getRecaudacionCombustibleIdVentaCombustible();
                    venta.setVentaCombustibleRecaudado(Boolean.FALSE);
                    this.ventaCombustibleFacade.edit(venta);
                    this.recaudacionCombustibleFacade.remove(p);
                }
            }

            /*INFORMACIÓN DE RECAUDACIÓN DE MINUTOS*/
            if (this.getSelected().getRecaudacionMinutoList() != null) {

                List<RecaudacionMinuto> listRecaudacionMinutos = new ArrayList<>();

                for (RecaudacionMinuto m : this.getSelected().getRecaudacionMinutoList()) {
                    RegistroMinuto r = m.getRecaudacionMinutoIdRegistroMinuto();
                    r.setRegistroMinutoRecaudado(Boolean.FALSE);
                    this.registroMinutoFacade.edit(r);
                }

            }

            for (RecaudacionEgreso r : this.getSelected().getRecaudacionEgresoList()) {
                String key = r.getRecaudacionEgresoIdEgreso().getEgresoNombre();
                if (getTotals().containsKey(key)) {
                    int aux = (int) getTotals().get(key);
                    aux -= r.getRecaudacionEgresoMonto();
                    getTotals().put(key, aux);
                }
            }

            this.facade.remove(this.getSelected());

            load();
        } else {
            System.err.println("El valor seleccionado es nulo");
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getSelected().getRecaudacionIdBus() != null) {
            this.setRegistroMinutoItems(null);
            this.setSelectedMinutosHelper(new ArrayList<>());
            this.setVentaCombustibleItems(null);
            this.setSelectedPetroleoHelper(null);

            this.registroMinutoDao = new IRegistroMinutoDaoImpl();

            List<RegistroMinuto> minutosList = this.registroMinutoDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());

            if (minutosList.isEmpty()) {
                //deshabilito el combobox
            } else {
                System.err.println("EL BUS TIENE MINUTOS");
                this.setRegistroMinutoItems(new ArrayList<>());
                //Si tiene más de una deuda de minutos, busco el total 
                if (minutosList.size() > 1) {
                    System.err.println("MÁS DE UN MINUTO");
                    int total = 0;
                    for (RegistroMinuto m : minutosList) {
                        total = total + m.getRegistroMinutoMonto();
                        MinutosHelper minuto = new MinutosHelper();
                        minuto.setRegistro(m);
                        minuto.setObservacion("$ " + decimalFormat.format(m.getRegistroMinutoMonto()) + "   N° Bus: " + m.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + currentDate.format(m.getRegistroMinutoFechaMinuto()));
                        this.getRegistroMinutoItems().add(minuto);
                        this.selectedMinutosHelper.add(minuto);
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

            this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();

            List<VentaCombustible> combustibleList = this.ventaCombustibleDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());

            if (combustibleList.isEmpty()) {
            } else {
                if (combustibleList.size() > 1) {

                    this.setVentaCombustibleItems(new ArrayList<>());

                    int totalCombustible = 0;

                    for (VentaCombustible v : combustibleList) {
                        PetroleoHelper vPetroleo = new PetroleoHelper();
                        vPetroleo.setObservacion("$ " + decimalFormat.format(v.getVentaCombustibleTotal()) + " -  " + currentDate.format(v.getVentaCombustibleFecha()));
                        vPetroleo.setVentaCombustible(v);
                        totalCombustible += v.getVentaCombustibleTotal();
                        this.getVentaCombustibleItems().add(vPetroleo);
                    }

                } else {

                    this.setVentaCombustibleItems(new ArrayList<>());

                    VentaCombustible vb = combustibleList.get(0);

                    PetroleoHelper ph = new PetroleoHelper();
                    ph.setVentaCombustible(vb);
                    ph.setObservacion("$ " + decimalFormat.format(vb.getVentaCombustibleTotal()) + " -  " + currentDate.format(vb.getVentaCombustibleFecha()));

                    this.getVentaCombustibleItems().add(ph);

                }

            }

        }

        this.setTotalRecaudacionMinuto(0);
        this.setTotalRecaudacionCombustible(0);
        this.getSelected().setRecaudacionTotal(0);

    }

    private List<RecaudacionEgreso> egresosRecaudacion(Recaudacion recaudacion) {
        List<RecaudacionEgreso> egresoGuiaAuxList = new ArrayList<>();

        int i = 0;

        for (Egreso e : this.egresoItems) {

            if (e.getEgresoActivo()) {
                RecaudacionEgreso egresoGuia = new RecaudacionEgreso();
                egresoGuia.setRecaudacionEgresoIdRecaudacion(recaudacion);
                egresoGuia.setRecaudacionEgresoIdEgreso(e);
                egresoGuia.setRecaudacionEgresoMonto(e.getEgresoValorDefecto());

                egresoGuiaAuxList.add(egresoGuia);
            }

            i++;
        }

        return egresoGuiaAuxList;
    }

    /**
     * @param value to format
     */
    public String getFormatValue(int value) {
        return decimalFormat.format(value);
    }

    /**
     * Suma todos los egresos de la guía junto al petroleo y a los minutos
     *
     * @return total calculado
     */
    public int calculaTotal() {
        int total = 0;
        int _minutos = 0;
        int _petroleo = 0;

        if (recaudacionEgresoItems != null) {
            for (RecaudacionEgreso r : recaudacionEgresoItems) {
                total = total + r.getRecaudacionEgresoMonto();
            }
        }

        if (this.getSelectedPetroleoHelper() != null) {
            for (PetroleoHelper p : this.getSelectedPetroleoHelper()) {
                _petroleo = _petroleo + p.getVentaCombustible().getVentaCombustibleTotal();
            }
        }

        this.getSelected().setTotalCombustible(_petroleo);

        if (this.getSelectedMinutosHelper() != null) {

            for (MinutosHelper m : this.getSelectedMinutosHelper()) {
                _minutos = _minutos + m.getRegistro().getRegistroMinutoMonto();
            }
        }

        this.getSelected().setTotalMinutos(_minutos);

        this.setTotalRecaudacionMinuto(_minutos);
        this.setTotalRecaudacionCombustible(_petroleo);

        total += _petroleo + _minutos;

        this.getSelected().setRecaudacionTotal(total);
        return total;
    }

    public void resumen() {
        this.columnModelItems = new ArrayList<>();
        Column model;
        for (Egreso e : this.egresoItems) {
            String key = e.getEgresoNombre();

            model = new Column();
            model.setNombre(key);

            if (this.totals.containsKey(key)) {
                model.setValor((int) this.totals.get(key));
            } else {
                model.setValor(0);
            }
            this.columnModelItems.add(model);
        }

        model = new Column();
        model.setNombre("Petroleo");
        model.setValor((totalCombustible));
        this.columnModelItems.add(model);

        model = new Column();
        model.setNombre("Minutos");
        model.setValor((totalMinutos));
        this.columnModelItems.add(model);

        IRecaudacionExtraDao dao = new IRecaudacionExtraDaoImpl();

        List<RecaudacionExtra> aux = dao.findByCajaDate(cajaRecaudacion, fechaRecaudacion);

        this.totalResumenRecaudacion = this.totalRecaudacion;

        for (RecaudacionExtra r : aux) {
            model = new Column();
            model.setNombre(r.getRecaudacionExtraNombre());
            model.setValor(r.getRecaudacionExtraMonto());
            this.columnModelItems.add(model);
            this.totalResumenRecaudacion += r.getRecaudacionExtraMonto();
        }

    }

    public void exportPdf2(ActionEvent event) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=VentasCombustible.pdf");

        try {
            servletOutputStream = httpServletResponse.getOutputStream();
            AdhocStyle style = new AdhocStyle();

            style.setHorizontalAlignment(AdhocHorizontalAlignment.RIGHT);

            AdhocConfiguration configuration = new AdhocConfiguration();
            AdhocReport report = new AdhocReport();
            report.setColumnTitleStyle(style);
            configuration.setReport(report);

            //DRDataSource dataSource = new DRDataSource("numero", "identificador", "hora", "boleta", "bus", "ppu", "operador", "cantidad", "total");
            AdhocSubtotal subtotal = new AdhocSubtotal();

            AdhocColumn column = new AdhocColumn();
            column.setName("numero");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("folio");
            column.setWidth(70);
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("bus");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("conductor");
            column.setWidth(95);
            report.addColumn(column);

            AdhocFont font = new AdhocFont();

            font.setBold(Boolean.TRUE);
            style.setFont(font);
            for (Egreso r : this.egresoItems) {
                column = new AdhocColumn();
                column.setName(r.getEgresoNombre());
                report.addColumn(column);

                subtotal = new AdhocSubtotal();
                subtotal.setCalculation(AdhocCalculation.SUM);
                subtotal.setName(r.getEgresoNombre());
                subtotal.setStyle(style);
                report.addSubtotal(subtotal);
            }

            column = new AdhocColumn();
            column.setName("combustible");
            report.addColumn(column);

            subtotal = new AdhocSubtotal();
            subtotal.setCalculation(AdhocCalculation.SUM);
            subtotal.setName("combustible");
            subtotal.setStyle(style);
            report.addSubtotal(subtotal);

            column = new AdhocColumn();
            column.setName("minutos");
            report.addColumn(column);

            subtotal = new AdhocSubtotal();
            subtotal.setCalculation(AdhocCalculation.SUM);
            subtotal.setName("minutos");
            subtotal.setStyle(style);
            report.addSubtotal(subtotal);

            column = new AdhocColumn();
            column.setName("liquido");
            report.addColumn(column);

            subtotal = new AdhocSubtotal();
            subtotal.setCalculation(AdhocCalculation.SUM);
            subtotal.setName("liquido");
            subtotal.setStyle(style);
            report.addSubtotal(subtotal);

            JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(), new ReportCustomizer());
            reportBuilder.setDataSource(createDataSource());
            reportBuilder.toPdf(servletOutputStream);

        } catch (IOException | DRException ex) {
            Logger.getLogger(RegistroMinutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().responseComplete();

        servletOutputStream = null;
    }

    private DRDataSource createDataSource() {

        ArrayList<String> list = new ArrayList<>(resultsHeader);
        list.add(0, "liquido");
        list.add(0, "minutos");
        list.add(0, "combustible");
        list.add(0, "conductor");
        list.add(0, "bus");
        list.add(0, "folio");
        list.add(0, "numero");

        String[] array = new String[list.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = (String) list.get(i);
        }

        DRDataSource dataSource = new DRDataSource(array);

        int i = 0;
        for (Recaudacion r : this.getItems()) {

            List a = new ArrayList();

            a.add(i + 1);
            a.add(r.getRecaudacionIdentificador());
            a.add(r.getRecaudacionIdBus().getBusNumero());
            a.add(r.getRecaudacionIdTrabajador().getTrabajadorApellidoPaterno());
            a.add(r.getTotalCombustible());
            a.add(r.getTotalMinutos());
            a.add(r.getRecaudacionTotal());
            a.addAll(this.mapsItems.get(i).values());

            dataSource.add(a.toArray());
            i++;
        }
        return dataSource;
    }

    private class ReportCustomizer extends DefaultAdhocReportCustomizer {

        /**
         *
         * If you want to add some fixed content to a report that is not needed
         * to store in the xml file.
         *
         * For example you can add default page header, footer, default
         * fonts,...
         *
         */
        @Override

        public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {
            super.customize(report, adhocReport);
            //default report values
            report.setTemplate(Templates.reportTemplate);
            report.title(Templates.createTitleComponent(cajaRecaudacion.getCajaRecaudacionNombre() + " Recaudación al :" + currentDate.format(fechaRecaudacion)));
            //a fixed page footer that user cannot change, this customization is not stored in the xml file
            //report.pageFooter(Templates.footerComponent);
            report.pageFooter(Templates.footerComponent);
        }

        /**
         *
         * This method returns a field type from a given field name.
         *
         */
        @Override
        protected DRIDataType<?, ?> getFieldType(String name) {
            if (name.equals("numero") || name.equals("boleta") || name.equals("bus") || name.equals("total") || name.equals("combustible") || name.equals("minutos")) {
                return type.integerType();
            }

            if (name.equals("conductor") || name.equals("ppu") || name.equals("operador")) {
                return type.stringType();
            }

            if (name.equals("hora")) {
                return type.timeHourToMinuteType();
            }

            return type.integerType();
        }

        /**
         *
         * If a user doesn’t specify a column title, getColumnTitle is evaluated
         * and the return value is used as a column title.
         *
         */
        @Override
////DRDataSource("numero", "identificador", "hora", "boleta", "bus", "ppu", "operador", "cantidad", "total");
        protected String getFieldLabel(String name) {
            if (name.equals("numero")) {
                return "#";
            }
            if (name.equals("liquido")) {
                return "Líquido";
            }
            if (name.equals("folio")) {
                return "Folio";
            }

            if (name.equals("minutos")) {
                return "Min.";
            }

            if (name.equals("combustible")) {
                return "Comb.";
            }

            if (name.equals("bus")) {
                return "N° Bus";
            }
            if (name.equals("conductor")) {
                return "Conductor";
            }
            if (name.equals("operador")) {
                return "Operador";
            }
            if (name.equals("cantidad")) {
                return "N° Litros";
            }
            if (name.equals("total")) {
                return "Total";
            }
            return name;
        }

    }

    public class Column {

        private String nombre;
        private int valor;

        public Column() {
        }

        public Column(String nombre, int valor) {
            this.nombre = nombre;
            this.valor = valor;
        }

        public String getNombre() {
            return nombre;
        }

        public int getValor() {
            return valor;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

    }

}
