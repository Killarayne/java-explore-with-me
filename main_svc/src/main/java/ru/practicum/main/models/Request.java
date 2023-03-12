package ru.practicum.main.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.main.enums.RequestStatus;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "Requests", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String created;
    private Long event;
    private Long requester;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}
