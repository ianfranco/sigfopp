package com.areatecnica.sigf.converters;

import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.controllers.DetalleCompraBoletoFacade;
import com.areatecnica.sigf.beans.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "detalleCompraBoletoConverter")
public class DetalleCompraBoletoConverter implements Converter {

    @Inject
    private DetalleCompraBoletoFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof DetalleCompraBoleto) {
            DetalleCompraBoleto o = (DetalleCompraBoleto) object;
            return getStringKey(o.getDetalleCompraBoletoId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleCompraBoleto.class.getName()});
            return null;
        }
    }

}
