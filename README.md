# cuba-example-help-system
CUBA platform example that allows to show contextual help for particular screens.

Within the application screens can have a context help that will be displayed on the right of the screen.

## Display context help for screens
For this to work, the screen has to extend the base class `AbstractLookupWithHelp`.
See [CustomerBrowse.groovy](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/web/src/com/company/cehs/web/customer/CustomerBrowse.groovy) as well as [customer-browse.xml](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/web/src/com/company/cehs/web/customer/customer-browse.xml) for an example.

> NOTE: currently the XML content for the actual help menu has to be copied into every screen that wants to use this. The controller code is reused through subclassing.
> If you have any idea's on improving this, don't hastitate to send a PR.

The result looks like this:

![Screenshot context help menu](https://github.com/mariodavid/cuba-example-help-system/blob/master/img/context-help-menu.png)

## Managing help content

In order to manage the content of the help, there is an entity (called [ContextHelp](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/global/src/com/company/cehs/entity/ContextHelp.java))
which basically has a UI screen for creating and editing the context help.

After the screen is selected, the content can be entered via a RichTextEditor, which makes formatting within the help possible.

![Screenshot context help editor](https://github.com/mariodavid/cuba-example-help-system/blob/master/img/context-help-editor.png)
