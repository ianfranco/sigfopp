/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.audit;

import com.areatecnica.sigf.entities.Usuario;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.queries.InsertObjectQuery;
import org.eclipse.persistence.queries.WriteObjectQuery;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.changesets.ChangeRecord;
import org.eclipse.persistence.sessions.changesets.DirectToFieldChangeRecord;

/**
 *
 * @author Omer Faruk KURT
 * @Created on date 18/10/2016 15:31:36
 */
public class AuditListener extends DescriptorEventAdapter implements SessionCustomizer, DescriptorCustomizer {

    public static ThreadLocal currentUser = new ThreadLocal();
    private Usuario usuario;

    @Override
    public void customize(Session sn) throws Exception {
        for (ClassDescriptor cd : sn.getDescriptors().values()) {
            customize(cd);
        }
    }

    @Override
    public void customize(ClassDescriptor cd) throws Exception {
        cd.getEventManager().addListener(this);
    }

    @PostPersist
    @Override
    public void aboutToInsert(DescriptorEvent event) {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        processEvent(event, AuditOperation.INGRESO);
    }

    @PostUpdate
    @Override
    public void aboutToUpdate(DescriptorEvent event) {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        processEvent(event, AuditOperation.EDICION);
    }

    @PostRemove
    @Override
    public void aboutToDelete(DescriptorEvent event) {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        processEvent(event, AuditOperation.ELIMINACION);
    }

    public void processEvent(DescriptorEvent event, AuditOperation operation) {
        Date date = new Date();

        for (String table : (List<String>) event.getDescriptor().getTableNames()) {

            System.err.println("TABLA:" + table);
            System.err.println("CORTADO:" + table.substring(8));

            if (operation == AuditOperation.EDICION) {
                processWriteEvent(event, operation, date, table.substring(8));
            } else {
                processAuditEvent(event, operation, date, table.substring(8));
            }
        }

    }

    protected void processAuditEvent(DescriptorEvent event, AuditOperation operation, Date date, String tableName) {
        AuditEntry entry = createAuditEntry(event, operation, date, tableName);
        InsertObjectQuery insertObjectQuery = new InsertObjectQuery(entry);
        event.getSession().executeQuery(insertObjectQuery);
    }

    protected void processWriteEvent(DescriptorEvent event,
            AuditOperation operation,
            Date date,
            String tableName) {
        AuditEntry entry = createAuditEntry(event, operation, date, tableName);

        Collection<AuditField> fields = new LinkedList<AuditField>();
        WriteObjectQuery query = (WriteObjectQuery) event.getQuery();
        List<ChangeRecord> changes = query.getObjectChangeSet().getChanges();

        for (ChangeRecord change : changes) {
            if (change instanceof DirectToFieldChangeRecord) {
                DirectToFieldChangeRecord fieldChange = (DirectToFieldChangeRecord) change;
                AuditField field = new AuditField();
                field.setAuditEntryId(entry);
                field.setFieldName(fieldChange.getAttribute().replaceAll(tableName, ""));

                field.setFieldValue(fieldChange.getNewValue().toString());
                fields.add(field);
            }
        }

        entry.setField(fields);

        InsertObjectQuery insertQuery = new InsertObjectQuery(entry);
        event.getSession().executeQuery(insertQuery);

        for (AuditField field : fields) {
            insertQuery = new InsertObjectQuery(field);
            event.getSession().executeQuery(insertQuery);
        }
    }

    protected AuditEntry createAuditEntry(DescriptorEvent event,
            AuditOperation operation,
            Date date,
            String tableName) {
        AuditEntry entry = new AuditEntry();

        entry.setAuditUser(usuario);
        entry.setOperation(operation);
        entry.setOperationTime(date);
        entry.setEventId(Long.valueOf(event.getSource().hashCode()));
        entry.setTableName(tableName);
        return entry;
    }

}
