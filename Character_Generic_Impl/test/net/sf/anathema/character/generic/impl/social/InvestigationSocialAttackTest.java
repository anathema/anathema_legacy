package net.sf.anathema.character.generic.impl.social;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.generic.traits.types.AbilityType.Investigation;
import static net.sf.anathema.character.generic.traits.types.AttributeType.Charisma;
import static net.sf.anathema.character.generic.traits.types.AttributeType.Manipulation;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvestigationSocialAttackTest {

  IGenericTraitCollection collection = mock(IGenericTraitCollection.class);
  IEquipmentModifiers equipment = mock(IEquipmentModifiers.class);
  private InvestigationSocialAttack socialAttack = new InvestigationSocialAttack(collection, equipment);

  @Before
  public void setUp() throws Exception {
    when(collection.getTrait(Investigation)).thenReturn(new DummyGenericTrait(Investigation, 0));
    when(collection.getTrait(Manipulation)).thenReturn(new DummyGenericTrait(Manipulation, 0));
    when(collection.getTrait(Charisma)).thenReturn(new DummyGenericTrait(Charisma, 0));
  }

  @Test
  public void addsParryModifierToDeceptionDV() throws Exception {
    when(equipment.getMPDVMod()).thenReturn(5);
    int deceptionMDV = socialAttack.getDeceptionMDV();
    assertThat(deceptionMDV, is(5));
  }

  @Test
  public void addsParryModifierToHonestyDV() throws Exception {
    when(equipment.getMPDVMod()).thenReturn(5);
    int honestyMDV = socialAttack.getHonestyMDV();
    assertThat(honestyMDV, is(5));
  }
}
