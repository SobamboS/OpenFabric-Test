package ai.openfabric.api.Service.dto.response;

import ai.openfabric.api.model.WorkerStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkerResponse{
    private String id;

    private String name;

    private String image;

    private String command;

    private WorkerStatus workerStatus;

    private int port;
}
