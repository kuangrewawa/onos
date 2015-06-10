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
package org.onosproject.net.subnet;

import org.onosproject.net.Subnet;
import org.onosproject.net.SubnetId;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Service for interacting with the inventory of infrastructure subnets.
 */
public interface SubnetService {
    /**
     * Returns a collection of the currently known infrastructure subnets.
     *
     * @return collection of subnets
     */
    Iterable<Subnet> getSubnets();

    /**
     * Returns the subnet with the specified identifier.
     *
     * @param subnetId subnet identifier
     * @return subnet or null if one with the given identifier is not known
     */
    Subnet getSubnet(SubnetId subnetId);

    /**
     * Returns the subnet with the specified identifier.
     *
     * @param subnetId subnet identifier
     * @return subnet or null if one with the given identifier is not known
     */
    Boolean createSubnet(JsonNode cfg);

    /**
     * Returns the subnet with the specified identifier.
     *
     * @param subnetId subnet identifier
     * @return subnet or null if one with the given identifier is not known
     */
    Boolean updateSubnet(JsonNode cfg);

    /**
     * Returns the subnet with the specified identifier.
     *
     * @param subnetId subnet identifier
     * @return subnet or null if one with the given identifier is not known
     */
    void removeSubnet(SubnetId subnetId);
}
