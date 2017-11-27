/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RecaudacionMinuto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class RecaudacionMinutoDataModel extends ListDataModel<RecaudacionMinuto> implements SelectableDataModel<RecaudacionMinuto> {

    public RecaudacionMinutoDataModel() {
    }

    public RecaudacionMinutoDataModel(List<RecaudacionMinuto> list) {
        super(list);
    }

    @Override
    public Object getRowKey(RecaudacionMinuto object) {
        return object.getRecaudacionMinutoId();
    }

    @Override
    public RecaudacionMinuto getRowData(String rowKey) {
        List<RecaudacionMinuto> items = (List<RecaudacionMinuto>) getWrappedData();

        for (RecaudacionMinuto e : items) {
            if (e.getRecaudacionMinutoId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
