/*
 * Copyright 2015 Open Networking Laboratory
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
package org.onosproject.net.flowext;

import static com.google.common.base.MoreObjects.toStringHelper;
import static org.slf4j.LoggerFactory.getLogger;

import org.onosproject.net.flow.DefaultFlowEntry;
import org.onosproject.net.flow.FlowRule;
import org.onosproject.net.flow.StoredFlowEntry;
import org.onosproject.netvirt.MultiTable;
import org.slf4j.Logger;

/**
 * Experimental extension to the flow rule subsystem; still under development.
 * Represents a generic abstraction of the service data. User app can customize
 * whatever it needs to install on devices.
 */
public class NeutronFlowEntry extends NeutronFlowRule
        implements StoredFlowEntry {
    private static final Logger log = getLogger(DefaultFlowEntry.class);

    private long life;
    private long packets;
    private long bytes;
    private FlowEntryState state;

    private long lastSeen = -1;

    private final int errType;

    private final int errCode;

    public NeutronFlowEntry(FlowRule rule, MultiTable table) {
        super(rule, table);
        this.state = FlowEntryState.PENDING_ADD;
        this.life = 0;
        this.packets = 0;
        this.bytes = 0;
        this.errCode = -1;
        this.errType = -1;
        this.lastSeen = System.currentTimeMillis();
    }

    @Override
    public long life() {
        return life;
    }

    @Override
    public long packets() {
        return packets;
    }

    @Override
    public long bytes() {
        return bytes;
    }

    @Override
    public FlowEntryState state() {
        return this.state;
    }

    @Override
    public long lastSeen() {
        return lastSeen;
    }

    @Override
    public void setLastSeen() {
        this.lastSeen = System.currentTimeMillis();
    }

    @Override
    public void setState(FlowEntryState newState) {
        this.state = newState;
    }

    @Override
    public void setLife(long life) {
        this.life = life;
    }

    @Override
    public void setPackets(long packets) {
        this.packets = packets;
    }

    @Override
    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    @Override
    public int errType() {
        return this.errType;
    }

    @Override
    public int errCode() {
        return this.errCode;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("rule", super.toString())
                .add("state", state).toString();
    }
}
