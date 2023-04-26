package ai.openfabric.api.controller;

import ai.openfabric.api.Service.WorkerService;
import ai.openfabric.api.Service.dto.request.WorkerRequest;
import ai.openfabric.api.Service.dto.response.WorkerResponse;
import ai.openfabric.api.model.Worker;
import com.github.dockerjava.api.model.Statistics;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("${node.api.path}/worker")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping(path = "/hello")
    public @ResponseBody String hello(@RequestBody String name) {
        return "Hello!" + name;
    }

   @GetMapping(path="/listOfWorker")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public Page<WorkerResponse> listOfWorkers(@RequestBody int pageNumber,int pageSize){
        return workerService.listOfWorkers(pageNumber,pageSize);
   }

    @PostMapping(path = "/startWorker")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String startWorker(@RequestBody WorkerRequest workerRequest){
        return workerService.startWorker(workerRequest);
    }

    @PostMapping(path = "/stopWorker")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String stopWorker(@RequestBody String id) {
        return workerService.stopWorker(id);
    }

    @GetMapping(path = "/workerStats")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Statistics getWorkerStatistics(@RequestParam String id) throws IOException{
        return workerService.getWorkerStatistics(id);
    }
    @GetMapping(path = "/getWorker")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Worker getWorker(@RequestParam String id){
        return workerService.getWorker(id);
    }


}





