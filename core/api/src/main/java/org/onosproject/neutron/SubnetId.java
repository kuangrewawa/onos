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
package org.onosproject.neutron;

import java.util.Objects;

import org.onosproject.net.ElementId;

/**
 * Immutable representation of a subnet identity.
 */
public final class SubnetId extends ElementId {

    /**
     * Represents either no subnet, or an unspecified subnet.
     */
    public static final SubnetId NONE = subnetId("none");

    private final String str;

    // Public construction is prohibited
    private SubnetId(String str) {
        this.str = str.toLowerCase();
    }

    // Default constructor for serialization
    protected SubnetId() {
        this.str = null;
    }


    /**
     * Creates a subnet id using the supplied  string.
     *
     * @param string
     *            subnet URI string
     * @return DeviceId
     */
    public static SubnetId subnetId(String string) {
        return  new SubnetId(string);
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
        if (obj instanceof SubnetId) {
            final SubnetId that = (SubnetId) obj;
            return this.getClass() == that.getClass()
                    && Objects.equals(this.str, that.str);
        }
        return false;
    }

    @Override
    public String toString() {
        return str;
    }

}
