package net.sf.anathema.hero.othertraits;

import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.character.main.model.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.character.main.testing.dummy.DummyInitializationContext;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.trait.DummyTrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.hero.othertraits.model.pool.EssencePoolModelImpl;
import net.sf.anathema.hero.traits.model.TraitModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EssencePoolTest {
  ICharacter statistics = mock(ICharacter.class);

  @Before
  public void createEssenceUserWithoutPeripheralPool() throws Exception {
    GenericEssenceTemplate essenceTemplate = new GenericEssenceTemplate();
    essenceTemplate.setEssenceUser(true);
    TraitModelImpl traitMap = new TraitModelImpl();
    traitMap.addTraits(new DummyTrait(OtherTraitType.Willpower));
    traitMap.addTraits(new DummyTrait(OtherTraitType.Essence));
    traitMap.addTraits(new DummyTrait(VirtueType.Compassion));
    traitMap.addTraits(new DummyTrait(VirtueType.Conviction));
    traitMap.addTraits(new DummyTrait(VirtueType.Temperance));
    traitMap.addTraits(new DummyTrait(VirtueType.Valor));
    EssencePoolModelImpl essencePoolModel = new EssencePoolModelImpl();
    DummyHero hero = new DummyHero();
    hero.addModel(new EssencePoolModelImpl());
    hero.template.setEssenceTemplate(essenceTemplate);
    when(statistics.getTemplate()).thenReturn(hero.getTemplate());
    hero.addModel(traitMap);
    InitializationContext initContext = new DummyInitializationContext();
    essencePoolModel.initialize(initContext, hero);
    when(EssencePoolModelFetcher.fetch(statistics)).thenReturn(essencePoolModel);
  }

  @Test
  public void returnsNullWhenEssenceUserHasNoPeripheralPool() throws Exception {
    GenericCharacter character = new GenericCharacter(statistics);
    String peripheralPool = character.getPeripheralPool();
    assertThat(peripheralPool, is(nullValue()));
  }
}
