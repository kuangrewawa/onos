package org.onosproject.netvirt.configuration;

import org.onosproject.net.provider.ProviderService;

/**
 * Service through which ovsdb configuration providers can inject ovsdb
 * configuration information into the core.
 */
public interface ConfigurationProviderService
        extends ProviderService<CfgAdapterProvider> {
    void rowInserted();

    void rowUpdated();

    void rowDeleted();
}
