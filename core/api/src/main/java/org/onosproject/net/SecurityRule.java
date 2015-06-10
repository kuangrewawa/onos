package org.onosproject.net;


public interface SecurityRule extends Element {
    /**
     * Returns the SecurityRule identifier.
     *
     * @return SecurityRule id
     */
    String securityRuleUUID();
    /**
     * Returns the SecurityRule description.
     *
     * @return SecurityRule description
     */
    String securityRuleDescription();
    /**
     * Returns the SecurityRule protocol.
     *
     * @return SecurityRule protocol
     */
    String securityRuleProtocol();
    /**
     * Returns the SecurityRule min port.
     *
     * @return SecurityRule  min port
     */
    String securityRulePorMin();
    /**
     * Returns the SecurityRule  max port.
     *
     * @return SecurityRule max port
     */
    String securityRulePorMax();
    /**
     * Returns the SecurityRule ethertype.
     *
     * @return SecurityRule ethertype
     */
    String securityRuleEthertype();
    /**
     * Returns the SecurityRule remote ipprefix.
     *
     * @return SecurityRule  remote ipprefix
     */
    String securityRuleRemoteIpPrefix();
    /**
     * Returns the SecurityRule remote group id.
     *
     * @return SecurityRule remote group id
     */
    String securityRemoteGroupId();
    /**
     * Returns the SecurityRule RuleGroup identifier.
     *
     * @return SecurityRule RuleGroup id
     */
    String securityRuleGroupID();
    /**
     * Returns the SecurityRule tennat identifier.
     *
     * @return SecurityRule tennat id
     */
    String securityTennatID();
}
