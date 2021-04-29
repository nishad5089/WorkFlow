package com.workflow.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nazim Uddin Asif
 * @since 1.0.0
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "configurations")
public class Configuration {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "configuration_id")
    private List<Checker> checker;

    private String department;

    private String label;

    @Enumerated(EnumType.STRING)
    private LabelLogic labelLogic;

    private int maxCheckerNo;
}
