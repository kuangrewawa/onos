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
package org.onosproject.vtn.subnet;

import org.onosproject.vtn.Subnet;
import org.onosproject.vtn.SubnetId;

/**
 * Service for interacting with the inventory of subnets.
 */
public interface SubnetService {
	/**
     * Returns the subnet with the specified identifier.
     *
     * @param subnetId subnet identifier
     * @return true or false
     */
    boolean isExists(SubnetId subnetId);
    /**
     * Returns a collection of the currently known subnets.
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
     * Creates new subnets.
     *
     * @param subnets the collection of subnets
     * @return false if the identifier subnet has been created
     */
    boolean createSubnet(Iterable<Subnet> subnets);

    /**
     * Updates existing subnets.
     *
     * @param subnets the collection of subnets
     * @return true if all subnets were updated successfully
     */
    boolean updateSubnet(Iterable<Subnet> subnets);

    /**
     * Administratively removes the specified subnet from the store.
     *
     * @param subnetId subnet identifier
     * @return true if remove identifier subnet successfully
     */
    boolean removeSubnet(SubnetId subnetId);
    

}
