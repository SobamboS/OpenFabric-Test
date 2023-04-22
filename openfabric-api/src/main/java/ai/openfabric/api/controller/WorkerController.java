package ai.openfabric.api.controller;

import ai.openfabric.api.Service.DockerService;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.Service.WorkerService;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.model.dto.CreateUserRequest;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${node.api.path}/worker")
public class WorkerController {


    @PostMapping(path = "/hello")
    public @ResponseBody String hello(@RequestBody String name) {
        return "Hello!" + name;
    }

        @Autowired
        private WorkerService workerService;

        @Autowired
        private DockerService dockerService;

        @PostMapping("/createWorker")
        public Worker createWorker(@RequestBody CreateUserRequest createUserRequest) {
            return workerService.createWorker(createUserRequest);
        }

        @GetMapping("/listWorkers")
        public Page<Worker> listWorkers(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size) {
            return workerService.listWorkers(PageRequest.of(page, size));
        }

        @GetMapping("/{id}")
        public Worker getWorker(@PathVariable String  id) {
            return workerService.getWorker(id);
        }

        @PostMapping("/{id}/start")
        public void startWorker(@PathVariable String id) {
            Worker worker = workerService.getWorker(id);
            dockerService.startWorkerContainer(worker);
        }

        @PostMapping("/{id}/stop")
        public void stopWorker(@PathVariable String id){
    Worker worker = workerService.getWorker(id);
        dockerService.stopWorkerContainer(worker);
}

    @GetMapping("/{id}/info")
    public Map<String, Object> getWorkerInfo(@PathVariable String id) {
        Worker worker = workerService.getWorker(id);
        return dockerService.getWorkerContainerInfo(worker);
    }

    @GetMapping("/{id}/stats")
    public List<WorkerStatistics> getWorkerStats(@PathVariable String  id) {
        Worker worker = workerService.getWorker(id);
        return workerService.getWorkerStatistics(worker);
    }
}




    }
