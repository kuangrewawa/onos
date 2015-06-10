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

/**
 * Default infrastructure VifDetail model implementation.
 */
public class DefaultVifDetail extends AbstractElement implements VifDetail {

    private final Boolean isportFilter;
    private final Boolean isovsHybridPlug;
    // For serialization
    private DefaultVifDetail() {
        this.isportFilter = null;
        this.isovsHybridPlug = null;
    }
    /**
     * Creates a VifDetail element attributed to the specified provider.
     *
     * @param isportFilter
     * @param isovsHybridPlug
     */
    public DefaultVifDetail(Boolean isportFilter, Boolean isovsHybridPlug) {
        this.isportFilter = isportFilter;
        this.isovsHybridPlug = isovsHybridPlug;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("portFilter", isportFilter).add("ovsHybridPlug", isovsHybridPlug)
                .toString();
    }

    @Override
    public ElementId id() {
        return null;
    }
    @Override
    public Boolean isportFilter() {
        return isportFilter;
    }
    @Override
    public Boolean isovsHybridPlug() {
        return isovsHybridPlug;
    }

}
