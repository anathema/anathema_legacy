package net.sf.anathema.hero.combat;

import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.character.framework.library.HeroStatsModifiers;
import net.sf.anathema.hero.combat.model.CharacterUtilities;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.trait.DummyTrait;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CharacterUtilitiesTest {

  private DefaultTraitMap traitCollection = new DefaultTraitMap();
  private HeroStatsModifiers modifiers = mock(HeroStatsModifiers.class);

  @Test
  public void testDodgeMdvWithEvenAttributeSum() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Integrity, 1));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 1));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Willpower, 4));
    Assert.assertEquals(3, CharacterUtilities.getDodgeMdv(traitCollection, modifiers));
  }

  @Test
  public void testDodgeMdvWithOddAttributeSum() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Integrity, 1));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 1));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Willpower, 5));
    assertEquals(3, CharacterUtilities.getDodgeMdv(traitCollection, modifiers));
  }

  @Test
  public void testMortalDodgeDvWithEssenceValueOne() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Dodge, 1));
    traitCollection.addTraits(new DummyTrait(AttributeType.Dexterity, 2));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 1));
    assertEquals(1, CharacterUtilities.getDodgeDv(new DummyMundaneCharacterType(), traitCollection, modifiers));
  }

  @Test
  public void testMortalDodgeDvWithEssenceValueTwo() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Dodge, 1));
    traitCollection.addTraits(new DummyTrait(AttributeType.Dexterity, 2));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 2));
    assertEquals(2, CharacterUtilities.getDodgeDv(new DummyMundaneCharacterType(), traitCollection, modifiers));
  }

  @Test
  public void testExaltDodgeWithEssenceValueOne() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Dodge, 1));
    traitCollection.addTraits(new DummyTrait(AttributeType.Dexterity, 1));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 1));
    assertEquals(1, CharacterUtilities.getDodgeDv(new DummyExaltCharacterType(), traitCollection, modifiers));
  }

  @Test
  public void testExaltDodgeWithEssenceValueTwo() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Dodge, 1));
    traitCollection.addTraits(new DummyTrait(AttributeType.Dexterity, 1));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 2));
    assertEquals(2, CharacterUtilities.getDodgeDv(new DummyExaltCharacterType(), traitCollection, modifiers));
  }

  @Test
  public void testExaltDodgeOddSum() throws Exception {
    traitCollection.addTraits(new DummyTrait(AbilityType.Dodge, 1));
    traitCollection.addTraits(new DummyTrait(AttributeType.Dexterity, 2));
    traitCollection.addTraits(new DummyTrait(OtherTraitType.Essence, 2));
    assertEquals(3, CharacterUtilities.getDodgeDv(new DummyExaltCharacterType(), traitCollection, modifiers));
  }
}