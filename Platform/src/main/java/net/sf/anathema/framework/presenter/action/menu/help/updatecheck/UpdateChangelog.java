package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.Version;

public class UpdateChangelog {
  public static final String CHANGELOG_URL = "https://raw.github.com/anathema/anathema/master/Development_Documentation/Distribution/English/versions.md";
  private final Version installed;
  private final Version available;

  public UpdateChangelog(Version installed, Version available) {
    this.installed = installed;
    this.available = available;
  }

  public String getText() {
    if (installed.isEqualTo(available)) {
      return "No changes to show. You are up to date.";
    }
    try {
      String startDelimiter = "## Release " + available.asString();
      String endDelimiter = "## Release " + installed.asString();
      String releaseNotes = new UrlLoader(CHANGELOG_URL).readAll();
      String[] split = releaseNotes.split(startDelimiter);
      String notesBeginningWithLatestVersion = split[1];
      String notesEndingWithInstalledVersion = notesBeginningWithLatestVersion.split(endDelimiter)[0];
      return startDelimiter + notesEndingWithInstalledVersion;
    }
    catch (Exception e) {
      return "I am sorry, the changelog is unavailable.";
    }
  }
}