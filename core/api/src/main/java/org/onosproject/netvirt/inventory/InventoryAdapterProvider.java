package org.onosproject.netvirt.inventory;

import java.net.InetAddress;

import org.onosproject.net.provider.Provider;
import org.onosproject.netvirt.configuration.Node;
import org.onosproject.ovsdb.lib.message.TableUpdates;

/**
 * Adapter of SB inventory provider.
 */
public interface InventoryAdapterProvider extends Provider {

    public void getDBCache();

    public void getTableCache();

    public Node[] getNodes();

    void notifyNodeAdded(Node node, InetAddress address, int port);

    public void processTableUpdates(Node node, String name, TableUpdates updates);
}
