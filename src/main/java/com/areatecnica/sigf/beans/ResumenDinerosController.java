/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans;

import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.dao.IResumenRecaudacionDao;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IResumenRecaudacionDaoImpl;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.EgresoResumenRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.joda.time.LocalDate;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author ianfr
 */
@Named(value = "resumenDinerosController")
@ViewScoped
public class ResumenDinerosController extends AbstractController<ResumenRecaudacion> {

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private IResumenRecaudacionDao resumenRecaudacionDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private List<ResumenRecaudacion> list;
    private List<CajaRecaudacion> cajaRecaudacionList;
    private List<ProcesoRecaudacion> procesoRecaudacionList;
    private List<EgresoResumenRecaudacion> egresoRecaudacionList;
    private Map mapResumen;
    private CajaRecaudacion cajaRecaudacion;
    private ProcesoRecaudacion procesoRecaudacion;
    private Date from;
    private Date to;
    private int mes;
    private int anio;
    private int resumenTotal;
    private String resumenTotalFormat;
    private String stringDate;
    private Calendar calendar;
    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates a new instance of ResumenDinerosController
     */
    public ResumenDinerosController() {
        super(ResumenRecaudacion.class);
    }

    @PostConstruct
    @Override
    public void init() {
        setEventModel(new DefaultScheduleModel());

        calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.DATE, 1);
        this.setFrom(calendar.getTime());

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        this.setTo(calendar.getTime());

        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.cajaRecaudacionList = this.cajaRecaudacionDao.findAllByUser(this.getCurrentUser());
        
        if (this.cajaRecaudacionList.size() == 1) {
            this.cajaRecaudacion = this.cajaRecaudacionList.get(0);

            handleCajaRecaudacionChange(null);
        }

    }

    /**
     * @return the list
     */
    public List<ResumenRecaudacion> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<ResumenRecaudacion> list) {
        this.list = list;
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
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Date from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Date getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Date to) {
        this.to = to;
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
     * @return the egresoRecaudacionList
     */
    public List<EgresoResumenRecaudacion> getEgresoRecaudacionList() {
        return egresoRecaudacionList;
    }

    /**
     * @param egresoRecaudacionList the egresoRecaudacionList to set
     */
    public void setEgresoRecaudacionList(List<EgresoResumenRecaudacion> egresoRecaudacionList) {
        this.egresoRecaudacionList = egresoRecaudacionList;
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

    public ScheduleEvent getEvent() {
        return event;
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            getEventModel().addEvent(event);
        } else {
            getEventModel().updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();

        if (mapResumen.containsKey(event.getStartDate())) {
            ResumenRecaudacion resumen = (ResumenRecaudacion) mapResumen.get(event.getStartDate());
            this.setEgresoRecaudacionList(resumen.getEgresoResumenRecaudacionList());
            int total = 0;
            for (EgresoResumenRecaudacion er : this.egresoRecaudacionList) {
                total += er.getEgresoResumenRecaudacionTotal();
            }
            this.setResumenTotal(total);
            this.setResumenTotalFormat(decimalFormat.format(getResumenTotal()));
            Date auxDate = event.getStartDate();
            this.stringDate = format.format(auxDate);
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void load() {
        mapResumen = new HashMap();
        setEventModel(new DefaultScheduleModel());

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, mes - 1);
        this.setFrom(calendar.getTime());

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        this.setTo(calendar.getTime());

        this.resumenRecaudacionDao = new IResumenRecaudacionDaoImpl();

        LocalDate fechaInicio = new LocalDate(this.from);
        LocalDate fechaFinal = new LocalDate(this.to);

        for (LocalDate date = fechaInicio; date.isBefore(fechaFinal.plusDays(1)); date = date.plusDays(1)) {

            ResumenRecaudacion recaudacion = this.resumenRecaudacionDao.findByCajaProcesoDate(cajaRecaudacion, procesoRecaudacion, date.toDate());
            if (recaudacion != null) {

                String evento = decimalFormat.format(recaudacion.getResumenRecaudacionTotal());

                DefaultScheduleEvent event = new DefaultScheduleEvent(recaudacion.getResumenRecaudacionCerrado() ? evento : "Digitación", date.toDate(), date.toDate(), true);
                event.setStyleClass(".event1");
                getEventModel().addEvent(event);
                mapResumen.put(date.toDate(), recaudacion);
            }
        }
    }

    public void handleCajaRecaudacionChange(ActionEvent event) {
        if (this.cajaRecaudacion != null) {
            this.procesoRecaudacionList = new ArrayList<ProcesoRecaudacion>();

            List<CajaProceso> cajaProcesoList = this.cajaRecaudacion.getCajaProcesoList();

            for (CajaProceso cp : cajaProcesoList) {
                if (cp.getCajaProcesoActivo()) {
                    this.procesoRecaudacionList.add(cp.getCajaProcesoIdProceso());
                }
            }
        }
    }

}
