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
import org.onosproject.net.AllowedAddressPair;
import org.onosproject.net.ExtraDHCPOption;
import org.onosproject.net.FixedIps;
import org.onosproject.net.OvsPort;
import org.onosproject.net.SecurityGroup;
import org.onosproject.net.VifDetail;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * OvsPort JSON codec.
 */
public final class OvsPortCodec extends AnnotatedCodec<OvsPort> {
    @Override
    public ObjectNode encode(OvsPort ovsport, CodecContext context) {
        checkNotNull(ovsport, "OvsPort cannot be null");
        JsonCodec<FixedIps> fixedipscodec = context.codec(FixedIps.class);
        JsonCodec<AllowedAddressPair> allowaddresscodec = context.codec(AllowedAddressPair.class);
        JsonCodec<ExtraDHCPOption> extradhcpopts = context.codec(ExtraDHCPOption.class);
        JsonCodec<VifDetail> vifdetail = context.codec(VifDetail.class);
        JsonCodec<SecurityGroup> securitygroup = context.codec(SecurityGroup.class);
        ObjectNode result = (context.mapper().createObjectNode()
                 .put("id", ovsport.uuid().toString())
                 .put("network_id", ovsport.networkid().toString())
                 .put("name", ovsport.name().toString())
                 .put("admin_state_up", ovsport.adminStateUp().toString()));
        result.set("fixed_ips", fixedipscodec.encode(ovsport.fixedIPs(), context));
        result.set("allowed_address_pairs", allowaddresscodec.encode(ovsport.allowedAddressPairs(), context));
//        result.set("extra_dhcp_opts", extradhcpopts.encode(ovsport.extralDHCPOptions(), context));
        result.set("binding:vif_details", vifdetail.encode(ovsport.bindingvifDetails(), context));
        result.set("security_groups", ovsport.securityGroups());
        result.put("status", ovsport.status().toString());
        result.put("mac_address", ovsport.macAddress());
        return annotate(result, ovsport, context);
    }
}
