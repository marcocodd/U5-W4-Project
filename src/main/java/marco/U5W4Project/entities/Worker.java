package marco.U5W4Project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "worker")
@Getter
@ToString
public class Worker {
    @Id
    @GeneratedValue
    private long id;
    @Setter
    private String username;
    @Setter
    private String name;
    @Setter
    private String surname;
    @Setter
    private String email;
    @Setter
    private String avatar;

    @OneToMany(mappedBy = "worker")
    @JsonIgnore
    private List<Device> device;

    public Worker() {
    }

    public Worker(String username, String name, String surname, String email, String avatar) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = avatar;
    }

}
