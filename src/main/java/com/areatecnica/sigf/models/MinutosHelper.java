/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.RegistroMinuto;

/**
 *
 * @author ianfr
 */
public class MinutosHelper {

        private String observacion;
        private RegistroMinuto registro;
        private Boolean todos;

        public MinutosHelper(String observacion, RegistroMinuto registro, Boolean todos) {
            this.observacion = observacion;
            this.registro = registro;
            this.todos = todos;
        }

        public MinutosHelper() {
        }

        public String getObservacion() {
            return observacion;
        }

        public void setObservacion(String observacion) {
            this.observacion = observacion;
        }

        public RegistroMinuto getRegistro() {
            return registro;
        }

        public void setRegistro(RegistroMinuto registro) {
            this.registro = registro;
        }

        public void setTodos(Boolean todos) {
            this.todos = todos;
        }

        public Boolean getTodos() {
            return todos;
        }

    }
