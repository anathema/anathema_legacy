package net.sf.anathema.test.character.abyssal.additional;

import net.sf.anathema.character.abyssal.additional.NecromancyLearnPool;
import net.sf.anathema.character.generic.magic.spells.CircleType;

public class NecromancyLearnPoolTest extends AbstractBackgroundRulesTest {
  private NecromancyLearnPool necromancyLearnPool;

  private void assertOnlyShadowlandsAllowed() {
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createCharm()));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Terrestrial)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Terrestrial)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Celestial)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Solar)));
    assertTrue(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Shadowlands)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Labyrinth)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Void)));
  }

  private void assertShadowlandsAndLabyrinthAllowed() {
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createCharm()));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Terrestrial)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Terrestrial)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Celestial)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Solar)));
    assertTrue(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Shadowlands)));
    assertTrue(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Labyrinth)));
    assertFalse(necromancyLearnPool.isAllowedFor(getTraitCollection(), createSpell(CircleType.Void)));
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    necromancyLearnPool = new NecromancyLearnPool(getBackgroundTemplate());
  }

  public void testBackgroundValue0() throws Exception {
    initTraitCollectionControlForValue(0);
    assertEquals(0, necromancyLearnPool.getAdditionalMagicCount(getTraitCollection()));
    assertOnlyShadowlandsAllowed();
  }

  public void testBackgroundValue1() throws Exception {
    initTraitCollectionControlForValue(1);
    assertEquals(1, necromancyLearnPool.getAdditionalMagicCount(getTraitCollection()));
    assertOnlyShadowlandsAllowed();
  }

  public void testBackgroundValue2() throws Exception {
    initTraitCollectionControlForValue(2);
    assertEquals(2, necromancyLearnPool.getAdditionalMagicCount(getTraitCollection()));
    assertOnlyShadowlandsAllowed();
  }

  public void testBackgroundValue3() throws Exception {
    initTraitCollectionControlForValue(3);
    assertEquals(3, necromancyLearnPool.getAdditionalMagicCount(getTraitCollection()));
    assertOnlyShadowlandsAllowed();
  }

  public void testBackgroundValue4() throws Exception {
    initTraitCollectionControlForValue(4);
    assertEquals(5, necromancyLearnPool.getAdditionalMagicCount(getTraitCollection()));
    assertShadowlandsAndLabyrinthAllowed();
  }

  public void testBackgroundValue5() throws Exception {
    initTraitCollectionControlForValue(5);
    assertEquals(7, necromancyLearnPool.getAdditionalMagicCount(getTraitCollection()));
    assertShadowlandsAndLabyrinthAllowed();
  }
}