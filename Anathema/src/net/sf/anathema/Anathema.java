package net.sf.anathema;

import org.java.plugin.boot.Application;

import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.AnathemaInitializer;

public class Anathema implements Application {

  public static void main(String[] arguments) throws Exception {
    new Anathema().startApplication();
  }

  public void startApplication() throws Exception {
    IAnathemaPreferences anathemaPreferences = AnathemaPreferences.getDefaultPreferences();
    AnathemaEnvironment.initLogging();
    AnathemaEnvironment.initLocale(anathemaPreferences);
    AnathemaEnvironment.initLookAndFeel(anathemaPreferences);
    AnathemaEnvironment.initTooltipManager(anathemaPreferences);
    IAnathemaView anathemaView = new AnathemaInitializer(anathemaPreferences).initialize();
    anathemaView.showFrame();
  }
}