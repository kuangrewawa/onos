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
 * Default infrastructure ExtraDHCPOption model implementation.
 */
public class DefaultExtraDHCPOption extends AbstractElement implements ExtraDHCPOption {

    private final String name;
    private final String value;
    // For serialization
    private DefaultExtraDHCPOption() {
        this.name = null;
        this.value = null;
    }
    /**
     * Creates a port DefaultExtraDHCPOption element attributed to the specified provider.
     *
     * @param name of the DefaultExtraDHCPOption
     * @param value of the DefaultExtraDHCPOption
     */
    public DefaultExtraDHCPOption(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("name", name).add("value", value)
                .toString();
    }

    @Override
    public ElementId id() {
        return null;
    }
    @Override
    public String name() {
        return name;
    }
    @Override
    public String value() {
        return value;
    }
}
