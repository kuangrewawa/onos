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

import javax.xml.bind.annotation.XmlRootElement;

import org.onosproject.net.provider.ProviderId;

/**
 * Default infrastructure SecurityRule model implementation.
 */
@XmlRootElement
public class DefaultSecurityRule implements SecurityRule {

    private final String securityRuleUUID;
    private final String securityRuleDescription;
    private final String securityRuleProtocol;
    private final String securityRulePorMin;
    private final String securityRulePorMax;
    private final String securityRuleEthertype;
    private final String securityRuleRemoteIpPrefix;
    private final String securityRemoteGroupId;
    private final String securityRuleGroupID;
    private final String securityTennatID;
    // For serialization
    private DefaultSecurityRule() {
        this.securityRuleUUID = null;
        this.securityRuleDescription = null;
        this.securityRuleProtocol = null;
        this.securityRulePorMin = null;
        this.securityRulePorMax = null;
        this.securityRuleEthertype = null;
        this.securityRuleRemoteIpPrefix = null;
        this.securityRemoteGroupId = null;
        this.securityRuleGroupID = null;
        this.securityTennatID = null;
    }

    /**
     * Creates a network element attributed to the specified provider.
     *
     * @param providerId identity of the provider
     */
    public DefaultSecurityRule(String securityRuleUUID, String securityRuleDescription,
            String securityRuleProtocol, String securityRulePorMin,
            String securityRulePorMax, String securityRuleEthertype,
            String securityRuleRemoteIpPrefix, String securityRemoteGroupId,
            String securityRuleGroupID, String securityTennatID) {
        this.securityRuleUUID = securityRuleUUID;
        this.securityRuleDescription = securityRuleDescription;
        this.securityRuleProtocol = securityRuleProtocol;
        this.securityRulePorMin = securityRulePorMin;
        this.securityRulePorMax = securityRulePorMax;
        this.securityRuleEthertype = securityRuleEthertype;
        this.securityRuleRemoteIpPrefix = securityRuleRemoteIpPrefix;
        this.securityRemoteGroupId = securityRemoteGroupId;
        this.securityRuleGroupID = securityRuleGroupID;
        this.securityTennatID = securityTennatID;
    }
    @Override
    public String toString() {
        return toStringHelper(this)
                .add("securityRuleUUID", securityRuleUUID)
                .add("securityRuleDescription", securityRuleDescription)
                .add("securityRuleProtocol", securityRuleProtocol)
                .add("securityRulePorMin", securityRulePorMin)
                .add("securityRulePorMax", securityRulePorMax)
                .add("securityRuleEthertype", securityRuleEthertype)
                .add("securityRuleRemoteIpPrefix", securityRuleRemoteIpPrefix)
                .add("securityRemoteGroupId", securityRemoteGroupId)
                .add("securityRuleGroupID", securityRuleGroupID)
                .add("securityTennatID", securityTennatID)
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
    public String securityRuleUUID() {
        return securityRuleUUID;
    }

    @Override
    public String securityRuleDescription() {
        return securityRuleDescription;
    }

    @Override
    public String securityRuleProtocol() {
        return securityRuleProtocol;
    }

    @Override
    public String securityRulePorMin() {
        return securityRulePorMin;
    }

    @Override
    public String securityRulePorMax() {
        return securityRulePorMax;
    }

    @Override
    public String securityRuleEthertype() {
        return securityRuleEthertype;
    }

    @Override
    public String securityRuleRemoteIpPrefix() {
        return securityRuleRemoteIpPrefix;
    }

    @Override
    public String securityRemoteGroupId() {
        return securityRemoteGroupId;
    }

    @Override
    public String securityRuleGroupID() {
        return securityRuleGroupID;
    }

    @Override
    public String securityTennatID() {
        return securityTennatID;
    }

}
