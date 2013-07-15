package net.sf.anathema.platform.environment;

import net.sf.anathema.framework.environment.LocaleEnvironment;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class LocaleEnvironmentTest {

  private DummyInitializationPreferences preferences = new DummyInitializationPreferences();

  @Test
  public void testSetDefaultLocale() throws Exception {
    preferences.setLocale(Locale.GERMAN);
    LocaleEnvironment.initLocale(preferences);
    Assert.assertEquals(Locale.GERMAN, Locale.getDefault());
  }
}