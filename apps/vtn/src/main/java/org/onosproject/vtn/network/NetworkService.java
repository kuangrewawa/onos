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
package org.onosproject.vtn.network;

import org.onosproject.vtn.Network;
import org.onosproject.vtn.NetworkId;

/**
 * Service for interacting with the inventory of network.
 */
public interface NetworkService {

    /**
     * Returns if the network is existed.
     *
     * @param networkId Network identifier
     * @return true or false if one with the given identifier is not existed.
     */
    boolean isExists(NetworkId networkId);

    /**
     * Returns the number of network known to the system.
     *
     * @return number of network.
     */
    int getNetworkCount();

    /**
     * Returns an iterable collection of the currently known network.
     *
     * @return collection of network.
     */
    Iterable<Network> getNetworks();

    /**
     * Returns the network with the identifier.
     *
     * @param networkId Network identifier
     * @return Network or null if one with the given identifier is not known.
     */
    Network getNetwork(NetworkId networkId);
    /**
     * Creates networks by networks.
     *
     * @param networks the collection of networks
     * @return true if all given identifiers created successfully.
     */
    boolean createNetworks(Iterable<Network> networks);

    /**
     * Updates networks by networks.
     *
     * @param networks the collection of networks
     * @return true if all given identifiers updated successfully.
     */
    boolean updateNetworks(Iterable<Network> networks);

    /**
     * Deletes networkId by networkId.
     *
     * @param networkId Network identifier
     * @return true if the specified network deleted successfully.
     */
    boolean removeNetwork(NetworkId networkId);
}
