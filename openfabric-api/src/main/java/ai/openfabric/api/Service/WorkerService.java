package ai.openfabric.api.Service;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.model.dto.CreateUserRequest;

import java.util.List;


public interface WorkerService{

    List<Worker> getAllWorkers();
    Worker getWorker(String id);
    Worker createWorker(CreateUserRequest createUserRequest);
    Worker updateWorker(String id, Worker workerDetails);
    void deleteWorker(String id);
    List<WorkerStatistics> getWorkerStatistics(Worker worker);

    }


