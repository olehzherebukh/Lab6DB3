package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "delivery", schema = "zherebukh", catalog = "")
public class DeliveryEntity {
    private int wayOfDeliveryId;
    private String wayOfDelivery;
    private Collection<EventTicketEntity> eventTicketsByWayOfDeliveryId;

    public DeliveryEntity(int wayOfDeliveryId, String wayOfDelivery) {
        this.wayOfDeliveryId = wayOfDeliveryId;
        this.wayOfDelivery = wayOfDelivery;
    }

    public DeliveryEntity(String wayOfDelivery) {
        this.wayOfDelivery = wayOfDelivery;
    }


    public DeliveryEntity() {

    }

    @Id
    @Column(name = "way_of_delivery_id", nullable = false)
    public int getWayOfDeliveryId() {
        return wayOfDeliveryId;
    }

    public void setWayOfDeliveryId(int wayOfDeliveryId) {
        this.wayOfDeliveryId = wayOfDeliveryId;
    }

    @Basic
    @Column(name = "way_of_delivery", nullable = true, length = 45)
    public String getWayOfDelivery() {
        return wayOfDelivery;
    }

    public void setWayOfDelivery(String wayOfDelivery) {
        this.wayOfDelivery = wayOfDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryEntity that = (DeliveryEntity) o;

        if (wayOfDeliveryId != that.wayOfDeliveryId) return false;
        if (wayOfDelivery != null ? !wayOfDelivery.equals(that.wayOfDelivery) : that.wayOfDelivery != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wayOfDeliveryId;
        result = 31 * result + (wayOfDelivery != null ? wayOfDelivery.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "deliveryByDeliveryWayOfDeliveryId")
    public Collection<EventTicketEntity> getEventTicketsByWayOfDeliveryId() {
        return eventTicketsByWayOfDeliveryId;
    }

    public void setEventTicketsByWayOfDeliveryId(Collection<EventTicketEntity> eventTicketsByWayOfDeliveryId) {
        this.eventTicketsByWayOfDeliveryId = eventTicketsByWayOfDeliveryId;
    }
}
