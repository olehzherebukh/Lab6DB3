package ua.lviv.iot.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class EventHasArtistEntityPK implements Serializable {
    private int eventEventId;
    private int artistArtistId;

    public EventHasArtistEntityPK(int eventEventId, int artistArtistId) {
        this.eventEventId = eventEventId;
        this.artistArtistId = artistArtistId;
    }

    public EventHasArtistEntityPK () {

    }

    @Column(name = "event_event_id", nullable = false)
    @Id
    public int getEventEventId() {
        return eventEventId;
    }

    public void setEventEventId(int eventEventId) {
        this.eventEventId = eventEventId;
    }

    @Column(name = "artist_artist_id", nullable = false)
    @Id
    public int getArtistArtistId() {
        return artistArtistId;
    }

    public void setArtistArtistId(int artistArtistId) {
        this.artistArtistId = artistArtistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventHasArtistEntityPK that = (EventHasArtistEntityPK) o;

        if (eventEventId != that.eventEventId) return false;
        if (artistArtistId != that.artistArtistId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventEventId;
        result = 31 * result + artistArtistId;
        return result;
    }
}
