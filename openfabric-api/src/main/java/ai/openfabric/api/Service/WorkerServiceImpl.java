package ai.openfabric.api.Service;

import ai.openfabric.api.Exception.GenericException;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.Service.dto.request.WorkerRequest;
import ai.openfabric.api.Service.dto.response.WorkerResponse;
import ai.openfabric.api.repository.WorkerRepository;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.InvocationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ai.openfabric.api.model.WorkerStatus.RUNNING;
import static ai.openfabric.api.model.WorkerStatus.STOPPED;


@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService{
    @Autowired
    private WorkerRepository workerRepository;

    private DockerClient dockerClient;

    @Override
    public Page<WorkerResponse> listOfWorkers(int pageNumber,int pageSize) {

        Pageable pageable =PageRequest.of(pageNumber, pageSize);

        Page<Worker> workers = workerRepository.findAll(pageable);
        List<WorkerResponse> workerResponses = new ArrayList<>();

        for (Worker worker: workers) {
            workerResponses.add(
                    WorkerResponse.builder()
                            .id(worker.getId())
                            .name((worker.getName()))
                            .image(worker.getImage())
                            .port((worker.getPort()))
                            .command(worker.getCommand())
                            .workerStatus(worker.getWorkerStatus())
                            .build()
            );
        }
        return new PageImpl<>(workerResponses, pageable, workers.getTotalElements());
    }

    @Override
    public Worker getWorker(String id){
        return workerRepository.findById(id)
                .orElseThrow(( ) -> new EntityNotFoundException("Worker not found with id " + id));
    }

    @Override
    public String  startWorker(WorkerRequest workerRequest)  {
        String containerID = dockerClient.createContainerCmd("my_image")
                .withName(workerRequest.getName())
                .withExposedPorts(ExposedPort.tcp(workerRequest.getPort()))
                .exec().getId();
        dockerClient.startContainerCmd(containerID).exec();
        Worker worker = new Worker(
                workerRequest.getName(),
                workerRequest.getPort(),
                RUNNING);
        workerRepository.save(worker);
        return String.format("Worker %s on port %s started",workerRequest.getName(), workerRequest.getPort() );
    }



    @Override
    public String stopWorker(String id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) {
           throw new  GenericException(String.format("Worker with %s not found", id));
        }
        Worker worker = optionalWorker.get();
        dockerClient.stopContainerCmd(worker.getId());
        worker.setWorkerStatus(STOPPED);
        workerRepository.save(worker);
        return String.format(" Worker %s stopped",id);
    }

    @Override
    public Statistics getWorkerStatistics(String id) throws IOException{
        InvocationBuilder.AsyncResultCallback<Statistics> callback = new InvocationBuilder.AsyncResultCallback<>();
        dockerClient.statsCmd(id).exec(callback);
        Statistics stats = callback.awaitResult();
        callback.close();
        return stats;
    }


}
