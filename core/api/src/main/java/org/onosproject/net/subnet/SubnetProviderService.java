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

import org.onosproject.net.MastershipRole;
import org.onosproject.net.SubnetId;
import org.onosproject.net.provider.ProviderService;

/**
 * Service through which subnet providers can inject subnet information into
 * the core.
 */
public interface SubnetProviderService extends ProviderService<SubnetProvider> {

    // TODO: define suspend and remove actions on the mezzanine administrative API

    /**
     * Signals the core that a subnet has connected or has been detected somehow.
     *
     * @param subnetId subnet identifier
     * @param subnetDescription information about network subnet
     */
    void subnetConnected(SubnetId subnetId, SubnetDescription subnetDescription);

    /**
     * Signals the core that a subnet has disconnected or is no longer reachable.
     *
     * @param subnetId identity of the subnet to be removed
     */
    void subnetDisconnected(SubnetId subnetId);

    /**
     * Sends information about all ports of a subnet. It is up to the core to
     * determine what has changed.
     *
     * @param subnetId         identity of the subnet
     * @param portDescriptions list of subnet ports
     */

    /**
     * Notifies the core about the result of a RoleRequest sent to a subnet.
     *
     * @param subnetId identity of the subnet
     * @param requested mastership role that was requested by the node
     * @param response mastership role the switch accepted
     */
    void receivedRoleReply(SubnetId subnetId, MastershipRole requested, MastershipRole response);

}
