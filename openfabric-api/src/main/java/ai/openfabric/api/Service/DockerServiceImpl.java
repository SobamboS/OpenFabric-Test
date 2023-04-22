package ai.openfabric.api.Service;

import ai.openfabric.api.model.Worker;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.PortBinding;

import java.awt.*;
import java.io.File;
import java.util.Collections;

public class DockerServiceImpl implements DockerService{
    private DockerClient dockerClient;

    public void DockerService() {
        dockerClient = DefaultDockerClient.fromEnv().build();
    }

    public List<Container> getAllContainers() {
        return dockerClient.listContainersCmd().exec();
    }

    public Container getContainer(String containerId) {
        return dockerClient.inspectContainerCmd(containerId).exec();
    }

    public String createContainer(Worker worker) {
        // Build a new Docker image using the worker's information
        String imageName = "myimage:latest";
        String dockerfileLocation = "/path/to/Dockerfile";
        BuildImageResultCallback callback = new BuildImageResultCallback();
        dockerClient.buildImageCmd()
                .withDockerfile(new File(dockerfileLocation))
                .withTags(Collections.singleton(imageName))
                .exec(callback)
                .awaitImageId();

        // Create a new container using the image we just built
        CreateContainerResponse response = dockerClient.createContainerCmd(imageName)
                .withName(worker.getName())
                .withPortBindings(PortBinding.parse(worker.getPort()))
                .exec();
        String containerId = response.getId();

        // Start the container
        dockerClient.startContainerCmd(containerId).exec();

        return containerId;
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }
}

