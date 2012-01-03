package net.sf.anathema.framework.module.preferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME;
import static net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement.SYSTEM_PREFERENCES;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LookAndFeelOverrideBootjobTest {

  private String previousSetting;

  @Before
  public void setUp() throws Exception {
    previousSetting = SYSTEM_PREFERENCES.get(USER_LOOK_AND_FEEL_CLASSNAME, null);
  }

  @After
  public void tearDown() throws Exception {
    if (previousSetting != null) {
      SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, previousSetting);
    }
  }

  @Test
  public void resetsLookAndFeelIfPropertyIsSet() throws Exception {
    SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, "anyLookAndFeel");
    System.setProperty("overrideLookAndFeel", "true");
    new LookAndFeelOverrideBootjob().run(null, null, null);
    assertThat(Arrays.asList(SYSTEM_PREFERENCES.keys()).contains(USER_LOOK_AND_FEEL_CLASSNAME), is(false));
  }
}
