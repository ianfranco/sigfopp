/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.recaudacion;

import com.areatecnica.sigf.beans.AbstractCrudController;
import com.areatecnica.sigf.entities.RecaudacionBoleto;

/**
 *
 * @author ianfr
 */
public class RecaudacionBoletoController extends AbstractCrudController<RecaudacionBoleto> {

    /**
     * Creates a new instance of RecaudacionBoletoController
     */
    public RecaudacionBoletoController() {
        super(RecaudacionBoleto.class);
    }

    @Override
    public void init() {

    }

}
