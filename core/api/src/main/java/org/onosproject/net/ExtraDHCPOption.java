package org.onosproject.net;

/**
 * Representation of a ExtraDHCPOption infrastructure ovsport.
 */
public interface ExtraDHCPOption extends Element {

    /**
     * Returns the name.
     *
     * @return name
     */
    String name();
    /**
     * Returns the value.
     *
     * @return value
     */
    String value();
}
