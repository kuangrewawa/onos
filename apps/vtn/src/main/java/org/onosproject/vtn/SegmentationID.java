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
package org.onosproject.vtn;

import java.util.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Immutable representation of a Segmentation identity.
 */
public final class SegmentationID {

    private final String segmentationid;

    // Public construction is prohibited
    private SegmentationID(String segmentationid) {
        this.segmentationid = segmentationid;
    }

    /**
     * Creates a network id using the segmentationid.
     *
     * @param segmentationid network String
     * @return SegmentationId
     */
    public static SegmentationID segmentationID(String segmentationid) {
        return new SegmentationID(segmentationid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentationid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SegmentationID) {
            final SegmentationID that = (SegmentationID) obj;
            return this.getClass() == that.getClass()
                    && Objects.equals(this.segmentationid, that.segmentationid);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("segmentationid", segmentationid)
                .toString();
    }

}
