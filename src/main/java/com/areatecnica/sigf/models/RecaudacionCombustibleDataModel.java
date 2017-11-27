/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RecaudacionCombustible;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class RecaudacionCombustibleDataModel extends ListDataModel<RecaudacionCombustible> implements SelectableDataModel<RecaudacionCombustible> {

    public RecaudacionCombustibleDataModel() {
    }

    public RecaudacionCombustibleDataModel(List<RecaudacionCombustible> list) {
        super(list);
    }

    @Override
    public Object getRowKey(RecaudacionCombustible object) {
        return object.getRecaudacionCombustibleId();
    }

    @Override
    public RecaudacionCombustible getRowData(String rowKey) {
        List<RecaudacionCombustible> items = (List<RecaudacionCombustible>) getWrappedData();

        for (RecaudacionCombustible e : items) {
            if (e.getRecaudacionCombustibleId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
