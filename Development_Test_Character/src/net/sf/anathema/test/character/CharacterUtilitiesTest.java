package net.sf.anathema.test.character;

import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.dummy.character.trait.DummyGenericTraitCollection;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharacterUtilitiesTest extends BasicTestCase {

  private DummyGenericTraitCollection traitCollection;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.traitCollection = new DummyGenericTraitCollection();
  }

  public void testDodgeMdvWithEvenAttributeSum() throws Exception {
    traitCollection.setValue(AbilityType.Integrity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    traitCollection.setValue(OtherTraitType.Willpower, 4);
    //assertEquals(3, CharacterUtilties.getDodgeMdv(traitCollection));
  }

  public void testDodgeMdvWithOddAttributeSum() throws Exception {
    traitCollection.setValue(AbilityType.Integrity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    traitCollection.setValue(OtherTraitType.Willpower, 5);
    //assertEquals(3, CharacterUtilties.getDodgeMdv(traitCollection));
  }

  public void testMortalDodgeDvWithEssenceValueOne() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 2);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    //assertEquals(1, CharacterUtilties.getDodgeDv(CharacterType.MORTAL, traitCollection));
  }

  public void testMortalDodgeDvWithEssenceValueTwo() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 2);
    traitCollection.setValue(OtherTraitType.Essence, 2);
    //assertEquals(2, CharacterUtilties.getDodgeDv(CharacterType.MORTAL, traitCollection));
  }

  public void testExaltDodgeWithEssenceValueOne() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    //assertEquals(1, CharacterUtilties.getDodgeDv(CharacterType.SOLAR, traitCollection));
  }

  public void testExaltDodgeWithEssenceValueTwo() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 2);
    //assertEquals(2, CharacterUtilties.getDodgeDv(CharacterType.SOLAR, traitCollection));
  }

  public void testExaltDodgeOddSum() throws Exception {
    traitCollection.setValue(AbilityType.Dodge, 1);
    traitCollection.setValue(AttributeType.Dexterity, 2);
    traitCollection.setValue(OtherTraitType.Essence, 2);
    //assertEquals(3, CharacterUtilties.getDodgeDv(CharacterType.SOLAR, traitCollection));
  }
}