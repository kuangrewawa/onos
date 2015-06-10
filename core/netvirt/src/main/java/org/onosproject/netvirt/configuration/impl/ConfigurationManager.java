package org.onosproject.netvirt.configuration.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.net.Network;
import org.onosproject.net.provider.AbstractProviderRegistry;
import org.onosproject.net.provider.AbstractProviderService;
import org.onosproject.net.provider.ProviderId;
import org.onosproject.netvirt.configuration.CfgAdapterProvider;
import org.onosproject.netvirt.configuration.ConfigurationProviderRegistry;
import org.onosproject.netvirt.configuration.ConfigurationProviderService;
import org.onosproject.netvirt.configuration.ConfigurationService;
import org.onosproject.netvirt.configuration.Node;
import org.onosproject.netvirt.configuration.RowEvent;
import org.slf4j.Logger;

@Component(immediate = true, enabled = true)
@Service
public class ConfigurationManager
        extends
        AbstractProviderRegistry<CfgAdapterProvider, ConfigurationProviderService>
        implements ConfigurationService, ConfigurationProviderRegistry {
    private final Logger log = getLogger(getClass());
    static ProviderId providerId = new ProviderId("ovsdb",
                                                  "org.onosproject.provider.ovsdb");

    @Activate
    public void activate() {
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        log.info("Stopped");
    }

    @Override
    public boolean isNodeNeutronReady(Node node) {
        CfgAdapterProvider provider = getProvider(providerId);
        return provider.isNodeNeutronReady(node);
    }

    @Override
    public boolean createLocalNetwork(Node node, Network network) {
        CfgAdapterProvider provider = getProvider(providerId);
        return provider.createLocalNetwork(node, network);
    }

    @Override
    public void prepareNode(Node node) {
        CfgAdapterProvider provider = getProvider(providerId);
        provider.prepareNode(node);

    }

    @Override
    public String getPhysicalInterfaceName(Node node, String physicalNetwork) {
        CfgAdapterProvider provider = getProvider(providerId);
        return provider.getPhysicalInterfaceName(node, physicalNetwork);
    }

    @Override
    public List<String> getAllPhysicalInterfaceNames(Node node) {
        CfgAdapterProvider provider = getProvider(providerId);
        return provider.getAllPhysicalInterfaceNames(node);
    }

    @Override
    protected ConfigurationProviderService createProviderService(CfgAdapterProvider provider) {
        return new InternalCfgProviderService(provider);
    }

    private class InternalCfgProviderService
            extends AbstractProviderService<CfgAdapterProvider>
            implements ConfigurationProviderService {

        protected InternalCfgProviderService(CfgAdapterProvider provider) {
            super(provider);
        }

        @Override
        public void rowInserted() {
            // TODO Auto-generated method stub

        }

        @Override
        public void rowUpdated() {
            // TODO Auto-generated method stub

        }

        @Override
        public void rowDeleted() {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public void processRowUpdate(RowEvent event) {
        CfgAdapterProvider provider = getProvider(providerId);
        provider.processRowUpdate(event);

    }

    @Override
    public void processRowAdded(RowEvent event) {
        // TODO Auto-generated method stub
        CfgAdapterProvider provider = getProvider(providerId);
        provider.processRowAdded(event);
    }

    @Override
    public void processRowRemove(RowEvent event) {
        // TODO Auto-generated method stub
        CfgAdapterProvider provider = getProvider(providerId);
        provider.processRowRemove(event);
    }
}
