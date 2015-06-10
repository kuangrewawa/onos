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

import java.util.List;

import org.onosproject.net.Description;
import org.onosproject.net.SubnetId;

/**
 * Carrier of immutable information about a device.
 */
public interface SubnetDescription extends Description {

    /**
     * Protocol/provider specific URI that can be used to encode the identity
     * information required to communicate with the device externally, e.g.
     * datapath ID.
     *
     * @return provider specific URI for the device
     */
    SubnetId id();

    /*
     *
     */
    String subnetName();

    /*
     *
     */
    String subnetID();

    /*
     *
     */
    String ipVersion();

    /*
     *
     */
    String cidr();

    /*
     *
     */
    String cidrPrefix();

    /*
     *
     */
    String gatewayIP();

    /*
     *
     */
    Boolean dhcpEnabled();

    /*
     *
     */
    Boolean shared();

    /*
     *
     */
    List<String> ipAlloc();

    /*
     *
     */
    List<String> hostRoutes();

//    List<String> allocationPools();
//
//    List<String> dnsNameservers();

//    String tenantID();

//    String ipV6AddressMode();

//    String ipV6RaMode();

}
