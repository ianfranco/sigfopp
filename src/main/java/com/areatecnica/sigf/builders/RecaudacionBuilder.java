/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.builders;

import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionEgreso;
import java.util.LinkedHashMap;

/**
 *
 * @author ianfr
 */
public class RecaudacionBuilder {

    private Recaudacion recaudacion;
    private LinkedHashMap auxLink;
    private int total;

    public RecaudacionBuilder(Recaudacion recaudacion) {
        this.recaudacion = recaudacion;
    }

    public LinkedHashMap getLink() {
        this.auxLink = new LinkedHashMap();

        for (RecaudacionEgreso eg : this.recaudacion.getRecaudacionEgresoList()) {
            this.auxLink.put(eg.getRecaudacionEgresoIdEgreso().getEgresoId(), eg.getRecaudacionEgresoMonto());
        }
        
        System.err.println("tamaño del link:"+this.auxLink.size());
        return this.auxLink;
    }

}
