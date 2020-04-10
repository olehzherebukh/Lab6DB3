package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "event", schema = "zherebukh", catalog = "")
public class EventEntity {
    private int eventId;
    private String name;
    private String city;
    private String location;
    private int eventTypeEventTypeId;
    private EventTypeEntity eventTypeByEventTypeEventTypeId;
    private Collection<EventHasArtistEntity> eventHasArtistsByEventId;
    private Collection<EventTicketEntity> eventTicketsByEventId;

    public EventEntity(int eventId, String name, String city, String location, int eventTypeEventTypeId) {
        this.eventId = eventId;
        this.name = name;
        this.city = city;
        this.location = location;
        this.eventTypeEventTypeId = eventTypeEventTypeId;
    }

    public EventEntity(String name, String city, String location, int eventTypeEventTypeId) {
        this.name = name;
        this.city = city;
        this.location = location;
        this.eventTypeEventTypeId = eventTypeEventTypeId;
    }


    public EventEntity() {

    }

    @Id
    @Column(name = "event_id", nullable = false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 45)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "event_type_event_type_id", nullable = false, insertable=false, updatable=false)
    public int getEventTypeEventTypeId() {
        return eventTypeEventTypeId;
    }

    public void setEventTypeEventTypeId(int eventTypeEventTypeId) {
        this.eventTypeEventTypeId = eventTypeEventTypeId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (eventId != that.eventId) return false;
        if (eventTypeEventTypeId != that.eventTypeEventTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + eventTypeEventTypeId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "event_type_event_type_id", referencedColumnName = "event_type_id", nullable = false)
    public EventTypeEntity getEventTypeByEventTypeEventTypeId() {
        return eventTypeByEventTypeEventTypeId;
    }

    public void setEventTypeByEventTypeEventTypeId(EventTypeEntity eventTypeByEventTypeEventTypeId) {
        this.eventTypeByEventTypeEventTypeId = eventTypeByEventTypeEventTypeId;
    }

    @OneToMany(mappedBy = "eventByEventEventId")
    public Collection<EventHasArtistEntity> getEventHasArtistsByEventId() {
        return eventHasArtistsByEventId;
    }

    public void setEventHasArtistsByEventId(Collection<EventHasArtistEntity> eventHasArtistsByEventId) {
        this.eventHasArtistsByEventId = eventHasArtistsByEventId;
    }

    @OneToMany(mappedBy = "eventByEventEventId")
    public Collection<EventTicketEntity> getEventTicketsByEventId() {
        return eventTicketsByEventId;
    }

    public void setEventTicketsByEventId(Collection<EventTicketEntity> eventTicketsByEventId) {
        this.eventTicketsByEventId = eventTicketsByEventId;
    }
}
