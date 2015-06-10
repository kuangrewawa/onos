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

import org.onosproject.net.provider.ProviderId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Default infrastructure network model implementation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultNetwork extends AbstractElement implements Network {

    private final NetworkId uuid;
    private final String name;
    private final Boolean adminStateUp;
    private final String status;
    private final Boolean shared;
    private final String tenantID;
    private final Boolean routerExternal;
    private final String providerNetworkType;
    private final String providerPhysicalNetwork;
    private final String providerSegmentationID;
    private final JsonNode subnets;
    // For serialization
    private DefaultNetwork() {
        this.uuid = null;
        this.name = null;
        this.adminStateUp = null;
        this.status = null;
        this.shared = null;
        this.tenantID = null;
        this.routerExternal = null;
        this.providerNetworkType = null;
        this.providerPhysicalNetwork = null;
        this.providerSegmentationID = null;
        this.subnets = null;
    }

    /**
     * Creates a network element attributed to the specified provider.
     *
     * @param providerId identity of the provider
     */
    public DefaultNetwork(ProviderId providerId, NetworkId id, String name,
            Boolean adminStateUp, String status, Boolean shared,
            String tenantID, Boolean routerExternal, String providerNetworkType,
            String providerPhysicalNetwork, String providerSegmentationID,
            JsonNode subnets, Annotations... annotations) {
        super(providerId, id, annotations);
        this.uuid = id;
        this.name = name;
        this.adminStateUp = adminStateUp;
        this.status = status;
        this.shared = shared;
        this.tenantID = tenantID;
        this.routerExternal = routerExternal;
        this.providerNetworkType = providerNetworkType;
        this.providerPhysicalNetwork = providerPhysicalNetwork;
        this.providerSegmentationID = providerSegmentationID;
        this.subnets = subnets;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("id", uuid).add("name", name)
                .add("adminStateUp", adminStateUp).add("status", status)
                .add("shared", shared).add("tenantID", tenantID)
                .add("routerExternal", routerExternal)
                .add("providerNetworkType", providerNetworkType)
                .add("providerPhysicalNetwork", providerPhysicalNetwork)
                .add("providerSegmentationID", providerSegmentationID)
                .toString();
    }

    @Override
    public NetworkId id() {
        return uuid;
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
    public Boolean shared() {
        return shared;
    }

    @Override
    public String tenantID() {
        return tenantID;
    }

    @Override
    public Boolean routerExternal() {
        return routerExternal;
    }

    @Override
    public String providerNetworkType() {
        return providerNetworkType;
    }

    @Override
    public String providerPhysicalNetwork() {
        return providerPhysicalNetwork;
    }

    @Override
    public String providerSegmentationID() {
        return providerSegmentationID;
    }

    @Override
    public JsonNode subnets() {
        return subnets;
    }

}
