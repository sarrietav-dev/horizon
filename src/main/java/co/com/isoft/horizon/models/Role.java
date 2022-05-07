package co.com.isoft.horizon.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("Admin")
    ADMIN,
    @JsonProperty("Resident")
    RESIDENT,
    @JsonProperty("Proprietary")
    PROPRIETARY
}
