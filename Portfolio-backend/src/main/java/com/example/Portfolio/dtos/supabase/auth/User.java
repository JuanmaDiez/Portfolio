
package com.example.Portfolio.dtos.supabase.auth;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "aud",
    "role",
    "email",
    "email_confirmed_at",
    "phone",
    "confirmed_at",
    "last_sign_in_at",
    "app_metadata",
    "user_metadata",
    "identities",
    "created_at",
    "updated_at",
    "is_anonymous"
})

public class User {

    @JsonProperty("id")
    private String id;
    @JsonProperty("aud")
    private String aud;
    @JsonProperty("role")
    private String role;
    @JsonProperty("email")
    private String email;
    @JsonProperty("email_confirmed_at")
    private String emailConfirmedAt;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("confirmed_at")
    private String confirmedAt;
    @JsonProperty("last_sign_in_at")
    private String lastSignInAt;
    @JsonProperty("app_metadata")
    private AppMetadata appMetadata;
    @JsonProperty("user_metadata")
    private UserMetadata userMetadata;
    @JsonProperty("identities")
    private List<Identity> identities;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("aud")
    public String getAud() {
        return aud;
    }

    @JsonProperty("aud")
    public void setAud(String aud) {
        this.aud = aud;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("email_confirmed_at")
    public String getEmailConfirmedAt() {
        return emailConfirmedAt;
    }

    @JsonProperty("email_confirmed_at")
    public void setEmailConfirmedAt(String emailConfirmedAt) {
        this.emailConfirmedAt = emailConfirmedAt;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("confirmed_at")
    public String getConfirmedAt() {
        return confirmedAt;
    }

    @JsonProperty("confirmed_at")
    public void setConfirmedAt(String confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    @JsonProperty("last_sign_in_at")
    public String getLastSignInAt() {
        return lastSignInAt;
    }

    @JsonProperty("last_sign_in_at")
    public void setLastSignInAt(String lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    @JsonProperty("app_metadata")
    public AppMetadata getAppMetadata() {
        return appMetadata;
    }

    @JsonProperty("app_metadata")
    public void setAppMetadata(AppMetadata appMetadata) {
        this.appMetadata = appMetadata;
    }

    @JsonProperty("user_metadata")
    public UserMetadata getUserMetadata() {
        return userMetadata;
    }

    @JsonProperty("user_metadata")
    public void setUserMetadata(UserMetadata userMetadata) {
        this.userMetadata = userMetadata;
    }

    @JsonProperty("identities")
    public List<Identity> getIdentities() {
        return identities;
    }

    @JsonProperty("identities")
    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("is_anonymous")
    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    @JsonProperty("is_anonymous")
    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
