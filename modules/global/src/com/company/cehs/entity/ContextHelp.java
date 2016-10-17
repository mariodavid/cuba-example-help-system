package com.company.cehs.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "CEHS_CONTEXT_HELP")
@Entity(name = "cehs$ContextHelp")
public class ContextHelp extends StandardEntity {
    private static final long serialVersionUID = -6064863666142863951L;

    @Column(name = "SCREEN_ID", nullable = false)
    protected String screenId;

    @Lob
    @Column(name = "HELP_TEXT")
    protected String helpText;

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getHelpText() {
        return helpText;
    }


}