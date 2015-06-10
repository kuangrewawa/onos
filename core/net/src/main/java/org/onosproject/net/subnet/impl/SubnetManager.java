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
package org.onosproject.net.subnet.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static org.onlab.util.Tools.groupedThreads;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.event.AbstractListenerRegistry;
import org.onosproject.event.EventDeliveryService;
import org.onosproject.net.DefaultSubnet;
import org.onosproject.net.Subnet;
import org.onosproject.net.SubnetId;
import org.onosproject.net.provider.ProviderId;
import org.onosproject.net.subnet.SubnetEvent;
import org.onosproject.net.subnet.SubnetListener;
import org.onosproject.net.subnet.SubnetService;
import org.onosproject.net.subnet.SubnetStore;
import org.onosproject.net.subnet.SubnetStoreDelegate;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

/**
 * Provides implementation of the subnet SB &amp; NB APIs.
 */
@Component(immediate = true)
@Service
public class SubnetManager implements SubnetService {

    private static final String SUBNET_ID_NULL = "Subnet ID cannot be null";

    private final Logger log = getLogger(getClass());

    protected final AbstractListenerRegistry<SubnetEvent, SubnetListener> listenerRegistry
          = new AbstractListenerRegistry<>();

    private final SubnetStoreDelegate delegate = new InternalStoreDelegate();

    private ScheduledExecutorService backgroundService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected SubnetStore store;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected EventDeliveryService eventDispatcher;

    @Activate
    public void activate() {
        backgroundService = newSingleThreadScheduledExecutor(groupedThreads(
                "onos/subnet", "manager-background"));
        store.setDelegate(delegate);
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        backgroundService.shutdown();

        store.unsetDelegate(delegate);
        log.info("Stopped");
    }


    @Override
    public Iterable<Subnet> getSubnets() {
        return store.getSubnets();
    }

    @Override
    public Subnet getSubnet(SubnetId subnetId) {
        checkNotNull(subnetId, SUBNET_ID_NULL);
        return store.getSubnet(subnetId);
    }

    @Override
    public Boolean createSubnet(JsonNode subnode) {
        // TODO Auto-generated method stub
        Iterable<Subnet> subnets = createOrUpdateByInputStream(subnode);
        return store.createSubnet(subnets);
    }

    @Override
    public Boolean updateSubnet(JsonNode subnode) {
        Iterable<Subnet> subnets = createOrUpdateByInputStream(subnode);
        if (subnets != null) {
            return store.updateSubnet(subnets);
        } else {
            return false;
        }
    }

    private Iterable<Subnet> createOrUpdateByInputStream(JsonNode subnode) {
        Iterable<Subnet> subnets = null;
        Map<String, Subnet> subMap = new HashMap<String, Subnet>();
        try {
            JsonNode subnetNodes = subnode.get("subnets");
            if (subnetNodes == null) {
                subnetNodes = subnode.get("subnet");
            }
            log.info("subnetNodes is {}");
            if (subnetNodes.isArray()) {
                for (JsonNode subnetNode : subnetNodes) {
                    String id = subnetNode.get("id").asText();
                    String subnetName = subnetNode.get("name").asText();
                    String tenantID = subnetNode.get("tenant_id").asText();
                    String networkID = subnetNode.get("network_id").asText();
                    String ipVersion = subnetNode.get("ip_version").asText();
                    String cidr = subnetNode.get("cidr").asText();
//                    String cidrPrefix = subnetNode.get("cidrPrefix").asText();
                    String gatewayIp = subnetNode.get("gateway_ip").asText();
                    Boolean dhcpEnabled = subnetNode.get("enable_dhcp")
                            .asBoolean();
                    Boolean shared = subnetNode.get("shared").asBoolean();
                    ConcurrentMap<String, String> strMap = Maps
                            .newConcurrentMap();
                    strMap.putIfAbsent("subnetName", subnetName);
                    strMap.putIfAbsent("ipVersion", ipVersion);
                    strMap.putIfAbsent("cidr", cidr);
                    JsonNode allocationPools = subnetNode
                            .get("allocation_pools");
                    Subnet subnet = new DefaultSubnet(new ProviderId("of",
                            "foo"), SubnetId.subnetId(id), strMap, gatewayIp,
                            dhcpEnabled, shared, networkID, tenantID, null,
                            null, allocationPools);
                    subMap.put(id, subnet);
                }
            } else {
                String id = subnetNodes.get("id").asText();
                String subnetName = subnetNodes.get("name").asText();
                String tenantID = subnetNodes.get("tenant_id").asText();
                String networkID = subnetNodes.get("network_id").asText();
                String ipVersion = subnetNodes.get("ip_version").asText();
                String cidr = subnetNodes.get("cidr").asText();
                String gatewayIp = subnetNodes.get("gateway_ip").asText();
                Boolean dhcpEnabled = subnetNodes.get("enable_dhcp")
                        .asBoolean();
                Boolean shared = subnetNodes.get("shared").asBoolean();
                ConcurrentMap<String, String> strMap = Maps
                        .newConcurrentMap();
                strMap.putIfAbsent("subnetName", subnetName);
                strMap.putIfAbsent("ipVersion", ipVersion);
                strMap.putIfAbsent("cidr", cidr);
                JsonNode allocationPools = subnetNodes
                        .get("allocation_pools");
                Subnet subnet = new DefaultSubnet(new ProviderId("of",
                        "foo"), SubnetId.subnetId(id), strMap, gatewayIp,
                        dhcpEnabled, shared, networkID, tenantID,  null,
                        null, allocationPools);
                subMap.put(id, subnet);
            }
            subnets = Collections.unmodifiableCollection(subMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subnets;
    }


    @Override
    public void removeSubnet(SubnetId subnetId) {
        checkNotNull(subnetId, SUBNET_ID_NULL);
        SubnetEvent event = store.removeSubnet(subnetId);
    }

    // Store delegate to re-post events emitted from the store.
    private class InternalStoreDelegate implements SubnetStoreDelegate {
        @Override
        public void notify(SubnetEvent event) {
        }
    }

}
