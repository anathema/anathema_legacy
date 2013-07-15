package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import net.sf.anathema.framework.Version;

import java.util.List;

public class TagVersionFinder {

  public static final Version Unidentified_Version = new Version(0, 0, 0);

  public Version findLatestTaggedVersion(List<Tag> tags) {
    Version latestVersion = Unidentified_Version;
    for (Tag tag : tags) {
      String versionString = tag.name.replace("v", "");
      if (Version.isParseable(versionString)) {
        Version version = new Version(versionString);
        if (version.isLargerThan(latestVersion)) {
          latestVersion = version;
        }
      }
    }
    return latestVersion;
  }
}