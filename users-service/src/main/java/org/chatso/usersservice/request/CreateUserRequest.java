package org.chatso.usersservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
    @JsonProperty("idp_uuid")
    private String idpUuid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("avatar_url")
    private String avatarUrl;
}
