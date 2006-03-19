package net.sf.anathema;

import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.AnathemaInitializer;

public class Anathema {

  public static void main(String[] arguments) throws Exception {
    IAnathemaPreferences anathemaPreferences = AnathemaPreferences.getDefaultPreferences();
    AnathemaEnvironment.initLogging();
    AnathemaEnvironment.initLocale(anathemaPreferences);
    AnathemaEnvironment.initLookAndFeel(anathemaPreferences);
    AnathemaEnvironment.initTooltipManager(anathemaPreferences);
    startAnathema(anathemaPreferences);
  }

  private static void startAnathema(IAnathemaPreferences anathemaPreferences) throws Exception {
    IAnathemaView anathemaView = new AnathemaInitializer(anathemaPreferences).initialize();
    anathemaView.showFrame();
  }
}