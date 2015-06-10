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
package org.onosproject.net.networkports.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.onosproject.net.NetworkId.networkId;
import static org.onosproject.net.OvsPortId.portId;
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
import org.onosproject.net.DefaultOvsPort;
import org.onosproject.net.OvsPort;
import org.onosproject.net.OvsPortId;
import org.onosproject.net.ovsports.OvsPortEvent;
import org.onosproject.net.ovsports.OvsPortService;
import org.onosproject.net.ovsports.OvsPortStore;
import org.onosproject.net.ovsports.OvsPortStoreDelegate;
import org.onosproject.net.provider.ProviderId;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

/**
 * Provides implementation of the OvsPort NB APIs.
 */
@Component(immediate = true)
@Service
public class OvsPortManager implements OvsPortService {

    private static final String OVSPORT_ID_NULL = "OvsPort ID cannot be null";
    private static final String CREATE_PORT = "create port";
    private static final String UPDATE_PORT = "update port";
    public static final ProviderId PID = new ProviderId("of", "foo");
    private final Logger log = getLogger(getClass());
    private final OvsPortStoreDelegate delegate = new InternalStoreDelegate();
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected OvsPortStore store;

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
    // Posts the specified event to the local event dispatcher.
    private void post(OvsPortEvent event) {
        if (event != null && eventDispatcher != null) {
            eventDispatcher.post(event);
        }
    }
    // Store delegate to re-post events emitted from the store.
    private class InternalStoreDelegate
            implements OvsPortStoreDelegate {
        @Override
        public void notify(OvsPortEvent event) {
            post(event);
        }
    }
    @Override
    public int getPortCount() {
        return store.getPortCount();
    }

    @Override
    public Iterable<OvsPort> getPorts() {
        return store.getPorts();
    }

    @Override
    public OvsPort getPort(OvsPortId ovsportId) {
        checkNotNull(ovsportId, OVSPORT_ID_NULL);
        return store.getPort(ovsportId);
    }

    @Override
    public boolean portExists(OvsPortId ovsportId) {
        checkNotNull(ovsportId, OVSPORT_ID_NULL);
        return store.portExists(ovsportId);
    }

    @Override
    public boolean removePort(OvsPortId ovsportId) {
        checkNotNull(ovsportId, OVSPORT_ID_NULL);
        return store.removePort(ovsportId);
    }

    @Override
    public boolean createPort(JsonNode cfg) {
        log.info("Handling STEP  NO.1 ---------------OvsportManager-------------------");
        JsonNode nodes = null;
        Iterable<OvsPort> ovsports = null;
        if (cfg.get("port") != null) {
            nodes = cfg.get("port");
            if (nodes.isArray()) {
                ovsports = changeJson2objs(nodes);
            } else {
                ovsports = changeJson2obj(CREATE_PORT, null, nodes);
            }
        } else if (cfg.get("ports") != null) {
            nodes = cfg.get("ports");
            ovsports = changeJson2objs(nodes);
        }
        log.info("Handling STEP  NO.2 ---------------OvsportManagerEnd-------------------");
        return store.createPort(ovsports);
    }

    @Override
    public boolean updatePort(OvsPortId ovsportId, JsonNode cfg) {
        JsonNode nodes = null;
        Iterable<OvsPort> ovsports = null;
        if (cfg.get("port") != null) {
            nodes = cfg.get("port");
            ovsports = changeJson2obj(UPDATE_PORT, ovsportId, nodes);
        } else if (cfg.get("ports") != null) {
            nodes = cfg.get("ports");
            ovsports = changeJson2obj(UPDATE_PORT, ovsportId, nodes);
        }
        return store.updatePort(ovsportId, ovsports);
    }
    /**
     * Returns a collection of the currently known infrastructure ovsport.
     * @param JsonNode node the ovsport json
     * @return collection of ovsport
     */
    public Iterable<OvsPort>  changeJson2objs(JsonNode nodes) {
        OvsPort ovsport = null;
        ConcurrentMap<OvsPortId, OvsPort> portsMap = Maps.newConcurrentMap();
        if (nodes != null) {
            for (JsonNode node : nodes) {
                String id = node.get("id").asText();
                String networkid = node.get("network_id").asText();
                String name = node.get("name").asText();
                Boolean adminStateUp = node.get("admin_state_up").asBoolean();
                String status = node.get("status").asText();
                String macaddress = node.get("mac_address").asText();
                String tenantID = node.get("tenant_id").asText();
                String deviceID = node.get("device_id").asText();
                String deviceOwner = node.get("device_owner").asText();
                JsonNode allowedAPnodes = node.get("allowed_address_pairs");
//                JsonNode extralDHCPOptions = node.get("extra_dhcp_opts");
                JsonNode fixedIPs = node.get("fixed_ips");
                String bindingHostId = node.get("binding:host_id").asText();
                String bindingVnicType = node.get("binding:vnic_type").asText();
                String bindingVifType = node.get("binding:vif_type").asText();
                JsonNode bindingvifDetails = node.get("binding:vif_details");
                JsonNode securityGroups = node.get("security_groups");
                ConcurrentMap<String, String> strMap = Maps.newConcurrentMap();
                strMap.putIfAbsent("name", name);
                strMap.putIfAbsent("adminStateUp", adminStateUp.toString());
                strMap.putIfAbsent("status", status);
                strMap.putIfAbsent("macaddress", macaddress);
                strMap.putIfAbsent("tenantID", tenantID);
                strMap.putIfAbsent("deviceID", deviceID);
                strMap.putIfAbsent("deviceOwner", deviceOwner);
                ovsport = new DefaultOvsPort(PID, portId(id), networkId(networkid), strMap,
                       allowedAPnodes,
//                       extralDHCPOptions,
                       fixedIPs, bindingHostId, bindingVnicType, bindingVifType,
                       bindingvifDetails, securityGroups);
                portsMap.putIfAbsent(portId(id), ovsport);
            }
        }
        return Collections.unmodifiableCollection(portsMap.values());
     }
    /**
     * Returns a Object of the currently known infrastructure ovsport.
     * @param ovsportId ovsport identifier
     * @param JsonNode node the ovsport json
     * @return Object of ovsport
     */
    public Iterable<OvsPort> changeJson2obj(String flag, OvsPortId ovsportId, JsonNode node) {
        OvsPort ovsport = null;
        ConcurrentMap<OvsPortId, OvsPort> portsMap = Maps.newConcurrentMap();
        if (node != null) {
            String networkid = node.get("network_id").asText();
            String name = node.get("name").asText();
            Boolean adminStateUp = node.get("admin_state_up").asBoolean();
            String status = node.get("status").asText();
            String macaddress = node.get("mac_address").asText();
            String tenantID = node.get("tenant_id").asText();
            String deviceID = node.get("device_id").asText();
            String deviceOwner = node.get("device_owner").asText();
            JsonNode allowedAPnodes = node.get("allowed_address_pairs");
//            JsonNode extralDHCPOptions = node.get("extra_dhcp_opts");
            JsonNode fixedIPs = node.get("fixed_ips");
            String bindingHostId = node.get("binding:host_id").asText();
            String bindingVnicType = node.get("binding:vnic_type").asText();
            String bindingVifType = node.get("binding:vif_type").asText();
            JsonNode bindingvifDetails = node.get("binding:vif_details");
            JsonNode securityGroups = node.get("security_groups");

            ConcurrentMap<String, String> strMap = Maps.newConcurrentMap();
            strMap.putIfAbsent("name", name);
            strMap.putIfAbsent("adminStateUp", adminStateUp.toString());
            strMap.putIfAbsent("status", status);
            strMap.putIfAbsent("macaddress", macaddress);
            strMap.putIfAbsent("tenantID", tenantID);
            strMap.putIfAbsent("deviceID", deviceID);
            strMap.putIfAbsent("deviceOwner", deviceOwner);
            OvsPortId id = null;
            if (flag == CREATE_PORT) {
                id = portId(node.get("id").asText());
            } else if (flag == UPDATE_PORT) {
                id = ovsportId;
            }
            ovsport = new DefaultOvsPort(PID, id, networkId(networkid), strMap,
                    allowedAPnodes,
//                    extralDHCPOptions,
                    fixedIPs, bindingHostId, bindingVnicType, bindingVifType,
                    bindingvifDetails, securityGroups);
            portsMap.putIfAbsent(id, ovsport);
        }
        return Collections.unmodifiableCollection(portsMap.values());
     }
}
