package com.neo.entity;

public class HomeFeedVo {


    private Integer type;
    private Moment moment;
    private String relatedActivityName;
    private Integer relatedActivityId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

   

    public String getRelatedActivityName() {
        return relatedActivityName;
    }


	public void setRelatedActivityName(String relatedActivityName) {
        this.relatedActivityName = relatedActivityName;
    }

    public Integer getRelatedActivityId() {
        return relatedActivityId;
    }

    public void setRelatedActivityId(Integer relatedActivityId) {
        this.relatedActivityId = relatedActivityId;
    }



}
