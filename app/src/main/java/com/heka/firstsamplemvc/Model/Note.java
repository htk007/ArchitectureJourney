package com.heka.firstsamplemvc.Model;

public class Note implements INote{
    private String note;
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
    public boolean isValid() {
        return validStatus;
    }

    @Override
    public void setValid(boolean valid) {
        this.validStatus = valid;
    }

}
