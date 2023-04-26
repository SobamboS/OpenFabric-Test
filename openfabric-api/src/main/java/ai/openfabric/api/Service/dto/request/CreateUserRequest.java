package ai.openfabric.api.Service.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest{
    @NotBlank
    private String name;

    @NotBlank
    private String status;

    @NotBlank
    private String port;

}
