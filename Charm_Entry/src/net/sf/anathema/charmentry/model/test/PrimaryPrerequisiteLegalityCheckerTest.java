package net.sf.anathema.charmentry.model.test;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.model.PrimaryPrerequisiteLegalityChecker;
import net.sf.anathema.lib.testing.BasicTestCase;

public class PrimaryPrerequisiteLegalityCheckerTest extends BasicTestCase {

  private PrimaryPrerequisiteLegalityChecker checker;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    checker = new PrimaryPrerequisiteLegalityChecker();
  }

  public void testOtherTraitTypeRejected() throws Exception {
    IGenericTrait prerequisite = new ValuedTraitType(OtherTraitType.Essence, 3);
    assertFalse(checker.isLegalPrimaryPrerequisite(CharacterType.SOLAR, prerequisite));
  }

  public void testNullTypeRejected() throws Exception {
    IGenericTrait prerequisite = new ValuedTraitType(null, 3);
    assertFalse(checker.isLegalPrimaryPrerequisite(CharacterType.SOLAR, prerequisite));
  }

  public void testNullCharacterNullTypeRejected() throws Exception {
    IGenericTrait prerequisite = new ValuedTraitType(null, 3);
    assertFalse(checker.isLegalPrimaryPrerequisite(null, prerequisite));
  }

  public void testAttributesLegalForNullCharacterType() throws Exception {
    IGenericTrait prerequisite = getAttributePrerequisite();
    assertTrue(checker.isLegalPrimaryPrerequisite(null, prerequisite));
  }

  public void testAbilitiesLegalForNullCharacterType() throws Exception {
    IGenericTrait prerequisite = getAbilityPrerequisite();
    assertTrue(checker.isLegalPrimaryPrerequisite(null, prerequisite));
  }

  public void testAttributesIllegalForAbilityBasedCharacter() throws Exception {
    IGenericTrait prerequisite = getAttributePrerequisite();
    assertFalse(checker.isLegalPrimaryPrerequisite(CharacterType.SOLAR, prerequisite));
    assertFalse(checker.isLegalPrimaryPrerequisite(CharacterType.DB, prerequisite));
  }

  public void testAbilitiesIllegalForLunars() throws Exception {
    IGenericTrait prerequisite = getAbilityPrerequisite();
    assertFalse(checker.isLegalPrimaryPrerequisite(CharacterType.LUNAR, prerequisite));
  }

  private IGenericTrait getAbilityPrerequisite() {
    return new ValuedTraitType(AbilityType.Survival, 3);
  }

  private IGenericTrait getAttributePrerequisite() {
    return new ValuedTraitType(AttributeType.Dexterity, 3);
  }
}