package org.onosproject.netvirt.multipletable.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.onosproject.net.DeviceId;
import org.onosproject.net.device.DeviceEvent;
import org.onosproject.net.device.DeviceListener;
import org.onosproject.net.device.DeviceService;
import org.onosproject.net.flow.FlowEntry;
import org.onosproject.net.flow.FlowId;
import org.onosproject.net.flow.FlowRule;
import org.onosproject.net.flow.FlowRuleService;
import org.onosproject.net.flow.instructions.Instruction;
import org.onosproject.netvirt.ClassifierTable;
import org.onosproject.netvirt.EtherTable;
import org.onosproject.netvirt.MacTable;
import org.onosproject.netvirt.MultiTable;
import org.onosproject.netvirt.MultiTableSevice;
import org.onosproject.netvirt.Pipeline;
import org.onosproject.netvirt.TableService;
import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Provides implementation of the MultiTableSevice APIs.
 */
@Component(immediate = true, enabled = true)
@Service
public class NeutronPipelineImp implements Pipeline, MultiTableSevice {

    private final Logger log = getLogger(getClass());

    private final DeviceListener deviceListener = new InternalDeviceListener();

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected FlowRuleService flowService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected DeviceService deviceService;

    private List<MultiTable> neutronPipeline = Lists.newLinkedList();

    private Map<MultiTable, TableService> tableServiceImps = Maps
            .newConcurrentMap();

    private static final int NEXT = 1;

    @Activate
    public void activate() {
        initPipeline();
        deviceService.addListener(deviceListener);
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        deviceService.removeListener(deviceListener);
        neutronPipeline.clear();
        tableServiceImps.clear();
        log.info("Stopped");
    }

    @Override
    public void programLocalIn(DeviceId dpid, String segmentationId,
                               Long inPort, String srcMac) {
        ClassifierTable classifier = (ClassifierTable) tableServiceImps
                .get(MultiTable.CLASSIIER);
        checkNotNull(classifier, "Classifier cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = classifier.programeLocalIn(dpid, segmentationId,
                                                   inPort, srcMac);
        flowService.applyFlowRules(rule);
    }

    @Override
    public void programLocalIn(DeviceId dpid, String segmentationId,
                               Long inPort, String srcMac, boolean write) {
        ClassifierTable classifier = (ClassifierTable) tableServiceImps
                .get(MultiTable.CLASSIIER);
        checkNotNull(classifier, "Classifier cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = classifier.programeLocalIn(dpid, segmentationId,
                                                   inPort, srcMac);
        if (write) {
            flowService.applyFlowRules(rule);
        } else {
            flowService.removeFlowRules(rule);
        }
    }

    @Override
    public void programLocalOut(DeviceId dpid, String segmentationId,
                                Long outPort, String destMac) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "Classifier cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = macTable.programLocalOut(dpid, segmentationId, outPort,
                                                 destMac);
        flowService.applyFlowRules(rule);
    }

    @Override
    public void programLocalOut(DeviceId dpid, String segmentationId,
                                Long outPort, String destMac, boolean write) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "Classifier cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = macTable.programLocalOut(dpid, segmentationId, outPort,
                                                 destMac);
        if (write) {
            flowService.applyFlowRules(rule);
        } else {
            flowService.removeFlowRules(rule);
        }
    }

    @Override
    public void programArpResponse(DeviceId dpid, String segmentationId) {
        EtherTable ethTable = (EtherTable) tableServiceImps
                .get(MultiTable.ETHER);
        checkNotNull(ethTable, "EthTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = ethTable.programArpResponse(dpid, segmentationId);
        flowService.applyFlowRules(rule);
    }

    @Override
    public void programArpResponse(DeviceId dpid, String segmentationId,
                                   boolean write) {
        EtherTable ethTable = (EtherTable) tableServiceImps
                .get(MultiTable.ETHER);
        checkNotNull(ethTable, "EthTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = ethTable.programArpResponse(dpid, segmentationId);
        if (write) {
            flowService.applyFlowRules(rule);
        } else {
            flowService.removeFlowRules(rule);
        }
    }

    @Override
    public void programTunnelOut(DeviceId dpid, String segmentationId,
                                 Long outPort, String destMac) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "MacTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = macTable.programTunnelOut(dpid, segmentationId,
                                                  outPort, destMac);
        flowService.applyFlowRules(rule);
    }

    @Override
    public void programTunnelOut(DeviceId dpid, String segmentationId,
                                 Long outPort, String destMac, boolean write) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "MacTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = macTable.programTunnelOut(dpid, segmentationId,
                                                  outPort, destMac);
        if (write) {
            flowService.applyFlowRules(rule);
        } else {
            flowService.removeFlowRules(rule);
        }
    }

    @Override
    public void programTunnelIn(DeviceId dpid, String segmentationId,
                                Long inPort, String destMac) {
        ClassifierTable classifier = (ClassifierTable) tableServiceImps
                .get(MultiTable.CLASSIIER);
        checkNotNull(classifier, "Classifier cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = classifier.programTunnelIn(dpid, segmentationId,
                                                   inPort, destMac);
        flowService.applyFlowRules(rule);
    }

    @Override
    public void programTunnelIn(DeviceId dpid, String segmentationId,
                                Long inPort, String destMac, boolean write) {
        ClassifierTable classifier = (ClassifierTable) tableServiceImps
                .get(MultiTable.CLASSIIER);
        checkNotNull(classifier, "Classifier cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = classifier.programTunnelIn(dpid, segmentationId,
                                                   inPort, destMac);
        if (write) {
            flowService.applyFlowRules(rule);
        } else {
            flowService.removeFlowRules(rule);
        }
    }

    @Override
    public MultiTable getNext(MultiTable table) {
        for (MultiTable stable : neutronPipeline) {
            if (stable.getClassT() == table.getClassT()) {
                int index = neutronPipeline.indexOf(stable) + NEXT;
                MultiTable ntable = neutronPipeline.get(index);
                checkNotNull(ntable, table.getClassT() + "is the last table");
                return ntable;
            }
        }
        log.error(table.getClassT() + "doesn't exist in the pipeline");
        return null;
    }

    @Override
    public void programLocalBcastRules(DeviceId dpid, String segmentationId,
                                       Long inPort, List<Long> allports) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "MacTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = macTable.programLocalBcastRules(dpid, segmentationId,
                                                        inPort, allports);
        flowService.applyFlowRules(rule);
    }

    @Override
    public void programLocalBcastRules(DeviceId dpid, String segmentationId,
                                       Long inPort, List<Long> allports,
                                       boolean write) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "MacTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule rule = macTable.programLocalBcastRules(dpid, segmentationId,
                                                        inPort, allports);
        if (write) {
            flowService.applyFlowRules(rule);
        } else {
            flowService.removeFlowRules(rule);
        }
    }

    @Override
    public void addFirst(MultiTable table) {

    }

    @Override
    public void addLast(MultiTable table) {
        neutronPipeline.add(table);
        if (!tableServiceImps.containsKey(table)) {
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                TableService serviceImp = (TableService) classLoader
                        .loadClass(table.getClassT()).newInstance();
                serviceImp.setPipeline(this);
                tableServiceImps.put(table, serviceImp);
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                log.error("Unable to Generate Object" + table.getClassT(), e);
            }
        }
        return;
    }

    @Override
    public void remove(MultiTable table) {
    }

    private void initPipeline() {
        this.addLast(MultiTable.CLASSIIER);
        this.addLast(MultiTable.ETHER);
        this.addLast(MultiTable.ROUTING);
        this.addLast(MultiTable.IP_FORWARDING);
        this.addLast(MultiTable.MAC_FORWARDING);
    }

    private Iterable<Instruction> getExistingInstructions(DeviceId dpid,
                                                          FlowId flowId) {
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        Iterable<FlowEntry> flows = flowService.getFlowEntries(dpid);
        Iterable<Instruction> instructions = new ArrayList();
        for (FlowEntry entry : flows) {
            if (entry.id().equals(flowId)) {
                instructions = entry.treatment().allInstructions();
                break;
            }
        }
        return instructions;

    };

    @Override
    public void programTunnelFloodOut(DeviceId dpid, String segmentationId,
                                      Long ofPortOut, List<Long> localports) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "macTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule flowRule = macTable.programTunnelFloodOut(dpid,
                                                           segmentationId,
                                                           ofPortOut,
                                                           localports);
        flowService.applyFlowRules(flowRule);
        return;
    }

    @Override
    public void programTunnelFloodOut(DeviceId dpid, String segmentationId,
                                      Long ofPortOut, List<Long> localports,
                                      boolean write) {
        MacTable macTable = (MacTable) tableServiceImps
                .get(MultiTable.MAC_FORWARDING);
        checkNotNull(macTable, "macTable cannot be null");
        checkNotNull(deviceService.getDevice(dpid), "device cannot be null");
        FlowRule flowRule = macTable.programTunnelFloodOut(dpid,
                                                           segmentationId,
                                                           ofPortOut,
                                                           localports);
        if (write) {
            flowService.applyFlowRules(flowRule);
        } else {
            flowService.removeFlowRules(flowRule);
        }

        return;
    }

    private class InternalDeviceListener implements DeviceListener {

        @Override
        public void event(DeviceEvent event) {
            if (event.type() == DeviceEvent.Type.DEVICE_ADDED
                    || event.type() == DeviceEvent.Type.DEVICE_UPDATED) {
                DeviceId deviceId = event.subject().id();
                for (MultiTable table : neutronPipeline) {
                    TableService tableService = tableServiceImps.get(table);
                    checkNotNull(tableService, tableService.toString()
                            + " cannot be null");
                    FlowRule rule = tableService.programDefaultRules(deviceId);
                    flowService.applyFlowRules(rule);
                }
            }
        }
    }
}
