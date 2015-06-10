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
package org.onosproject.store.trivial.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.net.Network;
import org.onosproject.net.NetworkId;
import org.onosproject.net.networks.NetworkEvent;
import org.onosproject.net.networks.NetworkStore;
import org.onosproject.net.networks.NetworkStoreDelegate;
import org.onosproject.store.AbstractStore;
import org.slf4j.Logger;

import com.google.common.collect.Maps;

/**
 * Manages inventory of infrastructure Networks using trivial in-memory
 * structures implementation.
 */
@Component(immediate = true)
@Service
public class SimpleNetworkStore
             extends AbstractStore<NetworkEvent, NetworkStoreDelegate>
             implements NetworkStore {
    private final Logger log = getLogger(getClass());

    // cache of Network
    private final ConcurrentMap<NetworkId, Network> networksMap = Maps.newConcurrentMap();

    @Activate
    public void activate() {
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        networksMap.clear();
        log.info("Stopped");
    }
    @Override
    public int getNetworkCount() {
        return networksMap.size();
    }

    @Override
    public Iterable<Network> getNetworks() {
        return Collections.unmodifiableCollection(networksMap.values());
    }

    @Override
    public Network getNetwork(NetworkId networkId) {
        if (!networkExists(networkId)) {
            return null;
        }
        return networksMap.get(networkId);
    }

    @Override
    public boolean networkExists(NetworkId networkId) {
        return networksMap.containsKey(networkId);
    }

    @Override
    public boolean removeNetwork(NetworkId networkId) {
        if (!networkExists(networkId)) {
            return false;
        }
        Network network = networksMap.remove(networkId);
        return network == null ? false : true;
    }

    @Override
    public boolean networkcreate(Iterable<Network> networks) {
        Iterator<Network> networkors = networks.iterator();
        while (networkors.hasNext()) {
            Network network = networkors.next();
            if (networkExists(network.id())) {
                return false;
            }
            networksMap.putIfAbsent(network.id(), network);
        }
        return true;
    }

    @Override
    public boolean networkupdate(NetworkId networkId, Iterable<Network> networks) {
        if (!networkExists(networkId)) {
            return false;
        }
        Iterator<Network> networkors = networks.iterator();
        while (networkors.hasNext()) {
            Network network = networkors.next();
            networksMap.put(networkId, network);
        }
        return true;
    }
}
