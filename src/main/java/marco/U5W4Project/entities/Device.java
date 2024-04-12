package marco.U5W4Project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "device")
@Getter
@ToString
public class Device {
    @Id
    @GeneratedValue
    private long id;
    @Setter
    private String type;
    @Setter
    private boolean available;
    @Setter
    private boolean assigned;
    @Setter
    private boolean maintenance;
    @Setter
    private boolean retired;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    @Setter
    private Worker worker;


    public Device() {
    }

    public Device(String type, boolean available, boolean assigned, boolean maintenance, boolean retired) {
        this.type = type;
        this.available = available;
        this.assigned = assigned;
        this.maintenance = maintenance;
        this.retired = retired;
    }

    public Device(String type, boolean available, boolean assigned, boolean maintenance, boolean retired, Worker worker) {
        this.type = type;
        this.available = available;
        this.assigned = assigned;
        this.maintenance = maintenance;
        this.retired = retired;
        this.worker = worker;
    }
}
