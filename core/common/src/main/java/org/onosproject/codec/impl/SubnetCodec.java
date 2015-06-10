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
package org.onosproject.codec.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.onosproject.codec.CodecContext;
import org.onosproject.codec.JsonCodec;
import org.onosproject.net.AllocationPools;
import org.onosproject.net.Subnet;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Device JSON codec.
 */
public final class SubnetCodec extends AnnotatedCodec<Subnet> {

    @Override
    public ObjectNode encode(Subnet subnet, CodecContext context) {
        checkNotNull(subnet, "Subnet cannot be null");
        JsonCodec<AllocationPools> apcodec = context
                .codec(AllocationPools.class);
        ObjectNode result = context.mapper().createObjectNode()
                .put("id", subnet.id().toString())
                .put("gateway_ip", subnet.gatewayIP().toString())
                .put("network_id", subnet.networkID().toString())
                .put("tenant_id", subnet.tenantID())
                .put("name", subnet.subnetName())
                .put("ip_version", subnet.ipVersion())
                .put("cidr", subnet.cidr())
//                .put("cidrPrefix", subnet.cidrPrefix().toString())
                .put("shared", subnet.shared().toString())
                .put("enable_dhcp", subnet.dhcpEnabled().toString())
                .put("tenant_id", subnet.tenantID().toString());
        result.set("allocation_pools", apcodec.encode(subnet.allocationPools(), context));
        return annotate(result, subnet, context);
    }

}
