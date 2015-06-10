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
import org.onosproject.net.provider.ProviderId;
import org.onosproject.store.Store;

/**
 * Manages inventory of infrastructure subnets; not intended for direct use.
 */
public interface SubnetStore  extends Store<SubnetEvent, SubnetStoreDelegate>  {

    /**
     * Returns the number of subnets known to the system.
     *
     * @return number of subnets
     */
    int getSubnetCount();

    /**
     * Returns an iterable collection of all subnets known to the system.
     *
     * @return subnet collection
     */
    Iterable<Subnet> getSubnets();

    /**
     * Returns an iterable collection of all subnets currently available to the system.
     *
     * @return subnet collection
     */
    Iterable<Subnet> getAvailableSubnets();



    /**
     * Returns the subnet with the specified identifier.
     *
     * @param subnetId subnet identifier
     * @return subnet
     */
    Subnet getSubnet(SubnetId subnetId);

    /**
     * Creates a new infrastructure subnet, or updates an existing one using
     * the supplied subnet description.
     *
     * @param providerId        provider identifier
     * @param subnetId          subnet identifier
     * @param subnetDescription subnet description
     * @return ready to send event describing what occurred; null if no change
     */
    SubnetEvent createOrUpdateSubnet(ProviderId providerId, SubnetId subnetId,
                                     SubnetDescription subnetDescription);

    boolean subnetExists(SubnetId subnetId);
    boolean createSubnet(Iterable<Subnet> subnets);

    boolean updateSubnet(Iterable<Subnet> subnets);
    /**
     * Administratively removes the specified subnet from the store.
     *
     * @param subnetId subnet to be removed
     * @return null if no such subnet, or was forwarded to remove master
     */
    SubnetEvent removeSubnet(SubnetId subnetId);
}
