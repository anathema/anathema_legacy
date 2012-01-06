package net.sf.anathema.test.character.main.impl.charm;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.charm.LearningCharmGroupArbitrator;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.util.IIdentificate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class LearningCharmGroupArbitratorTest {

  @Test
  public void testAlternativesDontBlockCompletion() throws Exception {
    ICharacterModelContext context = Mockito.mock(ICharacterModelContext.class);
    ILearningCharmGroup group = Mockito.mock(ILearningCharmGroup.class);
    expectCoreCharmsCall(group);
    expectCoreCharmsCall(group);
    LearningCharmGroupArbitrator arbitrator = new LearningCharmGroupArbitrator(null, context);
    boolean celestialMartialArtsGroupCompleted = arbitrator.isCelestialMartialArtsGroupCompleted(new ILearningCharmGroup[] { group });
    Assert.assertTrue(celestialMartialArtsGroupCompleted);
  }

  private void expectCoreCharmsCall(ILearningCharmGroup group) {
    Mockito.when(group.getCoreCharms()).thenReturn(new ICharm[]{new DummyCharm() {
      @Override
      public boolean isBlockedByAlternative(IMagicCollection magicCollection) {
        return true;
      }

      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return !attribute.equals(NO_STYLE_ATTRIBUTE);
      }
    }});
  }
}
