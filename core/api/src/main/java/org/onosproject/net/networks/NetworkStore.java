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
package org.onosproject.net.networks;

import org.onosproject.net.Network;
import org.onosproject.net.NetworkId;
import org.onosproject.store.Store;

/**
 * Manages inventory of infrastructure network.
 */
public interface NetworkStore extends Store<NetworkEvent, NetworkStoreDelegate> {
    /**
     * Returns the number of infrastructure network known to the system.
     * @return number of infrastructure network
     */
    int getNetworkCount();

    /**
     * Returns a collection of the currently known infrastructure
     * network.
     * @return collection of network
     */
    Iterable<Network> getNetworks();

    /**
     * Returns the network with the specified identifier.
     * @param networkId Network identifier
     * @return Network or null if one with the given identifier is not known
     */
    Network getNetwork(NetworkId networkId);

    /**
     * Return if the network is existed.
     * @param networkId Network identifier
     * @return true or false if one with the given identifier is not existed.
     */
    boolean networkExists(NetworkId networkId);

    /**
     * Return if it is successful for the network to delete..
     * @param networkId Network identifier
     * @return true or false if one with the given identifier to delete is successed.
     */
    boolean removeNetwork(NetworkId networkId);

    /**
     * Return if it is successed for the network to create.
     * @param networkId Network identifier
     * @return true or false if one with the given identifier to create is successed.
     */
    boolean networkcreate(Iterable<Network> networks);

    /**
     * Return if it is successed for the network to update.
     * @param networkId Network identifier
     * @return true or false if one with the given identifier to update is successed.
     */
    boolean networkupdate(NetworkId networkId, Iterable<Network> networks);
}
