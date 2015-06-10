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
package org.onosproject.store.ovsports.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.net.OvsPort;
import org.onosproject.net.OvsPortId;
import org.onosproject.net.networks.NetworkStore;
import org.onosproject.net.ovsports.OvsPortEvent;
import org.onosproject.net.ovsports.OvsPortStore;
import org.onosproject.net.ovsports.OvsPortStoreDelegate;
import org.onosproject.net.subnet.SubnetStore;
import org.onosproject.store.AbstractStore;
import org.slf4j.Logger;

import com.google.common.collect.Maps;

/**
 * Manages inventory of infrastructure ovsport using gossip protocol to distribute
 * information.
 */
@Component(immediate = true)
@Service
public class GossipOvsPortStore
        extends AbstractStore<OvsPortEvent, OvsPortStoreDelegate>
        implements OvsPortStore {

    private final Logger log = getLogger(getClass());

    // cache of OvsPort
    private final ConcurrentMap<OvsPortId, OvsPort> ovsportsMap = Maps.newConcurrentMap();
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected NetworkStore networkstore;
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected SubnetStore subnetstore;
    @Activate
    public void activate() {
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        ovsportsMap.clear();
        log.info("Stopped");
    }
    @Override
    public int getPortCount() {
        return ovsportsMap.size();
    }

    @Override
    public Iterable<OvsPort> getPorts() {
        return Collections.unmodifiableCollection(ovsportsMap.values());
    }

    @Override
    public OvsPort getPort(OvsPortId ovsportId) {
        if (!portExists(ovsportId)) {
            return null;
        }
        return ovsportsMap.get(ovsportId);
    }

    @Override
    public boolean portExists(OvsPortId ovsportId) {
        return ovsportsMap.containsKey(ovsportId);
    }

    @Override
    public boolean removePort(OvsPortId ovsportId) {
        if (!portExists(ovsportId)) {
            return false;
        }
        OvsPort ovsport = ovsportsMap.remove(ovsportId);
        return ovsport == null ? false : true;
    }

    @Override
    public boolean createPort(Iterable<OvsPort> ovsports) {
        Iterator<OvsPort> portors = ovsports.iterator();
        while (portors.hasNext()) {
            OvsPort networkport = portors.next();
            if (portExists(networkport.uuid())) {
                log.info("Handling STEP  NO.3 ---------------OvsportStoreresult-------------------portExists");
                return false;
            }
            if (!networkstore.networkExists(networkport.networkid())) {
                log.info("Handling STEP  NO.3 ---------------OvsportStoreresult-------------------networkExists");
                return false;
            }
            ovsportsMap.putIfAbsent(networkport.uuid(), networkport);
        }
        log.info("Handling STEP  NO.3 ---------------OvsportStoreresult-------------------TRUE");
        return true;
    }

    @Override
    public boolean updatePort(OvsPortId ovsportId, Iterable<OvsPort> ovsports) {
        if (!portExists(ovsportId)) {
            return false;
        }
        Iterator<OvsPort> portors = ovsports.iterator();
        while (portors.hasNext()) {
            OvsPort ovsport = portors.next();
            ovsportsMap.put(ovsportId, ovsport);
        }
        return true;
    }
}
