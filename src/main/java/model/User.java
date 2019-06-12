
package model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("iana_time_zone")
    @Expose
    private String ianaTimeZone;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("shared_phone_number")
    @Expose
    private Object sharedPhoneNumber;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("locale_id")
    @Expose
    private Long localeId;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("organization_id")
    @Expose
    private Long organizationId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("external_id")
    @Expose
    private Object externalId;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("alias")
    @Expose
    private Object alias;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("shared")
    @Expose
    private Boolean shared;
    @SerializedName("shared_agent")
    @Expose
    private Boolean sharedAgent;
    @SerializedName("last_login_at")
    @Expose
    private String lastLoginAt;
    @SerializedName("two_factor_auth_enabled")
    @Expose
    private Object twoFactorAuthEnabled;
    @SerializedName("signature")
    @Expose
    private Object signature;
    @SerializedName("details")
    @Expose
    private Object details;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("role_type")
    @Expose
    private Object roleType;
    @SerializedName("custom_role_id")
    @Expose
    private Object customRoleId;
    @SerializedName("moderator")
    @Expose
    private Boolean moderator;
    @SerializedName("ticket_restriction")
    @Expose
    private Object ticketRestriction;
    @SerializedName("only_private_comments")
    @Expose
    private Boolean onlyPrivateComments;
    @SerializedName("restricted_agent")
    @Expose
    private Boolean restrictedAgent;
    @SerializedName("suspended")
    @Expose
    private Boolean suspended;
    @SerializedName("chat_only")
    @Expose
    private Boolean chatOnly;
    @SerializedName("default_group_id")
    @Expose
    private Long defaultGroupId;
    @SerializedName("report_csv")
    @Expose
    private Boolean reportCsv;
    @SerializedName("user_fields")
    @Expose
    private UserFields userFields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getIanaTimeZone() {
        return ianaTimeZone;
    }

    public void setIanaTimeZone(String ianaTimeZone) {
        this.ianaTimeZone = ianaTimeZone;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getSharedPhoneNumber() {
        return sharedPhoneNumber;
    }

    public void setSharedPhoneNumber(Object sharedPhoneNumber) {
        this.sharedPhoneNumber = sharedPhoneNumber;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Long getLocaleId() {
        return localeId;
    }

    public void setLocaleId(Long localeId) {
        this.localeId = localeId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Object getExternalId() {
        return externalId;
    }

    public void setExternalId(Object externalId) {
        this.externalId = externalId;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Object getAlias() {
        return alias;
    }

    public void setAlias(Object alias) {
        this.alias = alias;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Boolean getSharedAgent() {
        return sharedAgent;
    }

    public void setSharedAgent(Boolean sharedAgent) {
        this.sharedAgent = sharedAgent;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Object getTwoFactorAuthEnabled() {
        return twoFactorAuthEnabled;
    }

    public void setTwoFactorAuthEnabled(Object twoFactorAuthEnabled) {
        this.twoFactorAuthEnabled = twoFactorAuthEnabled;
    }

    public Object getSignature() {
        return signature;
    }

    public void setSignature(Object signature) {
        this.signature = signature;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public Object getRoleType() {
        return roleType;
    }

    public void setRoleType(Object roleType) {
        this.roleType = roleType;
    }

    public Object getCustomRoleId() {
        return customRoleId;
    }

    public void setCustomRoleId(Object customRoleId) {
        this.customRoleId = customRoleId;
    }

    public Boolean getModerator() {
        return moderator;
    }

    public void setModerator(Boolean moderator) {
        this.moderator = moderator;
    }

    public Object getTicketRestriction() {
        return ticketRestriction;
    }

    public void setTicketRestriction(Object ticketRestriction) {
        this.ticketRestriction = ticketRestriction;
    }

    public Boolean getOnlyPrivateComments() {
        return onlyPrivateComments;
    }

    public void setOnlyPrivateComments(Boolean onlyPrivateComments) {
        this.onlyPrivateComments = onlyPrivateComments;
    }

    public Boolean getRestrictedAgent() {
        return restrictedAgent;
    }

    public void setRestrictedAgent(Boolean restrictedAgent) {
        this.restrictedAgent = restrictedAgent;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public Boolean getChatOnly() {
        return chatOnly;
    }

    public void setChatOnly(Boolean chatOnly) {
        this.chatOnly = chatOnly;
    }

    public Long getDefaultGroupId() {
        return defaultGroupId;
    }

    public void setDefaultGroupId(Long defaultGroupId) {
        this.defaultGroupId = defaultGroupId;
    }

    public Boolean getReportCsv() {
        return reportCsv;
    }

    public void setReportCsv(Boolean reportCsv) {
        this.reportCsv = reportCsv;
    }

    public UserFields getUserFields() {
        return userFields;
    }

    public void setUserFields(UserFields userFields) {
        this.userFields = userFields;
    }

}
