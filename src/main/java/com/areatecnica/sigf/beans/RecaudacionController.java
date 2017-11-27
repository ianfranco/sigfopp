package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.beans.util.JsfUtil;
import com.areatecnica.sigf.builders.RecaudacionBuilder;
import com.areatecnica.sigf.controllers.RecaudacionEgresoFacade;
import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.controllers.RegistroMinutoFacade;
import com.areatecnica.sigf.controllers.ResumenRecaudacionFacade;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IProcesoRecaudacionDao;
import com.areatecnica.sigf.dao.IRecaudacionDao;
import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.IResumenRecaudacionDao;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.IResumenRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.EgresoResumenRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.RecaudacionEgreso;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.models.MinutosHelper;
import com.areatecnica.sigf.models.PetroleoHelper;
import com.areatecnica.sigf.models.RecaudacionDataModel;
import com.areatecnica.sigf.reports.PdfReportController;
import com.areatecnica.sigf.reports.Templates;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

@Named(value = "recaudacionController")
@ViewScoped
public class RecaudacionController extends AbstractController<Recaudacion> {

    @Inject
    private RecaudacionFacade ejbFacade;

    @Inject
    private BusController guiaIdBusController;
    @Inject
    private CajaRecaudacionController guiaIdCajaTerminalController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;
    @Inject
    private ResumenRecaudacionFacade resumenRecaudacionFacade;    
    @Inject
    private PdfReportController pdfReportController;
    @Inject
    private VentaCombustibleFacade ventaCombustibleFacade;
    @Inject
    private RegistroMinutoFacade registroMinutoFacade;
    @Inject
    private RecaudacionEgresoFacade recaudacionEgresoFacade;

    private List<MinutosHelper> selectedMinutosHelper;
    private List<PetroleoHelper> selectedPetroleoHelper;

    private List<Recaudacion> recaudacionItems;
    private List<RecaudacionEgreso> recaudacionEgresoList;
    private List<Egreso> egresosList;
    private List<ProcesoRecaudacion> procesoRecaudacionList;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private List<EgresoResumenRecaudacion> egresosResumenList;
    private List<Bus> busesList;
    private List<PetroleoHelper> ventaCombustibleList;
    private List<MinutosHelper> registroMinutoList;
    private ArrayList<LinkedHashMap> listOfMaps;
    private LinkedHashMap guiaLink;
    private LinkedHashMap totals;
    private List<String> resultsHeader;
    private List<String> resultsTotals;
    private ProcesoRecaudacion procesoRecaudacion;
    private ResumenRecaudacion resumenRecaudacion;
    private CajaRecaudacion cajaRecaudacion;
    private RecaudacionMinuto recaudacionMinuto;
    private RecaudacionCombustible recaudacionCombustible;
    private RecaudacionDataModel model;
    private MinutosHelper registroMinuto;
    private String minutos;
    private String petroleo;
    private Date fechaRecaudacion;
    private Boolean permitirEgresoFlota;
    private Boolean permitirEgresoBus;
    private Boolean permitirEgresoProceso;
    private Boolean estadoProceso;
    private int resumenTotal;
    private int resumenCombustible;
    private int resumenMinutos;
    private int totalRecaudacion;
    private String resumenTotalFormat;
    private String resumenTotalRecaudacionFormat;
    private String resumenCombustibleFormat;
    private String resumenMinutosFormat;
    private IRecaudacionDao recaudacionDao;
    private IProcesoRecaudacionDao procesoDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IBusDao busDao;
    private IResumenRecaudacionDao resumenRecaudacionDao;
    private IRegistroMinutoDao registroMinutoDao;
    private IVentaCombustibleDao ventaCombustibleDao;
    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private ServletOutputStream servletOutputStream;

    /**
     * Initialize the concrete Region controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.setCajaRecaudacionList((List<CajaRecaudacion>) this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser()));

        if (this.getCajaRecaudacionList().size() == 1) {
            this.setCajaRecaudacion(this.getCajaRecaudacionList().get(0));

            handleCajaRecaudacionChange(null);
        }

        this.setPermitirEgresoBus(Boolean.TRUE);
        this.setPermitirEgresoFlota(Boolean.TRUE);
        this.setPermitirEgresoProceso(Boolean.TRUE);
        this.setResumenRecaudacion(new ResumenRecaudacion());
        this.getResumenRecaudacion().setResumenRecaudacionCerrado(Boolean.FALSE);
        this.setEstadoProceso(Boolean.FALSE);
        this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
        this.registroMinutoDao = new IRegistroMinutoDaoImpl();
        this.totalRecaudacion = 0;
        this.resumenCombustible = 0;
        this.resumenMinutos = 0;
    }

    public RecaudacionController() {
        // Inform the Abstract parent controller of the concrete Region Entity
        super(Recaudacion.class);
        this.fechaRecaudacion = new Date();
    }

    /**
     * @return the recaudacionItems
     */
    public List<Recaudacion> getRecaudacionItems() {
        return recaudacionItems;
    }

    /**
     * @param recaudacionItems the recaudacionItems to set
     */
    public void setRecaudacionItems(List<Recaudacion> recaudacionItems) {
        this.recaudacionItems = recaudacionItems;
    }

    public void setSelectedMinutosHelper(List<MinutosHelper> selectedMinutosHelper) {
        this.selectedMinutosHelper = selectedMinutosHelper;
    }

    public List<MinutosHelper> getSelectedMinutosHelper() {
        return selectedMinutosHelper;
    }

    public List<PetroleoHelper> getSelectedPetroleoHelper() {
        return selectedPetroleoHelper;
    }

    public void setSelectedPetroleoHelper(List<PetroleoHelper> selectedPetroleoHelper) {
        this.selectedPetroleoHelper = selectedPetroleoHelper;
    }

    /**
     * @return the egresosList
     */
    public List<Egreso> getEgresosList() {
        return egresosList;
    }

    /**
     * @param egresosList the egresosList to set
     */
    public void setEgresosList(List<Egreso> egresosList) {
        this.egresosList = egresosList;
    }

    /**
     * @return the procesoRecaudacionList
     */
    public List<ProcesoRecaudacion> getProcesoRecaudacionList() {
        return procesoRecaudacionList;
    }

    /**
     * @param procesoRecaudacionList the procesoRecaudacionList to set
     */
    public void setProcesoRecaudacionList(List<ProcesoRecaudacion> procesoRecaudacionList) {
        this.procesoRecaudacionList = procesoRecaudacionList;
    }

    /**
     * @return the cajaRecaudacionList
     */
    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    /**
     * @param cajaRecaudacionList the cajaRecaudacionList to set
     */
    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

    /**
     * @return the egresosResumenList
     */
    public List<EgresoResumenRecaudacion> getEgresosResumenList() {
        return egresosResumenList;
    }

    /**
     * @param egresosResumenList the egresosResumenList to set
     */
    public void setEgresosResumenList(List<EgresoResumenRecaudacion> egresosResumenList) {
        this.egresosResumenList = egresosResumenList;
    }

    /**
     * @return the busesList
     */
    public List<Bus> getBusesList() {
        return busesList;
    }

    /**
     * @param busesList the busesList to set
     */
    public void setBusesList(List<Bus> busesList) {
        this.busesList = busesList;
    }

    /**
     * @return the listOfMaps
     */
    public ArrayList<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    /**
     * @param listOfMaps the listOfMaps to set
     */
    public void setListOfMaps(ArrayList<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    /**
     * @return the guiaLink
     */
    public LinkedHashMap getGuiaLink() {
        return guiaLink;
    }

    /**
     * @param guiaLink the guiaLink to set
     */
    public void setGuiaLink(LinkedHashMap guiaLink) {
        this.guiaLink = guiaLink;
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
     * @return the procesoRecaudacion
     */
    public ProcesoRecaudacion getProcesoRecaudacion() {
        return procesoRecaudacion;
    }

    /**
     * @param procesoRecaudacion the procesoRecaudacion to set
     */
    public void setProcesoRecaudacion(ProcesoRecaudacion procesoRecaudacion) {
        this.procesoRecaudacion = procesoRecaudacion;
    }

    /**
     * @return the resumenRecaudacion
     */
    public ResumenRecaudacion getResumenRecaudacion() {
        return resumenRecaudacion;
    }

    /**
     * @param resumenRecaudacion the resumenRecaudacion to set
     */
    public void setResumenRecaudacion(ResumenRecaudacion resumenRecaudacion) {
        this.resumenRecaudacion = resumenRecaudacion;
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
     * @return the model
     */
    public RecaudacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(RecaudacionDataModel model) {
        this.model = model;
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
     * @return the permitirEgresoFlota
     */
    public Boolean getPermitirEgresoFlota() {
        return permitirEgresoFlota;
    }

    /**
     * @param permitirEgresoFlota the permitirEgresoFlota to set
     */
    public void setPermitirEgresoFlota(Boolean permitirEgresoFlota) {
        this.permitirEgresoFlota = permitirEgresoFlota;
    }

    /**
     * @return the permitirEgresoBus
     */
    public Boolean getPermitirEgresoBus() {
        return permitirEgresoBus;
    }

    /**
     * @param permitirEgresoBus the permitirEgresoBus to set
     */
    public void setPermitirEgresoBus(Boolean permitirEgresoBus) {
        this.permitirEgresoBus = permitirEgresoBus;
    }

    /**
     * @return the permitirEgresoProceso
     */
    public Boolean getPermitirEgresoProceso() {
        return permitirEgresoProceso;
    }

    /**
     * @param permitirEgresoProceso the permitirEgresoProceso to set
     */
    public void setPermitirEgresoProceso(Boolean permitirEgresoProceso) {
        this.permitirEgresoProceso = permitirEgresoProceso;
    }

    /**
     * @return the estadoProceso
     */
    public Boolean getEstadoProceso() {
        return estadoProceso;
    }

    /**
     * @param estadoProceso the estadoProceso to set
     */
    public void setEstadoProceso(Boolean estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    /**
     * @return the resumenTotal
     */
    public int getResumenTotal() {
        return resumenTotal;
    }

    /**
     * @param resumenTotal the resumenTotal to set
     */
    public void setResumenTotal(int resumenTotal) {
        this.resumenTotal = resumenTotal;
    }

    /**
     * @return the resumenTotalFormat
     */
    public String getResumenTotalFormat() {
        return resumenTotalFormat;
    }

    /**
     * @param resumenTotalFormat the resumenTotalFormat to set
     */
    public void setResumenTotalFormat(String resumenTotalFormat) {
        this.resumenTotalFormat = resumenTotalFormat;
    }

    /**
     * @return the recaudacionEgresoList
     */
    public List<RecaudacionEgreso> getRecaudacionEgresoList() {
        return recaudacionEgresoList;
    }

    /**
     * @param recaudacionEgresoList the recaudacionEgresoList to set
     */
    public void setRecaudacionEgresoList(List<RecaudacionEgreso> recaudacionEgresoList) {
        this.recaudacionEgresoList = recaudacionEgresoList;
    }

    /**
     * @return the pattern
     */
    public static String getPattern() {
        return pattern;
    }

    /**
     * @param aPattern the pattern to set
     */
    public static void setPattern(String aPattern) {
        pattern = aPattern;
    }

    /**
     * @return the decimalFormat
     */
    public static DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * @param aDecimalFormat the decimalFormat to set
     */
    public static void setDecimalFormat(DecimalFormat aDecimalFormat) {
        decimalFormat = aDecimalFormat;
    }

    /**
     * @return the ventaCombustibleList
     */
    public List<PetroleoHelper> getVentaCombustibleList() {
        return ventaCombustibleList;
    }

    /**
     * @param ventaCombustibleList the ventaCombustibleList to set
     */
    public void setVentaCombustibleList(List<PetroleoHelper> ventaCombustibleList) {
        this.ventaCombustibleList = ventaCombustibleList;
    }

    /**
     * @return the registroMinutoList
     */
    public List<MinutosHelper> getRegistroMinutoList() {
        return registroMinutoList;
    }

    /**
     * @param registroMinutoList the registroMinutoList to set
     */
    public void setRegistroMinutoList(List<MinutosHelper> registroMinutoList) {
        this.registroMinutoList = registroMinutoList;
    }

    /**
     * @return the registroMinuto
     */
    public MinutosHelper getRegistroMinuto() {
        return registroMinuto;
    }

    /**
     * @param registroMinuto the registroMinuto to set
     */
    public void setRegistroMinuto(MinutosHelper registroMinuto) {
        this.registroMinuto = registroMinuto;
    }

    public void setPetroleo(String petroleo) {
        this.petroleo = petroleo;
    }

    public String getPetroleo() {
        return petroleo;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getMinutos() {
        return minutos;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public String getResumenTotalRecaudacionFormat() {
        return resumenTotalRecaudacionFormat;
    }

    public void setResumenTotalRecaudacionFormat(String resumenTotalRecaudacionFormat) {
        this.resumenTotalRecaudacionFormat = resumenTotalRecaudacionFormat;
    }

    public String getResumenMinutosFormat() {
        return resumenMinutosFormat;
    }

    public void setResumenMinutosFormat(String resumenMinutosFormat) {
        this.resumenMinutosFormat = resumenMinutosFormat;
    }

    public String getResumenCombustibleFormat() {
        return resumenCombustibleFormat;
    }

    public void setResumenCombustibleFormat(String resumenCombustibleFormat) {
        this.resumenCombustibleFormat = resumenCombustibleFormat;
    }
        
    public void load() {
        this.egresosList = new ArrayList<>();
        this.setListOfMaps(new ArrayList<>());
        this.setResultsHeader(new ArrayList<>());
        this.resultsTotals = new ArrayList<>();
        this.resumenRecaudacion = new ResumenRecaudacion();
        this.totalRecaudacion = 0;
        this.totals = new LinkedHashMap();

        /*NOMBRE DE LAS COLUMNAS*/
        if (this.permitirEgresoProceso) {

            System.err.println("CANTIDAD DE EGRESOS X PROCESO: " + this.procesoRecaudacion.getEgresoProcesoRecaudacionList().size());

            for (EgresoProcesoRecaudacion epr : this.procesoRecaudacion.getEgresoProcesoRecaudacionList()) {

                //if (epr.getEgresoProcesoRecaudacionActivo()) {
                System.err.println("Tipo:" + epr.getEgresoProcesoRecaudacionActivo() + " Nombre:" + epr.getEgresoProcesoRecaudacionIdEgreso().getEgresoNombre());

                Egreso egreso = epr.getEgresoProcesoRecaudacionIdEgreso();
                egreso.setEgresoValorDefecto(epr.getEgresoProcesoRecaudacionValorDefecto());
                egreso.setEgresoPorcentaje(epr.getEgresoProcesoRecaudacionPorcentaje());
                this.egresosList.add(egreso);
                //}

            }

        } else {
            for (EgresoCajaRecaudacion ecr : this.cajaRecaudacion.getEgresoCajaRecaudacionList()) {
                Egreso egreso = ecr.getEgresoCajaRecaudacionIdEgreso();
                egreso.setEgresoValorDefecto(ecr.getEgresoCajaRecaudacionValorDefecto());
                egreso.setEgresoPorcentaje(ecr.getEgresoCajaRecaudacionPorcentaje());
                this.egresosList.add(egreso);
            }
        }

        Collections.sort(this.egresosList, new Comparator<Egreso>() {
            @Override
            public int compare(Egreso o1, Egreso o2) {
                if (o1.getEgresoNumeroOrden() == o2.getEgresoNumeroOrden()) {
                    return 0;
                } else if (o1.getEgresoNumeroOrden() < o2.getEgresoNumeroOrden()) {
                    return -1;
                }
                return 1;
            }
        });

        this.recaudacionDao = new IRecaudacionDaoImpl();
        this.recaudacionItems = this.recaudacionDao.findByCajaFechaRecaudacion(cajaRecaudacion, fechaRecaudacion);
        JsfUtil.addSuccessMessage("Cantidad de Guías Registradas: " + this.recaudacionItems.size());

        /*Si la lista no está vacía*/
        if (!this.recaudacionItems.isEmpty()) {
            /*Proceso de carga de egresos a partir de cada recaudación*/
            for (Recaudacion g : this.recaudacionItems) {
                //Se crea un nuevo linkedhashmap que contendrá todos los egresos de cada recaudación 
                LinkedHashMap auxLink = new LinkedHashMap();

                if (!g.getRecaudacionEgresoList().isEmpty()) {
                    for (RecaudacionEgreso eg : g.getRecaudacionEgresoList()) {

                        String key = eg.getRecaudacionEgresoIdEgreso().getEgresoNombre();
                        //this.getResultsHeader().add(String.valueOf(eg.getRecaudacionEgresoIdEgreso().getEgresoId()));
                        if (totals.containsKey(key)) {
                            int aux = (int) totals.get(key);
                            aux += eg.getRecaudacionEgresoMonto();
                            totals.put(key, aux);
                        } else {
                            totals.put(key, eg.getRecaudacionEgresoMonto());
                        }
                        auxLink.put(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre(), eg.getRecaudacionEgresoMonto());

                    }

                    //Ordenar lista
                    this.getResultsHeader().addAll(this.egresosList.stream().map(e -> e.getEgresoNombre()).collect(Collectors.toList()));

                    RecaudacionBuilder builder = new RecaudacionBuilder(g);

                    g.setLink(builder.getLink());
                    this.getListOfMaps().add(g.getLink());

                    //Fin verificación recaudación sin egresos 
                } else {
                    LinkedHashMap _auxLink = new LinkedHashMap();

                    for (Egreso eg : this.egresosList) {

                        this.totals.put(eg.getEgresoNombre(), 0);
                        this.resultsTotals.add("0");
                        this.resultsHeader.add(eg.getEgresoNombre());
                        _auxLink.put(eg.getEgresoNombre(), 0);

                    }

                    this.listOfMaps.add(_auxLink);
                }
                this.totalRecaudacion += g.getRecaudacionTotal();

                if (!g.getRecaudacionCombustibleList().isEmpty()) {
                    int _auxCombustible = 0;

                    for (RecaudacionCombustible c : g.getRecaudacionCombustibleList()) {
                        _auxCombustible += c.getRecaudacionCombustibleMonto();
                    }
                    g.setTotalCombustible(_auxCombustible);
                    this.resumenCombustible += _auxCombustible;
                }

                if (!g.getRecaudacionMinutoList().isEmpty()) {
                    int _auxMinutos = 0;

                    for (RecaudacionMinuto m : g.getRecaudacionMinutoList()) {
                        _auxMinutos += m.getRecaudacionMinutoMonto();
                    }
                    g.setTotalMinutos(_auxMinutos);
                    this.resumenMinutos += _auxMinutos;
                }

            }
            //Fin verificación lista vacía 
        } else {
            LinkedHashMap auxLink = new LinkedHashMap();

            for (Egreso eg : this.egresosList) {
                if (eg.getEgresoObligatorio()) {
                    this.totals.put(eg.getEgresoNombre(), 0);
                    this.resultsTotals.add("0");
                    this.resultsHeader.add(eg.getEgresoNombre());
                    auxLink.put(eg.getEgresoNombre(), 0);
                }
            }

            this.listOfMaps.add(auxLink);
        }

        if (!this.recaudacionItems.isEmpty()) {
            for (Object i : totals.values()) {
                resultsTotals.add(decimalFormat.format((int) i));
            }
        }

        this.setModel(new RecaudacionDataModel(recaudacionItems));

        this.setBusesList(busesProceso(this.procesoRecaudacion));
        JsfUtil.addSuccessMessage("N° de Buses en el Proceso: " + this.getBusesList().size());

        this.resumenRecaudacionDao = new IResumenRecaudacionDaoImpl();
        this.resumenRecaudacion = this.resumenRecaudacionDao.findByCajaProcesoDate(cajaRecaudacion, procesoRecaudacion, fechaRecaudacion);

        if (this.resumenRecaudacion == null) {

            this.resumenRecaudacion = new ResumenRecaudacion();
            
            this.resumenRecaudacion.setResumenRecaudacionFecha(fechaRecaudacion);
            this.resumenRecaudacion.setResumenRecaudacionIdCaja(cajaRecaudacion);
            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
            this.resumenRecaudacion.setResumenRecaudacionIdProceso(procesoRecaudacion);
            this.resumenRecaudacion.setResumenRecaudacionTotal(0);

            this.egresosResumenList = new ArrayList<>();
            this.egresosList.stream().map((eg) -> {
                EgresoResumenRecaudacion egresoRecaudacion = new EgresoResumenRecaudacion();
                egresoRecaudacion.setEgresoResumenRecaudacionIdResumen(resumenRecaudacion);
                egresoRecaudacion.setEgresoResumenRecaudacionIdEgreso(eg);
                return egresoRecaudacion;
            }).map((egresoRecaudacion) -> {
                egresoRecaudacion.setEgresoResumenRecaudacionTotal(0);
                return egresoRecaudacion;
            }).forEach((egresoRecaudacion) -> {
                this.egresosResumenList.add(egresoRecaudacion);
            });
            //prueba
            this.listOfMaps.add(new LinkedHashMap());
            this.resumenRecaudacion.setEgresoResumenRecaudacionList(this.egresosResumenList);
            this.resumenRecaudacionFacade.create(this.resumenRecaudacion);

        } else {
            this.egresosResumenList = this.resumenRecaudacion.getEgresoResumenRecaudacionList();

            this.egresosResumenList.stream().forEach((EgresoResumenRecaudacion eg) -> {
                int val = (int) totals.get(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre());
                System.err.println(":" + val);
                eg.setEgresoResumenRecaudacionTotal(val);
            });
            this.resumenRecaudacion.setEgresoResumenRecaudacionList(this.egresosResumenList);

        }

        this.setResumenTotalFormat(decimalFormat.format(getResumenTotal()));
        this.resumenTotalRecaudacionFormat = decimalFormat.format(totalRecaudacion);
        this.resumenMinutosFormat = decimalFormat.format(resumenMinutos);
        this.resumenCombustibleFormat = decimalFormat.format(resumenCombustible);
    }

    @Override
    public Recaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.resumenTotalFormat = "$ 0";
        this.setRecaudacionEgresoList(egresosRecaudacion(this.getSelected()));
        this.getSelected().setRecaudacionFecha(fechaRecaudacion);
        this.getSelected().setRecaudacionIdCaja(cajaRecaudacion);
        return this.getSelected();
    }

    private List<RecaudacionEgreso> egresosRecaudacion(Recaudacion recaudacion) {
        List<RecaudacionEgreso> egresoGuiaAuxList = new ArrayList<>();

        int i = 0;

        for (Egreso e : this.egresosList) {

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

    @Override
    public void save(ActionEvent event) {

        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {

        /*INFORMACIÓN DE RECAUDACION DE COMBUSTIBLE*/
        if (this.selectedPetroleoHelper != null) {

            List<RecaudacionCombustible> listRecaudacionCombustible = new ArrayList<RecaudacionCombustible>();

            for (PetroleoHelper p : this.selectedPetroleoHelper) {
                VentaCombustible venta = p.getVentaCombustible();
                venta.setVentaCombustibleRecaudado(true);
                RecaudacionCombustible recaudacionCombustible = new RecaudacionCombustible();
                recaudacionCombustible.setRecaudacionCombustibleIdRecaudacion(this.getSelected());
                recaudacionCombustible.setRecaudacionCombustibleIdVentaCombustible(venta);
                recaudacionCombustible.setRecaudacionCombustibleMonto(venta.getVentaCombustibleTotal());

                listRecaudacionCombustible.add(recaudacionCombustible);

                this.ventaCombustibleFacade.edit(venta);
            }

            this.getSelected().setRecaudacionCombustibleList(listRecaudacionCombustible);

            this.ventaCombustibleList = null;

        } else {
            System.err.println("Si es nula la venta de combustible");
        }

        /*INFORMACIÓN DE RECAUDACIÓN DE MINUTOS*/
        if (this.selectedMinutosHelper != null) {

            List<RecaudacionMinuto> listRecaudacionMinutos = new ArrayList<RecaudacionMinuto>();

            for (MinutosHelper m : this.selectedMinutosHelper) {
                m.getRegistro().setRegistroMinutoRecaudado(Boolean.TRUE);
                this.registroMinutoFacade.edit(m.getRegistro());

                RecaudacionMinuto recaudacionMinuto = new RecaudacionMinuto();
                recaudacionMinuto.setRecaudacionMinutoIdRecaudacion(this.getSelected());
                recaudacionMinuto.setRecaudacionMinutoIdRegistroMinuto(m.getRegistro());
                recaudacionMinuto.setRecaudacionMinutoMonto(m.getRegistro().getRegistroMinutoMonto());

                listRecaudacionMinutos.add(recaudacionMinuto);

            }

            /*if (this.registroMinuto.getTodos()) {
                System.err.println("estan seleccionados todos los REGISTROs DE MINUTOs");

                this.registroMinutoList.remove(0);

                this.registroMinutoList.stream().map((m) -> m.getRegistro()).map((registro) -> {
                    registro.setRegistroMinutoRecaudado(Boolean.TRUE);

                    return registro;
                }).forEachOrdered((registro) -> {
                    this.registroMinutoFacade.edit(registro);
                    RecaudacionMinuto recaudacionMinuto = new RecaudacionMinuto();
                    recaudacionMinuto.setRecaudacionMinutoIdRecaudacion(this.getSelected());
                    recaudacionMinuto.setRecaudacionMinutoIdRegistroMinuto(registro);
                    recaudacionMinuto.setRecaudacionMinutoMonto(registro.getRegistroMinutoMonto());

                    listRecaudacionMinutos.add(recaudacionMinuto);
                });

            } else {
                System.err.println("esta seleccionado sólo 1 REGISTRO DE MINUTO");

                RegistroMinuto registro = this.registroMinuto.getRegistro();
                registro.setRegistroMinutoRecaudado(Boolean.TRUE);
                this.registroMinutoFacade.edit(registro);

                RecaudacionMinuto recaudacionMinuto = new RecaudacionMinuto();
                recaudacionMinuto.setRecaudacionMinutoIdRecaudacion(this.getSelected());
                recaudacionMinuto.setRecaudacionMinutoIdRegistroMinuto(registro);
                recaudacionMinuto.setRecaudacionMinutoMonto(registro.getRegistroMinutoMonto());

                listRecaudacionMinutos.add(recaudacionMinuto);

            }*/
            this.getSelected().setRecaudacionMinutoList(listRecaudacionMinutos);

            this.registroMinuto = null;
            this.registroMinutoList = null;

        } else {
            System.err.println("SI ES NULO EL REGISTRO DE MINUTO");
        }

        this.getSelected().setRecaudacionHora(new Date());
        this.getSelected().setRecaudacionEgresoList(recaudacionEgresoList);

        this.ejbFacade.create(this.getSelected());

        //this.getSelected().setRecaudacionCombustibleList(recaudacionCombustibleList);
        if (this.resumenRecaudacion.getResumenRecaudacionCerrado()) {
            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
            this.resumenRecaudacionFacade.edit(resumenRecaudacion);
        }

        /*
         * Agrega nueva fila a la tabla
         *
         */
        LinkedHashMap auxLink = new LinkedHashMap();
        for (RecaudacionEgreso eg : this.getSelected().getRecaudacionEgresoList()) {
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
        this.getListOfMaps().add(this.getSelected().getLink());

        this.resultsTotals = new ArrayList<>();

        totals.values().stream().forEach((i) -> {
            this.resultsTotals.add(decimalFormat.format((int) i));
        });

        //dudas acá
        this.egresosResumenList.stream().forEach((eg) -> {
            if (totals.containsKey(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre())) {
                eg.setEgresoResumenRecaudacionTotal((int) totals.get(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre()));
            }
        });

        this.recaudacionItems.add(this.getSelected());

        //notificationController.pushNotify("/notify", "Se ha ingresado la Guia N°" + this.getSelected().getRecaudacionIdentificador());
        JsfUtil.addSuccessMessage("Se ha ingresado la Guia N°" + this.getSelected().getRecaudacionIdentificador());

        this.setResumenTotalFormat(decimalFormat.format(getResumenTotal()));

        this.minutos = this.decimalFormat.format(0);
        this.petroleo = this.decimalFormat.format(0);
        this.selectedPetroleoHelper = null;
        this.selectedMinutosHelper = null;
        this.recaudacionEgresoList = null;

        calculaTotal();
    }

    private List<Bus> busesProceso(ProcesoRecaudacion procesoRecaudacion) {
        this.busDao = new IBusDaoImpl();
        return this.busDao.findByProceso(procesoRecaudacion);
    }

    public void loadGuia() {
        if (this.getSelected() != null) {
            this.guiaLink = this.getSelected().getLink();
            this.recaudacionEgresoList = this.getSelected().getRecaudacionEgresoList();
            //this.porcentajesList = new ArrayList<PorcentajeHelper>();

            for (RecaudacionEgreso eg : this.recaudacionEgresoList) {
                if (this.totals.containsKey(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre())) {
                    int val = (int) this.totals.get(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre());
                    val -= eg.getRecaudacionEgresoMonto();
                    this.totals.put(eg.getRecaudacionEgresoIdEgreso().getEgresoNombre(), val);
                }
            }
        }
    }

    public int calculaTotal() {
        int total = 0;
        int _minutos = 0;
        int _petroleo = 0;

        for (RecaudacionEgreso r : recaudacionEgresoList) {
            total = total + r.getRecaudacionEgresoMonto();
        }

        if (this.selectedPetroleoHelper != null) {
            for (PetroleoHelper p : this.selectedPetroleoHelper) {
                _petroleo = _petroleo + p.getVentaCombustible().getVentaCombustibleTotal();
            }
        }

        this.getSelected().setTotalCombustible(_petroleo);

        if (this.selectedMinutosHelper != null) {

            for (MinutosHelper m : this.selectedMinutosHelper) {
                _minutos = _minutos + m.getRegistro().getRegistroMinutoMonto();
            }
        }

        this.getSelected().setTotalMinutos(_minutos);

        this.minutos = this.decimalFormat.format(_minutos);
        this.petroleo = this.decimalFormat.format(_petroleo);

        total += _petroleo + _minutos;

        this.getSelected().setRecaudacionTotal(total);
        this.resumenTotalFormat = decimalFormat.format(total);
        return total;
    }

    public int calculaTotalMinutos() {
        int total = calculaTotal();

        if (this.registroMinuto != null) {
            if (this.registroMinuto.getRegistro().getRegistroMinutoDesdeIdBus() == null) {
                this.minutos = "Sin deuda";
            } else {
                total = total + registroMinuto.getRegistro().getRegistroMinutoMonto();
            }
        }

        this.getSelected().setRecaudacionTotal(total);

        return total;
    }

    public void handleCajaRecaudacionChange(ActionEvent event) {
        if (this.getCajaRecaudacion() != null) {
            this.setProcesoRecaudacionList(new ArrayList<ProcesoRecaudacion>());

            List<CajaProceso> cajaProcesoList = this.getCajaRecaudacion().getCajaProcesoList();

            for (CajaProceso cp : cajaProcesoList) {
                if (cp.getCajaProcesoActivo()) {
                    this.getProcesoRecaudacionList().add(cp.getCajaProcesoIdProceso());
                }
            }

            if (this.getProcesoRecaudacionList().size() == 1) {
                this.procesoRecaudacion = this.procesoRecaudacionList.get(0);
            }
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getSelected().getRecaudacionIdBus() != null) {
            this.registroMinutoList = null;
            this.ventaCombustibleList = null;
            this.minutos = "";
            this.petroleo = "";
            this.registroMinuto = null;

            List<RegistroMinuto> minutosList = this.registroMinutoDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());

            if (minutosList.isEmpty()) {
                //deshabilito el combobox
                this.registroMinuto = null;
            } else {
                System.err.println("EL BUS TIENE MINUTOS");
                this.registroMinutoList = new ArrayList<>();
                //Si tiene más de una deuda de minutos, busco el total 
                if (minutosList.size() > 1) {
                    System.err.println("MÁS DE UN MINUTO");
                    int total = 0;
                    for (RegistroMinuto m : minutosList) {
                        total = total + m.getRegistroMinutoMonto();
                        MinutosHelper minuto = new MinutosHelper();
                        minuto.setRegistro(m);
                        minuto.setObservacion("$ " + decimalFormat.format(m.getRegistroMinutoMonto()) + "   N° Bus: " + m.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + sdf.format(m.getRegistroMinutoFechaMinuto()));
                        this.registroMinutoList.add(minuto);
                    }
                    //Creo un nuevo minutohelper con la suma de todos los minutos 
                    /*RegistroMinuto r = new RegistroMinuto();
                    r.setRegistroMinutoMonto(total);
                    MinutosHelper totalMinutos = new MinutosHelper();
                    totalMinutos.setObservacion("$ " + decimalFormat.format(total) + " (Total)");
                    totalMinutos.setRegistro(r);
                    totalMinutos.setTodos(Boolean.TRUE);
                    this.registroMinutoList.add(0, totalMinutos);*/

                } else {
                    System.err.println("SÓLO UN MINUTO");
                    RegistroMinuto r = minutosList.get(0);
                    MinutosHelper minuto = new MinutosHelper();
                    minuto.setRegistro(r);
                    minuto.setObservacion("$ " + decimalFormat.format(r.getRegistroMinutoMonto()) + "   N° Bus: " + r.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + sdf.format(r.getRegistroMinutoFechaMinuto()));
                    minuto.setTodos(Boolean.FALSE);
                    this.registroMinutoList.add(minuto);

                }
                this.registroMinuto = null;
                //this.registroMinuto = this.registroMinutoList.get(0);
                //calculaTotal();
            }

            List<VentaCombustible> combustibleList = this.ventaCombustibleDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());

            if (combustibleList.isEmpty()) {
            } else {
                if (combustibleList.size() > 1) {

                    this.ventaCombustibleList = new ArrayList<>();

                    int totalCombustible = 0;

                    for (VentaCombustible v : combustibleList) {
                        PetroleoHelper vPetroleo = new PetroleoHelper();
                        vPetroleo.setObservacion("$ " + decimalFormat.format(v.getVentaCombustibleTotal()) + " -  " + sdf.format(v.getVentaCombustibleFecha()));
                        vPetroleo.setVentaCombustible(v);
                        totalCombustible += v.getVentaCombustibleTotal();
                        this.ventaCombustibleList.add(vPetroleo);
                    }

                    /*VentaCombustible vf = new VentaCombustible();
                    vf.setVentaCombustibleTotal(totalCombustible);

                    PetroleoHelper ph = new PetroleoHelper();
                    ph.setVentaCombustible(vf);
                    ph.setObservacion("$ " + decimalFormat.format(totalCombustible) + " (Total)");

                    this.ventaCombustibleList.add(0, ph);*/
                } else {

                    this.ventaCombustibleList = new ArrayList<>();

                    VentaCombustible vb = combustibleList.get(0);

                    PetroleoHelper ph = new PetroleoHelper();
                    ph.setVentaCombustible(vb);
                    ph.setObservacion("$ " + decimalFormat.format(vb.getVentaCombustibleTotal()) + " -  " + sdf.format(vb.getVentaCombustibleFecha()));

                    this.ventaCombustibleList.add(ph);

                }
                //this.ventaCombustible = this.ventaCombustibleList.get(0);
                //calculaTotal();
            }

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
            for (Egreso r : this.egresosList) {
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
        for (Recaudacion r : this.recaudacionItems) {

            List a = new ArrayList();

            a.add(i + 1);
            a.add(r.getRecaudacionIdentificador());
            a.add(r.getRecaudacionIdBus().getBusNumero());
            a.add(r.getRecaudacionIdTrabajador().getTrabajadorApellidoPaterno());
            a.add(r.getTotalCombustible());
            a.add(r.getTotalMinutos());
            a.add(r.getRecaudacionTotal());
            a.addAll(this.listOfMaps.get(i).values());

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
            report.title(Templates.createTitleComponent(cajaRecaudacion.getCajaRecaudacionNombre() + " Recaudación al :" + sdf.format(fechaRecaudacion)));
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

}
