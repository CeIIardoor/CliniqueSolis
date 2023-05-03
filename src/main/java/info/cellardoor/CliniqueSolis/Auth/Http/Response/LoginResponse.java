package info.cellardoor.CliniqueSolis.Auth.Http.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("role_name")
    private String role;
}