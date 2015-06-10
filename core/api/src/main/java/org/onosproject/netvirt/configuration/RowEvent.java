package org.onosproject.netvirt.configuration;

import org.onosproject.event.AbstractEvent;
import org.onosproject.ovsdb.lib.notation.Row;

public class RowEvent extends AbstractEvent<RowEvent.Type, Row> {

    private Node node;
    private String tableName;
    private String uuid;
    private Row oldRow;

    /**
     * Type of device events.
     */
    public enum Type {
        /**
         * Signifies that a new row has been detected.
         */
        ROW_ADDED,

        /**
         * Signifies that some row attributes have changed; excludes
         * availability changes.
         */
        ROW_UPDATED,

        /**
         * Signifies that a row has been removed.
         */
        ROW_REMOVED,

    }

    public RowEvent(Type type, Row row) {
        this(type, row, null);
    }

    public RowEvent(Type type, Row row, Node node) {
        super(type, row);
        this.node = node;
    }

    public RowEvent(Type type, Row row, Node node, String tableName,
                    String uuid, Row oldRow) {
        super(type, row);
        this.node = node;
        this.tableName = tableName;
        this.uuid = uuid;
        this.oldRow = oldRow;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Row getOldRow() {
        return oldRow;
    }

    public void setOldRow(Row oldRow) {
        this.oldRow = oldRow;
    }

}
