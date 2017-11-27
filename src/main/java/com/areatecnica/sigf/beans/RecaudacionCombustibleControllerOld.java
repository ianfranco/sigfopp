package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IRecaudacionCombustibleDao;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionCombustibleDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.PetroleoHelper;
import com.areatecnica.sigf.models.RecaudacionCombustibleDataModel;
import com.areatecnica.sigf.models.VentaCombustibleModel;
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

@Named(value = "recaudacionCombustibleOldController")
@ViewScoped
public class RecaudacionCombustibleControllerOld extends AbstractController<Recaudacion> {

    @Inject
    private RecaudacionFacade ejbFacade;
    @Inject
    private VentaCombustibleFacade ventaCombustibleFacade;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    private List<RecaudacionCombustible> recaudacionCombustibleList;
    private List<PetroleoHelper> ventaCombustibleList;
    private List<Bus> busItems;
    private List<Guia> guiaItems;
    private List<UnidadNegocio> unidadItems;
    private List<VentaCombustible> ventasItems;
    private List<CajaRecaudacion> cajaRecaudacionItems;
    private VentaCombustibleModel deudasModel;
    private RecaudacionCombustibleDataModel recaudacionModel;
    private Map unidadMap;
    private PetroleoHelper ventaCombustible;
    private VentaCombustible ventaSinRecaudacionItem;
    private CajaRecaudacion cajaRecaudacion;
    private RecaudacionCombustible item;
    private Guia guiaSelected;
    private Bus busSelected;
    private UnidadNegocio unidadSelected;
    private IRecaudacionCombustibleDao recaudacionDao;
    private IBusDao busDao;
    private IVentaCombustibleDao ventaCombustibleDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private String tipoVenta;
    private Boolean print;
    private int total;
    private String formatTotal;
    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private Date fecha;
    private String formatFechaVentaBoleto;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private ServletOutputStream servletOutputStream;

    /**
     * Initialize the concrete VentaCombustible controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        /*Instanciación de Data Access Objects*/
        super.setFacade(ejbFacade);
        this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.busDao = new IBusDaoImpl();
        this.recaudacionDao = new IRecaudacionCombustibleDaoImpl();

        //Inicia la impresión, total y cantidad de recaudaciones 
        this.print = Boolean.TRUE;
        this.total = 0;
        this.formatTotal = "0";

        this.fecha = new Date();
        this.formatFechaVentaBoleto = this.sdf.format(fecha);

        /*Busqueda de las cajas asociadas al usuario*/
        this.cajaRecaudacionItems = this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser());
        this.cajaRecaudacion = this.cajaRecaudacionItems.get(0);

        /*Búsqueda de los buses asociados al terminal*/
        this.busItems = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());

        unidadMap = new HashMap();

        for (Bus b : this.busItems) {
            unidadMap.put(b.getBusIdUnidadNegocio(), b.getBusIdUnidadNegocio());
        }

        this.unidadItems = new ArrayList(this.unidadMap.values());

    }

    public RecaudacionCombustibleControllerOld() {
        // Inform the Abstract parent controller of the concrete VentaCombustible Entity
        super(Recaudacion.class);
    }

    public void setRecaudacionModel(RecaudacionCombustibleDataModel recaudacionModel) {
        this.recaudacionModel = recaudacionModel;
    }

    public RecaudacionCombustibleDataModel getRecaudacionModel() {
        return recaudacionModel;
    }

    public void setVentaSinRecaudacionItem(VentaCombustible ventaSinRecaudacionItem) {
        this.ventaSinRecaudacionItem = ventaSinRecaudacionItem;
    }

    public VentaCombustible getVentaSinRecaudacionItem() {
        return ventaSinRecaudacionItem;
    }

    public PetroleoHelper getVentaCombustible() {
        return ventaCombustible;
    }

    public void setVentaCombustible(PetroleoHelper ventaCombustible) {
        this.ventaCombustible = ventaCombustible;
    }

    public void setVentaCombustibleList(List<PetroleoHelper> ventaCombustibleList) {
        this.ventaCombustibleList = ventaCombustibleList;
    }

    public List<PetroleoHelper> getVentaCombustibleList() {
        return ventaCombustibleList;
    }

    public void setItem(RecaudacionCombustible item) {
        this.item = item;
    }

    public RecaudacionCombustible getItem() {
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

    public void setRecaudacionCombustibleList(List<RecaudacionCombustible> recaudacionCombustibleList) {
        this.recaudacionCombustibleList = recaudacionCombustibleList;
    }

    public List<RecaudacionCombustible> getRecaudacionCombustibleList() {
        return recaudacionCombustibleList;
    }

    public void setPrint(Boolean print) {
        this.print = print;
    }

    public Boolean getPrint() {
        return print;
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

    public void setVentasItems(List<VentaCombustible> ventasItems) {
        this.ventasItems = ventasItems;
    }

    public List<VentaCombustible> getVentasItems() {
        return ventasItems;
    }

    public void setDeudasModel(VentaCombustibleModel model) {
        this.deudasModel = model;
    }

    public VentaCombustibleModel getDeudasModel() {
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
        if (this.cajaRecaudacion != null && this.fecha != null) {
            /*Creación de Modelo de Datos para la recaudación*/
            this.recaudacionCombustibleList = this.recaudacionDao.findByCajaDate(cajaRecaudacion, fecha);
            this.recaudacionModel = new RecaudacionCombustibleDataModel(recaudacionCombustibleList);

            /*Cálculo del total de recaudación*/
            this.total = 0;
            for (RecaudacionCombustible v : this.recaudacionCombustibleList) {
                this.total += v.getRecaudacionCombustibleMonto();
            }
            this.formatTotal = this.decimalFormat.format(total);
            this.formatFechaVentaBoleto = this.sdf.format(fecha);
            loadDeudas();
        }
    }

    public void load() {
        if (this.getItem() != null) {
            this.setSelected(this.getItem().getRecaudacionCombustibleIdRecaudacion());
        }
    }

    @Override
    public Recaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.item = new RecaudacionCombustible();
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {

        if (this.getSelected() != null && this.ventaCombustible != null) {

            int total = 0;
            List<RecaudacionCombustible> _recaudacionCombusteList = new ArrayList<>();

            if (this.ventaCombustible.getVentaCombustible().getVentaCombustibleIdBus() == null) {
                this.ventaCombustibleList.remove(0);

                for (PetroleoHelper p : this.ventaCombustibleList) {
                    VentaCombustible v = p.getVentaCombustible();
                    v.setVentaCombustibleRecaudado(Boolean.TRUE);
                    this.ventaCombustibleFacade.edit(v);
                    total += v.getVentaCombustibleTotal();

                    RecaudacionCombustible combustible = new RecaudacionCombustible();
                    combustible.setRecaudacionCombustibleIdRecaudacion(this.getSelected());
                    combustible.setRecaudacionCombustibleIdVentaCombustible(v);
                    combustible.setRecaudacionCombustibleMonto(v.getVentaCombustibleTotal());
                    _recaudacionCombusteList.add(combustible);

                    this.recaudacionCombustibleList.add(combustible);

                    this.total += combustible.getRecaudacionCombustibleMonto();
                }

            } else {
                RecaudacionCombustible recaudacionCombustible = new RecaudacionCombustible();
                recaudacionCombustible.setRecaudacionCombustibleIdRecaudacion(this.getSelected());
                recaudacionCombustible.setRecaudacionCombustibleIdVentaCombustible(this.ventaCombustible.getVentaCombustible());
                recaudacionCombustible.setRecaudacionCombustibleMonto(this.ventaCombustible.getVentaCombustible().getVentaCombustibleTotal());
                this.ventaCombustible.getVentaCombustible().setVentaCombustibleRecaudado(Boolean.TRUE);
                this.ventaCombustibleFacade.edit(this.ventaCombustible.getVentaCombustible());
                total = this.ventaCombustible.getVentaCombustible().getVentaCombustibleTotal();
                this.recaudacionCombustibleList.add(recaudacionCombustible);
                _recaudacionCombusteList.add(recaudacionCombustible);
            }

            this.item = this.recaudacionCombustibleList.get(0);

            this.getSelected().setRecaudacionIdCaja(this.cajaRecaudacion);
            this.getSelected().setRecaudacionFecha(this.fecha);
            this.getSelected().setRecaudacionHora(new Date());
            this.getSelected().setRecaudacionTotal(total);

            this.getSelected().setRecaudacionCombustibleList(_recaudacionCombusteList);

            this.ejbFacade.create(this.getSelected());

            this.formatTotal = this.decimalFormat.format(total);

            this.ventaCombustible = null;
            this.ventaCombustibleList = null;

        }
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleBoletaChange() {

    }

    public void handleOperadorChange() {
        if (this.unidadSelected != null) {
            this.busDao = new IBusDaoImpl();
            this.busItems = this.busDao.findByUnidad(unidadSelected);
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getSelected().getRecaudacionIdBus() != null) {
            this.ventaCombustibleList = null;
            this.ventaCombustible = null;

            List<VentaCombustible> combustibleList = this.ventaCombustibleDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());

            if (combustibleList.isEmpty()) {
                this.ventaCombustible = null;
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

                    VentaCombustible vf = new VentaCombustible();
                    vf.setVentaCombustibleTotal(totalCombustible);

                    PetroleoHelper ph = new PetroleoHelper();
                    ph.setVentaCombustible(vf);
                    ph.setObservacion("$ " + decimalFormat.format(totalCombustible) + " TODOS");

                    this.ventaCombustibleList.add(0, ph);

                } else {

                    this.ventaCombustibleList = new ArrayList<>();

                    VentaCombustible vb = combustibleList.get(0);

                    PetroleoHelper ph = new PetroleoHelper();
                    ph.setVentaCombustible(vb);
                    ph.setObservacion("$ " + decimalFormat.format(vb.getVentaCombustibleTotal()) + " -  " + sdf.format(vb.getVentaCombustibleFecha()));

                    this.ventaCombustibleList.add(ph);

                }

                this.ventaCombustible = this.ventaCombustibleList.get(0);

            }

        }
    }

    /*Creación de Modelo de Datos para las Deudas de Combustible*/
    public void loadDeudas() {
        this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
        this.ventasItems = this.ventaCombustibleDao.findByTerminalSinRecaudar(this.getCurrentUser().getUsuarioIdTerminal(), Boolean.FALSE);
        this.deudasModel = new VentaCombustibleModel(ventasItems);
    }

    public void exportPdf2(ActionEvent event) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=Recaudacion-Combustible.pdf");

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
            column.setName("boleta");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("fecha");            
            report.addColumn(column);
            
            column = new AdhocColumn();
            column.setName("hora");            
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
            column.setName("monto");
            report.addColumn(column);

            AdhocSubtotal subtotal = new AdhocSubtotal();
            subtotal.setCalculation(AdhocCalculation.SUM);
            subtotal.setName("monto");
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
        DRDataSource dataSource = new DRDataSource("numero", "boleta", "fecha", "hora",  "bus", "ppu", "operador", "monto");
        int i = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        
        for (RecaudacionCombustible v : this.recaudacionCombustibleList) {
            dataSource.add(i,
                    v.getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleNumeroBoleta(),
                    sdf.format(v.getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleFecha()),
                    v.getRecaudacionCombustibleIdRecaudacion().getRecaudacionHora(), 
                    v.getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleIdBus().getBusNumero(),
                    v.getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleIdBus().getBusPatente(),
                    v.getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleIdBus().getBusIdUnidadNegocio().getUnidadNegocioIdOperadorTransporte().getOperadorTransporteNombre(),
                    v.getRecaudacionCombustibleIdVentaCombustible().getVentaCombustibleTotal()
            );
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
            if (name.equals("numero") || name.equals("boleta") || name.equals("bus") || name.equals("total") || name.equals("monto")) {
                return type.integerType();
            }

            if (name.equals("hora")) {
                return type.timeHourToMinuteType();
            }

            if (name.equals("ppu") || name.equals("operador")) {
                return type.stringType();
            }

            if (name.equals("fecha")) {
                return type.stringType();
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
                return "N° Boleta";
            }
            if (name.equals("hora")) {
                return "Hora";
            }            
            if (name.equals("fecha")) {
                return "Fecha";
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
            if (name.equals("monto")) {
                return "Monto";
            }
            return name;
        }

    }

}
