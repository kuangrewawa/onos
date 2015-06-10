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
import org.onosproject.net.ExtraDHCPOption;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Network ExtraDHCPOption codec.
 */
public final class ExtralDHCPOptionsCodec extends JsonCodec<ExtraDHCPOption> {

    @Override
    public ObjectNode encode(ExtraDHCPOption extradhcpopt, CodecContext context) {
        checkNotNull(extradhcpopt, "ExtraDHCPOpt cannot be null");
        ObjectNode result = context.mapper().createObjectNode()
                 .put("opt_name", extradhcpopt.name().toString())
                 .put("opt_value", extradhcpopt.value().toString());
        return result;
    }
}
