package net.sf.anathema.platform.environment;

import net.sf.anathema.framework.environment.SwingEnvironment;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class SwingEnvironmentTest {

  private DummyInitializationPreferences preferences = new DummyInitializationPreferences();

  @Test
  public void testTooltipTime() throws Exception {
    int preferedTimeInSeconds = 5;
    preferences.setToolTipTime(preferedTimeInSeconds);
    SwingEnvironment.initTooltipManager(preferences);
    Assert.assertEquals(0, ToolTipManager.sharedInstance().getInitialDelay());
    Assert.assertEquals(0, ToolTipManager.sharedInstance().getReshowDelay());
    int expectedTimeInMiliseconds = 5000;
    Assert.assertEquals(expectedTimeInMiliseconds, ToolTipManager.sharedInstance().getDismissDelay());
  }

  @Test
  public void initializesToSystemLookAndFeelIfNoneIsSet() throws Exception {
    preferences.setUserLookAndFeel(null);
    SwingEnvironment.initLookAndFeel(preferences);
    Assert.assertEquals(UIManager.getSystemLookAndFeelClassName(), UIManager.getLookAndFeel().getClass().getName());
  }

  @Test
  public void initializesToSetLookAndFeel() throws Exception {
    preferences.setUserLookAndFeel(MetalLookAndFeel.class.getName());
    SwingEnvironment.initLookAndFeel(preferences);
    Assert.assertEquals(MetalLookAndFeel.class, UIManager.getLookAndFeel().getClass());
  }
}