alter table CEHS_CONTEXT_HELP add column GENERAL_HELP_TEXT longvarchar ;
alter table CEHS_CONTEXT_HELP add column KEYBOARD_SHORTCUTS_TEXT longvarchar ;
alter table CEHS_CONTEXT_HELP add column USER_DEFINED_HELP_TEXT longvarchar ;
alter table CEHS_CONTEXT_HELP drop column HELP_TEXT cascade ;
