package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.controllers.VentaCombustibleFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.IPrecioCombustibleDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IPrecioCombustibleDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.Surtidor;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.VentaCombustibleModel;
import com.areatecnica.sigf.reports.PdfReportController;
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

@Named(value = "ventaCombustibleController")
@ViewScoped
public class VentaCombustibleController extends AbstractController<VentaCombustible> {

    @Inject
    private VentaCombustibleFacade ejbFacade;
    @Inject
    private GuiaController ventaCombustibleIdGuiaController;
    @Inject
    private SurtidorController ventaCombustibleIdSurtidorController;
    @Inject
    private PdfReportController pdfReportController;

    private List<Bus> busItems;
    private List<Guia> guiaItems;
    private List<UnidadNegocio> unidadItems;
    private List<VentaCombustible> ventasItems;
    private VentaCombustibleModel model;
    private Map unidadMap;
    private Guia guiaSelected;
    private Bus busSelected;
    private UnidadNegocio unidadSelected;
    private Surtidor surtidor;
    private IGuiaDao guiaDao;
    private IBusDao busDao;
    private IVentaCombustibleDao ventasDao;
    private PrecioCombustible precioCombustible;
    private IPrecioCombustibleDao precioCombustibleDao;
    private IUnidadNegocioDao unidadNegocioDao;
    private String tipoVenta;
    private int total;
    private int ultimaBoleta;
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
        super.setFacade(ejbFacade);
        this.fecha = new Date();
        this.formatFechaVentaBoleto = sdf.format(fecha);
        this.busDao = new IBusDaoImpl();
        this.busItems = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());
        this.unidadNegocioDao = new IUnidadNegocioDaoImpl();
        unidadMap = new HashMap();

        for (Bus b : this.busItems) {
            unidadMap.put(b.getBusIdUnidadNegocio(), b.getBusIdUnidadNegocio());
        }

        this.unidadItems = new ArrayList(this.unidadMap.values());

        this.precioCombustibleDao = new IPrecioCombustibleDaoImpl();
        this.precioCombustible = this.precioCombustibleDao.findLastPrecio(this.getUserCount());

        this.formatTotal = decimalFormat.format(0);
        this.ultimaBoleta = 0;
    }

    public VentaCombustibleController() {
        // Inform the Abstract parent controller of the concrete VentaCombustible Entity
        super(VentaCombustible.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaCombustibleIdGuiaController.setSelected(null);
        ventaCombustibleIdSurtidorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Surtidor controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaCombustibleIdSurtidor(ActionEvent event) {
        if (this.getSelected() != null && ventaCombustibleIdSurtidorController.getSelected() == null) {
            ventaCombustibleIdSurtidorController.setSelected(this.getSelected().getVentaCombustibleIdSurtidor());
        }
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

    public void setModel(VentaCombustibleModel model) {
        this.model = model;
    }

    public VentaCombustibleModel getModel() {
        return model;
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

    public void setUltimaBoleta(int ultimaBoleta) {
        this.ultimaBoleta = ultimaBoleta;
    }

    public int getUltimaBoleta() {
        return ultimaBoleta;
    }

    public void loadVenta() {
        if (this.getSelected() != null) {
            this.unidadSelected = this.getSelected().getVentaCombustibleIdBus().getBusIdUnidadNegocio();
        }
    }

    @Override
    public VentaCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setVentaCombustibleFecha(this.fecha);
        this.getSelected().setVentaCombustiblePrecio(precioCombustible.getPrecioCombustibleValor());

        this.getSelected().setVentaCombustibleNumeroBoleta(this.ventasDao.findLastNumeroBoleta(this.getCurrentUser().getUsuarioIdTerminal()));
        return this.getSelected();
    }

    public VentaCombustible prepareCreateSolyMar(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setVentaCombustibleFecha(new Date());
        this.getSelected().setVentaCombustiblePrecio(precioCombustible.getPrecioCombustibleValor());

        this.unidadSelected = this.unidadNegocioDao.findById(2);
        this.busDao = new IBusDaoImpl();
        this.busItems = this.busDao.findByUnidad(unidadSelected);

        this.surtidor = this.ventaCombustibleIdSurtidorController.getItems().stream().findFirst().get();
        this.getSelected().setVentaCombustibleIdSurtidor(surtidor);
        this.getSelected().setVentaCombustibleNumeroBoleta(this.ventasDao.findLastNumeroBoleta(this.getCurrentUser().getUsuarioIdTerminal()));
        return this.getSelected();
    }

    public VentaCombustible prepareCreateFenur(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setVentaCombustibleFecha(new Date());
        this.getSelected().setVentaCombustiblePrecio(precioCombustible.getPrecioCombustibleValor());

        this.unidadSelected = this.unidadNegocioDao.findById(3);
        this.busDao = new IBusDaoImpl();
        this.busItems = this.busDao.findByUnidad(unidadSelected);

        this.surtidor = this.ventaCombustibleIdSurtidorController.getItems().stream().findFirst().get();
        this.getSelected().setVentaCombustibleIdSurtidor(surtidor);
        this.getSelected().setVentaCombustibleNumeroBoleta(this.ventasDao.findLastNumeroBoleta(this.getCurrentUser().getUsuarioIdTerminal()));
        return this.getSelected();
    }

    public VentaCombustible prepareCreateParticular(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setVentaCombustibleFecha(new Date());
        this.getSelected().setVentaCombustiblePrecio(precioCombustible.getPrecioCombustibleValor());

        this.unidadSelected = this.unidadNegocioDao.findById(1);
        this.busDao = new IBusDaoImpl();
        this.busItems = this.busDao.findByUnidad(unidadSelected);

        this.surtidor = this.ventaCombustibleIdSurtidorController.getItems().stream().findFirst().get();
        this.getSelected().setVentaCombustibleIdSurtidor(surtidor);
        this.getSelected().setVentaCombustibleNumeroBoleta(this.ventasDao.findLastNumeroBoleta(this.getCurrentUser().getUsuarioIdTerminal()));

        this.getSelected().setVentaCombustibleIdBus(this.busItems.get(0));

        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setVentaCombustibleHora(new Date());
        this.total += this.getSelected().getVentaCombustibleTotal();
        this.formatTotal = decimalFormat.format(total);
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        this.ventasItems.add(this.getSelected());
        this.ultimaBoleta = this.getSelected().getVentaCombustibleNumeroBoleta();
    }

    public void load() {

        this.ventasDao = new IVentaCombustibleDaoImpl();
        this.ventasItems = this.ventasDao.findByTerminalDate(this.getCurrentUser().getUsuarioIdTerminal(), fecha);

        this.model = new VentaCombustibleModel(ventasItems);

        this.total = 0;
        for (VentaCombustible v : this.ventasItems) {
            this.total += v.getVentaCombustibleTotal();
        }

        this.formatTotal = decimalFormat.format(total);
        this.ultimaBoleta = this.ventasDao.findLastNumeroBoleta(this.getCurrentUser().getUsuarioIdTerminal());

        this.unidadNegocioDao = new IUnidadNegocioDaoImpl();

    }

    public void handleOperadorChange() {

        if (this.unidadSelected != null) {
            this.busDao = new IBusDaoImpl();
            this.busItems = this.busDao.findByUnidad(unidadSelected);
        }
    }

    public void handleBusChange() {
        /*this.guiaDao = new IGuiaDaoImpl();
        this.guiaSelected = this.guiaDao.findLastGuiaByBusEqualsFecha(busSelected, fecha);*/

    }

    public void handleTotalChange() {
        this.getSelected().setVentaCombustibleCantidad((float) this.getSelected().getVentaCombustibleTotal() / precioCombustible.getPrecioCombustibleValor());
    }

    public void handleNumeroLitrosChange() {
        this.getSelected().setVentaCombustiblePrecio((int) (precioCombustible.getPrecioCombustibleValor() * this.getSelected().getVentaCombustibleCantidad()));
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
        DRDataSource dataSource = new DRDataSource("numero", "identificador", "hora", "boleta", "bus", "ppu", "operador", "cantidad", "total");
        int i = 1;

        for (VentaCombustible v : this.ventasItems) {
            dataSource.add(i,
                    v.getVentaCombustibleIdSurtidor().getSurtidorIdentificador(),
                    v.getVentaCombustibleHora(),
                    v.getVentaCombustibleNumeroBoleta(),
                    v.getVentaCombustibleIdBus().getBusNumero(),
                    v.getVentaCombustibleIdBus().getBusPatente(),
                    v.getVentaCombustibleIdBus().getBusIdUnidadNegocio().getUnidadNegocioIdOperadorTransporte().getOperadorTransporteNombre(),
                    v.getVentaCombustibleCantidad(),
                    v.getVentaCombustibleTotal());
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
