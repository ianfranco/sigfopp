/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.entities.ProcesoRecaudacion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

/**
 *
 * @author ianfranco
 */
@Named(value = "pdfReportController")
@SessionScoped
public class PdfReportController implements Serializable {

    private JRMapCollectionDataSource data;
    private Collection<Map<String, ?>> map;
    private Map parameters;
    private JasperPrint jasperPrint;
    private Date recaudacion;
    private ProcesoRecaudacion procesoRecaudacion;
    private ServletOutputStream servletOutputStream;

    /**
     * Creates a new instance of PdfReportController
     */
    public PdfReportController() {
    }

    /**
     * @return the map
     */
    public Collection<Map<String, ?>> getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Collection<Map<String, ?>> map) {
        this.map = map;
    }

    public JRMapCollectionDataSource getData() {
        return data;
    }

    public void setData(JRMapCollectionDataSource data) {
        this.data = data;
    }

    public void init() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(map);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/INF-Recaudacion.jasper");

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://www.areatecnica.cl:3306/sigf_v2", "root", "NintendO64");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        this.parameters = new HashMap();
        this.parameters.put("fecha_recaudacion", recaudacion);
        this.parameters.put("proceso_id", this.procesoRecaudacion.getProcesoRecaudacionId());

        jasperPrint = JasperFillManager.fillReport(reportPath, this.parameters, connection);
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=report.pdf");
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void DOCX(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
        servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);

        //docxExporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public ServletOutputStream getServletOutputStream() {
        return servletOutputStream;
    }

    public void setServletOutputStream(ServletOutputStream servletOutputStream) {
        this.servletOutputStream = servletOutputStream;
    }
    
    

    public void init2() {
        //JasperReportBuilder report = new JasperReportBuilder();
        /*String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/report.jasper");        

        this.data = new JRMapCollectionDataSource(getMap());

        try {
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), data);
        } catch (JRException ex) {
            Logger.getLogger(PdfReportController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://www.areatecnica.cl:3306/sigf_v2", "root", "NintendO64");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        JasperReportBuilder report = DynamicReports.report();//a new report
        report
                .columns(
                        Columns.column("Numero", "flota_id", DataTypes.integerType()),
                        Columns.column("Nombre", "flota_nombre", DataTypes.stringType())
                )
                .title(//title of the report
                        Components.text("SimpleReportExample")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource("SELECT flota_id, flota_nombre FROM flota",
                        connection);

        try {
            //show the report
            //report.show();

            //export the report to a pdf file
            report.toCsv(new FileOutputStream("C:/Users/ianfr/report.csv"));
        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public OutputStream getOS(ServletContext context, OutputStream outputStream) {

        //InputStream is = context.getResourceAsStream("/jasper/invoices/" + invoiceName);
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://www.areatecnica.cl:3306/sigf_v2", "root", "NintendO64");
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        JasperReportBuilder report = DynamicReports.report();//a new report
        report
                .columns(
                        Columns.column("Numero", "flota_id", DataTypes.integerType()),
                        Columns.column("Nombre", "flota_nombre", DataTypes.stringType())
                )
                .title(//title of the report
                        Components.text("SimpleReportExample")
                                .setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource("SELECT flota_id, flota_nombre FROM flota",
                        connection);

        try {

            report
                    .toPdf(outputStream);

        } catch (DRException e) {

            e.printStackTrace();

        }

        return outputStream;

    }

    public Date getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Date recaudacion) {
        this.recaudacion = recaudacion;
    }

    public ProcesoRecaudacion getProcesoRecaudacion() {
        return procesoRecaudacion;
    }

    public void setProcesoRecaudacion(ProcesoRecaudacion procesoRecaudacion) {
        this.procesoRecaudacion = procesoRecaudacion;
    }

}
