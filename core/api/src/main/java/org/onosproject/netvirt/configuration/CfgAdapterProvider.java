package org.onosproject.netvirt.configuration;

import java.util.List;

import org.onosproject.net.Network;
import org.onosproject.net.provider.Provider;

/**
 * Configuration southbound adaptor provider.
 */
public interface CfgAdapterProvider extends Provider {

    /**
     * Checks for the existence of the Integration Bridge on a given Node.
     *
     * @param node the Node where the bridge should be configured
     * @return True if the bridge exists, False if it does not
     */
    public boolean isNodeNeutronReady(Node node);

    /**
     * Returns true if the bridges required for the provider network type are
     * created If the bridges are not created, this method will attempt to
     * create them.
     *
     * @param node the Node to query
     * @param network the NeutronNetwork
     *
     * @return True or False
     */
    public boolean createLocalNetwork(Node node, Network network);

    /**
     * Prepares the given Node for Neutron Networking by creating the
     * Integration Bridge.
     *
     * @param node the Node to prepare
     */
    public void prepareNode(Node node);

    /**
     * Returns the physical interface mapped to the given neutron physical
     * network.
     *
     * @param node
     * @param physicalNetwork
     * @return
     */
    public String getPhysicalInterfaceName(Node node, String physicalNetwork);

    /**
     * Returns all physical interfaces configured in the bridge mapping Bridge
     * mappings will be of the following format.
     *
     * @param node the Node to query
     * @return a List in the format {eth1, eth2} given
     *         bridge_mappings=physnet1:eth1,physnet2:eth2
     */
    public List<String> getAllPhysicalInterfaceNames(Node node);

    public void processRowUpdate(RowEvent event);
    public void processRowAdded(RowEvent event);
    public void processRowRemove(RowEvent event);
}
