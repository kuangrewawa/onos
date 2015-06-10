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
package org.onosproject.net;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Default infrastructure AllowedAddressPair model implementation.
 */
public class DefaultAllowedAddressPair extends AbstractElement implements AllowedAddressPair {

    private final String portID;
    private final String ipAddress;
    private final String macAddress;
    // For serialization
    private DefaultAllowedAddressPair() {
        this.portID = null;
        this.ipAddress = null;
        this.macAddress = null;
    }
    /**
     * Creates a port DefaultAllowedAddressPair element attributed to the specified provider.
     *
     * @param portID of the AllowedAddressPair
     * @param ipAddress of the AllowedAddressPair
     * @param macAddress of the AllowedAddressPair
     */
    public DefaultAllowedAddressPair(String portID, String ipAddress, String macAddress) {
        this.portID = portID;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("portID", portID).add("ipAddress", ipAddress)
                .add("macAddress", macAddress)
                .toString();
    }

    @Override
    public ElementId id() {
        return null;
    }

    @Override
    public String ipAddress() {
        return ipAddress;
    }
    @Override
    public String portID() {
        return portID;
    }
    @Override
    public String macAddress() {
        return macAddress;
    }
}
