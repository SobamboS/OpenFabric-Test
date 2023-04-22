package ai.openfabric.api.Service;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.model.dto.CreateUserRequest;
import ai.openfabric.api.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService{
    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getWorker(String id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found with id " + id));
    }

    @Override
    public Worker createWorker(CreateUserRequest createUserRequest) {
    Worker worker = new Worker(
        createUserRequest.getName(),
        createUserRequest.getStatus(),
        createUserRequest.getPort()
    );
        return workerRepository.save(worker);
    }

    @Override
    public Worker updateWorker(String id, Worker workerDetails) {
        Worker worker = getWorker(id);
        worker.setName(workerDetails.getName());
        worker.setPort(workerDetails.getPort());
        worker.setStatus(worker.getStatus());
        return workerRepository.save(worker);
    }


   @Override
    public void deleteWorker(String id) {
        workerRepository.deleteById(id);
    }

    @Override
    public List<WorkerStatistics> getWorkerStatistics(Worker worker) {
        return worker.getStatistics();
    }

    // Add any other methods you need to interact with the Worker entity
}
