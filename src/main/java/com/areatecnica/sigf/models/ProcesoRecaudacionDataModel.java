/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class ProcesoRecaudacionDataModel extends ListDataModel<ProcesoRecaudacion> implements SelectableDataModel<ProcesoRecaudacion> {

    public ProcesoRecaudacionDataModel() {
    }

    public ProcesoRecaudacionDataModel(List<ProcesoRecaudacion> list) {
        super(list);
    }

    @Override
    public Object getRowKey(ProcesoRecaudacion object) {
        return object.getProcesoRecaudacionId();
    }

    @Override
    public ProcesoRecaudacion getRowData(String rowKey) {
        List<ProcesoRecaudacion> items = (List<ProcesoRecaudacion>) getWrappedData();

        for (ProcesoRecaudacion e : items) {
            if (e.getProcesoRecaudacionId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
