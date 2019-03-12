package ru.stqa.pft.rest;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Issue {

    private int id;
    private String subject;
    private String description;
    private int state;

    @SerializedName("state_name")
    private String stateName;

    public Issue withStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Issue{" + "id=" + id + ", subject='" + subject + '\'' + ", description='" + description + '\'' + ", " +
                "state=" + state + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && Objects.equals(subject, issue.subject) && Objects.equals(description,
                issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public int getState() {
        return state;
    }

    public Issue withState(int state) {
        this.state = state;
        return this;
    }

    public String getStateName() {
        return stateName;
    }
}
