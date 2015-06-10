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
package org.onosproject.net.ovsports;

import org.onosproject.net.OvsPort;
import org.onosproject.net.OvsPortId;
import org.onosproject.store.Store;

/**
 * Manages inventory of infrastructure ovsport.
 */
public interface OvsPortStore extends Store<OvsPortEvent, OvsPortStoreDelegate> {
    /**
     * Returns the number of infrastructure ovsport known to the system.
     * @return number of infrastructure ovsport
     */
    int getPortCount();
    /**
     * Returns a collection of the currently known infrastructure
     * ovsport.
     * @return collection of ovsport
     */
    Iterable<OvsPort> getPorts();

    /**
     * Returns the ovsport with the specified identifier.
     * @param ovsportId ovsport identifier
     * @return ovsport or null if one with the given identifier is not known
     */
    OvsPort getPort(OvsPortId ovsportId);

    /**
     * Return if the ovsport is existed.
     * @param ovsportId ovsport identifier
     * @return true or false if one with the given identifier is not existed.
     */
    boolean portExists(OvsPortId ovsportId);
    /**
     * Return if it is successful for the ovsport to delete..
     * @param ovsportId ovsport identifier
     * @return true or false if one with the given identifier to delete is successed.
     */
    boolean removePort(OvsPortId ovsportId);
    /**
     * Return if it is successed for the ovsport to create.
     * @param ovsportId ovsport identifier
     * @return true or false if one with the given identifier to create is successed.
     */
    boolean createPort(Iterable<OvsPort> ovsports);
    /**
     * Return if it is successed for the ovsport to update.
     * @param networkId ovsport identifier
     * @return true or false if one with the given identifier to update is successed.
     */
    boolean updatePort(OvsPortId ovsportId, Iterable<OvsPort> ovsport);
}
