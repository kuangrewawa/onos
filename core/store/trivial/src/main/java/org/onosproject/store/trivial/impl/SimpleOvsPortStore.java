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
import org.onosproject.net.OvsPort;
import org.onosproject.net.OvsPortId;
import org.onosproject.net.ovsports.OvsPortEvent;
import org.onosproject.net.ovsports.OvsPortStore;
import org.onosproject.net.ovsports.OvsPortStoreDelegate;
import org.onosproject.store.AbstractStore;
import org.slf4j.Logger;

import com.google.common.collect.Maps;

/**
 * Manages inventory of infrastructure OvsPort using trivial in-memory
 * structures implementation.
 */
@Component(immediate = true)
@Service
public class SimpleOvsPortStore
             extends AbstractStore<OvsPortEvent, OvsPortStoreDelegate>
             implements OvsPortStore {


    private final Logger log = getLogger(getClass());

    // cache of OvsPort
    private final ConcurrentMap<OvsPortId, OvsPort> ovsportsMap = Maps.newConcurrentMap();

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
                return false;
            }
            ovsportsMap.putIfAbsent(networkport.uuid(), networkport);
        }
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
