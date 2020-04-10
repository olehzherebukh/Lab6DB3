package ua.lviv.iot.domain;

import javax.persistence.*;

@Entity
@Table(name = "event_has_artist", schema = "zherebukh", catalog = "")
@IdClass(EventHasArtistEntityPK.class)
public class EventHasArtistEntity extends EventHasArtistEntityPK {
    private int eventEventId;
    private int artistArtistId;
    private EventEntity eventByEventEventId;
    private ArtistEntity artistByArtistArtistId;

    public EventHasArtistEntity(int eventEventId, int artistArtistId) {
        this.eventEventId = eventEventId;
        this.artistArtistId = artistArtistId;
    }

    public EventHasArtistEntity() {

    }

    @Id
    @Column(name = "event_event_id", nullable = false)
    public int getEventEventId() {
        return eventEventId;
    }

    public void setEventEventId(int eventEventId) {
        this.eventEventId = eventEventId;
    }

    @Id
    @Column(name = "artist_artist_id", nullable = false)
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

        EventHasArtistEntity that = (EventHasArtistEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "event_event_id", referencedColumnName = "event_id", nullable = false, insertable=false, updatable=false)
    public EventEntity getEventByEventEventId() {
        return eventByEventEventId;
    }

    public void setEventByEventEventId(EventEntity eventByEventEventId) {
        this.eventByEventEventId = eventByEventEventId;
    }

    @ManyToOne
    @JoinColumn(name = "artist_artist_id", referencedColumnName = "artist_id", nullable = false, insertable=false, updatable=false)
    public ArtistEntity getArtistByArtistArtistId() {
        return artistByArtistArtistId;
    }

    public void setArtistByArtistArtistId(ArtistEntity artistByArtistArtistId) {
        this.artistByArtistArtistId = artistByArtistArtistId;
    }
}
