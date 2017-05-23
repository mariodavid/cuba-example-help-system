# cuba-example-help-system
CUBA platform example that allows to show contextual help for particular screens.

Within the application screens can have a context help that will be displayed on the right of the screen.

## Display context help for screens
For this to work, the screen has to extend the base class `AbstractLookupWithHelp`.
See [CustomerBrowse.groovy](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/web/src/com/company/cehs/web/customer/CustomerBrowse.groovy) as well as [customer-browse.xml](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/web/src/com/company/cehs/web/customer/customer-browse.xml) for an example.

> The XML includes a frame that has to be included into the screens that want to use the context help. Additionally it is currently required to create a split [e.g. here](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/web/src/com/company/cehs/web/customer/customer-browse.xml#L19) that will be used by the frame.
> If you have any optimisation tips, don't hasitate to send a PR.

The result looks like this:

![Screenshot context help menu](https://github.com/mariodavid/cuba-example-help-system/blob/master/img/context-help-menu.png)

## Managing help content

In order to manage the content of the help, there is an entity (called [ContextHelp](https://github.com/mariodavid/cuba-example-help-system/blob/master/modules/global/src/com/company/cehs/entity/ContextHelp.java))
which basically has a UI screen for creating and editing the context help.

After the screen is selected, the content can be entered via a RichTextEditor, which makes formatting within the help possible.

![Screenshot context help editor](https://github.com/mariodavid/cuba-example-help-system/blob/master/img/context-help-editor.png)
