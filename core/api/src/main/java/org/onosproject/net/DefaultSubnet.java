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
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import org.onosproject.net.provider.ProviderId;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

/**
 * Default infrastructure device model implementation.
 */
public class DefaultSubnet extends AbstractElement implements Subnet {

    private final String subnetName;
    private final String networkID;
    private final String tenantID;
    private final String ipVersion;
    private final String cidr;
    private final String gatewayIp;
    private final Boolean dhcpEnabled;
    private final Boolean shared;
    private final List<String> ipAlloc;
    private final List<String> hostRoutes;
    private final Iterable<AllocationPool> allocationPools;


    // For serialization
    private DefaultSubnet() {
        this.subnetName = null;
        this.tenantID = null;
        this.networkID = null;
        this.ipVersion = null;
        this.cidr = null;
        this.gatewayIp = null;
        this.dhcpEnabled = null;
        this.shared = null;
        this.ipAlloc = null;
        this.hostRoutes = null;
        this.allocationPools = null;
    }

    /**
     * Creates a subnet element attributed to the specified provider.
     *
     * @param providerId
     *            identity of the provider
     * @param id
     *            subnet identifier
     */
    public DefaultSubnet(ProviderId providerId, SubnetId id,
            ConcurrentMap<String, String> strMap, String gatewayIp,
            Boolean dhcpEnabled, Boolean shared, String networkID,
            String tenantID, List<String> ipAlloc, List<String> hostRoutes,
            JsonNode allocationPools, Annotations... annotations) {
        super(providerId, id, annotations);
        this.subnetName = strMap.get("subnetName");
        this.tenantID = tenantID;
        this.networkID = networkID;
        this.ipVersion = strMap.get("ipVersion");
        this.cidr = strMap.get("cidr");
        this.gatewayIp = gatewayIp;
        this.dhcpEnabled = dhcpEnabled;
        this.shared = shared;
        this.ipAlloc = ipAlloc;
        this.hostRoutes = hostRoutes;
        this.allocationPools = jsonNodeToAlocPolls(allocationPools);
    }

    /**
     * Change JsonNode alocPools to a collection of the alocPools.
     *
     * @param JsonNode
     *            extralDHCPOptions
     */
    public Iterable<AllocationPool> jsonNodeToAlocPolls(
            JsonNode allocationPools) {
        ConcurrentMap<Integer, AllocationPool> alocplMaps = Maps
                .newConcurrentMap();
        Integer i = 0;
        for (JsonNode node : allocationPools) {
            String startIP = node.get("start").asText();
            String endIP = node.get("end").asText();
            AllocationPool alocPls = new DefaultAllocationPools(startIP, endIP);
            alocplMaps.putIfAbsent(i, alocPls);
            i++;
        }
        return Collections.unmodifiableCollection(alocplMaps.values());
    }

    @Override
    public SubnetId id() {
        return (SubnetId) id;
    }

    @Override
    public String subnetName() {
        return subnetName;
    }

    @Override
    public String ipV6AddressMode() {
        return gatewayIp;
    }

    @Override
    public String networkID() {
        return networkID;
    }

    @Override
    public String ipVersion() {
        return ipVersion;
    }

    @Override
    public String cidr() {
        return cidr;
    }


    @Override
    public String gatewayIP() {
        return gatewayIp;
    }

    @Override
    public Boolean dhcpEnabled() {
        return dhcpEnabled;
    }

    @Override
    public Boolean shared() {
        return shared;
    }

    @Override
    public List<String> ipAlloc() {
        return ipAlloc;
    }

    @Override
    public List<String> hostRoutes() {
        return hostRoutes;
    }

    @Override
    public String tenantID() {
        return tenantID;
    }

    @Override
    public String ipV6RaMode() {
        return gatewayIp;
    }

    @Override
    public int hashCode() {
        //TODO
        return Objects.hash(id, subnetName,  ipVersion, cidr,
                 gatewayIp, dhcpEnabled, shared, ipAlloc);
    }

    @Override
    public Iterable<AllocationPool> allocationPools() {
        // TODO Auto-generated method stub
        return allocationPools;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultSubnet) {
            final DefaultSubnet other = (DefaultSubnet) obj;
            return Objects.equals(this.id, other.id)
                    && Objects.equals(this.subnetName, other.subnetName)
                    && Objects.equals(this.ipVersion, other.ipVersion)
                    && Objects.equals(this.cidr, other.cidr)
                    && Objects.equals(this.gatewayIp, other.gatewayIp)
                    && Objects.equals(this.dhcpEnabled, other.dhcpEnabled)
                    && Objects.equals(this.shared, other.shared)
                    && Objects.equals(this.ipAlloc, other.ipAlloc);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("id", id).add("subnetName", subnetName)
                .add("ipVersion", ipVersion)
                .add("cidr", cidr)
                .add("gatewayIp", gatewayIp).add("dhcpEnabled", cidr)
                .add("shared", shared).add("ipAlloc", ipAlloc).toString();
    }

}
