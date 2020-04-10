package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "event_type", schema = "zherebukh", catalog = "")
public class EventTypeEntity {
    private int eventTypeId;
    private String typeOfEvent;
    private Collection<EventEntity> eventsByEventTypeId;

    public EventTypeEntity(int eventTypeId, String typeOfEvent) {
        this.eventTypeId = eventTypeId;
        this.typeOfEvent = typeOfEvent;
    }

    public EventTypeEntity(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public EventTypeEntity() {

    }

    @Id
    @Column(name = "event_type_id", nullable = false)
    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    @Basic
    @Column(name = "type_of_event", nullable = false, length = 45)
    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTypeEntity that = (EventTypeEntity) o;

        if (eventTypeId != that.eventTypeId) return false;
        if (typeOfEvent != null ? !typeOfEvent.equals(that.typeOfEvent) : that.typeOfEvent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventTypeId;
        result = 31 * result + (typeOfEvent != null ? typeOfEvent.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "eventTypeByEventTypeEventTypeId")
    public Collection<EventEntity> getEventsByEventTypeId() {
        return eventsByEventTypeId;
    }

    public void setEventsByEventTypeId(Collection<EventEntity> eventsByEventTypeId) {
        this.eventsByEventTypeId = eventsByEventTypeId;
    }
}
