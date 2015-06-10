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
package org.onosproject.net.subnet;

import org.onosproject.net.device.DeviceProvider;
import org.onosproject.net.provider.ProviderService;

/**
 * Service through which device providers can inject device information into the
 * core.
 */
public interface SubnetsProviderService extends ProviderService<DeviceProvider> {

    // TODO: define suspend and remove actions on the mezzanine administrative
    // API
    /*
     *
     */
    void listSubnets();

    /*
     *
     */
    void showSubnet();

    /*
     *
     */
    void createSubnets();

    /*
    *
    */
    void updateSubnet();

    /*
     *
     */
    void deleteSubnet();

}
