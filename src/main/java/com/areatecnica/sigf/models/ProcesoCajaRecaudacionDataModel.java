/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.CajaProceso;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class ProcesoCajaRecaudacionDataModel extends ListDataModel<CajaProceso> implements SelectableDataModel<CajaProceso> {

    public ProcesoCajaRecaudacionDataModel() {
    }

    public ProcesoCajaRecaudacionDataModel(List<CajaProceso> list) {
        super(list);
    }

    @Override
    public Object getRowKey(CajaProceso object) {
        return object.getCajaProcesoId();
    }

    @Override
    public CajaProceso getRowData(String rowKey) {
        List<CajaProceso> items = (List<CajaProceso>) getWrappedData();

        for (CajaProceso e : items) {
            if (e.getCajaProcesoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
