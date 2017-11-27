/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Rol;
import com.areatecnica.sigf.entities.RolMenu;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRolMenuDao<T> extends IGenericDAO<T> {

    public List<RolMenu> findAllByRol(Rol rol);

}
