package ai.openfabric.api.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class WorkerStatistics{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String  id;

    @Column(name = "worker_id")
    private Long workerId;

    @Column(name = "cpu_usage")
    private Double cpuUsage;

    @Column(name = "memory_usage")
    private Double memoryUsage;

    @Column(name = "network_in")
    private Double networkIn;

    @Column(name = "network_out")
    private Double networkOut;

    @Column(name = "disk_io")
    private Double diskIo;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;


}
