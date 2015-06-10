package org.onosproject.netvirt.inventory;

import org.onosproject.net.provider.ProviderService;
import org.onosproject.netvirt.configuration.NodeEvent;
import org.onosproject.netvirt.configuration.RowEvent;

/**
 * Inventory provider service for southbound ovsdb.
 */
public interface InventoryProviderService
        extends ProviderService<InventoryAdapterProvider> {

    public void nodeAdded(NodeEvent event);

    public void rowAdded(RowEvent event);
    public void rowUpdate(RowEvent event);
    public void rowRemove(RowEvent event);
}
