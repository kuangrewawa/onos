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
package org.onosproject.netvirt;

import java.util.List;

import org.onosproject.net.DeviceId;

/**
 * Service for supporting multiple table by OpenFlow 1.3.
 */
public interface MultiTableSevice {
    /**
     * Program local unicast flow rule. Match TunID and Destination dMAc Action:
     * Output Port
     */
    void programLocalOut(DeviceId dpid, String segmentationId, Long outPort,
                         String destMac);

    /**
     * Program Arp response flow rule. Match Ethernet Type and TunID Action:
     * Output to Contoller
     */
    void programArpResponse(DeviceId dpid, String segmentationId);

    /**
     * Program get out of tunnel flow rule. Match Inport and TunnelID Action:
     * GOTO Next Table
     */
    void programTunnelOut(DeviceId dpid, String segmentationId, Long outPort,
                          String destMac);

    /**
     * Program get into tunnel flow rule. Match TunnelID and DestMac Action:
     * GOTO Next Table
     */
    void programTunnelIn(DeviceId dpid, String segmentationId, Long inPort,
                         String destMac);

    /**
     * Program get into pipeline flow rule. Match SrcMac and local in Port
     * Action: set Tunnel ID GOTO Next Table
     */
    void programLocalIn(DeviceId dpid, String segmentationId, Long inPort,
                        String srcMac);

    /**
     * Program local bcast rules pipeline flow rule. Match SrcMac and
     * segmentationId Action: GOTO Next Table outPort
     */
    void programLocalBcastRules(DeviceId dpid, String segmentationId,
                                Long outPort, List<Long> allports);

    /**
     * Program remote bcast rules pipeline flow rule. Match SrcMac and
     * segmentationId Action: GOTO Next Table outPort
     */
    void programTunnelFloodOut(DeviceId dpid, String segmentationId,
                               Long ofPortOut, List<Long> localports);

    /**
     * Program local unicast flow rule. Match TunID and Destination dMAc Action:
     * Output Port
     */
    void programLocalOut(DeviceId dpid, String segmentationId, Long outPort,
                         String destMac, boolean write);

    /**
     * Program Arp response flow rule. Match Ethernet Type and TunID Action:
     * Output to Contoller
     */
    void programArpResponse(DeviceId dpid, String segmentationId, boolean write);

    /**
     * Program get out of tunnel flow rule. Match Inport and TunnelID Action:
     * GOTO Next Table
     */
    void programTunnelOut(DeviceId dpid, String segmentationId, Long outPort,
                          String destMac, boolean write);

    /**
     * Program get into tunnel flow rule. Match TunnelID and DestMac Action:
     * GOTO Next Table
     */
    void programTunnelIn(DeviceId dpid, String segmentationId, Long inPort,
                         String destMac, boolean write);

    /**
     * Program get into pipeline flow rule. Match SrcMac and local in Port
     * Action: set Tunnel ID GOTO Next Table
     */
    void programLocalIn(DeviceId dpid, String segmentationId, Long inPort,
                        String srcMac, boolean write);

    /**
     * Program local bcast rules pipeline flow rule. Match SrcMac and
     * segmentationId Action: GOTO Next Table outPort
     */
    void programLocalBcastRules(DeviceId dpid, String segmentationId,
                                Long outPort, List<Long> allports, boolean write);

    /**
     * Program remote bcast rules pipeline flow rule. Match SrcMac and
     * segmentationId Action: GOTO Next Table outPort
     */
    void programTunnelFloodOut(DeviceId dpid, String segmentationId,
                               Long ofPortOut, List<Long> localports, boolean write);
}
