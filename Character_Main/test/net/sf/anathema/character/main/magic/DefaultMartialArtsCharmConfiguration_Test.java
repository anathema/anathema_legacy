package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.charm.special.DefaultMartialArtsCharmConfiguration;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.testing.dummy.magic.DummyCharmsModel;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DefaultMartialArtsCharmConfiguration_Test {

  @Test
  public void testAlternativesDontBlockCompletion() throws Exception {
    MagicCollection collection = Mockito.mock(MagicCollection.class);
    ILearningCharmGroup group = Mockito.mock(ILearningCharmGroup.class);
    expectCoreCharmsCall(group);
    expectCoreCharmsCall(group);
    DummyCharmsModel dummyConfig = new DummyCharmsModel();
    dummyConfig.setGroups(group);
    ExperienceModel experienceModel = new ExperienceModel() {
      public boolean experienced;

      @Override
      public IExperiencePointConfiguration getExperiencePoints() {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean isExperienced() {
        return experienced;
      }

      @Override
      public void setExperienced(boolean experienced) {
        this.experienced = experienced;
      }

      @Override
      public void addStateChangeListener(IChangeListener listener) {
        // nothing to do
      }
    };
    DefaultMartialArtsCharmConfiguration configuration = new DefaultMartialArtsCharmConfiguration(dummyConfig, collection, experienceModel);
    boolean celestialMartialArtsGroupCompleted = configuration.isAnyCelestialStyleCompleted();
    Assert.assertTrue(celestialMartialArtsGroupCompleted);
  }

  private void expectCoreCharmsCall(ILearningCharmGroup group) {
    Mockito.when(group.getCoreCharms()).thenReturn(new ICharm[]{new DummyCharm() {
      @Override
      public boolean isBlockedByAlternative(MagicCollection magicCollection) {
        return true;
      }

      @Override
      public boolean hasAttribute(Identifier attribute) {
        return !attribute.equals(NO_STYLE_ATTRIBUTE);
      }
    }});
  }
}