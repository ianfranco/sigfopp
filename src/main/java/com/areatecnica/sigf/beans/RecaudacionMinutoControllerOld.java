package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.controllers.RegistroMinutoFacade;
import com.areatecnica.sigf.entities.RegistroMinuto;
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
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.MinutosHelper;
import com.areatecnica.sigf.models.RecaudacionMinutoDataModel;
import com.areatecnica.sigf.models.RegistroMinutoDataModel;
import com.areatecnica.sigf.reports.Templates;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
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

@Named(value = "recaudacionMinutoOldController")
@ViewScoped
public class RecaudacionMinutoControllerOld extends AbstractController<Recaudacion> {

    @Inject
    private RecaudacionFacade ejbFacade;
    @Inject
    private RegistroMinutoFacade ventaCombustibleFacade;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    private List<RecaudacionMinuto> recaudacionCombustibleList;
    private List<MinutosHelper> minutosHelperList;
    private List<Bus> busItems;
    private List<Guia> guiaItems;
    private List<UnidadNegocio> unidadItems;
    private List<RegistroMinuto> registroMinutosList;
    private List<CajaRecaudacion> cajaRecaudacionItems;
    private RegistroMinutoDataModel deudasModel;
    private RecaudacionMinutoDataModel recaudacionModel;
    private Map unidadMap;
    private MinutosHelper registroMinuto;
    private RegistroMinuto ventaSinRecaudacionItem;
    private CajaRecaudacion cajaRecaudacion;
    private RecaudacionMinuto item;
    private Guia guiaSelected;
    private Bus busSelected;
    private UnidadNegocio unidadSelected;
    private IRecaudacionMinutoDao recaudacionDao;
    private IBusDao busDao;
    private IRegistroMinutoDao registroMinutoDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private String tipoVenta;
    private int total;
    private String formatTotal;
    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private Date fecha;
    private String formatFechaVentaBoleto;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private ServletOutputStream servletOutputStream;

    /**
     * Initialize the concrete RegistroMinuto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        /*Instanciación de Data Access Objects*/
        super.setFacade(ejbFacade);
        this.registroMinutoDao = new IRegistroMinutoDaoImpl();
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.busDao = new IBusDaoImpl();
        this.recaudacionDao = new IRecaudacionMinutoDaoImpl();

        this.fecha = new Date();
        this.formatFechaVentaBoleto = sdf.format(fecha);

        /*Busqueda de las cajas asociadas al usuario*/
        this.cajaRecaudacionItems = this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser());
        this.cajaRecaudacion = this.cajaRecaudacionItems.get(0);

        /*Búsqueda de los buses asociados al terminal*/
        this.busItems = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());

        /*Creación de Modelo de Datos para la recaudación*/
        this.recaudacionCombustibleList = this.recaudacionDao.findByCajaDate(cajaRecaudacion, fecha);
        this.recaudacionModel = new RecaudacionMinutoDataModel(recaudacionCombustibleList);

        /*Cálculo del total de recaudación*/
        this.total = 0;
        for (RecaudacionMinuto v : this.recaudacionCombustibleList) {
            this.total += v.getRecaudacionMinutoMonto();
        }
        this.formatTotal = this.decimalFormat.format(total);

        unidadMap = new HashMap();

        for (Bus b : this.busItems) {
            unidadMap.put(b.getBusIdUnidadNegocio(), b.getBusIdUnidadNegocio());
        }

        this.unidadItems = new ArrayList(this.unidadMap.values());

        loadDeudas();
    }

    public RecaudacionMinutoControllerOld() {
        // Inform the Abstract parent controller of the concrete RegistroMinuto Entity
        super(Recaudacion.class);
    }

    public void setRecaudacionModel(RecaudacionMinutoDataModel recaudacionModel) {
        this.recaudacionModel = recaudacionModel;
    }

    public RecaudacionMinutoDataModel getRecaudacionModel() {
        return recaudacionModel;
    }

    public void setVentaSinRecaudacionItem(RegistroMinuto ventaSinRecaudacionItem) {
        this.ventaSinRecaudacionItem = ventaSinRecaudacionItem;
    }

    public RegistroMinuto getVentaSinRecaudacionItem() {
        return ventaSinRecaudacionItem;
    }

    public MinutosHelper getRegistroMinuto() {
        return registroMinuto;
    }

    public void setRegistroMinuto(MinutosHelper registroMinuto) {
        this.registroMinuto = registroMinuto;
    }

    public void setMinutosHelperList(List<MinutosHelper> minutosHelperList) {
        this.minutosHelperList = minutosHelperList;
    }

    public List<MinutosHelper> getMinutosHelperList() {
        return minutosHelperList;
    }

    public void setItem(RecaudacionMinuto item) {
        this.item = item;
    }

    public RecaudacionMinuto getItem() {
        return item;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    public void setRecaudacionMinutoList(List<RecaudacionMinuto> recaudacionCombustibleList) {
        this.recaudacionCombustibleList = recaudacionCombustibleList;
    }

    public List<RecaudacionMinuto> getRecaudacionMinutoList() {
        return recaudacionCombustibleList;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Guia> getGuiaItems() {
        return guiaItems;
    }

    public void setGuiaItems(List<Guia> guiaItems) {
        this.guiaItems = guiaItems;
    }

    public Guia getGuiaSelected() {
        return guiaSelected;
    }

    public void setGuiaSelected(Guia guiaSelected) {
        this.guiaSelected = guiaSelected;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public List<UnidadNegocio> getUnidadItems() {
        return unidadItems;
    }

    public void setUnidadItems(List<UnidadNegocio> unidadItems) {
        this.unidadItems = unidadItems;
    }

    public void setUnidadMap(Map unidadMap) {
        this.unidadMap = unidadMap;
    }

    public Map getUnidadMap() {
        return unidadMap;
    }

    public UnidadNegocio getUnidadSelected() {
        return unidadSelected;
    }

    public void setUnidadSelected(UnidadNegocio unidadSelected) {
        this.unidadSelected = unidadSelected;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public void setBusSelected(Bus busSelected) {
        this.busSelected = busSelected;
    }

    public Bus getBusSelected() {
        return busSelected;
    }

    public void setRegistroMinutosList(List<RegistroMinuto> registroMinutosList) {
        this.registroMinutosList = registroMinutosList;
    }

    public List<RegistroMinuto> getRegistroMinutosList() {
        return registroMinutosList;
    }

    public void setDeudasModel(RegistroMinutoDataModel model) {
        this.deudasModel = model;
    }

    public RegistroMinutoDataModel getDeudasModel() {
        return deudasModel;
    }

    public void setFormatFechaVentaBoleto(String formatFechaVentaBoleto) {
        this.formatFechaVentaBoleto = formatFechaVentaBoleto;
    }

    public String getFormatFechaVentaBoleto() {
        return formatFechaVentaBoleto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFormatTotal() {
        return formatTotal;
    }

    public void setFormatTotal(String formatTotal) {
        this.formatTotal = formatTotal;
    }

    public void loadRecaudacionCombustible() {
        if (this.getItem() != null) {

        }
    }

    public void load() {
        if (this.getItem() != null) {
            this.setSelected(this.getItem().getRecaudacionMinutoIdRecaudacion());
        }
    }

    @Override
    public Recaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.item = new RecaudacionMinuto();
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {

        if (this.getSelected() != null && this.registroMinuto != null) {

            int total = 0;
            List<RecaudacionMinuto> _recaudacionCombusteList = new ArrayList<>();

            if (this.minutosHelperList.size() > 1) {
                this.minutosHelperList.remove(0);
            }

            for (MinutosHelper p : this.minutosHelperList) {
                RegistroMinuto v = p.getRegistro();
                v.setRegistroMinutoRecaudado(Boolean.TRUE);
                this.ventaCombustibleFacade.edit(v);
                total += v.getRegistroMinutoMonto();

                RecaudacionMinuto combustible = new RecaudacionMinuto();
                combustible.setRecaudacionMinutoIdRecaudacion(this.getSelected());
                combustible.setRecaudacionMinutoIdRegistroMinuto(v);
                combustible.setRecaudacionMinutoMonto(v.getRegistroMinutoMonto());
                _recaudacionCombusteList.add(combustible);

                this.recaudacionCombustibleList.add(combustible);

                this.total += combustible.getRecaudacionMinutoMonto();                
            }

            this.item = this.recaudacionCombustibleList.get(0);
                        
            this.getSelected().setRecaudacionIdCaja(this.cajaRecaudacion);
            this.getSelected().setRecaudacionFecha(new Date());
            this.getSelected().setRecaudacionHora(new Date());
            this.getSelected().setRecaudacionTotal(total);

            this.getSelected().setRecaudacionMinutoList(_recaudacionCombusteList);

            this.ejbFacade.create(this.getSelected());

            this.formatTotal = this.decimalFormat.format(total);

            this.registroMinuto = null;
            this.minutosHelperList = null;
            
            
        }
    }

    public void handleOperadorChange() {
        if (this.unidadSelected != null) {
            this.busDao = new IBusDaoImpl();
            this.busItems = this.busDao.findByUnidad(unidadSelected);
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getSelected().getRecaudacionIdBus() != null) {
            this.minutosHelperList = null;
            this.registroMinuto = null;

            List<RegistroMinuto> combustibleList = this.registroMinutoDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());

            if (combustibleList.isEmpty()) {
                this.registroMinuto = null;
            } else {
                if (combustibleList.size() > 1) {

                    this.minutosHelperList = new ArrayList<>();

                    int totalCombustible = 0;

                    for (RegistroMinuto v : combustibleList) {
                        MinutosHelper vPetroleo = new MinutosHelper();
                        vPetroleo.setObservacion("$ " + decimalFormat.format(v.getRegistroMinutoMonto()) + " -  " + sdf.format(v.getRegistroMinutoFechaMinuto()));
                        vPetroleo.setRegistro(v);
                        totalCombustible += v.getRegistroMinutoMonto();
                        this.minutosHelperList.add(vPetroleo);
                    }

                    RegistroMinuto vf = new RegistroMinuto();
                    vf.setRegistroMinutoMonto(totalCombustible);

                    MinutosHelper ph = new MinutosHelper();
                    ph.setRegistro(vf);
                    ph.setObservacion("$ " + decimalFormat.format(totalCombustible) + " TODOS");

                    this.minutosHelperList.add(0, ph);

                } else {

                    this.minutosHelperList = new ArrayList<>();

                    RegistroMinuto vb = combustibleList.get(0);

                    MinutosHelper ph = new MinutosHelper();
                    ph.setRegistro(vb);
                    ph.setObservacion("$ " + decimalFormat.format(vb.getRegistroMinutoMonto()) + " -  " + sdf.format(vb.getRegistroMinutoFechaMinuto()));

                    this.minutosHelperList.add(ph);

                }

                this.registroMinuto = this.minutosHelperList.get(0);

            }

        }
    }

    /*Creación de Modelo de Datos para las Deudas de Combustible*/
    public void loadDeudas() {
        this.registroMinutoDao = new IRegistroMinutoDaoImpl();
        this.registroMinutosList = this.registroMinutoDao.findBySinRecaudar();
        this.deudasModel = new RegistroMinutoDataModel(registroMinutosList);
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
            AdhocColumn column = new AdhocColumn();
            column.setName("numero");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("identificador");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("hora");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("boleta");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("bus");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("ppu");
            column.setWidth(80);
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("operador");
            column.setWidth(100);
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("cantidad");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("total");
            report.addColumn(column);

            AdhocSubtotal subtotal = new AdhocSubtotal();
            subtotal.setCalculation(AdhocCalculation.SUM);
            subtotal.setName("cantidad");
            subtotal.setStyle(style);
            report.addSubtotal(subtotal);

            subtotal = new AdhocSubtotal();
            subtotal.setCalculation(AdhocCalculation.SUM);
            subtotal.setName("total");
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
        DRDataSource dataSource = new DRDataSource("numero", "paga", "ppupaga", "flotapaga", "recibe", "ppurecibe", "flotarecibe", "total");
        int i = 1;

        for (RegistroMinuto r : this.registroMinutosList) {
            dataSource.add(i, 
                    r.getRegistroMinutoDesdeIdBus().getBusNumero(), 
                    r.getRegistroMinutoDesdeIdBus().getBusPatente(), 
                    r.getRegistroMinutoDesdeIdBus().getBusIdFlota().getFlotaNombre(), 
                    r.getRegistroMinutoHastaIdBus().getBusNumero(), 
                    r.getRegistroMinutoHastaIdBus().getBusPatente(), 
                    r.getRegistroMinutoHastaIdBus().getBusIdFlota().getFlotaNombre(), 
                    r.getRegistroMinutoMonto());
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
            report.title(Templates.createTitleComponent("Registro de Ventas de Combustible al :" + sdf.format(fecha)));
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
            if (name.equals("numero") || name.equals("boleta") || name.equals("bus") || name.equals("total")) {
                return type.integerType();
            }

            if (name.equals("cantidad")) {
                return type.floatType();
            }

            if (name.equals("identificador") || name.equals("ppu") || name.equals("operador")) {
                return type.stringType();
            }

            if (name.equals("hora")) {
                return type.timeHourToMinuteType();
            }

            return super.getFieldType(name);
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
            if (name.equals("identificador")) {
                return "Surtidor";
            }
            if (name.equals("hora")) {
                return "Hora";
            }
            if (name.equals("boleta")) {
                return "N° Boleta";
            }
            if (name.equals("bus")) {
                return "N° Bus";
            }
            if (name.equals("ppu")) {
                return "PPU";
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
