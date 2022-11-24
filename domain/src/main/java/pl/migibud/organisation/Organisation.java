package pl.migibud.organisation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Organisation {

    private Long id;
    private String name;
    private Status status;

    Organisation(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public enum Status{
        ACTIVE,INACTIVE
    }

    public void delete(){
        this.status=Status.INACTIVE;
    }


}
