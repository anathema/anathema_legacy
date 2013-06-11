package net.sf.anathema.character.generic.impl.magic.persistence.prerequisite;

import org.junit.Test;

public class SelectiveCharmGroupTemplateTest {

  @Test
  public void allowsThresholdsEqualToNumberOfChoices() throws Exception {
    String[] ids = new String[5];
    new SelectiveCharmGroupTemplate(ids, 5, "foo");
  }
}
