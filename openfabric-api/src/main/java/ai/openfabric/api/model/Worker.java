package ai.openfabric.api.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Worker extends Datable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    private String Id;

    private String name;

    private String status;
    private int port;
    private String image;
    private String command;
    private WorkerStatus workerStatus;

    public Worker(String name,String status,
                  int port,String image,String command){
        this.name=name;
        this.status=status;
        this.port=port;
        this.image=image;
        this.command=command;
    }



    public Worker(String name,int port,WorkerStatus running){
        super();
    }
}
