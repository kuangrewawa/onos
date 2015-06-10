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

import java.util.Objects;

/**
 * Immutable representation of a network identity.
 */
public final class NetworkId extends ElementId {
    private final String str;

    // Public construction is prohibited
    private NetworkId(String str) {
        this.str = str.toLowerCase();
    }

    // Default constructor for serialization
    protected NetworkId() {
        this.str = null;
    }

    /**
     * Creates a network id using the str.
     *
     * @param str network String
     * @return networkId
     */
    public static NetworkId networkId(String str) {
        return new NetworkId(str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NetworkId) {
            final NetworkId that = (NetworkId) obj;
            return this.getClass() == that.getClass() &&
                    Objects.equals(this.str, that.str);
        }
        return false;
    }

    @Override
    public String toString() {
        return str;
    }

}
