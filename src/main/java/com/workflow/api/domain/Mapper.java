package com.workflow.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author Nazim Uddin Asif
 * @since 1.0.0
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "mappers")
public class Mapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuration_id")
    private Configuration configuration;
}
