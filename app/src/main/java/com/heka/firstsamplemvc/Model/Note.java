package com.heka.firstsamplemvc.Model;

public class Note implements INote{
    private int id;
    private String note;
    private String createTime;
    private boolean validStatus;
    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean isValid() {
        return validStatus;
    }

    @Override
    public void setValid(boolean valid) {
        this.validStatus = valid;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

}
