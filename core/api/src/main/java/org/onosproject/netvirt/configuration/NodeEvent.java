package org.onosproject.netvirt.configuration;

import org.onosproject.event.AbstractEvent;

public class NodeEvent extends AbstractEvent<NodeEvent.Type, Node> {
    public NodeEvent(Type type, Node node) {
        super(type, node);
    }

    public enum Type {
        /**
         * Signifies that a new node has been added.
         */
        NODE_ADDED,

        /**
         * Signifies that a node has been removed.
         */
        NODE_REMOVED

    }
}
