package net.sf.anathema.character.main.magic.charm.prerequisite.impl;

import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleCharmLearnPrerequisite_Test {

  @Test(expected = IllegalStateException.class)
  public void cantProvidePrerequisitesUntilLinked() throws Exception {
    SimpleCharmLearnPrerequisite prerequisite = new SimpleCharmLearnPrerequisite("id");
    ICharmLearnArbitrator arbitrator = Mockito.mock(ICharmLearnArbitrator.class);
    prerequisite.getLearnPrerequisites(arbitrator);
  }
}
