package ai.openfabric.api.repository;

import ai.openfabric.api.model.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, String> {

        List<Worker> findAll();
}
