package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "state", schema = "zherebukh", catalog = "")
public class StateEntity {
    private int id;
    private String nameOfState;
    private Collection<EventTicketEntity> eventTicketsById;

    public StateEntity(int id, String nameOfState) {
        this.id = id;
        this.nameOfState = nameOfState;
    }

    public StateEntity(String nameOfState) {
        this.nameOfState = nameOfState;
    }

    public StateEntity() {

    }

    @Id
    @Column(name = "id", nullable = false)
    public int getStateId() {
        return id;
    }

    public void setStateId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_state", nullable = true, length = 45)
    public String getNameOfState() {
        return nameOfState;
    }

    public void setNameOfState(String nameOfState) {
        this.nameOfState = nameOfState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateEntity that = (StateEntity) o;

        if (id != that.id) return false;
        if (nameOfState != null ? !nameOfState.equals(that.nameOfState) : that.nameOfState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameOfState != null ? nameOfState.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "stateByStateId")
    public Collection<EventTicketEntity> getEventTicketsById() {
        return eventTicketsById;
    }

    public void setEventTicketsById(Collection<EventTicketEntity> eventTicketsById) {
        this.eventTicketsById = eventTicketsById;
    }
}
