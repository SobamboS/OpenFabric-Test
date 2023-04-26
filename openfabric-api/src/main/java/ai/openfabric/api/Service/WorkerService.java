package ai.openfabric.api.Service;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.Service.dto.request.WorkerRequest;
import ai.openfabric.api.Service.dto.response.WorkerResponse;
import com.github.dockerjava.api.model.Statistics;
import org.springframework.data.domain.Page;

import java.io.IOException;


public interface WorkerService{

    Page<WorkerResponse> listOfWorkers(int pageNumber,int pageSize);
    Worker getWorker(String id);

    String startWorker(WorkerRequest workerRequest);
    String stopWorker(String id);
     Statistics getWorkerStatistics(String id)throws IOException;


    }


