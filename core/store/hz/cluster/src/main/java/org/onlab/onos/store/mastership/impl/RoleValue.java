package org.onlab.onos.store.mastership.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.onlab.onos.cluster.NodeId;
import org.onlab.onos.net.MastershipRole;
import org.onlab.onos.store.common.RoleInfo;

/**
 * A structure that holds node mastership roles associated with a
 * {@link DeviceId}. This structure needs to be locked through IMap.
 */
public class RoleValue {

    protected Map<MastershipRole, List<NodeId>> value = new HashMap<>();

    public RoleValue() {
        value.put(MastershipRole.MASTER, new LinkedList<NodeId>());
        value.put(MastershipRole.STANDBY, new LinkedList<NodeId>());
        value.put(MastershipRole.NONE, new LinkedList<NodeId>());
    }

    public Map<MastershipRole, List<NodeId>> value() {
        return Collections.unmodifiableMap(value);
    }

    public List<NodeId> nodesOfRole(MastershipRole type) {
        return value.get(type);
    }

    public NodeId get(MastershipRole type) {
        return value.get(type).isEmpty() ? null : value.get(type).get(0);
    }

    public boolean contains(MastershipRole type, NodeId nodeId) {
        return value.get(type).contains(nodeId);
    }

    /**
     * Associates a node to a certain role.
     *
     * @param type the role
     * @param nodeId the node ID of the node to associate
     */
    public void add(MastershipRole type, NodeId nodeId) {
        List<NodeId> nodes = value.get(type);

        if (!nodes.contains(nodeId)) {
            nodes.add(nodeId);
        }
    }

    /**
     * Removes a node from a certain role.
     *
     * @param type the role
     * @param nodeId the ID of the node to remove
     * @return
     */
    public boolean remove(MastershipRole type, NodeId nodeId) {
        List<NodeId> nodes = value.get(type);
        if (!nodes.isEmpty()) {
            return nodes.remove(nodeId);
        } else {
            return false;
        }
    }

    /**
     * Reassigns a node from one role to another. If the node was not of the
     * old role, it will still be assigned the new role.
     *
     * @param nodeId the Node ID of node changing roles
     * @param from the old role
     * @param to the new role
     */
    public void reassign(NodeId nodeId, MastershipRole from, MastershipRole to) {
        remove(from, nodeId);
        add(to, nodeId);
    }

    /**
     * Replaces a node in one role with another node. Even if there is no node to
     * replace, the new node is associated to the role.
     *
     * @param from the old NodeId to replace
     * @param to the new NodeId
     * @param type the role associated with the old NodeId
     */
    public void replace(NodeId from, NodeId to, MastershipRole type) {
        remove(type, from);
        add(type, to);
    }

    /**
     * Summarizes this RoleValue as a RoleInfo. Note that master and/or backups
     * may be empty, so the values should be checked for safety.
     *
     * @return the RoleInfo.
     */
    public RoleInfo roleInfo() {
        return new RoleInfo(
                get(MastershipRole.MASTER), nodesOfRole(MastershipRole.STANDBY));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<MastershipRole, List<NodeId>> el : value.entrySet()) {
            builder.append(el.getKey().toString()).append(": [");
            for (NodeId n : el.getValue()) {
                builder.append(n);
            }
            builder.append("]\n");
        }
        return builder.toString();
    }
}