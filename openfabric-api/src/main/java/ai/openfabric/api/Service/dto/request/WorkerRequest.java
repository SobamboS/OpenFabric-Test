package ai.openfabric.api.Service.dto.request;

import ai.openfabric.api.model.WorkerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class WorkerRequest{
   @NotBlank
   private String name;
   @NotBlank
   private int port;
   @NotBlank
   private WorkerStatus workerStatus;
}
