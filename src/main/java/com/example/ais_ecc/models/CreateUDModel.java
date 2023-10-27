package com.example.ais_ecc.models;

import com.example.ais_ecc.entity.User;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

public class CreateUDModel {
    public String Occasion; // Повод
    public String Base;  // Основание
    public String Article;  // Статья
    public List<String> ApplicantIds; // Заявитель
    public String TextOfApplicant; // Текст заявления
    public String Fabula; // Фабула
    public List<String> SuspectIds; // Поозреваемый
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

    public List<String> getApplicantIds() {
        return ApplicantIds;
    }

    public void setApplicantIds(List<String> applicantIds) {
        ApplicantIds = applicantIds;
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

    public List<String> getSuspectIds() {
        return SuspectIds;
    }

    public void setSuspectIds(List<String> suspectIds) {
        SuspectIds = suspectIds;
    }

    public User getInvestigator() {
        return Investigator;
    }

    public void setInvestigator(User investigator) {
        Investigator = investigator;
    }
}
