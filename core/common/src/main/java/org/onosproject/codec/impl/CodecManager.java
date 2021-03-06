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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.onlab.packet.Ethernet;
import org.onosproject.codec.CodecService;
import org.onosproject.codec.JsonCodec;
import org.onosproject.core.Application;
import org.onosproject.net.AllocationPools;
import org.onosproject.net.AllowedAddressPair;
import org.onosproject.net.Annotations;
import org.onosproject.net.ConnectPoint;
import org.onosproject.net.Device;
import org.onosproject.net.ExtraDHCPOption;
import org.onosproject.net.FixedIps;
import org.onosproject.net.Host;
import org.onosproject.net.HostLocation;
import org.onosproject.net.Link;
import org.onosproject.net.Network;
import org.onosproject.net.OvsPort;
import org.onosproject.net.Path;
import org.onosproject.net.Port;
import org.onosproject.net.SecurityGroup;
import org.onosproject.net.SecurityRule;
import org.onosproject.net.Subnet;
import org.onosproject.net.VifDetail;
import org.onosproject.net.flow.FlowEntry;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.flow.criteria.Criterion;
import org.onosproject.net.flow.instructions.Instruction;
import org.onosproject.net.intent.ConnectivityIntent;
import org.onosproject.net.intent.Constraint;
import org.onosproject.net.intent.HostToHostIntent;
import org.onosproject.net.intent.Intent;
import org.onosproject.net.intent.PointToPointIntent;
import org.onosproject.net.topology.Topology;
import org.onosproject.net.topology.TopologyCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;

/**
 * Implementation of the JSON codec brokering service.
 */
@Component(immediate = true)
@Service
public class CodecManager implements CodecService {

    private static Logger log = LoggerFactory.getLogger(CodecManager.class);

    private final Map<Class<?>, JsonCodec> codecs = new ConcurrentHashMap<>();

    @Activate
    public void activate() {
        codecs.clear();
        registerCodec(Application.class, new ApplicationCodec());
        registerCodec(Annotations.class, new AnnotationsCodec());
        registerCodec(Device.class, new DeviceCodec());
        registerCodec(Port.class, new PortCodec());
        registerCodec(ConnectPoint.class, new ConnectPointCodec());
        registerCodec(Link.class, new LinkCodec());
        registerCodec(Host.class, new HostCodec());
        registerCodec(HostLocation.class, new HostLocationCodec());
        registerCodec(HostToHostIntent.class, new HostToHostIntentCodec());
        registerCodec(PointToPointIntent.class, new PointToPointIntentCodec());
        registerCodec(Intent.class, new IntentCodec());
        registerCodec(ConnectivityIntent.class, new ConnectivityIntentCodec());
        registerCodec(FlowEntry.class, new FlowEntryCodec());
        registerCodec(TrafficTreatment.class, new TrafficTreatmentCodec());
        registerCodec(TrafficSelector.class, new TrafficSelectorCodec());
        registerCodec(Instruction.class, new InstructionCodec());
        registerCodec(Criterion.class, new CriterionCodec());
        registerCodec(Ethernet.class, new EthernetCodec());
        registerCodec(Constraint.class, new ConstraintCodec());
        registerCodec(Topology.class, new TopologyCodec());
        registerCodec(TopologyCluster.class, new TopologyClusterCodec());
        registerCodec(Path.class, new PathCodec());
        registerCodec(Network.class, new NetworkCodec());
        registerCodec(OvsPort.class, new OvsPortCodec());
        registerCodec(FixedIps.class, new FixedIpsCodec());
        registerCodec(AllowedAddressPair.class, new AllowedAddressPairCodec());
        registerCodec(ExtraDHCPOption.class, new ExtralDHCPOptionsCodec());
        registerCodec(VifDetail.class, new VifDetailCodec());
        registerCodec(SecurityGroup.class, new SecurityGroupsCodec());
        registerCodec(SecurityRule.class, new SecurityRuleCodec());
        registerCodec(Subnet.class, new SubnetCodec());
        registerCodec(AllocationPools.class, new AllocationPoolsCodec());
        log.info("Started");
    }

    @Deactivate
    public void deactivate() {
        codecs.clear();
        log.info("Stopped");
    }

    @Override
    public Set<Class<?>> getCodecs() {
        return ImmutableSet.copyOf(codecs.keySet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> JsonCodec<T> getCodec(Class<T> entityClass) {
        return codecs.get(entityClass);
    }

    @Override
    public <T> void registerCodec(Class<T> entityClass, JsonCodec<T> codec) {
        codecs.putIfAbsent(entityClass, codec);
    }

    @Override
    public void unregisterCodec(Class<?> entityClass) {
        codecs.remove(entityClass);
    }

}
