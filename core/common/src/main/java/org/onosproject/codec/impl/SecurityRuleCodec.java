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
import org.onosproject.net.SecurityRule;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Network SecurityRule codec.
 */
public final class SecurityRuleCodec extends JsonCodec<SecurityRule> {

    @Override
    public ObjectNode encode(SecurityRule securityrule, CodecContext context) {
        checkNotNull(securityrule, "SecurityRule cannot be null");
        ObjectNode result = context.mapper().createObjectNode()
                 .put("id", securityrule.securityRuleUUID())
                 .put("description", securityrule.securityRuleDescription())
                 .put("protocol", securityrule.securityRuleProtocol())
                 .put("port_range_min", securityrule.securityRulePorMin())
                 .put("port_range_max", securityrule.securityRulePorMax())
                 .put("ethertype", securityrule.securityRuleEthertype())
                 .put("remote_ip_prefix", securityrule.securityRuleRemoteIpPrefix())
                 .put("remote_group_id", securityrule.securityRemoteGroupId())
                 .put("security_group_id", securityrule.securityRuleGroupID())
                 .put("tennat_id", securityrule.securityTennatID());
        return result;
    }
}
