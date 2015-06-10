package org.onosproject.net.flowext;

import java.util.Objects;

import org.onosproject.core.ApplicationId;
import org.onosproject.net.DeviceId;
import org.onosproject.net.flow.DefaultFlowRule;
import org.onosproject.net.flow.FlowRule;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.netvirt.MultiTable;

public class NeutronFlowRule extends DefaultFlowRule {

    private final MultiTable table;

    public NeutronFlowRule(DeviceId deviceId, TrafficSelector selector,
                           TrafficTreatment treatment, int priority,
                           ApplicationId flowId, int timeout,
                           boolean permanent, MultiTable table) {
        super(deviceId, selector, treatment, priority, flowId, timeout,
              permanent);
        this.table = table;
    }

    public NeutronFlowRule(FlowRule rule, MultiTable table) {
        super(rule);
        this.table = table;
    }
    public MultiTable getTable() {
        return table;
    }

    @Override
    /*
     * The priority and statistics can change on a given treatment and selector
     *
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public int hashCode() {
        return Objects.hash(this.deviceId(), this.selector(), this.priority(),
                            table);
    }

    public int hash() {
        return Objects.hash(this.deviceId(), this.selector(), this.treatment(),
                            table);
    }

    @Override
    /*
     * The priority and statistics can change on a given treatment and selector
     *
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NeutronFlowRule) {
            NeutronFlowRule that = (NeutronFlowRule) obj;
            return Objects.equals(this.deviceId(), that.deviceId())
                    && Objects.equals(this.selector(), that.selector())
                    && Objects.equals(this.priority(), that.priority())
                    && Objects.equals(table, that.getTable());

        }
        return false;
    }
}
