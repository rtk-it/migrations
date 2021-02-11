package ru.rgordeev.migrations.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "tbl_user", indexes = {
    @Index(name = "idx_user_name", columnList = "name"),
    @Index(name = "idx_user_last_name", columnList = "last_name")
})
@Getter
@Setter
public class User {

    @Id
    @GenericGenerator(
        name = "user_id_gen",
        strategy = "enhanced-sequence",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_id_seq"),
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "50")
        }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Basic
    @Column(name = "login", nullable = false)
    private String login;

    @Basic
    @Column(name = "bio", columnDefinition = "text")
    private String bio;

    @Basic
    @Column(name = "status")
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_country_id"))
    private Country country;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "tbl_user_language",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id"),
        foreignKey = @ForeignKey(name = "fk_user_language_user_id"),
        inverseForeignKey = @ForeignKey(name = "fk_user_language_language_id")
    )
    private Set<Language> languages = new HashSet<>();;

    public void addLanguage(Language language) {
        languages.add(language);
        language.getUsers().add(this);
    }

    public void removeTag(Language language) {
        languages.remove(language);
        language.getUsers().remove(this);
    }

}
