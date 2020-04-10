package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "event_ticket", schema = "zherebukh", catalog = "")
public class EventTicketEntity {
    private int eventTicketId;
    private String date;
    private String time;
    private String place;
    private int eventEventId;
    private Byte isAvailable;
    private int stateId;
    private String sectorName;
    private int deliveryWayOfDeliveryId;
    private EventEntity eventByEventEventId;
    private StateEntity stateByStateId;
    private DeliveryEntity deliveryByDeliveryWayOfDeliveryId;

    public EventTicketEntity(int eventTicketId, String date, String time, String place, int eventEventId, Byte isAvailable, int stateId, String sectorName, int deliveryWayOfDeliveryId) {
        this.eventTicketId = eventTicketId;
        this.date = date;
        this.time = time;
        this.place = place;
        this.eventEventId = eventEventId;
        this.isAvailable = isAvailable;
        this.stateId = stateId;
        this.sectorName = sectorName;
        this.deliveryWayOfDeliveryId = deliveryWayOfDeliveryId;
    }

    public EventTicketEntity(String date, String time, String place, int eventEventId, Byte isAvailable, int stateId, String sectorName, int deliveryWayOfDeliveryId) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.eventEventId = eventEventId;
        this.isAvailable = isAvailable;
        this.stateId = stateId;
        this.sectorName = sectorName;
        this.deliveryWayOfDeliveryId = deliveryWayOfDeliveryId;
    }

    public EventTicketEntity() {

    }

    @Id
    @Column(name = "event_ticket_id", nullable = false)
    public int getEventTicketId() {
        return eventTicketId;
    }

    public void setEventTicketId(int eventTicketId) {
        this.eventTicketId = eventTicketId;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 45)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time", nullable = false, length = 45)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "place", nullable = false, length = 45)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "event_event_id", nullable = false)
    public int getEventEventId() {
        return eventEventId;
    }

    public void setEventEventId(int eventEventId) {
        this.eventEventId = eventEventId;
    }

    @Basic
    @Column(name = "is_available", nullable = true)
    public Byte getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Byte isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Basic
    @Column(name = "state_id", nullable = false)
    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "sector_name", nullable = true, length = 45)
    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    @Basic
    @Column(name = "delivery_way_of_delivery_id", nullable = false)
    public int getDeliveryWayOfDeliveryId() {
        return deliveryWayOfDeliveryId;
    }

    public void setDeliveryWayOfDeliveryId(int deliveryWayOfDeliveryId) {
        this.deliveryWayOfDeliveryId = deliveryWayOfDeliveryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTicketEntity that = (EventTicketEntity) o;

        if (eventTicketId != that.eventTicketId) return false;
        if (eventEventId != that.eventEventId) return false;
        if (stateId != that.stateId) return false;
        if (deliveryWayOfDeliveryId != that.deliveryWayOfDeliveryId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (isAvailable != null ? !isAvailable.equals(that.isAvailable) : that.isAvailable != null) return false;
        if (sectorName != null ? !sectorName.equals(that.sectorName) : that.sectorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventTicketId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + eventEventId;
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);
        result = 31 * result + stateId;
        result = 31 * result + (sectorName != null ? sectorName.hashCode() : 0);
        result = 31 * result + deliveryWayOfDeliveryId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "event_event_id", referencedColumnName = "event_id", nullable = false, insertable=false, updatable=false)
    public EventEntity getEventByEventEventId() {
        return eventByEventEventId;
    }

    public void setEventByEventEventId(EventEntity eventByEventEventId) {
        this.eventByEventEventId = eventByEventEventId;
    }

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    public StateEntity getStateByStateId() {
        return stateByStateId;
    }

    public void setStateByStateId(StateEntity stateByStateId) {
        this.stateByStateId = stateByStateId;
    }

    @ManyToOne
    @JoinColumn(name = "delivery_way_of_delivery_id", referencedColumnName = "way_of_delivery_id", nullable = false, insertable=false, updatable=false)
    public DeliveryEntity getDeliveryByDeliveryWayOfDeliveryId() {
        return deliveryByDeliveryWayOfDeliveryId;
    }

    public void setDeliveryByDeliveryWayOfDeliveryId(DeliveryEntity deliveryByDeliveryWayOfDeliveryId) {
        this.deliveryByDeliveryWayOfDeliveryId = deliveryByDeliveryWayOfDeliveryId;
    }
}
