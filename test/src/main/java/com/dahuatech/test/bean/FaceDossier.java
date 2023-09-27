package com.dahuatech.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.test.bean</p>
 * <p>className: FaceDossier</p>
 * <p>date: 2023/7/21</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

public class FaceDossier  {
    private Integer ageGroup; // 年龄段对应 枚举值
    private Integer faceClusterCount;//子档数量:
    private Integer gender; // 性别 0 男,1 女 2 未知
    private String identificationId;//身份证号:
    private String identificationType;//身份证号:
    private String name; // 姓名
    private Long identifyTime; // 落档时间
    private String  faceIdentifyImgUrl; // 身份证图片
    private String repositoryId;// 专题库id
    private String memberId;// 成员表id
    private String dossierId;//类标签:（主档id:dossierId）
    private String profileId; // 人像ID
    public FaceDossier(Integer ageGroup, Integer faceClusterCount, Integer gender, String identificationId, String identificationType, String name, Long identifyTime, String faceIdentifyImgUrl, String repositoryId, String memberId, String dossierId, String profileId) {
        this.ageGroup = ageGroup;
        this.faceClusterCount = faceClusterCount;
        this.gender = gender;
        this.identificationId = identificationId;
        this.identificationType = identificationType;
        this.name = name;
        this.identifyTime = identifyTime;
        this.faceIdentifyImgUrl = faceIdentifyImgUrl;
        this.repositoryId = repositoryId;
        this.memberId = memberId;
        this.dossierId = dossierId;
        this.profileId = profileId;
    }

    public Integer getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(Integer ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getFaceClusterCount() {
        return faceClusterCount;
    }

    public void setFaceClusterCount(Integer faceClusterCount) {
        this.faceClusterCount = faceClusterCount;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdentifyTime() {
        return identifyTime;
    }

    public void setIdentifyTime(Long identifyTime) {
        this.identifyTime = identifyTime;
    }

    public String getFaceIdentifyImgUrl() {
        return faceIdentifyImgUrl;
    }

    public void setFaceIdentifyImgUrl(String faceIdentifyImgUrl) {
        this.faceIdentifyImgUrl = faceIdentifyImgUrl;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDossierId() {
        return dossierId;
    }

    public void setDossierId(String dossierId) {
        this.dossierId = dossierId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "FaceDossier{" +
                "ageGroup=" + ageGroup +
                ", faceClusterCount=" + faceClusterCount +
                ", gender=" + gender +
                ", identificationId='" + identificationId + '\'' +
                ", identificationType='" + identificationType + '\'' +
                ", name='" + name + '\'' +
                ", identifyTime=" + identifyTime +
                ", faceIdentifyImgUrl='" + faceIdentifyImgUrl + '\'' +
                ", repositoryId='" + repositoryId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", dossierId='" + dossierId + '\'' +
                ", profileId='" + profileId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaceDossier that = (FaceDossier) o;
        return Objects.equals(ageGroup, that.ageGroup) && Objects.equals(faceClusterCount, that.faceClusterCount) && Objects.equals(gender, that.gender) && Objects.equals(identificationId, that.identificationId) && Objects.equals(identificationType, that.identificationType) && Objects.equals(name, that.name) && Objects.equals(identifyTime, that.identifyTime) && Objects.equals(faceIdentifyImgUrl, that.faceIdentifyImgUrl) && Objects.equals(repositoryId, that.repositoryId) && Objects.equals(memberId, that.memberId) && Objects.equals(dossierId, that.dossierId) && Objects.equals(profileId, that.profileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ageGroup, faceClusterCount, gender, identificationId, identificationType, name, identifyTime, faceIdentifyImgUrl, repositoryId, memberId, dossierId, profileId);
    }
}