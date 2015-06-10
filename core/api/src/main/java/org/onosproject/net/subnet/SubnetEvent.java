/*
 * Copyright 2014-2015 Open Networking Laboratory
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

import static com.google.common.base.MoreObjects.toStringHelper;

import org.joda.time.LocalDateTime;
import org.onosproject.event.AbstractEvent;
import org.onosproject.net.Subnet;
import org.onosproject.net.Port;

/**
 * Describes infrastructure subnet event.
 */
public class SubnetEvent extends AbstractEvent<SubnetEvent.Type, Subnet> {

    private final Port port;

    /**
     * Type of subnet events.
     */
    public enum Type {
        /**
         * Signifies that a new subnet has been detected.
         */
        SUBNET_ADDED,

        /**
         * Signifies that some subnet attributes have changed; excludes
         * availability changes.
         */
        SUBNET_UPDATED,

        /**
         * Signifies that a subnet has been removed.
         */
        SUBNET_REMOVED,

        /**
         * Signifies that a subnet has been administratively suspended.
         */
        SUBNET_SUSPENDED,

        /**
         * Signifies that a subnet has come online or has gone offline.
         */
        SUBNET_AVAILABILITY_CHANGED,

        /**
         * Signifies that a port has been added.
         */
        PORT_ADDED,

        /**
         * Signifies that a port has been updated.
         */
        PORT_UPDATED,

        /**
         * Signifies that a port has been removed.
         */
        PORT_REMOVED,

        /*
         * Signifies that port statistics has been updated.
         */
        PORT_STATS_UPDATED
    }

    /**
     * Creates an event of a given type and for the specified subnet and the
     * current time.
     *
     * @param type subnet event type
     * @param subnet event subnet subject
     */
    public SubnetEvent(Type type, Subnet subnet) {
        this(type, subnet, null);
    }

    /**
     * Creates an event of a given type and for the specified subnet, port and
     * the current time.
     *
     * @param type subnet event type
     * @param subnet event subnet subject
     * @param port optional port subject
     */
    public SubnetEvent(Type type, Subnet subnet, Port port) {
        super(type, subnet);
        this.port = port;
    }

    /**
     * Creates an event of a given type and for the specified subnet and time.
     *
     * @param type subnet event type
     * @param subnet event subnet subject
     * @param port optional port subject
     * @param time occurrence time
     */
    public SubnetEvent(Type type, Subnet subnet, Port port, long time) {
        super(type, subnet, time);
        this.port = port;
    }

    /**
     * Returns the port subject.
     *
     * @return port subject or null if the event is not port specific.
     */
    public Port port() {
        return port;
    }

    @Override
    public String toString() {
        if (port == null) {
            return super.toString();
        }
        return toStringHelper(this).add("time", new LocalDateTime(time()))
                .add("type", type()).add("subject", subject())
                .add("port", port).toString();
    }
}
