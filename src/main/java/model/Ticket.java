package model;

import java.util.List;

public class Ticket {

    private String url;
    private Integer id;
    private String externalId;
    private Via via;
    private String createdAt;
    private String updatedAt;
    private String type;
    private String subject;
    private String rawSubject;
    private String description;
    private String priority;
    private String status;
    private String recipient;
    private String requesterId;
    private String submitterId;
    private String assigneeId;
    private String organizationId;
    private String groupId;
    private List<String> collaboratorIds = null;
    private List<String> followerIds = null;
    private List<String> emailCcIds = null;
    private String forumTopicId;
    private String problemId;
    private Boolean hasIncidents;
    private Boolean isprivate;
    private String dueAt;
    private List<String> tags = null;
    private List<String> customFields = null;
    private String satisfactionRating;
    private List<String> sharingAgreementIds = null;
    private List<String> fields = null;
    private List<String> followupIds = null;
    private String brandId;
    private Boolean allowChannelback;
    private Boolean allowAttachments;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Via getVia() {
        return via;
    }

    public void setVia(Via via) {
        this.via = via;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRawSubject() {
        return rawSubject;
    }

    public void setRawSubject(String rawSubject) {
        this.rawSubject = rawSubject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getCollaboratorIds() {
        return collaboratorIds;
    }

    public void setCollaboratorIds(List<String> collaboratorIds) {
        this.collaboratorIds = collaboratorIds;
    }

    public List<String> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(List<String> followerIds) {
        this.followerIds = followerIds;
    }

    public List<String> getEmailCcIds() {
        return emailCcIds;
    }

    public void setEmailCcIds(List<String> emailCcIds) {
        this.emailCcIds = emailCcIds;
    }

    public String getForumTopicId() {
        return forumTopicId;
    }

    public void setForumTopicId(String forumTopicId) {
        this.forumTopicId = forumTopicId;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public Boolean getHasIncidents() {
        return hasIncidents;
    }

    public void setHasIncidents(Boolean hasIncidents) {
        this.hasIncidents = hasIncidents;
    }

    public Boolean getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(Boolean isprivate) {
        this.isprivate = isprivate;
    }

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<String> customFields) {
        this.customFields = customFields;
    }

    public String getSatisfactionRating() {
        return satisfactionRating;
    }

    public void setSatisfactionRating(String satisfactionRating) {
        this.satisfactionRating = satisfactionRating;
    }

    public List<String> getSharingAgreementIds() {
        return sharingAgreementIds;
    }

    public void setSharingAgreementIds(List<String> sharingAgreementIds) {
        this.sharingAgreementIds = sharingAgreementIds;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFollowupIds() {
        return followupIds;
    }

    public void setFollowupIds(List<String> followupIds) {
        this.followupIds = followupIds;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Boolean getAllowChannelback() {
        return allowChannelback;
    }

    public void setAllowChannelback(Boolean allowChannelback) {
        this.allowChannelback = allowChannelback;
    }

    public Boolean getAllowAttachments() {
        return allowAttachments;
    }

    public void setAllowAttachments(Boolean allowAttachments) {
        this.allowAttachments = allowAttachments;
    }
}
