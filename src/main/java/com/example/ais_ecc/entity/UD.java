package com.example.ais_ecc.entity;

import com.example.ais_ecc.entity.actions.Action;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class UD {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    public java.util.Date Date;
    public String Article;

    //@OneToMany(mappedBy = "ud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL)
    public List<Action> Actions;

    @ManyToOne
    @JoinColumn(name = "investigator_id")
    public User Investigator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getInvestigator() {
        return Investigator;
    }

    public void setInvestigator(User investigator) {
        Investigator = investigator;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getArticle() {
        return Article;
    }

    public void setArticle(String article) {
        Article = article;
    }

    public List<Action> getActions() {
        return Actions;
    }

    public void setActions(List<Action> actionList) {
        this.Actions = actionList;
    }
}
