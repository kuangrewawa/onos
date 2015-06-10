package org.onosproject.netvirt.inventory.impl;

import static org.slf4j.LoggerFactory.getLogger;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.net.provider.AbstractProviderRegistry;
import org.onosproject.net.provider.AbstractProviderService;
import org.onosproject.net.provider.ProviderId;
import org.onosproject.netvirt.configuration.ConfigurationService;
import org.onosproject.netvirt.configuration.Node;
import org.onosproject.netvirt.configuration.NodeEvent;
import org.onosproject.netvirt.configuration.RowEvent;
import org.onosproject.netvirt.inventory.InventoryAdapterProvider;
import org.onosproject.netvirt.inventory.InventoryProviderRegistry;
import org.onosproject.netvirt.inventory.InventoryProviderService;
import org.onosproject.netvirt.inventory.InventoryService;
import org.slf4j.Logger;

@Component(immediate = true, enabled = true)
@Service
public class InventoryManager
        extends
        AbstractProviderRegistry<InventoryAdapterProvider, InventoryProviderService>
        implements InventoryService, InventoryProviderRegistry {
    private final Logger log = getLogger(getClass());
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    ConfigurationService configurationService;

    @Activate
    public void activate() {
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        log.info("Stopped");
    }

    @Override
    public Node[] getNodes() {
        InventoryAdapterProvider provider = getProvider(new ProviderId("ovsdb",
                                                                       "org.onosproject.provider.ovsdb.inventory"));
        return provider.getNodes();
    }

    @Override
    public void processNodeUpdate(NodeEvent event) {
        if (NodeEvent.Type.NODE_ADDED.equals(event.type())) {
            configurationService.prepareNode(event.subject());
        }
    }

    @Override
    protected InventoryProviderService createProviderService(InventoryAdapterProvider provider) {
        return new InternalInventoryProviderService(provider);
    }

    private class InternalInventoryProviderService
            extends AbstractProviderService<InventoryAdapterProvider>
            implements InventoryProviderService {

        protected InternalInventoryProviderService(InventoryAdapterProvider provider) {
            super(provider);
        }

        @Override
        public void nodeAdded(NodeEvent event) {
            processNodeUpdate(event);

        }

        @Override
        public void rowAdded(RowEvent event) {
            configurationService.processRowUpdate(event);

        }

        @Override
        public void rowUpdate(RowEvent event) {
            // TODO Auto-generated method stub
            configurationService.processRowUpdate(event);
        }

        @Override
        public void rowRemove(RowEvent event) {
            // TODO Auto-generated method stub
            configurationService.processRowUpdate(event);
        }
    }
}
