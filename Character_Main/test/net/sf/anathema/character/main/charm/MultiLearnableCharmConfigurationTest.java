package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.impl.model.charm.special.MultiLearnableCharmConfiguration;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.main.testing.dummy.magic.DummyCharmConfiguration;
import net.sf.anathema.character.main.testing.dummy.magic.DummyLearnableArbitrator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultiLearnableCharmConfigurationTest {

  private ICharacterModelContext context = new DummyCharacterModelContext(new ExperiencedTraitValueStrategy());
  private ICharmConfiguration config = new DummyCharmConfiguration();
  private ICharm charm = new DummyCharm("id");
  private IMultiLearnableCharm specialCharm = new StaticMultiLearnableCharm("id", 5);
  private ICharmLearnableArbitrator arbitrator = new DummyLearnableArbitrator("id");
  private MultiLearnableCharmConfiguration configuration = new MultiLearnableCharmConfiguration(context, config, charm, specialCharm, arbitrator);

  @Test
  public void learnsExperiencedIfCurrentlyExperienced() throws Exception {
    configuration.learn(true);
    assertThat(configuration.getCategory().getExperiencedValue(), is(1));
  }

  @Test
  public void doesNotChangeCreationValueIfAlreadyLearnedAndCurrentlyExperienced() throws Exception {
    configuration.setCurrentLearnCount(1);
    configuration.learn(true);
    assertThat(configuration.getCategory().getCreationValue(), is(0));
  }
}
