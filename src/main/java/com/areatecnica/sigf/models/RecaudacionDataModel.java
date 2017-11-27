/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.Recaudacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class RecaudacionDataModel extends ListDataModel<Recaudacion> implements SelectableDataModel<Recaudacion> {

    public RecaudacionDataModel() {
    }

    public RecaudacionDataModel(List<Recaudacion> list) {
        super(list);
    }

    @Override
    public Object getRowKey(Recaudacion object) {
        return object.getRecaudacionId();
    }

    @Override
    public Recaudacion getRowData(String rowKey) {
        List<Recaudacion> items = (List<Recaudacion>) getWrappedData();

        for (Recaudacion e : items) {
            if (e.getRecaudacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
