package hellohjpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_TEAM")
public class Team {

    @Id
    @GeneratedValue()
    private long id;

    @Column(name = "TEAM_NAME")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
