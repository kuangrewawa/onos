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
package org.onosproject.vtn;

import org.onosproject.net.Annotated;

/**
 * Representation of the network from neutron.
 */
public interface Network extends Annotated {

    /**
     * Coarse classification of the state of the network.
     */
    public enum State {
        /**
         * Signifies that a network is currently active.This state means that
         * this network is available.
         */
        ACTIVE,
        /**
         * Signifies that a network is currently build.
         */
        BUILD,
        /**
         * Signifies that a network is currently unavailable.
         */
        DOWN,
        /**
         * Signifies that a network is currently error.
         */
        ERROR
    }

    /**
     * Coarse classification of the type of the network.
     */
    public enum Type {
        /**
         * Signifies that a network is local.
         */
        LOCAL
    }

    /**
     * Returns the network ID.
     *
     * @return network id
     */
    NetworkId id();

    /**
     * Returns the network name.
     *
     * @return network name
     */
    String name();

    /**
     * Returns the administrative state of the network,which is up(true) or
     * down(false).
     *
     * @return network admin state up
     */
    boolean adminStateUp();

    /**
     * Returns the network state.
     *
     * @return network state
     */
    State state();

    /**
     * Indicates whether this network is shared across all tenants. By
     * default,only administrative user can change this value.
     *
     * @return network shared
     */
    boolean shared();

    /**
     * Returns the UUID of the tenant that will own the network. This tenant can
     * be different from the tenant that makes the create network request.
     *
     * @return network tenant id
     */
    TenantId tenantID();

    /**
     * Returns the routerExternal.Indicates whether this network is externally
     * accessible.
     *
     * @return network router external
     */
    boolean routerExternal();

    /**
     * Returns the network Type.
     *
     * @return network Type
     */
    Type type();

    /**
     * Returns the network physical network.
     *
     * @return network physical network
     */
    PhysicalNetwork physicalNetwork();

    /**
     * Returns the network segmentation id.
     *
     * @return network segmentation id
     */
    SegmentationID segmentationID();
}