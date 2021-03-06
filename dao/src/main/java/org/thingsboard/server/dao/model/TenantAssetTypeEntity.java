/**
 * Copyright © 2016-2017 The Thingsboard Authors
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

package org.thingsboard.server.dao.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import org.thingsboard.server.common.data.asset.TenantAssetType;
import org.thingsboard.server.common.data.id.TenantId;

import java.util.UUID;

import static org.thingsboard.server.dao.model.ModelConstants.*;

@Table(name = ASSET_TYPES_BY_TENANT_VIEW_NAME)
public class TenantAssetTypeEntity {

    @Transient
    private static final long serialVersionUID = -1268181161886910152L;

    @PartitionKey(value = 0)
    @Column(name = ASSET_TYPE_PROPERTY)
    private String type;

    @PartitionKey(value = 1)
    @Column(name = ASSET_TENANT_ID_PROPERTY)
    private UUID tenantId;

    public TenantAssetTypeEntity() {
        super();
    }

    public TenantAssetTypeEntity(TenantAssetType tenantAssetType) {
        this.type = tenantAssetType.getType();
        if (tenantAssetType.getTenantId() != null) {
            this.tenantId = tenantAssetType.getTenantId().getId();
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenantAssetTypeEntity that = (TenantAssetTypeEntity) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return tenantId != null ? tenantId.equals(that.tenantId) : that.tenantId == null;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TenantAssetTypeEntity{");
        sb.append("type='").append(type).append('\'');
        sb.append(", tenantId=").append(tenantId);
        sb.append('}');
        return sb.toString();
    }

    public TenantAssetType toTenantAssetType() {
        TenantAssetType tenantAssetType = new TenantAssetType();
        tenantAssetType.setType(type);
        if (tenantId != null) {
            tenantAssetType.setTenantId(new TenantId(tenantId));
        }
        return tenantAssetType;
    }
}
