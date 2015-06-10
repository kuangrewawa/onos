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
import org.onosproject.net.Network;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Network JSON codec.
 */
public final class NetworkCodec extends AnnotatedCodec<Network> {

    @Override
    public ObjectNode encode(Network network, CodecContext context) {
        checkNotNull(network, "Network cannot be null");
        //NetworkService service = context.get(NetworkService.class);
        ObjectNode result = context.mapper().createObjectNode()
                 .put("id", network.id().toString())
                 .put("name", network.name().toString())
                 .put("admin_state_up", network.adminStateUp())
                 .put("status", network.status().toString())
                 .put("shared", network.shared())
                 .put("tenant_id", network.tenantID().toString())
                 .put("router:external", network.routerExternal())
                 .put("provider:network_type", network.providerNetworkType().toString())
                 .put("provider:physical_network", network.providerPhysicalNetwork().toString())
                 .put("provider:segmentation_id", network.providerSegmentationID().toString());
        result.set("subnets", network.subnets());
        return annotate(result, network, context);
    }

}
