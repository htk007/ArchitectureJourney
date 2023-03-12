package com.heka.firstsamplemvc.Model;

public interface INote {
    String getNote();
    void setNote(String note);
    String getCreateTime();
    void setCreateTime(String createTime);
    boolean isValid();
    void setValid(boolean valid);
}
