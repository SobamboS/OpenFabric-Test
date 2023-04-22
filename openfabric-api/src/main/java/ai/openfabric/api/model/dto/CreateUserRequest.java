package ai.openfabric.api.model.dto;

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
