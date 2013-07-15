package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.NumericVersion;
import de.idos.updates.Version;
import de.idos.updates.VersionDiscovery;
import de.idos.updates.VersionFinder;
import de.idos.updates.store.ProgressReport;

import java.io.IOException;
import java.util.List;

public class TagsOnGithub implements VersionDiscovery {
  public static final String Update_Url = "https://api.github.com/repos/anathema/anathema/tags";
  private ProgressReport progressReport;


  @Override
  public Version getLatestVersion() {
    try {
      progressReport.lookingUpLatestAvailableVersion();
      String response = new UrlLoader(Update_Url).readAll();
      List<Tag> tags = new JsonTagLoader().loadFrom(response);
      net.sf.anathema.framework.Version remoteVersion = new TagVersionFinder().findLatestTaggedVersion(tags);
      boolean couldNotDetermineVersion = remoteVersion == TagVersionFinder.Unidentified_Version;
      if (couldNotDetermineVersion) {
        progressReport.versionLookupFailed();
        return VersionFinder.BASE_VERSION;
      } else {
        NumericVersion version = new NumericVersion(remoteVersion.getMajorVersion(), remoteVersion.getMinorVersion(), remoteVersion.getRevision());
        progressReport.latestAvailableVersionIs(version);
        return version;
      }
    } catch (IOException e) {
      progressReport.versionLookupFailed(e);
      return VersionFinder.BASE_VERSION;
    }
  }

  @Override
  public void reportAllProgressTo(ProgressReport progressReport) {
    this.progressReport = progressReport;
  }
}
