package net.sf.anathema;

import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.AnathemaInitializer;

import org.java.plugin.PluginManager;
import org.java.plugin.boot.Application;
import org.java.plugin.boot.Boot;

public class Anathema implements Application {

  private final PluginManager manager;

  public static void main(String[] arguments) throws Exception {
    Boot.main(arguments);
  }

  public Anathema(PluginManager manager) {
    this.manager = manager;
  }

  public void startApplication() throws Exception {
    IAnathemaPreferences anathemaPreferences = AnathemaPreferences.getDefaultPreferences();
    AnathemaEnvironment.initLogging();
    AnathemaEnvironment.initLocale(anathemaPreferences);
    AnathemaEnvironment.initLookAndFeel(anathemaPreferences);
    AnathemaEnvironment.initTooltipManager(anathemaPreferences);
    IAnathemaView anathemaView = new AnathemaInitializer(manager, anathemaPreferences).initialize();
    anathemaView.showFrame();
  }
}