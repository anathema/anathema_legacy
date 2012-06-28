package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.presenter.action.menu.help.IMessageData;
import net.sf.anathema.framework.presenter.action.menu.help.IUpdateChecker;
import net.sf.anathema.framework.presenter.action.menu.help.MessageData;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static net.sf.anathema.lib.message.MessageType.ERROR;
import static net.sf.anathema.lib.message.MessageType.INFORMATION;

public class UpdateChecker implements IUpdateChecker {

  public static final String Update_Url = "https://api.github.com/repos/anathema/anathema/tags";
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final IResources resources;
  private IMessageData data;
  private Version remoteVersion = TagVersionFinder.Unidentified_Version;
  private Boolean success;

  public UpdateChecker(IResources resources) {
    this.resources = resources;
  }

  public void checkForUpdates() {
    try {
      String response = new UrlLoader(Update_Url).readAll();
      List<Tag> tags = new JsonTagLoader().loadFrom(response);
      this.remoteVersion = new TagVersionFinder().findLatestTaggedVersion(tags);
      boolean couldNotDetermineVersion = remoteVersion == TagVersionFinder.Unidentified_Version;
      if (couldNotDetermineVersion) {
        setErrorState("Help.UpdateCheck.GeneralException");
      } else {
        boolean newVersionAvailable = remoteIsNewer(remoteVersion);
        if (newVersionAvailable) {
          setSuccessState("Help.UpdateCheck.Outdated");
        } else {
          setSuccessState("Help.UpdateCheck.UpToDate");
        }
      }
    } catch (IOException e) {
      setErrorState("Help.UpdateCheck.IOException");
    } catch (Exception e) {
      setErrorState("Help.UpdateCheck.GeneralException");
    }
    control.announce().changeOccurred();
  }

  private void setErrorState(String key) {
    this.success = false;
    this.data = new MessageData(key, ERROR);
  }

  private void setSuccessState(String key) {
    this.success = true;
    this.data = new MessageData(key, INFORMATION);
  }

  @Override
  public Boolean isCheckSuccessful() {
    return success;
  }

  @Override
  public String getCurrentVersion() {
    return resources.getString("Anathema.Version.Numeric"); //$NON-NLS-1$
  }

  @Override
  public String getLatestVersion() {
    return remoteVersion.asString();
  }

  @Override
  public IMessageData getMessageData() {
    return data;
  }

  private boolean remoteIsNewer(Version remoteVersion) throws ParseException {
    Version currentVersion = new Version(getCurrentVersion());
    return remoteVersion.isLargerThan(currentVersion);
  }

  @Override
  public void addDataChangedListener(IChangeListener listener) {
    control.addListener(listener);
  }
}