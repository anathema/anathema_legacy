package net.sf.anathema.character.generic.impl;

import net.sf.anathema.character.generic.dummy.DummyGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CharacterUtilitiesTest {

  private DummyGenericTraitCollection traitCollection = new DummyGenericTraitCollection();
  private IEquipmentModifiers modifiers = mock(IEquipmentModifiers.class);

  @Test
  public void testDodgeMdvWithEvenAttributeSum() throws Exception {
    traitCollection.setValue(AbilityType.Integrity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    traitCollection.setValue(OtherTraitType.Willpower, 4);
    assertEquals(3, CharacterUtilties.getDodgeMdv(traitCollection, modifiers));
  }

  @Test
  public void testDodgeMdvWithOddAttributeSum() throws Exception {
    traitCollection.setValue(AbilityType.Integrity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    traitCollection.setValue(OtherTraitType.Willpower, 5);
    assertEquals(3, CharacterUtilties.getDodgeMdv(traitCollection, modifiers));
  }

  @Test
  public void testMortalDodgeDvWithEssenceValueOne() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 2);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    assertEquals(1, CharacterUtilties.getDodgeDv(CharacterType.MORTAL, traitCollection, modifiers));
  }

  @Test
  public void testMortalDodgeDvWithEssenceValueTwo() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 2);
    traitCollection.setValue(OtherTraitType.Essence, 2);
    assertEquals(2, CharacterUtilties.getDodgeDv(CharacterType.MORTAL, traitCollection, modifiers));
  }

  @Test
  public void testExaltDodgeWithEssenceValueOne() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    assertEquals(1, CharacterUtilties.getDodgeDv(CharacterType.SOLAR, traitCollection, modifiers));
  }

  @Test
  public void testExaltDodgeWithEssenceValueTwo() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 2);
    assertEquals(2, CharacterUtilties.getDodgeDv(CharacterType.SOLAR, traitCollection, modifiers));
  }

  @Test
  public void testExaltDodgeOddSum() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 2);
    traitCollection.setValue(OtherTraitType.Essence, 2);
    assertEquals(3, CharacterUtilties.getDodgeDv(CharacterType.SOLAR, traitCollection, modifiers));
  }
}