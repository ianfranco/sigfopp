/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.beans.RecaudacionController;
import com.areatecnica.sigf.beans.RegistroMinutoController;
import com.areatecnica.sigf.entities.Egreso;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author ianfr
 */
public class DynamicReportsServlet{

    private ServletOutputStream servletOutputStream;
    private static final long serialVersionUID = 1L;

    public void exportPdf2(ActionEvent event) {

        /*HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
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

            JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(), new RecaudacionController.ReportCustomizer());
            //reportBuilder.setDataSource(createDataSource());
            reportBuilder.toPdf(servletOutputStream);

        } catch (IOException | DRException ex) {
            Logger.getLogger(RegistroMinutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().responseComplete();
*/
        servletOutputStream = null;
    }

}
