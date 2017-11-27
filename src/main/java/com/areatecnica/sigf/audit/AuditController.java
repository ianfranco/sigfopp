package com.areatecnica.sigf.audit;

import com.areatecnica.sigf.beans.AbstractController;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Omer Faruk KURT
 * @blog http://kurtomerfaruk.com
 * @mail kurtomerfaruk@gmail.com
 * @Created on date 19/10/2016 15:19:37
 */
@ViewScoped
@Named
public class AuditController extends AbstractController<AuditEntry> implements java.io.Serializable {

    private static final long serialVersionUID = 8548529037103057476L;

    public AuditController() {
        super(AuditEntry.class);
        columnNames();
        //columnList();
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
