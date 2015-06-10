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

import com.fasterxml.jackson.databind.JsonNode;



/**
 * Representation of a network infrastructure network.
 */
public interface OvsPort extends Element {

    /**
     * Returns the ovsport identifier.
     *
     * @return ovsport id
     */
    OvsPortId uuid();
    /**
     * Returns the network identifier.
     *
     * @return network id
     */
    NetworkId networkid();
    /**
     * Returns the ovsport name.
     *
     * @return ovsport name
     */
    String name();

    /**
     * Returns the ovsport adminStateUp.
     *
     * @return adminStateUp version
     */
    Boolean adminStateUp();
    /**
     * Returns the ovsport status.
     *
     * @return ovsport status
     */
    String status();
    /**
     * Returns the macaddress.
     *
     * @return macaddress
     */
    String macAddress();
    /**
     * Returns the port tenantID.
     *
     * @return port tenantID
     */
    String tenantID();
    /**
     * Returns the device identifier.
     *
     * @return device id
     */
    String deviceID();
    /**
     * Returns the deviceOwner.
     *
     * @return deviceOwner
     */
    String deviceOwner();
    /**
     * Returns the port allowedAddressPairs.
     *
     * @return port allowedAddressPairs
     */
    Iterable<AllowedAddressPair> allowedAddressPairs();
    /**
     * Returns the port allowedAddressPairs.
     *
     * @return port allowedAddressPairs
     */
//    Iterable<ExtraDHCPOption> extralDHCPOptions();
    /**
     * Returns the port fixedIPs.
     *
     * @return port fixedIPs
     */
    Iterable<FixedIps> fixedIPs();
    /**
     * Returns the port bindinghostId.
     *
     * @return port bindinghostId
     */
    String bindingHostId();
    /**
     * Returns the port bindinghostIdVnicType.
     *
     * @return port bindinghostIdVnicType
     */
    String bindingVnicType();
    /**
     * Returns the port bindinghostIdVifcType.
     *
     * @return port bindinghostIdVifcType
     */
    String bindingVifType();
    /**
     * Returns the port fixedIPs.
     *
     * @return port fixedIPs
     */
    Iterable<VifDetail> bindingvifDetails();
    /**
     * Returns the port fixedIPs.
     *
     * @return port fixedIPs
     */
    JsonNode securityGroups();
}
