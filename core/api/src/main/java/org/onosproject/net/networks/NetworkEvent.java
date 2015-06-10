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

import static com.google.common.base.MoreObjects.toStringHelper;

import org.joda.time.LocalDateTime;
import org.onosproject.event.AbstractEvent;
import org.onosproject.net.Network;

/**
 * Describes infrastructure network event.
 */
public class NetworkEvent extends AbstractEvent<NetworkEvent.Type, Network> {

    /**
     * Type of network events.
     */
    public enum Type {
        /**
         * Signifies that a new network has been detected.
         */
        NETWORK_ADDED,

        /**
         * Signifies that some network attributes have changed; excludes
         * availability changes.
         */
        NETWORK_UPDATED,

        /**
         * Signifies that a network has been removed.
         */
        NETWORK_REMOVED
    }

    /**
     * Creates an event of a given type and for the specified network and the
     * current time.
     *
     * @param type   network event type
     * @param network event network subject
     */
    public NetworkEvent(Type type, Network network) {
        super(type, network);
    }

    /**
     * Creates an event of a given type and for the specified network and time.
     *
     * @param type   network event type
     * @param network event network subject
     * @param time   occurrence time
     */
    public NetworkEvent(Type type, Network network, long time) {
        super(type, network, time);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("time", new LocalDateTime(time()))
                .add("type", type())
                .add("subject", subject())
                .toString();
     }
}
