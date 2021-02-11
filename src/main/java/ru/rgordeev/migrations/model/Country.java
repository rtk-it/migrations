package ru.rgordeev.migrations.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "tbl_country", indexes = {
    @Index(name = "idx_country_name", columnList = "country_name")
})
@Getter
@Setter
public class Country {

    @Id
    @Column(name = "code", updatable = false, nullable = false)
    private String code;

    @Basic
    @Column(name = "country_name", nullable = false, columnDefinition = "text")
    private String name;
}
