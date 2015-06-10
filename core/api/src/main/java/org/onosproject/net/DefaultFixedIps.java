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
 * Default infrastructure network model implementation.
 */
public class DefaultFixedIps extends AbstractElement implements FixedIps {

    private final String ipAddress;
    private final String subnetId;
    // For serialization
    private DefaultFixedIps() {
        this.ipAddress = null;
        this.subnetId = null;
    }
    /**
     * Creates a network element attributed to the specified provider.
     *
     * @param providerId identity of the provider
     */
    public DefaultFixedIps(String ipAddress, String subnetId) {
        this.ipAddress = ipAddress;
        this.subnetId = subnetId;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("ipAddress", ipAddress).add("subnetId", subnetId)
                .toString();
    }

    @Override
    public ElementId id() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String ipAddress() {
        // TODO Auto-generated method stub
        return ipAddress;
    }

    @Override
    public String subnetId() {
        // TODO Auto-generated method stub
        return subnetId;
    }
}
