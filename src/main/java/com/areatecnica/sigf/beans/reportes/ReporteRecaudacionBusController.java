/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.reportes;

import com.areatecnica.sigf.beans.recaudacion.*;
import com.areatecnica.sigf.beans.AbstractCrudController;
import com.areatecnica.sigf.beans.RegistroMinutoController;
import com.areatecnica.sigf.beans.TrabajadorController;
import com.areatecnica.sigf.controllers.RecaudacionFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IRecaudacionDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.RecaudacionEgreso;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.UnidadNegocio;
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
public class ReporteRecaudacionBusController extends AbstractCrudController<Recaudacion> implements IProcess {

    /*Inyección de Controladores*/
    @Inject
    private RecaudacionFacade facade;
    @Inject
    private TrabajadorController trabajadorController;

    /*Listas para llenar las tablas*/
    private List<LinkedHashMap> mapsItems;

    private RecaudacionDataModel recaudacionDataModel;

    //Listas auxiliares para tabla general
    /*Lista de todos los egresos en caja*/
    private List<Egreso> egresoItems;
    /*Auxiliar al registro de egresos x recaudación*/
    private List<RecaudacionEgreso> recaudacionEgresoItems;
    /*Lista de buses permitidos de recaudar*/
    private List<Bus> busItems;
    /*Lista de Unidades por Terminal*/
    private List<UnidadNegocio> unidadItems;
    /*Listas para totales y resultados*/
    private LinkedHashMap totals;
    private LinkedHashMap<String, Egreso> header;
    private List<String> resultsHeader;
    private List<String> resultsTotals;

    /*Data Access Objects*/
    private IRecaudacionDao recaudacionDao;
    private IUnidadNegocioDao unidadNegocioDao;
    private IBusDao busDao;

    /*Objetos Auxiliares*/
    private Date fechaRecaudacion;
    private CurrentDate currentDate = new CurrentDate();
    private UnidadNegocio unidad;
    private Bus bus;
    private int mes;
    private int anio;

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

    private ServletOutputStream servletOutputStream;

    /**
     * Creates a new instance of RecaudacionGeneralController
     */
    public ReporteRecaudacionBusController() {
        super(Recaudacion.class);
    }

    @PostConstruct
    @Override
    public void init() {
        this.header = new LinkedHashMap();
        super.setFacade(facade);
        this.setFechaRecaudacion(getCurrentDate().date());

        this.currentDate = new CurrentDate();

        this.setAnio(getCurrentDate().year());
        this.setMes(getCurrentDate().month());

        this.unidadNegocioDao = new IUnidadNegocioDaoImpl();
        this.setUnidadItems((List<UnidadNegocio>) this.unidadNegocioDao.findByCuenta(this.getUserCount()));

        this.busDao = new IBusDaoImpl();
        this.busItems = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());

        this.setTotalRecaudacion(0);
        this.setTotalCombustible(0);
        this.setTotalMinutos(0);

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
     * @return the unidadItems
     */
    public List<UnidadNegocio> getUnidadItems() {
        return unidadItems;
    }

    /**
     * @param unidadItems the unidadItems to set
     */
    public void setUnidadItems(List<UnidadNegocio> unidadItems) {
        this.unidadItems = unidadItems;
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
     * @return the unidad
     */
    public UnidadNegocio getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(UnidadNegocio unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
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

    @Override
    public void load() {
        /* Lista de objetos recaudación*/
        this.setMapsItems(new ArrayList<>());
        /* Carga información de egresos*/
        this.setEgresoItems(new ArrayList<>());
        /* Inicio de Totals*/
        this.setTotals(new LinkedHashMap());

        this.totalRecaudacion = 0;
        this.totalMinutos = 0;
        this.totalCombustible = 0;
        this.resultsTotals = new ArrayList<>();

        /* Inicio de headers*/
        this.setResultsHeader(new ArrayList<>());

        LinkedHashMap _defaultLink = new LinkedHashMap();

        CurrentDate cd = new CurrentDate(1, mes, anio);

        this.recaudacionDao = new IRecaudacionDaoImpl();
        this.setItems(this.recaudacionDao.findByBusBetweenFechaRecaudacion(this.getBus(), cd.date(), cd.getMaxDate()));

        this.setRecaudacionDataModel(new RecaudacionDataModel(getItems()));

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
                        header.put(key, re.getRecaudacionEgresoIdEgreso());
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

                }

                /*Carga información de minutos por cada recaudación*/
                if (!r.getRecaudacionMinutoList().isEmpty()) {
                    int _auxMinutos = 0;

                    for (RecaudacionMinuto m : r.getRecaudacionMinutoList()) {
                        _auxMinutos += m.getRecaudacionMinutoMonto();
                    }
                    r.setTotalMinutos(_auxMinutos);
                    this.setTotalMinutos(this.getTotalMinutos() + _auxMinutos);

                }

                r.setRecaudacionTotal(r.getTotalMinutos() + r.getTotalCombustible() + _totalRecaudacion);

                totalRecaudacion += r.getRecaudacionTotal();
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
        System.err.println("total de elementos en el array de valores:" + this.totals.size());

        this.resultsHeader = new ArrayList<>();

        for (Map.Entry<String, Egreso> a : header.entrySet()) {
            resultsHeader.add(a.getKey());
        }

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

    public void handleUnidadChange(ActionEvent event) {
        if (this.getUnidad() != null) {
            this.busItems = this.getUnidad().getBusList();
        }
    }

    public void handleBusChange(ActionEvent event) {
        if (this.getSelected().getRecaudacionIdBus() != null) {

        }
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

        this.getSelected().setTotalMinutos(_minutos);

        this.setTotalRecaudacionMinuto(_minutos);
        this.setTotalRecaudacionCombustible(_petroleo);

        total += _petroleo + _minutos;

        this.getSelected().setRecaudacionTotal(total);
        return total;
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
            report.title(Templates.createTitleComponent("Producción Bus N°:" + bus.getBusNumero() + " al :" + currentDate.format(fechaRecaudacion)));
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
