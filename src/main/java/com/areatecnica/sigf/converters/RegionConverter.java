package com.areatecnica.sigf.converters;

import com.areatecnica.sigf.entities.Region;
import com.areatecnica.sigf.controllers.RegionFacade;
import com.areatecnica.sigf.beans.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

@FacesConverter(value = "regionConverter")
public class RegionConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Region) {
            Region o = (Region) object;
            return getStringKey(o.getRegionId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Region.class.getName()});
            return null;
        }
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

}
