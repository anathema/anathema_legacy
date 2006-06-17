package net.sf.anathema.test.character;

import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
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
    assertEquals(3, CharacterUtilties.getDodgeMdv(traitCollection));
  }

  public void testDodgeMdvWithOddAttributeSum() throws Exception {
    traitCollection.setValue(AbilityType.Integrity, 1);
    traitCollection.setValue(OtherTraitType.Essence, 1);
    traitCollection.setValue(OtherTraitType.Willpower, 5);
    assertEquals(3, CharacterUtilties.getDodgeMdv(traitCollection));
  }
}