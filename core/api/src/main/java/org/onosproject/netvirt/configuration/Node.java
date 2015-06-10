package org.onosproject.netvirt.configuration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Describe a generic network element in multiple SDNs technologies. A Node is
 * identified by the pair (NodeType, NodeID), the nodetype are needed in order
 * to further specify the nodeID
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class Node implements Serializable {
        private static final long serialVersionUID = 1L;

        // This is the identity of the Node a (Type,ID) pair!, the full
        // essence of this class.
        private Object nodeID;
        public Object getNodeID() {
            return nodeID;
        }

        public void setNodeID(Object nodeID) {
            this.nodeID = nodeID;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public static long getSerialversionuid() {
            return serialVersionUID;
        }

        private String nodeType;

        /*
         * Private constructor used for JAXB mapping
         */
        private Node() {
                this.nodeID = null;
                this.nodeType = null;
        }

        /*
         * Constructor for the Node objects, it validate the input so if the ID
         * passed is not of the type expected accordingly to the type an exception
         * is raised.
         *
         * @param nodeType
         *            Type of the node we are building
         * @param id
         *            ID used by the SDN technology to identify the node
         *
         */
         public Node(String nodeType, Object id) {
                this.nodeType = nodeType;
                this.nodeID = id;
         }
         public Node(String nodeType) {
             this.nodeType = nodeType;
         }
        /*
         * Copy Constructor for the Node objects.
         *
         * @param src
         *            type of nodes to copy from
         *
         */
         public Node(Node src) {
                if (src != null) {
                        this.nodeType = src.getType();
                        this.nodeID = src.getID();
                }
         }

        /*
         * getter for node type
         *
         *
         * @return The node Type for this Node object
         */
        @XmlElement(name = "type")
        public String getType() {
            return this.nodeType;
        }

        /*
        * Private setter for nodeType to be called by JAXB not by anyone else, Node
        * is immutable
        *
        * @param type
        *            of node to be set
        */
        @SuppressWarnings("unused")
        private void setType(String type) {
            this.nodeType = type;
        }

        /*
         * getter for node ID
         *
         *
         * @return The node ID for this Node object
         */
        public Object getID() {
            return this.nodeID;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((nodeID == null) ? 0 : nodeID.hashCode());
            result = prime * result
                    + ((nodeType == null) ? 0 : nodeType.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Node other = (Node) obj;
            if (nodeID == null) {
                if (other.nodeID != null) {
                    return false;
                }
                } else if (!nodeID.equals(other.nodeID)) {
                    return false;
                }
            if (nodeType == null) {
                if (other.nodeType != null) {
                    return false;
                }
                } else if (!nodeType.equals(other.nodeType)) {
                    return false;
                }
            return true;
            }
}