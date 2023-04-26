package ai.openfabric.api.Service.dto.request;

import ai.openfabric.api.model.WorkerStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StopWorkerRequest{
    @NotBlank
    private String id;
    private WorkerStatus workerStatus;

}
