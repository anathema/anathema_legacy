package net.sf.anathema.framework.presenter.action.menu.help;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import net.disy.commons.core.message.MessageType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

public class UpdateChecker implements IUpdateChecker {

  private final IResources resources;
  private final ChangeControl control = new ChangeControl();
  private IMessageData data;
  private String remoteVersion;
  private Boolean success = null;

  public UpdateChecker(IResources resources) {
    this.resources = resources;
  }

  public void checkForUpdates() {
    try {
      String response = getVersionData();
      String[] split = response.split("#"); //$NON-NLS-1$
      boolean newVersionAvailable = remoteIsNewer(split[1]);
      this.remoteVersion = split[0];
      if (newVersionAvailable) {
        this.success = true;
        this.data = new MessageData("Help.UpdateCheck.Outdated", MessageType.INFORMATION); //$NON-NLS-1$
      }
      else {
        this.success = true;
        this.data = new MessageData("Help.UpdateCheck.UpToDate", MessageType.INFORMATION); //$NON-NLS-1$
      }
    }
    catch (IOException e) {
      this.success = false;
      this.data = new MessageData("Help.UpdateCheck.IOException", MessageType.ERROR); //$NON-NLS-1$
    }
    catch (Exception e) {
      this.success = false;
      this.data = new MessageData("Help.UpdateCheck.GeneralException", MessageType.ERROR); //$NON-NLS-1$      
    }
    control.fireChangedEvent();
  }

  public Boolean isCheckSuccessful() {
    return success;
  }

  public String getCurrentVersion() {
    return resources.getString("Anathema.Version.Numeric"); //$NON-NLS-1$
  }

  public String getLatestVersion() {
    return remoteVersion;
  }

  public IMessageData getMessageData() {
    return data;
  }

  private boolean remoteIsNewer(String string) throws ParseException {
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
    Date currentVersionDate = dateFormat.parse(resources.getString("Anathema.Version.Built")); //$NON-NLS-1$
    Date remoteVersionDate = dateFormat.parse(string);
    boolean b = currentVersionDate.compareTo(remoteVersionDate) < 0;
    return b;
  }

  private String getVersionData() throws IOException {
    URL url = new URL("http://anathema.sf.net/version/version.txt"); //$NON-NLS-1$
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    String response = in.readLine();
    in.close();
    return response;
  }

  public void addDataChangedListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }
}