package org.onosproject.net;


public interface SecurityGroup extends Element {
    /**
     * Returns the securityGroups identifier.
     *
     * @return securityGroups id
     */
    String securityGroupsUUID();
    /**
     * Returns the securityGroups Name.
     *
     * @return securityGroups Name
     */
    String securityGroupsName();
    /**
     * Returns the securityGroups Description.
     *
     * @return securityGroups Description
     */
    String securityGroupsDescription();
    /**
     * Returns the securityGroups TenantID.
     *
     * @return securityGroups TenantID
     */
    String securityGroupsTenantID();
    /**
     * Returns the port securityGroupsRules.
     *
     * @return port securityGroupsRules
     */
    Iterable<SecurityRule> securityGroupsRules();

}
