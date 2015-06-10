package org.onosproject.net;


public interface VifDetail extends Element {
    /**
     * Returns the VifDetail portFilter.
     *
     * @return VifDetail portFilter
     */
    Boolean isportFilter();;
    /**
     * Returns the VifDetail ovsHybridPlug.
     *
     * @return VifDetail ovsHybridPlug.
     */
    Boolean isovsHybridPlug();
}
