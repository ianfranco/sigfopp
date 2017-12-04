package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.controllers.RegistroMinutoFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.models.RegistroMinutoDataModel;
import com.areatecnica.sigf.reports.Templates;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

@Named(value = "registroMinutoController")
@ViewScoped
public class RegistroMinutoController extends AbstractController<RegistroMinuto> {

    @Inject
    private RegistroMinutoFacade ejbFacade;
    @Inject
    private ValorMinutoController registroMinutoIdValorMinutoController;
    @Inject
    private BusController registroMinutoDesdeIdBusController;
    @Inject
    private BusController registroMinutoHastaIdBusController;
    private RegistroMinutoDataModel model;
    private Date fecha;
    private Date fechaMax;
    private List<RegistroMinuto> minutosItems;
    private List<Bus> recibeBusItems;
    private List<Bus> pagaBusItems;
    private IBusDao busDao;
    private IRegistroMinutoDao registroDao;
    private ServletOutputStream servletOutputStream;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Initialize the concrete RegistroMinuto controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public RegistroMinutoController() {
        // Inform the Abstract parent controller of the concrete RegistroMinuto Entity
        super(RegistroMinuto.class);
        this.fecha = new Date();
        this.fechaMax = new Date();
        this.busDao = new IBusDaoImpl();
        this.pagaBusItems = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        registroMinutoIdValorMinutoController.setSelected(null);
        registroMinutoDesdeIdBusController.setSelected(null);
        registroMinutoHastaIdBusController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoDesdeIdBus(ActionEvent event) {
        if (this.getSelected() != null && registroMinutoDesdeIdBusController.getSelected() == null) {
            registroMinutoDesdeIdBusController.setSelected(this.getSelected().getRegistroMinutoDesdeIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoHastaIdBus(ActionEvent event) {
        if (this.getSelected() != null && registroMinutoHastaIdBusController.getSelected() == null) {
            registroMinutoHastaIdBusController.setSelected(this.getSelected().getRegistroMinutoHastaIdBus());
        }
    }

    public void setPagaBusItems(List<Bus> pagaBusItems) {
        this.pagaBusItems = pagaBusItems;
    }

    public List<Bus> getPagaBusItems() {
        return pagaBusItems;
    }

    public void setRecibeBusItems(List<Bus> recibeBusItems) {
        this.recibeBusItems = recibeBusItems;
    }

    public List<Bus> getRecibeBusItems() {
        return recibeBusItems;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<RegistroMinuto> getMinutosItems() {
        return minutosItems;
    }

    public void setMinutosItems(List<RegistroMinuto> minutosItems) {
        this.minutosItems = minutosItems;
    }

    public void setModel(RegistroMinutoDataModel model) {
        this.model = model;
    }

    public RegistroMinutoDataModel getModel() {
        return model;
    }

    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

    public Date getFechaMax() {
        return fechaMax;
    }

    public void load() {
        this.registroDao = new IRegistroMinutoDaoImpl();
        this.minutosItems = this.registroDao.findByDate(fecha);
        this.model = new RegistroMinutoDataModel(minutosItems);
        this.setSelected(prepareCreate(null));
    }

    public void loadRegistro() {
        if (this.getSelected() != null) {
            Bus _bus = this.getSelected().getRegistroMinutoDesdeIdBus();
            removeBus();
        }
    }

    @Override
    public RegistroMinuto prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        
        this.getSelected().setRegistroMinutoFechaMinuto(fecha);
        this.getSelected().setRegistroMinutoRecaudado(Boolean.FALSE);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
        this.minutosItems.add(this.getSelected());
        this.setSelected(prepareCreate(event));
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ActionEvent event) {
        this.minutosItems.remove(this.getSelected());
    }

    public void removeBus() {
        this.recibeBusItems = new ArrayList<>(this.pagaBusItems);
        this.recibeBusItems.remove(this.getSelected().getRegistroMinutoDesdeIdBus());
    }

    public void exportPdf2(ActionEvent event) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=RegistroMinutos.pdf");

        try {
            servletOutputStream = httpServletResponse.getOutputStream();
            AdhocStyle style = new AdhocStyle();

            style.setHorizontalAlignment(AdhocHorizontalAlignment.RIGHT);

            AdhocConfiguration configuration = new AdhocConfiguration();
            AdhocReport report = new AdhocReport();
            report.setColumnTitleStyle(style);
            configuration.setReport(report);

            AdhocColumn column = new AdhocColumn();
            column.setName("numero");            

            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("paga");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("ppupaga");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("flotapaga");
            column.setWidth(130);
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("recibe");
            column.setWidth(70);
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("ppurecibe");
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("flotarecibe");
            column.setWidth(130);
            report.addColumn(column);

            column = new AdhocColumn();
            column.setName("total");

            report.addColumn(column);

            AdhocSubtotal subtotal = new AdhocSubtotal();
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

    public void exportPdf(ActionEvent event) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=RegistroMinutos.pdf");
        try {
            servletOutputStream = httpServletResponse.getOutputStream();

            report().columns(//add columns
                    //            title,     field name     data type
                    col.column("#", "numero", type.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                    col.column("N° Bus Paga", "Paga", type.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                    col.column("N° Bus Recibe", "Recibe", type.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
                    col.column("Total", "Total", type.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT))//create new report design
                    .title(cmp.text("Registro de Minutos al:" + sdf.format(this.fecha)))//shows report title                    
                    .pageFooter(cmp.pageXslashY())//shows number of page at page footer
                    .setDataSource(createDataSource())//set datasource
                    .toPdf(servletOutputStream);//create and show report

        } catch (IOException | DRException ex) {
            Logger.getLogger(VentaCombustibleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FacesContext.getCurrentInstance().responseComplete();
    }

    private DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("numero", "paga", "ppupaga", "flotapaga", "recibe", "ppurecibe", "flotarecibe", "total");
        int i = 1;

        for (RegistroMinuto r : this.minutosItems) {
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
            report.title(Templates.createTitleComponent("Registro de Minutos al :" + sdf.format(fecha)));
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
            if (name.equals("numero")) {
                return type.integerType();
            }
            if (name.equals("recibe")) {
                return type.integerType();
            }
            if (name.equals("paga")) {
                return type.integerType();
            }
            if (name.equals("total")) {
                return type.integerType();
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

        protected String getFieldLabel(String name) {
            if (name.equals("numero")) {
                return "#";
            }
            if (name.equals("ppupaga")) {
                return "PPU";
            }
            if (name.equals("ppurecibe")) {
                return "PPU";
            }
            if (name.equals("flotapaga")) {
                return "Flota";
            }
            if (name.equals("flotarecibe")) {
                return "Flota";
            }
            if (name.equals("paga")) {
                return "N° Bus Paga";
            }
            if (name.equals("recibe")) {
                return "N° Bus Recibe";
            }
            if (name.equals("total")) {
                return "Total";
            }
            return name;
        }

    }

}
