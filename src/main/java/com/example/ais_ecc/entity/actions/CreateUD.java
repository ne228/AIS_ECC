package com.example.ais_ecc.entity.actions;


import com.example.ais_ecc.entity.User;

import javax.persistence.*;
import java.util.List;


@Entity
//@Table("createUD")
public class CreateUD extends Action {
    public String Occasion; // Повод
    public String Base;  // Основание
    public String Article;  // Статья

    @ManyToMany
    public List<User> Applicant; // Заявитель
    public String TextOfApplicant; // Текст заявления
    public String Fabula; // Фабула
    @ManyToMany
    public List<User> Suspect; // Поозреваемый

    @ManyToOne
//    @JoinColumn(name = "investigator_id")
    public User Investigator; // Следователь


    public String getOccasion() {
        return Occasion;
    }

    public void setOccasion(String occasion) {
        Occasion = occasion;
    }

    public String getBase() {
        return Base;
    }

    public void setBase(String base) {
        Base = base;
    }

    public String getArticle() {
        return Article;
    }

    public void setArticle(String article) {
        Article = article;
    }

    public List<User> getApplicant() {
        return Applicant;
    }

    public void setApplicant(List<User> applicant) {
        Applicant = applicant;
    }

    public String getTextOfApplicant() {
        return TextOfApplicant;
    }

    public void setTextOfApplicant(String textOfApplicant) {
        TextOfApplicant = textOfApplicant;
    }

    public String getFabula() {
        return Fabula;
    }

    public void setFabula(String fabula) {
        Fabula = fabula;
    }

    public List<User> getSuspect() {
        return Suspect;
    }

    public void setSuspect(List<User> suspect) {
        Suspect = suspect;
    }

    public User getInvestigator() {
        return Investigator;
    }

    public void setInvestigator(User investigator) {
        Investigator = investigator;
    }
}
