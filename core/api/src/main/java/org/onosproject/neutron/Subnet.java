/*
 *Copyright 2014 Open Networking Laboratory
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */
package org.onosproject.neutron;

import java.util.Collection;

import org.jboss.netty.handler.ipfilter.CIDR;
import org.onlab.packet.IpAddress;
import org.onlab.packet.IpAddress.Version;
import org.onosproject.net.Element;
import org.onosproject.net.NetworkId;

/**
 * Representation of a network infrastructure device.
 */
public interface Subnet extends Element {

    /**
     * . Coarse classification of the type of the infrastructure device.
     */

    /**
     * . Returns the id.
     *
     * @return id
     */
    SubnetId id();

    /**
     * . Returns the subnetName.
     *
     * @return subnetName
     */
    String subnetName();

    /**
     * . Returns the networkID.
     *
     * @return networkID
     */
    NetworkId networkID();

    /**
     * . Returns the tenantID.
     *
     * @return tenantID
     */
    String tenantID();

    /**
     * . Returns the ipVersion.
     *
     * @return ipVersion
     */
    Version ipVersion();

    /**
     * . Returns the cidr.
     *
     * @return cidr
     */
    CIDR cidr();

    /**
     * . Returns the gatewayIP.
     *
     * @return gatewayIP
     */
    IpAddress gatewayIP();

    /**
     * . Returns the dhcpEnabled.
     *
     * @return dhcpEnabled
     */
    Boolean dhcpEnabled();

    /**
     * Returns the shared.
     *
     * @return shared
     */
    Boolean shared();

    /**
     * Returns the hostRoutes.
     *
     * @return hostRoutes
     */
    Collection<HostRoute> hostRoutes();

    /**
     * Returns the ipV6AddressMode.
     *
     * @return ipV6AddressMode
     */
    Mode ipV6AddressMode();

    /**
     * . Returns the ipV6RaMode.
     *
     * @return ipV6RaMode
     */
    Mode ipV6RaMode();

    /**
     * Returns the allocation_pools.
     *
     * @return AllocationPools
     */

    Collection<AllocationPool> allocationPools();
}
