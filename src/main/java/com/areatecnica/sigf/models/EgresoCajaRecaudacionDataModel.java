/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class EgresoCajaRecaudacionDataModel extends ListDataModel<EgresoCajaRecaudacion> implements SelectableDataModel<EgresoCajaRecaudacion> {

    public EgresoCajaRecaudacionDataModel() {
    }

    public EgresoCajaRecaudacionDataModel(List<EgresoCajaRecaudacion> list) {
        super(list);
    }

    @Override
    public Object getRowKey(EgresoCajaRecaudacion object) {
        return object.getEgresoCajaRecaudacionId();
    }

    @Override
    public EgresoCajaRecaudacion getRowData(String rowKey) {
        List<EgresoCajaRecaudacion> items = (List<EgresoCajaRecaudacion>) getWrappedData();

        for (EgresoCajaRecaudacion e : items) {
            if (e.getEgresoCajaRecaudacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
