/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.VentaCombustible;

/**
 *
 * @author ianfr
 */
public class PetroleoHelper {

    private String observacion;
    private VentaCombustible ventaCombustible;

    public PetroleoHelper() {
    }

    public PetroleoHelper(String observacion, VentaCombustible ventaCombustible) {
        this.observacion = observacion;
        this.ventaCombustible = ventaCombustible;
    }

    public VentaCombustible getVentaCombustible() {
        return ventaCombustible;
    }

    public void setVentaCombustible(VentaCombustible ventaCombustible) {
        this.ventaCombustible = ventaCombustible;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
