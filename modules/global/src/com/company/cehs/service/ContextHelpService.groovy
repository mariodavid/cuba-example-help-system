package com.company.cehs.service

import com.company.cehs.entity.ContextHelp;


public interface ContextHelpService {
    String NAME = "cehs_ContextHelpService";

    ContextHelp getContextHelpForScreen(String screenId)
}