package com.example.taskdoc.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "form_document")
public class FormDoc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Attachment file;

    // register number for example PT120
    private String registerNumber;

    private Date registerDate;

    // outgoing document number
    private String outgoingDocNumber;

    private Date outgoingDocDate;

    // forma dostavky
    @ManyToOne
    private FormDelivery formDelivery;

    @ManyToOne
    private Correspondent correspondent;

    private String theme, description;

    private Date expiredDoc;

    private boolean access = false, control = false;
}
