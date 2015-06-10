package org.onosproject.netvirt;

import org.onlab.packet.MacAddress;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.DefaultApplicationId;
import org.onosproject.net.DeviceId;
import org.onosproject.net.PortNumber;
import org.onosproject.net.flow.DefaultTrafficSelector;
import org.onosproject.net.flow.DefaultTrafficTreatment;
import org.onosproject.net.flow.FlowRule;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.flowext.NeutronFlowRule;

public class ClassifierTable implements TableService {

    private static final int CONTROLLER_PRIORITY = 4000;
    private static final int DROP_PRIORITY = 100;
    private static final int DEFAULT_PRIORITY = 200;
    private static final int HIGHEST_PRIORITY = 0xffff;
    private static final int TIME_OUT = 0;
    private Pipeline pipeline;
    private static final ApplicationId APP_ID = new DefaultApplicationId(6540,
                                                                         "ClassifierTable");

    public FlowRule programeLocalIn(DeviceId dpid, String segmentationId,
                                    Long inPort, String srcMac) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchEthSrc(MacAddress.valueOf(srcMac));
        selector.matchInPort(PortNumber.portNumber(inPort));

        treatment.setTunnelId(Long.parseLong(segmentationId));
        treatment.transition(pipeline.getNext(MultiTable.CLASSIIER).tableId);
        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            CONTROLLER_PRIORITY, APP_ID,
                                            TIME_OUT, true,
                                            MultiTable.CLASSIIER);
        return rule;
    }

    public FlowRule programTunnelIn(DeviceId dpid, String segmentationId,
                                    Long inPort, String destMac) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchInPort(PortNumber.portNumber(inPort));
        selector.matchTunnelId(Long.parseLong(segmentationId));

        treatment.transition(pipeline.getNext(MultiTable.CLASSIIER).tableId);

        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            CONTROLLER_PRIORITY, APP_ID,
                                            TIME_OUT, true,
                                            MultiTable.CLASSIIER);
        return rule;
    }

    @Override
    public FlowRule programDefaultRules(DeviceId dpid) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        treatment.transition(pipeline.getNext(MultiTable.CLASSIIER).tableId);
        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            DEFAULT_PRIORITY, APP_ID, TIME_OUT,
                                            true, MultiTable.CLASSIIER);
        return rule;
    }

    @Override
    public FlowRule tableMiss(DeviceId dpid) {
        return null;
    }

    @Override
    public void setPipeline(Pipeline pipe) {
        this.pipeline = pipe;
    }
}