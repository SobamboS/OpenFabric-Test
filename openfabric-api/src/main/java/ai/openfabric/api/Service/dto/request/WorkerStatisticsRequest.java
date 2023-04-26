package ai.openfabric.api.Service.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class WorkerStatisticsRequest{
        @NotBlank(message = "This field is required")
        private String workerName;
        @NotBlank(message = "This field is required")
        private Double cpuUsage;
        @NotBlank(message = "This field is required")
        private Double memoryUsage;
}
