/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.controllers.BusFacade;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import javax.inject.Named;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author ianfr
 */
@Named(value = "reporteProduccionbusController")
@ViewScoped
public class ReporteProduccionBusController extends AbstractController<Bus> {

    @Inject
    private BusFacade ejbFacade;
    private List<LinkedHashMap> mapsItems;
    private List<Bus> busList;
    private IBusDao busDao;
    private int mes;
    private int anio;

    /**
     * Creates a new instance of reporteProduccionbusController
     */
    public ReporteProduccionBusController() {
        super(Bus.class);

        this.busDao = new IBusDaoImpl();

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH);
        this.anio = calendar.get(Calendar.YEAR);
    }

    /**
     * Initialize the concrete Bus controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.busList = this.busDao.findAllByTerminal(this.getCurrentUser().getUsuarioIdTerminal());
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

}
