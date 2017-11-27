/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.EgresoFlota;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class EgresoFlotaDataModel extends ListDataModel<EgresoFlota> implements SelectableDataModel<EgresoFlota> {

    public EgresoFlotaDataModel() {
    }

    public EgresoFlotaDataModel(List<EgresoFlota> list) {
        super(list);
    }

    @Override
    public Object getRowKey(EgresoFlota object) {
        return object.getEgresoFlotaId();
    }

    @Override
    public EgresoFlota getRowData(String rowKey) {
        List<EgresoFlota> items = (List<EgresoFlota>) getWrappedData();

        for (EgresoFlota e : items) {
            if (e.getEgresoFlotaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
