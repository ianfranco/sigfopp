/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.UsuarioSession;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IUsuarioSessionDao<T> extends IGenericDAO<T> {
    
    public List<UsuarioSession> findAllByUsuario(Usuario usuario);
        
    public UsuarioSession findById(int id);

    
    public void ingresar(UsuarioSession usuarioSession);
    
    
    
}
