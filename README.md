# CuboxMemo

An IntelliJ IDEA plugin that allows you to send clipboard content to Cubox as a memo.
It's entirely powered by AI. Here's the prompt given to it:
```
I'm going to develop an IntelliJ IDEA plugin that sends clipboard content to a third-party API. I've already cloned the latest IntelliJ Platform Plugin Template. The plugin needs to implement the following features:

1.Add a new menu item called "Cubox Memo" to the context menu when right-clicking in the code editor. When clicked, it should read the clipboard content, format it into a fixed structure, and send a POST request.

2.Under Settings > Tools, allow users to configure the third-party API URL and specify the tags field in the request body. The tags is an array of strings.

3.Example request body:{"type": "memo","content": "123123123","tags": ["note"]}, type is fixed as "memo", content is taken from the clipboard, tags come from the plugin settings.


4.Show a simple success message on successful submission, or display the error returned by the third-party API if the request fails.
```

![Build](https://github.com/LennyPu/CuboxMemo/workflows/Build/badge.svg)

<!-- Plugin description -->
CuboxMemo is a simple plugin that integrates IntelliJ IDEA with Cubox. It allows you to:

- Right-click in the editor and send your clipboard content to Cubox
- Configure your Cubox API URL and tags in the settings
- Quickly capture code snippets, notes, and other content without leaving your IDE

Perfect for developers who want to save useful code snippets or notes directly to their Cubox account.
<!-- Plugin description end -->

## Features

- Add clipboard content to Cubox as a memo with a single click
- Configure API URL and tags in Settings > Tools > Cubox Memo
- Receive notifications for successful submissions or errors

## Installation

### From ZIP File (Direct Installation)

1. Download the [latest release ZIP file](https://github.com/LennyPu/CuboxMemo/releases/latest)
2. In IntelliJ IDEA, go to **Settings/Preferences** > **Plugins** > ⚙️ (gear icon) > **Install Plugin from Disk...**
3. Select the downloaded ZIP file
4. Restart IntelliJ IDEA when prompted

### Manual Build and Installation

1. Clone this repository
2. Run `./gradlew buildPlugin` (or `gradlew.bat buildPlugin` on Windows)
3. The plugin ZIP file will be generated in `build/distributions/`
4. Install using **Settings/Preferences** > **Plugins** > ⚙️ > **Install Plugin from Disk...**

## Configuration

1. After installation, go to **Settings/Preferences** > **Tools** > **Cubox Memo**
2. Enter your Cubox API URL
3. Enter the tags you want to apply to your memos (comma-separated)

## Usage

1. Copy the content you want to send to Cubox
2. Right-click anywhere in the editor
3. Select "Cubox Memo" from the context menu
4. A notification will appear indicating success or an error message

---
Plugin based on the [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template).


# CuboxMemo

![Build](https://github.com/LennyPu/CuboxMemo/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties) and [pluginName](./gradle.properties), as well as the [id](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `MARKETPLACE_ID` in the above README badges. You can obtain it once the plugin is published to JetBrains Marketplace.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "CuboxMemo"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/LennyPu/CuboxMemo/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
