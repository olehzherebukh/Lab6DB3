package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "artist", schema = "zherebukh", catalog = "")
public class ArtistEntity {
    private int artistId;
    private String firstNameOfArtist;
    private String lastNameOfArtist;
    private int sexSexId;
    private SexEntity sexBySexSexId;
    private Collection<EventHasArtistEntity> eventHasArtistsByArtistId;

    public ArtistEntity(String firstNameOfArtist, String lastNameOfArtist, int sexSexId) {
        this.firstNameOfArtist = firstNameOfArtist;
        this.lastNameOfArtist = lastNameOfArtist;
        this.sexSexId = sexSexId;
    }

    public ArtistEntity() {

    }

    public ArtistEntity(Integer artistId, String firstName, String lastName, Integer sexId) {
        this.artistId = artistId;
        this.firstNameOfArtist = firstName;
        this.lastNameOfArtist = lastName;
        this.sexSexId = sexId;
    }

    @Id
    @Column(name = "artist_id", nullable = false)
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "first_name_of_artist", nullable = false, length = 45)
    public String getFirstNameOfArtist() {
        return firstNameOfArtist;
    }

    public void setFirstNameOfArtist(String firstNameOfArtist) {
        this.firstNameOfArtist = firstNameOfArtist;
    }

    @Basic
    @Column(name = "last_name_of_artist", nullable = false, length = 45)
    public String getLastNameOfArtist() {
        return lastNameOfArtist;
    }

    public void setLastNameOfArtist(String lastNameOfArtist) {
        this.lastNameOfArtist = lastNameOfArtist;
    }

    @Basic
    @Column(name = "sex_sex_id", nullable = false)
    public int getSexSexId() {
        return sexSexId;
    }

    public void setSexSexId(int sexSexId) {
        this.sexSexId = sexSexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistEntity that = (ArtistEntity) o;

        if (artistId != that.artistId) return false;
        if (sexSexId != that.sexSexId) return false;
        if (firstNameOfArtist != null ? !firstNameOfArtist.equals(that.firstNameOfArtist) : that.firstNameOfArtist != null)
            return false;
        if (lastNameOfArtist != null ? !lastNameOfArtist.equals(that.lastNameOfArtist) : that.lastNameOfArtist != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artistId;
        result = 31 * result + (firstNameOfArtist != null ? firstNameOfArtist.hashCode() : 0);
        result = 31 * result + (lastNameOfArtist != null ? lastNameOfArtist.hashCode() : 0);
        result = 31 * result + sexSexId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sex_sex_id", referencedColumnName = "sex_id", nullable = false, insertable=false, updatable=false)
    public SexEntity getSexBySexSexId() {
        return sexBySexSexId;
    }

    public void setSexBySexSexId(SexEntity sexBySexSexId) {
        this.sexBySexSexId = sexBySexSexId;
    }

    @OneToMany(mappedBy = "artistByArtistArtistId")
    public Collection<EventHasArtistEntity> getEventHasArtistsByArtistId() {
        return eventHasArtistsByArtistId;
    }

    public void setEventHasArtistsByArtistId(Collection<EventHasArtistEntity> eventHasArtistsByArtistId) {
        this.eventHasArtistsByArtistId = eventHasArtistsByArtistId;
    }
}
