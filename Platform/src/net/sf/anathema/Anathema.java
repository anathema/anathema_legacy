package net.sf.anathema;

import javax.swing.JOptionPane;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.AnathemaInitializer;
import net.sf.anathema.initialization.InitializationException;

import org.java.plugin.PluginManager;
import org.java.plugin.boot.Application;

public class Anathema implements Application {

  private final PluginManager manager;

  public Anathema(PluginManager manager) {
    this.manager = manager;
  }

  public void startApplication() throws Exception {
    ProxySplashscreen.getInstance().displayStatusMessage("Retrieving Preferences..."); //$NON-NLS-1$
    IAnathemaPreferences anathemaPreferences = AnathemaPreferences.getDefaultPreferences();
    ProxySplashscreen.getInstance().displayStatusMessage("Preparing Environment..."); //$NON-NLS-1$
    AnathemaEnvironment.initLogging();
    AnathemaEnvironment.initLocale(anathemaPreferences);
    AnathemaEnvironment.initLookAndFeel(anathemaPreferences);
    AnathemaEnvironment.initTooltipManager(anathemaPreferences);
    IAnathemaView anathemaView;
    try {
      ProxySplashscreen.getInstance().displayStatusMessage("Starting Platform..."); //$NON-NLS-1$
      anathemaView = new AnathemaInitializer(manager, anathemaPreferences).initialize();
    }
    catch (InitializationException e) {
      JOptionPane.showMessageDialog(
          null,
          e.getMessage() + "\n" + e.getStackTrace(), "Initialization Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
      // TODO: Disy-Dialoge ohne I18n?
      // UserDialog dialog = MessageDialogFactory.createMessageDialog(null, new Message(e.getMessage(), e));
      // dialog.show();
      return;
    }
    ProxySplashscreen.getInstance().displayStatusMessage("Done."); //$NON-NLS-1$
    anathemaView.showFrame();
  }
}