package org.onosproject.netvirt.inventory;

import org.onosproject.netvirt.configuration.Node;
import org.onosproject.netvirt.configuration.NodeEvent;

/**
 * Inventory of core.
 */
public interface InventoryService {

    /**
     * Get all Nodes.
     *
     * @return Node[]
     */
    Node[] getNodes();

    /**
     * Process event when Node is update.
     *
     * @param event NodeEvent
     */
    void processNodeUpdate(NodeEvent event);
}
