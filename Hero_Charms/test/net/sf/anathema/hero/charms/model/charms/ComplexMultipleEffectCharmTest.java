package net.sf.anathema.hero.charms.model.charms;

import net.sf.anathema.character.main.charm.CharmSpecialistImpl;
import net.sf.anathema.character.main.magic.charms.special.ComplexMultipleEffectCharm;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.charms.special.ISubeffect;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ComplexMultipleEffectCharmTest {
  private CharmSpecialistImpl specialist = new CharmSpecialistImpl(new DummyHero());
  private ICharmLearnableArbitrator arbitrator = mock(ICharmLearnableArbitrator.class);
  private ICharm baseCharm = mock(ICharm.class);
  private IMultipleEffectCharm charmWithThreeEffects =
          new ComplexMultipleEffectCharm("Solar.TestCharm", new String[]{"A", "B", "C"}, new HashMap<String, String>());

  @Test
  public void instantiatesSubeffects() throws Exception {
    ISubeffect[] subeffects = charmWithThreeEffects.buildSubeffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffects.length, is(3));
  }

  @Test
  public void instantiatesSubeffectsOnlyOnce() throws Exception {
    charmWithThreeEffects.buildSubeffects(specialist, arbitrator, baseCharm);
    ISubeffect[] subeffectsAgain = charmWithThreeEffects.buildSubeffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffectsAgain.length, is(3));
  }
}