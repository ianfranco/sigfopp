package com.areatecnica.sigf.beans;


import com.areatecnica.sigf.audit.AuditEntry;
import com.areatecnica.sigf.controllers.AuditEntryFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Omer Faruk KURT 
 * @e-mail kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.com
 */
@ManagedBean
@ViewScoped
public class AuditController extends AbstractController<AuditEntry> implements java.io.Serializable {

    private static final long serialVersionUID = 8548529037103057476L;
    @Inject
    private AuditEntryFacade ejbFacade;

    public AuditController() {
        super(AuditEntry.class);
        columnNames();
        //columnList();
    }

    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

}
