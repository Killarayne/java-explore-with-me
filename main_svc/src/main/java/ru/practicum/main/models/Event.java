package ru.practicum.main.models;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.main.enums.EventState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "Events", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String annotation;
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @Column(name = "confirmed_Requests")
    private int confirmedRequests;
    @Column(name = "created_On")
    private String createdOn;
    private String description;
    @Column(name = "event_date")
    private String eventDate;
    @OneToOne
    @JoinColumn(name = "initiator_id", referencedColumnName = "id")
    private User initiator;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
    private Boolean paid;
    @Column(name = "participant_Limit")
    private int participantLimit;
    @Column(name = "published_On")
    private String publishedOn;
    @Column(name = "request_Moderation")
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private EventState state;
    private String title;
    private Long views;
    @Transient
    private final String datePattern = "yyyy-MM-dd HH:mm:ss";
    @Transient
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

    public Event(Long id, String annotation, Category category, int confirmedRequests, String createdOn, String description, String eventDate, User initiator, Location location, Boolean paid, Integer participantLimit, String publishedOn, Boolean requestModeration, EventState eventState, String title, Long views) {
        this.annotation = annotation;
        this.category = category;
        this.confirmedRequests = confirmedRequests;
        if (createdOn == null) {
            this.createdOn = LocalDateTime.now().format(dateFormatter);
        } else {
            this.createdOn = createdOn;
        }

        this.description = description;
        this.eventDate = eventDate;
        this.id = id;
        this.initiator = initiator;
        this.location = location;
        this.paid = paid;
        this.participantLimit = participantLimit;
        this.publishedOn = publishedOn;
        if (requestModeration == null) {
            this.requestModeration = true;
        } else {
            this.requestModeration = requestModeration;
        }
        if (eventState == null) {
            this.state = EventState.PENDING;
        } else {
            this.state = eventState;
        }
        this.title = title;
        this.views = views;
    }

    protected Event() {
    }
}
