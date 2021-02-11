package ru.rgordeev.migrations.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "tbl_language", indexes = {
    @Index(name = "idx_language_name", columnList = "name")
})
@Getter
@Setter
public class Language {

    @Id
    @Column(name = "code", updatable = false, nullable = false)
    private String code;

    @Basic
    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @ManyToMany(mappedBy = "languages", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

}
