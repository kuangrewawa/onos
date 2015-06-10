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

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Collections;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.annotation.XmlRootElement;

import org.onosproject.net.provider.ProviderId;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;

/**
 * Default infrastructure network model implementation.
 */
@XmlRootElement
public class DefaultSecurityGroup implements SecurityGroup {

    private final String securityGroupsUUID;
    private final String securityGroupsName;
    private final String securityGroupsDescription;
    private final String securityGroupsTenantID;
    private final Iterable<SecurityRule> securityGroupsRules;
    // For serialization
    private DefaultSecurityGroup() {
        this.securityGroupsUUID = null;
        this.securityGroupsName = null;
        this.securityGroupsDescription = null;
        this.securityGroupsTenantID = null;
        this.securityGroupsRules = null;
    }

    /**
     * Creates a network element attributed to the specified provider.
     *
     * @param providerId identity of the provider
     */
    public DefaultSecurityGroup(String securityGroupsUUID, String securityGroupsName,
            String securityGroupsDescription, String securityGroupsTenantID,
            JsonNode securityGroupsRules) {
        this.securityGroupsUUID = securityGroupsUUID;
        this.securityGroupsName = securityGroupsName;
        this.securityGroupsDescription = securityGroupsDescription;
        this.securityGroupsTenantID = securityGroupsTenantID;
        this.securityGroupsRules = jsonNodeToSR(securityGroupsRules);
    }
    /**
     * Change JsonNode securityGroupsRules to a collection of the SecurityRule.
     *
     * @param JsonNode securityGroupsRules
     */
    public Iterable<SecurityRule> jsonNodeToSR(JsonNode securityGroupsRules) {
        ConcurrentMap<Integer, SecurityRule> srsMap = Maps.newConcurrentMap();
        Integer i = 0;
        String securityRuleUUID = "";
        String securityRuleDescription = "";
        String securityRuleProtocol = "";
        String securityRulePorMin = "";
        String securityRulePorMax = "";
        String securityRuleEthertype = "";
        String securityRuleRemoteIpPrefix = "";
        String securityRemoteGroupId = "";
        String securityRuleGroupID = "";
        String securityTennatID = "";
        if (securityGroupsRules.isArray()) {
            for (JsonNode node : securityGroupsRules) {
                securityRuleUUID = node.get("id").asText();
                securityRuleDescription = node.get("description").asText();
                securityRuleProtocol = node.get("protocol").asText();
                securityRulePorMin = node.get("port_range_min").asText();
                securityRulePorMax = node.get("port_range_max").asText();
                securityRuleEthertype = node.get("ethertype").asText();
                securityRuleRemoteIpPrefix = node.get("remote_ip_prefix").asText();
                securityRemoteGroupId = node.get("remote_group_id").asText();
                securityRuleGroupID = node.get("security_group_id").asText();
                securityTennatID = node.get("tennat_id").asText();
                SecurityRule sr = new DefaultSecurityRule(securityRuleUUID, securityRuleDescription,
                        securityRuleProtocol, securityRulePorMin, securityRulePorMax,
                        securityRuleEthertype, securityRuleRemoteIpPrefix, securityRemoteGroupId,
                        securityRuleGroupID, securityTennatID);
                srsMap.putIfAbsent(i, sr);
                i++;
            }
        } else {
            securityRuleUUID = securityGroupsRules.get("id").asText();
            securityRuleDescription = securityGroupsRules.get("description").asText();
            securityRuleProtocol = securityGroupsRules.get("protocol").asText();
            securityRulePorMin = securityGroupsRules.get("port_range_min").asText();
            securityRulePorMax = securityGroupsRules.get("port_range_max").asText();
            securityRuleEthertype = securityGroupsRules.get("ethertype").asText();
            securityRuleRemoteIpPrefix = securityGroupsRules.get("remote_ip_prefix").asText();
            securityRemoteGroupId = securityGroupsRules.get("remote_group_id").asText();
            securityRuleGroupID = securityGroupsRules.get("security_group_id").asText();
            securityTennatID = securityGroupsRules.get("tennat_id").asText();
            SecurityRule sr = new DefaultSecurityRule(securityRuleUUID, securityRuleDescription,
                    securityRuleProtocol, securityRulePorMin, securityRulePorMax,
                    securityRuleEthertype, securityRuleRemoteIpPrefix, securityRemoteGroupId,
                    securityRuleGroupID, securityTennatID);
            srsMap.putIfAbsent(i, sr);
        }
        return Collections.unmodifiableCollection(srsMap.values());
    }
    @Override
    public String toString() {
        return toStringHelper(this)
                .add("securityGroupsUUID", securityGroupsUUID)
                .add("securityGroupsName", securityGroupsName)
                .add("securityGroupsDescription", securityGroupsDescription)
                .add("securityGroupsTenantID", securityGroupsTenantID)
                .add("securityGroupsRules", securityGroupsRules)
                .toString();
    }

    @Override
    public ElementId id() {
        return null;
    }

    @Override
    public Annotations annotations() {
        return null;
    }

    @Override
    public ProviderId providerId() {
        return null;
    }

    @Override
    public String securityGroupsUUID() {
        return securityGroupsUUID;
    }

    @Override
    public String securityGroupsName() {
        return securityGroupsName;
    }

    @Override
    public String securityGroupsDescription() {
        return securityGroupsDescription;
    }

    @Override
    public String securityGroupsTenantID() {
        return securityGroupsTenantID;
    }

    @Override
    public Iterable<SecurityRule> securityGroupsRules() {
        return securityGroupsRules;
    }

}
