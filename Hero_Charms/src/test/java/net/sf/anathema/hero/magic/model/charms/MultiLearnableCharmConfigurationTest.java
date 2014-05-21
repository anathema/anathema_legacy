package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.hero.charms.CharmHeroObjectMother;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.StaticMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.MultiLearnableCharmSpecialsImpl;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.magic.dummy.DummyCharmsModel;
import net.sf.anathema.hero.magic.dummy.DummyLearnableArbitrator;
import net.sf.anathema.hero.traits.model.context.CreationTraitValueStrategy;
import net.sf.anathema.hero.traits.model.context.ExperiencedTraitValueStrategy;
import net.sf.anathema.hero.traits.model.context.ProxyTraitValueStrategy;
import net.sf.anathema.hero.charms.model.CharmsModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultiLearnableCharmConfigurationTest {

  private CharmsModel config = new DummyCharmsModel();
  private Charm charm = new DummyCharm("id");
  private IMultiLearnableCharm specialCharm = new StaticMultiLearnableCharm("id", 5);
  private ICharmLearnableArbitrator arbitrator = new DummyLearnableArbitrator("id");
  private DummyHero hero = new DummyHero();
  private MultiLearnableCharmSpecialsImpl configuration;
  private ProxyTraitValueStrategy valueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());

  @Before
  public void setUp() throws Exception {
    hero = new CharmHeroObjectMother().createModelContextWithEssence2(valueStrategy);
    configuration = new MultiLearnableCharmSpecialsImpl(hero, config, charm, specialCharm, arbitrator);
  }

  @Test
  public void learnsExperiencedIfCurrentlyExperienced() throws Exception {
    configuration.learn(true);
    assertThat(configuration.getCategory().getExperiencedValue(), is(1));
  }

  @Test
  public void doesNotChangeCreationValueIfAlreadyLearnedAndCurrentlyExperienced() throws Exception {
    valueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
    configuration.setCurrentLearnCount(1);
    configuration.learn(true);
    assertThat(configuration.getCategory().getCreationValue(), is(0));
  }
}
