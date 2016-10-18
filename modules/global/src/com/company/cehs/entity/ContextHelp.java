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

    @Column(name = "SCREEN_ID", nullable = false, unique = true)
    protected String screenId;

    @Lob
    @Column(name = "GENERAL_HELP_TEXT")
    protected String generalHelpText;

    @Lob
    @Column(name = "KEYBOARD_SHORTCUTS_TEXT")
    protected String keyboardShortcutsText;

    @Lob
    @Column(name = "USER_DEFINED_HELP_TEXT")
    protected String userDefinedHelpText;

    public void setGeneralHelpText(String generalHelpText) {
        this.generalHelpText = generalHelpText;
    }

    public String getGeneralHelpText() {
        return generalHelpText;
    }

    public void setKeyboardShortcutsText(String keyboardShortcutsText) {
        this.keyboardShortcutsText = keyboardShortcutsText;
    }

    public String getKeyboardShortcutsText() {
        return keyboardShortcutsText;
    }

    public void setUserDefinedHelpText(String userDefinedHelpText) {
        this.userDefinedHelpText = userDefinedHelpText;
    }

    public String getUserDefinedHelpText() {
        return userDefinedHelpText;
    }


    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getScreenId() {
        return screenId;
    }


}