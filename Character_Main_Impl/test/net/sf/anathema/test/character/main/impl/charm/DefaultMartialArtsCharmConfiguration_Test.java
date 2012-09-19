package net.sf.anathema.test.character.main.impl.charm;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.dummy.magic.DummyCharmConfiguration;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.charm.special.DefaultMartialArtsCharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.util.Identified;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DefaultMartialArtsCharmConfiguration_Test {

  @Test
  public void testAlternativesDontBlockCompletion() throws Exception {
    IMagicCollection collection = Mockito.mock(IMagicCollection.class);
    IBasicCharacterData character = Mockito.mock(IBasicCharacterData.class);
    ILearningCharmGroup group = Mockito.mock(ILearningCharmGroup.class);
    expectCoreCharmsCall(group);
    expectCoreCharmsCall(group);
    DummyCharmConfiguration dummyConfig = new DummyCharmConfiguration();
    dummyConfig.setGroups(group);
    DefaultMartialArtsCharmConfiguration configuration = new DefaultMartialArtsCharmConfiguration(dummyConfig, collection, character);
    boolean celestialMartialArtsGroupCompleted = configuration.isAnyCelestialStyleCompleted();
    Assert.assertTrue(celestialMartialArtsGroupCompleted);
  }

  private void expectCoreCharmsCall(ILearningCharmGroup group) {
    Mockito.when(group.getCoreCharms()).thenReturn(new ICharm[]{new DummyCharm() {
      @Override
      public boolean isBlockedByAlternative(IMagicCollection magicCollection) {
        return true;
      }

      @Override
      public boolean hasAttribute(Identified attribute) {
        return !attribute.equals(NO_STYLE_ATTRIBUTE);
      }
    }});
  }
}