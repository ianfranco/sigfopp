/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ITrabajadorDao<T> extends IGenericDAO<T> {
    
    public int findMaxCodigoCuenta(Cuenta cuenta);
    
    public List<Trabajador> findByTerminal(Terminal terminal);
    
    public Trabajador findByTrabajadorRutAndCuenta(String rut, Cuenta cuenta);
    
    public Trabajador findByDefaultTrabajador(Integer id, Cuenta cuenta);
    
}
