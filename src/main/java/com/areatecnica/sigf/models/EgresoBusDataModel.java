/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.EgresoBus;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class EgresoBusDataModel extends ListDataModel<EgresoBus> implements SelectableDataModel<EgresoBus> {

    public EgresoBusDataModel() {
    }

    public EgresoBusDataModel(List<EgresoBus> list) {
        super(list);
    }

    @Override
    public Object getRowKey(EgresoBus object) {
        return object.getEgresoBusId();
    }

    @Override
    public EgresoBus getRowData(String rowKey) {
        List<EgresoBus> items = (List<EgresoBus>) getWrappedData();

        for (EgresoBus e : items) {
            if (e.getEgresoBusId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
