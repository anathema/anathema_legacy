package net.sf.anathema.hero.othertraits;

import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.main.model.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.hero.ModelInitializationContext;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.main.testing.dummy.DummyHero;
import net.sf.anathema.character.main.testing.dummy.template.DummyHeroTemplate;
import net.sf.anathema.character.main.testing.dummy.trait.DummyTrait;
import net.sf.anathema.hero.traits.model.TraitModelImpl;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.hero.othertraits.model.pool.EssencePoolModelImpl;
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
    DummyHeroTemplate heroTemplate = new DummyHeroTemplate();
    GenericEssenceTemplate essenceTemplate = new GenericEssenceTemplate();
    essenceTemplate.setEssenceUser(true);
    heroTemplate.setEssenceTemplate(essenceTemplate);
    when(statistics.getHeroTemplate()).thenReturn(heroTemplate);
    TraitModelImpl traitMap = new TraitModelImpl();
    traitMap.addTraits(new DummyTrait(OtherTraitType.Willpower));
    traitMap.addTraits(new DummyTrait(OtherTraitType.Essence));
    traitMap.addTraits(new DummyTrait(VirtueType.Compassion));
    traitMap.addTraits(new DummyTrait(VirtueType.Conviction));
    traitMap.addTraits(new DummyTrait(VirtueType.Temperance));
    traitMap.addTraits(new DummyTrait(VirtueType.Valor));
    EssencePoolModelImpl essencePoolModel = new EssencePoolModelImpl();
    DummyHero hero = new DummyHero();
    hero.addModel(traitMap);
    InitializationContext initContext = new ModelInitializationContext(new DummyCharacterModelContext(), heroTemplate, null);
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
