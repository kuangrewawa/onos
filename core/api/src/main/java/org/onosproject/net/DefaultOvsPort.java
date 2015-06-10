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
package org.onosproject.net;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Collections;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.onosproject.net.provider.ProviderId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

/**
 * Default infrastructure network model implementation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultOvsPort extends AbstractElement implements OvsPort {

    private final OvsPortId uuid;
    private final NetworkId networkid;
    private final String name;
    private final Boolean adminStateUp;
    private final String status;
    private final String macAddress;
    private final String tenantID;
    private final String deviceID;
    private final String deviceOwner;
    private final Iterable<AllowedAddressPair> allowedAddressPairs;
//    private final Iterable<ExtraDHCPOption> extralDHCPOptions;
    private final Iterable<FixedIps> fixedIPs;
    private final String bindingHostId;
    private final String bindingVnicType;
    private final String bindingVifType;
    private final Iterable<VifDetail> bindingvifDetails;
    private final JsonNode securityGroups;
    // For serialization
    private DefaultOvsPort() {
        this.uuid = null;
        this.networkid = null;
        this.name = null;
        this.adminStateUp = null;
        this.status = null;
        this.macAddress = null;
        this.tenantID = null;
        this.deviceID = null;
        this.deviceOwner = null;
        this.allowedAddressPairs = null;
//        this.extralDHCPOptions = null;
        this.fixedIPs = null;
        this.bindingHostId = null;
        this.bindingVnicType = null;
        this.bindingVifType = null;
        this.bindingvifDetails = null;
        this.securityGroups = null;
    }

    /**
     * Creates a network element attributed to the specified provider.
     *
     * @param providerId identity of the provider
     */
    public DefaultOvsPort(ProviderId providerId, OvsPortId id, NetworkId networkid,
            ConcurrentMap<String, String> strMap,
            JsonNode allowedAddressPairs,
//            JsonNode extralDHCPOptions,
            JsonNode fixedIPs,
            String bindingHostId, String bindingVnicType, String bindingVifType,
            JsonNode bindingvifDetails, JsonNode securityGroups,
            Annotations... annotations) {
        super(providerId, id, annotations);
        this.uuid = id;
        this.networkid = networkid;
        this.name = strMap.get("name");
        this.adminStateUp = new Boolean(strMap.get("adminStateUp"));
        this.status = strMap.get("status");
        this.macAddress = strMap.get("macaddress");
        this.tenantID = strMap.get("tenantID");
        this.deviceID = strMap.get("deviceID");
        this.deviceOwner = strMap.get("deviceOwner");
        this.allowedAddressPairs = jsonNodeToAAP(allowedAddressPairs);
//        this.extralDHCPOptions = jsonNodeToEDO(extralDHCPOptions);
        this.fixedIPs = jsonNodeToFIps(fixedIPs);
        this.bindingHostId = bindingHostId;
        this.bindingVnicType = bindingVnicType;
        this.bindingVifType = bindingVifType;
        this.bindingvifDetails = jsonNodeToBVifD(bindingvifDetails);
        this.securityGroups = securityGroups;
    }
    /**
     * Change JsonNode allowedAddressPairs to a collection of the AllowedAddressPair.
     *
     * @param JsonNode allowedAPnodes
     */
    public Iterable<AllowedAddressPair> jsonNodeToAAP(JsonNode allowedAPnodes) {
        ConcurrentMap<Integer, AllowedAddressPair> aapsMap = Maps.newConcurrentMap();
        Integer i = 0;
        String portID = "";
        String ipAddress = "";
        String macAddress = "";
        if (allowedAPnodes.isArray()) {
            for (JsonNode node : allowedAPnodes) {
                portID = node.get("port_id").asText();
                ipAddress = node.get("ip_address").asText();
                macAddress = node.get("mac_address").asText();
                AllowedAddressPair aap = new DefaultAllowedAddressPair(portID, ipAddress, macAddress);
                aapsMap.putIfAbsent(i, aap);
                i++;
            }
        } else {
            portID = allowedAPnodes.get("port_id").asText();
            ipAddress = allowedAPnodes.get("ip_address").asText();
            macAddress = allowedAPnodes.get("mac_address").asText();
            AllowedAddressPair aap = new DefaultAllowedAddressPair(portID, ipAddress, macAddress);
            aapsMap.putIfAbsent(i, aap);
        }
        return Collections.unmodifiableCollection(aapsMap.values());
    }
    /**
     * Change JsonNode extralDHCPOptions to a collection of the ExtraDHCPOption.
     *
     * @param JsonNode extralDHCPOptions
     */
    public Iterable<ExtraDHCPOption> jsonNodeToEDO(JsonNode extralDHCPOptions) {
        ConcurrentMap<Integer, ExtraDHCPOption> edoMap = Maps.newConcurrentMap();
        Integer i = 0;
        String name = "";
        String value = "";
        if (extralDHCPOptions.isArray()) {
            for (JsonNode node : extralDHCPOptions) {
                name = node.get("opt_name").asText();
                value = node.get("opt_value").asText();
                ExtraDHCPOption edo = new DefaultExtraDHCPOption(name, value);
                edoMap.putIfAbsent(i, edo);
                i++;
            }
        } else {
            name = extralDHCPOptions.get("opt_name").asText();
            value = extralDHCPOptions.get("opt_value").asText();
            ExtraDHCPOption edo = new DefaultExtraDHCPOption(name, value);
            edoMap.putIfAbsent(i, edo);
        }
        return Collections.unmodifiableCollection(edoMap.values());
    }
    /**
     * Change JsonNode extralDHCPOptions to a collection of the ExtraDHCPOption.
     *
     * @param JsonNode extralDHCPOptions
     */
    public Iterable<FixedIps> jsonNodeToFIps(JsonNode fixedIPs) {
        ConcurrentMap<Integer, FixedIps> fipsMap = Maps.newConcurrentMap();
        Integer i = 0;
        String ipaddress = "";
        String subnetId = "";
        if (fixedIPs.isArray()) {
            for (JsonNode node : fixedIPs) {
                ipaddress = node.get("ip_address").asText();
                subnetId = node.get("subnet_id").asText();
                FixedIps fips = new DefaultFixedIps(ipaddress, subnetId);
                fipsMap.putIfAbsent(i, fips);
                i++;
            }
        } else {
             ipaddress = fixedIPs.get("ip_address").asText();
             subnetId = fixedIPs.get("subnet_id").asText();
             FixedIps fips = new DefaultFixedIps(ipaddress, subnetId);
             fipsMap.putIfAbsent(i, fips);
        }
        return Collections.unmodifiableCollection(fipsMap.values());
    }
    /**
     * Change JsonNode bindingvifDetails to a collection of the VifDetail.
     *
     * @param JsonNode bindingvifDetails
     */
    public Iterable<VifDetail> jsonNodeToBVifD(JsonNode bindingvifDetails) {
        ConcurrentMap<Integer, VifDetail> vdsMap = Maps.newConcurrentMap();
        Integer i = 0;
        Boolean isportFilter = false;
        Boolean isovsHybridPlug    = false;
        if (bindingvifDetails.isArray()) {
            for (JsonNode node : bindingvifDetails) {
                if (node.has("port_filter")) {
                    isportFilter = node.get("port_filter").asBoolean();
                } else if (node.has("ovs_hybrid_plug")) {
                    isovsHybridPlug = node.get("ovs_hybrid_plug").asBoolean();
                }
                VifDetail vds = new DefaultVifDetail(isportFilter, isovsHybridPlug);
                vdsMap.putIfAbsent(i, vds);
                i++;
            }
        } else {
            if (bindingvifDetails.size() != 0) {
                if (bindingvifDetails.has("port_filter")) {
                    isportFilter = bindingvifDetails.get("port_filter").asBoolean();
                }
                if (bindingvifDetails.has("ovs_hybrid_plug")) {
                    isovsHybridPlug = bindingvifDetails.get("ovs_hybrid_plug").asBoolean();
                }
                VifDetail vds = new DefaultVifDetail(isportFilter, isovsHybridPlug);
                vdsMap.putIfAbsent(i, vds);
            }
        }
        return Collections.unmodifiableCollection(vdsMap.values());
    }
    /**
     * Change JsonNode bindingvifDetails to a collection of the VifDetail.
     *
     * @param JsonNode bindingvifDetails
     */
    public Iterable<SecurityGroup> jsonNodeToBSgs(JsonNode securityGroups) {
        ConcurrentMap<Integer, SecurityGroup> sgsMap = Maps.newConcurrentMap();
        Integer i = 0;
        String securityGroupsUUID = "";
        String securityGroupsName = "";
        String securityGroupsDescription = "";
        String securityGroupsTenantID = "";
        JsonNode securityGroupsRules = null;
        if (securityGroups.isArray()) {
            for (JsonNode node : securityGroups) {
                securityGroupsUUID = node.get("id").asText();
                securityGroupsName = node.get("name").asText();
                securityGroupsDescription = node.get("description").asText();
                securityGroupsTenantID = node.get("tenant_id").asText();
                securityGroupsRules = node.get("security_group_rules");
                SecurityGroup sgs = new DefaultSecurityGroup(securityGroupsUUID, securityGroupsName,
                        securityGroupsDescription, securityGroupsTenantID,
                        securityGroupsRules);
                sgsMap.putIfAbsent(i, sgs);
                i++;
            }
        } else {
            securityGroupsUUID = securityGroups.get("id").asText();
            securityGroupsName = securityGroups.get("name").asText();
            securityGroupsDescription = securityGroups.get("description").asText();
            securityGroupsTenantID = securityGroups.get("tenant_id").asText();
            securityGroupsRules = securityGroups.get("security_group_rules");
            SecurityGroup sgs = new DefaultSecurityGroup(securityGroupsUUID, securityGroupsName,
                    securityGroupsDescription, securityGroupsTenantID,
                    securityGroupsRules);
            sgsMap.putIfAbsent(i, sgs);
        }
        return Collections.unmodifiableCollection(sgsMap.values());
    }
    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", uuid)
                .add("network_id", networkid)
                .add("name", name)
                .add("adminStateUp", adminStateUp)
                .add("status", status)
                .add("shared", macAddress)
                .add("tenantID", tenantID)
                .add("deviceID", deviceID)
                .add("deviceOwner", deviceOwner)
                .add("allowedAddressPairs", allowedAddressPairs)
//                .add("extraDHCPOptions", extralDHCPOptions)
                .add("fixedIPs", fixedIPs)
                .add("bindingHostId", bindingHostId)
                .add("bindingVnicType", bindingVnicType)
                .add("bindingVifType", bindingVifType)
                .add("bindingvifDetails", bindingvifDetails)
                .add("securityGroups", securityGroups)
                .toString();
    }

    @Override
    public OvsPortId uuid() {
        return uuid;
    }

    @Override
    public NetworkId networkid() {
        return networkid;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Boolean adminStateUp() {
        return adminStateUp;
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public String macAddress() {
        return macAddress;
    }

    @Override
    public String tenantID() {
        return tenantID;
    }

    @Override
    public String deviceID() {
        return deviceID;
    }

    @Override
    public String deviceOwner() {
        return deviceOwner;
    }

    @Override
    public Iterable<AllowedAddressPair> allowedAddressPairs() {
        return allowedAddressPairs;
    }

//    @Override
//    public Iterable<ExtraDHCPOption> extralDHCPOptions() {
//        return extralDHCPOptions;
//    }

    @Override
    public Iterable<FixedIps> fixedIPs() {
        return fixedIPs;
    }

    @Override
    public String bindingHostId() {
        return bindingHostId;
    }

    @Override
    public String bindingVnicType() {
        return bindingVnicType;
    }

    @Override
    public String bindingVifType() {
        return bindingVifType;
    }

    @Override
    public Iterable<VifDetail> bindingvifDetails() {
        return bindingvifDetails;
    }

    @Override
    public JsonNode securityGroups() {
        return securityGroups;
    }

    @Override
    public ElementId id() {
        return null;
    }

}
