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
package org.onosproject.net.subnet;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.List;

import org.onosproject.net.AbstractDescription;
import org.onosproject.net.Annotations;
import org.onosproject.net.SparseAnnotations;
import org.onosproject.net.SubnetId;

/**
 * Default implementation of immutable device description entity.
 */
public class DefaultSubnetDescription extends AbstractDescription
        implements SubnetDescription {
    private final SubnetId id;
    private final String subnetName;
    private final String subnetID;
    private final String ipVersion;
    private final String cidr;
    private final String cidrPrefix;
    private final String gatewayIp;
    private final Boolean dhcpEnabled;
    private final Boolean shared;
    private final List<String> ipAlloc;
    private final List<String> hostRoutes;

    /**
     * Creates a device description using the supplied information.
     *
     * @param uri device URI
     * @param type device type
     * @param manufacturer device manufacturer
     * @param hwVersion device HW version
     * @param swVersion device SW version
     * @param serialNumber device serial number
     * @param chassis chassis id
     * @param annotations optional key/value annotations map
     */
    public DefaultSubnetDescription(SubnetId id, String subnetName,
                                    String subnetID, String ipVersion,
                                    String cidr, String cidrPrefix,
                                    String gatewayIp, Boolean dhcpEnabled,
                                    Boolean shared, List<String> ipAlloc,
                                    List<String> hostRoutes,
                                    Annotations... annotations) {
        super((SparseAnnotations[]) annotations);
        this.id = id;
        this.subnetName = subnetName;
        this.subnetID = subnetID;
        this.ipVersion = ipVersion;
        this.cidr = cidr;
        this.cidrPrefix = cidrPrefix;
        this.gatewayIp = gatewayIp;
        this.dhcpEnabled = dhcpEnabled;
        this.shared = shared;
        this.ipAlloc = ipAlloc;
        this.hostRoutes = hostRoutes;
    }

    /**
     * Creates a device description using the supplied information.
     *
     * @param base SubnetDescription to basic information
     * @param annotations Annotations to use.
     */
    public DefaultSubnetDescription(SubnetDescription base,
                                    SparseAnnotations... annotations) {
        this(base.id(), base.subnetName(), base.subnetID(), base.ipVersion(),
             base.cidr(), base.cidrPrefix(), base.gatewayIP(), base
                     .dhcpEnabled(), base.shared(), base.ipAlloc(), base
                     .hostRoutes(), annotations);
    }

    @Override
    public SubnetId id() {
        return (SubnetId) id;
    }

    @Override
    public String subnetName() {
        return subnetName;
    }

    @Override
    public String subnetID() {
        return subnetID;
    }

    @Override
    public String ipVersion() {
        return ipVersion;
    }

    @Override
    public String cidr() {
        return cidr;
    }

    @Override
    public String cidrPrefix() {
        return cidrPrefix;
    }

    @Override
    public String gatewayIP() {
        return gatewayIp;
    }

    @Override
    public Boolean dhcpEnabled() {
        return dhcpEnabled;
    }

    @Override
    public Boolean shared() {
        return shared;
    }

    @Override
    public List<String> ipAlloc() {
        return ipAlloc;
    }

    @Override
    public List<String> hostRoutes() {
        return hostRoutes;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("subnetName", subnetName)
                .add("subnetID", subnetID).add("ipVersion", ipVersion)
                .add("cidr", cidr).add("cidrPrefix", cidrPrefix)
                .add("gatewayIp", gatewayIp).add("dhcpEnabled", cidr)
                .add("shared", shared).add("ipAlloc", ipAlloc).toString();
    }

    // default constructor for serialization
    private DefaultSubnetDescription() {
        this.id = null;
        this.subnetName = null;
        this.subnetID = null;
        this.ipVersion = null;
        this.cidr = null;
        this.cidrPrefix = null;
        this.gatewayIp = null;
        this.dhcpEnabled = null;
        this.shared = null;
        this.ipAlloc = null;
        this.hostRoutes = null;
        // this.allocationPools = null;
        // this.dnsNameservers = null;
        // this.tenantID = null;
        // this.ipV6AddressMode = null;
        // this.ipV6RaMode = null;
    }

}
