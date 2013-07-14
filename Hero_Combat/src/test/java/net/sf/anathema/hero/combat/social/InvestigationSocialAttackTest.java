package net.sf.anathema.hero.combat.social;

import net.sf.anathema.character.main.equipment.HeroStatsModifiers;
import net.sf.anathema.hero.dummy.trait.DummyTrait;
import net.sf.anathema.hero.combat.model.social.InvestigationSocialAttack;
import net.sf.anathema.hero.traits.TraitMap;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.main.traits.types.AbilityType.Investigation;
import static net.sf.anathema.character.main.traits.types.AttributeType.Charisma;
import static net.sf.anathema.character.main.traits.types.AttributeType.Manipulation;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvestigationSocialAttackTest {

  TraitMap collection = mock(TraitMap.class);
  HeroStatsModifiers equipment = mock(HeroStatsModifiers.class);
  private InvestigationSocialAttack socialAttack = new InvestigationSocialAttack(collection, equipment);

  @Before
  public void setUp() throws Exception {
    when(collection.getTrait(Investigation)).thenReturn(new DummyTrait(Investigation, 0));
    when(collection.getTrait(Manipulation)).thenReturn(new DummyTrait(Manipulation, 0));
    when(collection.getTrait(Charisma)).thenReturn(new DummyTrait(Charisma, 0));
  }

  @Test
  public void addsParryModifierToDeceptionDV() throws Exception {
    when(equipment.getMPDVPoolMod()).thenReturn(4);
    int deceptionMDV = socialAttack.getDeceptionMDV();
    assertThat(deceptionMDV, is(2));
  }

  @Test
  public void addsParryModifierToHonestyDV() throws Exception {
    when(equipment.getMPDVPoolMod()).thenReturn(4);
    int honestyMDV = socialAttack.getHonestyMDV();
    assertThat(honestyMDV, is(2));
  }
}
