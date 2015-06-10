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

public enum MultiTable {

    CLASSIIER((Integer) 0, "org.onosproject.netvirt.ClassifierTable"),
    ETHER((Integer) 10, "org.onosproject.netvirt.EtherTable"),
    ROUTING((Integer) 20, "org.onosproject.netvirt.RoutingTable"),
    IP_FORWARDING((Integer) 30, "org.onosproject.netvirt.IpTable"),
    MAC_FORWARDING((Integer) 40, "org.onosproject.netvirt.MacTable");

    Integer tableId;
    String classT;

    private MultiTable(Integer tableId, String classT) {
        this.tableId = tableId;
        this.classT = classT;
    }

    public Integer getTable() {
        return tableId;
    }

    public String getClassT() {
        return classT;
    }
}
