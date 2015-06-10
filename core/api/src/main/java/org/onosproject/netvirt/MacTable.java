package org.onosproject.netvirt;

import java.util.List;

import org.onlab.packet.MacAddress;
import org.onlab.packet.VlanId;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.DefaultApplicationId;
import org.onosproject.net.DeviceId;
import org.onosproject.net.PortNumber;
import org.onosproject.net.flow.DefaultTrafficSelector;
import org.onosproject.net.flow.DefaultTrafficTreatment;
import org.onosproject.net.flow.FlowId;
import org.onosproject.net.flow.FlowRule;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.flow.instructions.Instruction;
import org.onosproject.net.flow.instructions.Instructions.OutputInstruction;
import org.onosproject.net.flowext.NeutronFlowRule;

public class MacTable implements TableService {

    private static final int CONTROLLER_PRIORITY = 4000;
    private static final int MID_PRIORITY = 1000;
    private static final int DEFAULT_PRIORITY = 200;
    private static final int HIGHEST_PRIORITY = 0xffff;
    private static final int TIME_OUT = 0;
    private Pipeline pipeline;
    private static final ApplicationId APP_ID = new DefaultApplicationId(6544,
                                                                         "MacTable");

    public FlowRule programLocalOut(DeviceId dpid, String segmentationId,
                                    Long outPort, String destMac) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchEthDst(MacAddress.valueOf(destMac));
        selector.matchTunnelId(Long.parseLong(segmentationId));

        treatment.setOutput(PortNumber.portNumber(outPort));
        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            CONTROLLER_PRIORITY, APP_ID,
                                            TIME_OUT, true,
                                            MultiTable.MAC_FORWARDING);
        return rule;
    }

    public FlowRule programTunnelOut(DeviceId dpid, String segmentationId,
                                     Long outPort, String destMac) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchEthDst(MacAddress.valueOf(destMac));
        selector.matchTunnelId(Long.parseLong(segmentationId));

        treatment.setOutput(PortNumber.portNumber(outPort));

        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(), MID_PRIORITY,
                                            APP_ID, TIME_OUT, true,
                                            MultiTable.MAC_FORWARDING);
        return rule;
    }

    private boolean isExisted(String segmentationId, DeviceId dpid,
                              Long localPort,
                              Iterable<Instruction> existingInstructions) {
        if (existingInstructions == null) {
            return false;
        }
        for (Instruction inst : existingInstructions) {
            if (inst instanceof OutputInstruction) {
                OutputInstruction op = (OutputInstruction) inst;
                if (localPort.equals(op.port())) {
                    return true;
                }

            }
        }
        return false;

    }

    public FlowRule programTunnelFloodOut(DeviceId dpid, String segmentationId,
                                          Long ofPortOut,
                                          Iterable<Long> localports) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchInPort(PortNumber.portNumber(ofPortOut));
        selector.matchTunnelId(Long.parseLong(segmentationId));
        selector.matchEthDst(MacAddress.BROADCAST);

        for (Long outport : localports) {
            treatment.setOutput(PortNumber.portNumber(outport));
        }

        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            CONTROLLER_PRIORITY, APP_ID,
                                            TIME_OUT, true,
                                            MultiTable.MAC_FORWARDING);
        return rule;
    }

    @Override
    public FlowRule programDefaultRules(DeviceId dpid) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchVlanId(VlanId.ANY);

        treatment.drop();

        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            DEFAULT_PRIORITY, APP_ID, TIME_OUT,
                                            true, MultiTable.MAC_FORWARDING);
        return rule;
    }

    public FlowRule programLocalBcastRules(DeviceId dpid,
                                           String segmentationId, Long inPort,
                                           List<Long> allports) {
        TrafficSelector.Builder selector;
        TrafficTreatment.Builder treatment;

        FlowId flowId = FlowId.valueOf(((Long.parseLong(segmentationId)) << 48)
                | inPort << 32);

        selector = DefaultTrafficSelector.builder();
        treatment = DefaultTrafficTreatment.builder();

        selector.matchInPort(PortNumber.portNumber(inPort));
        selector.matchEthDst(MacAddress.BROADCAST);
        selector.matchTunnelId(Long.parseLong(segmentationId));

        for (Long outport : allports) {
            if (inPort != outport) {
                treatment.setOutput(PortNumber.portNumber(outport));
            }
        }
        FlowRule rule = new NeutronFlowRule(dpid, selector.build(),
                                            treatment.build(),
                                            DEFAULT_PRIORITY, APP_ID, TIME_OUT,
                                            true, MultiTable.MAC_FORWARDING);
        return rule;
    }

    @Override
    public FlowRule tableMiss(DeviceId dpid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPipeline(Pipeline pipe) {
        this.pipeline = pipe;
    }
}
