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

import static com.google.common.base.MoreObjects.toStringHelper;

import org.joda.time.LocalDateTime;
import org.onosproject.event.AbstractEvent;
import org.onosproject.net.OvsPort;

/**
 * Describes infrastructure networkport event.
 */
public class OvsPortEvent extends AbstractEvent<OvsPortEvent.Type, OvsPort> {

    /**
     * Type of networkport events.
     */
    public enum Type {
        /**
         * Signifies that a new networkport has been detected.
         */
        NETWORKPORT_ADDED,

        /**
         * Signifies that some networkport attributes have changed; excludes
         * availability changes.
         */
        NETWORKPORT_UPDATED,

        /**
         * Signifies that a networkport has been removed.
         */
        NETWORKPORT_REMOVED
    }

    /**
     * Creates an event of a given type and for the specified networkport and the
     * current time.
     *
     * @param type   networkport event type
     * @param networkport event networkport subject
     */
    public OvsPortEvent(Type type, OvsPort port) {
        super(type, port);
    }
    /**
     * Creates an event of a given type and for the specified networkport and time.
     *
     * @param type   networkport event type
     * @param networkport event networkport subject
     * @param time   occurrence time
     */
    public OvsPortEvent(Type type, OvsPort port, long time) {
        super(type, port, time);
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
