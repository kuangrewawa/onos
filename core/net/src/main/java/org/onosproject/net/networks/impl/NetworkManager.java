/*
 * Copyright 2014 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.net.networks.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.onosproject.net.NetworkId.networkId;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collections;
import java.util.concurrent.ConcurrentMap;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.event.EventDeliveryService;
import org.onosproject.net.DefaultNetwork;
import org.onosproject.net.Network;
import org.onosproject.net.NetworkId;
import org.onosproject.net.networks.NetworkEvent;
import org.onosproject.net.networks.NetworkService;
import org.onosproject.net.networks.NetworkStore;
import org.onosproject.net.networks.NetworkStoreDelegate;
import org.onosproject.net.provider.ProviderId;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

/**
 * Provides implementation of the network NB APIs.
 */
@Component(immediate = true)
@Service
public class NetworkManager implements NetworkService {

    private static final String NETORK_ID_NULL = "Network ID cannot be null";
    private static final String CREATE_NETWORK = "create network";
    private static final String UPDATE_NETWORK = "update network";
    public static final ProviderId PID = new ProviderId("of", "foo");
    private final Logger log = getLogger(getClass());
    private final NetworkStoreDelegate delegate = new InternalStoreDelegate();
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected NetworkStore store;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected EventDeliveryService eventDispatcher;
    @Activate
    public void activate() {
        store.setDelegate(delegate);
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        store.unsetDelegate(delegate);
        log.info("Stopped");
    }
    @Override
    public int getNetworkCount() {
        return store.getNetworkCount();
    }

    @Override
    public Iterable<Network> getNetworks() {
        return store.getNetworks();
    }

    @Override
    public Network getNetwork(NetworkId networkId) {
        checkNotNull(networkId, NETORK_ID_NULL);
        return store.getNetwork(networkId);
    }

    @Override
    public boolean networkExists(NetworkId networkId) {
        checkNotNull(networkId, NETORK_ID_NULL);
        return store.networkExists(networkId);
    }

    @Override
    public boolean removeNetwork(NetworkId networkId) {
        checkNotNull(networkId, NETORK_ID_NULL);
        return store.removeNetwork(networkId);
    }

    @Override
    public boolean networkcreate(JsonNode cfg) {
        JsonNode nodes = null;
        Iterable<Network> networks = null;
        if (cfg.get("network") != null) {
            nodes = cfg.get("network");
            if (nodes.isArray()) {
                networks = changeJson2objs(nodes);
            } else {
                networks = changeJson2obj(CREATE_NETWORK, null, nodes);
            }
        } else if (cfg.get("networks") != null) {
            nodes = cfg.get("networks");
            networks = changeJson2objs(nodes);
        }
        return store.networkcreate(networks);
    }

    @Override
    public boolean networkupdate(NetworkId networkId, JsonNode cfg) {
        JsonNode nodes = null;
        Iterable<Network> networks = null;
        if (cfg.get("network") != null) {
            nodes = cfg.get("network");
            networks = changeJson2obj(UPDATE_NETWORK, networkId, nodes);
        } else if (cfg.get("networks") != null) {
            nodes = cfg.get("networks");
            networks = changeJson2obj(UPDATE_NETWORK, networkId, nodes);
        }
        return store.networkupdate(networkId, networks);
    }
    /**
     * Returns a Object of the currently known infrastructure ovsport.
     * @param ovsportId ovsport identifier
     * @param JsonNode node the ovsport json
     * @return Object of ovsport
     */
    public Iterable<Network> changeJson2obj(String flag, NetworkId networkId, JsonNode node) {
        Network network = null;
        ConcurrentMap<NetworkId, Network> networksMap = Maps.newConcurrentMap();
        if (node != null) {
             String name = node.get("name").asText();
             Boolean adminStateUp = node.get("admin_state_up").asBoolean();
             String status = node.get("status").asText();
             Boolean shared = node.get("shared").asBoolean();
             String tenantID = node.get("tenant_id").asText();
             Boolean routerExternal = node.get("router:external").asBoolean();
             String providerNetworkType = node.get("provider:network_type").asText();
             String providerPhysicalNetwork = node.get("provider:physical_network").asText();
             String providerSegmentationID = node.get("provider:segmentation_id").asText();
             JsonNode subnets = node.get("subnets");
             NetworkId id = null;
             if (flag == CREATE_NETWORK) {
                 id = networkId(node.get("id").asText());
             } else if (flag == UPDATE_NETWORK) {
                 id = networkId;
             }
             network = new DefaultNetwork(PID, id, name, adminStateUp, status,
                         shared, tenantID, routerExternal, providerNetworkType,
                         providerPhysicalNetwork, providerSegmentationID, subnets);
             networksMap.putIfAbsent(id, network);
         }
        return Collections.unmodifiableCollection(networksMap.values());
    }
    /**
     * Returns a Object of the currently known infrastructure ovsport.
     * @param ovsportId ovsport identifier
     * @param JsonNode node the ovsport json
     * @return Object of ovsport
     */
    public Iterable<Network> changeJson2objs(JsonNode nodes) {
        Network network = null;
        ConcurrentMap<NetworkId, Network> networksMap = Maps.newConcurrentMap();
        if (nodes != null) {
             for (JsonNode node : nodes) {
                   String id = node.get("id").asText();
                   String name = node.get("name").asText();
                   Boolean adminStateUp = node.get("admin_state_up").asBoolean();
                   String status = node.get("status").asText();
                   Boolean shared = node.get("shared").asBoolean();
                   String tenantID = node.get("tenant_id").asText();
                   Boolean routerExternal = node.get("router:external").asBoolean();
                   String providerNetworkType = node.get("provider:network_type").asText();
                   String providerPhysicalNetwork = node.get("provider:physical_network").asText();
                   String providerSegmentationID = node.get("provider:segmentation_id").asText();
                   JsonNode subnets = node.get("subnets");
                   network = new DefaultNetwork(PID, networkId(id), name, adminStateUp, status,
                               shared, tenantID, routerExternal, providerNetworkType,
                               providerPhysicalNetwork, providerSegmentationID, subnets);
                   networksMap.putIfAbsent(networkId(id), network);
             }
         }
        return Collections.unmodifiableCollection(networksMap.values());
    }
    // Posts the specified event to the local event dispatcher.
    private void post(NetworkEvent event) {
        if (event != null && eventDispatcher != null) {
            eventDispatcher.post(event);
        }
    }
    // Store delegate to re-post events emitted from the store.
    private class InternalStoreDelegate
            implements NetworkStoreDelegate {
        @Override
        public void notify(NetworkEvent event) {
            post(event);
        }
    }
}
