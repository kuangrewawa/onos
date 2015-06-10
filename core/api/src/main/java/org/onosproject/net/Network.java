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

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Representation of the infrastructure network.
 */
public interface Network extends Element {

    /**
     * Returns the network identifier.
     * @return network id
     */
    NetworkId id();

    /**
     * Returns the network name.
     * @return network name
     */
    String name();

    /**
     * Returns the network admin state up.
     * @return network admin state up
     */
    Boolean adminStateUp();

    /**
     * Returns the network status.
     * @return network status
     */
    String status();

    /**
     * Returns the network shared.
     * @return network shared
     */
    Boolean shared();

    /**
     * Returns the network tenant id.
     * @return network tenant id
     */
    String tenantID();

    /**
     * Returns the network router external.
     * @return network router external
     */
    Boolean routerExternal();
    /**
     * Returns the network ProviderNetworkType.
     * @return network ProviderNetworkType
     */
    String providerNetworkType();

    /**
     * Returns the network provider:physical network.
     * @return network provider:physical network
     */
    String providerPhysicalNetwork();

    /**
     * Returns the network provider:segmentation id.
     * @return network provider:segmentation id
     */
    String providerSegmentationID();

    /**
     * Returns the JsonNode subnets.
     * @return network subnets
     */
    JsonNode subnets();
}
