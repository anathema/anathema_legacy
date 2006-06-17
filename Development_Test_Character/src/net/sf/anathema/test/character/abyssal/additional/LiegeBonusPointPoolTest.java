package net.sf.anathema.test.character.abyssal.additional;

import net.sf.anathema.character.abyssal.additional.LiegeBonusPointPool;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.test.character.dummy.DummyGenericTrait;

public class LiegeBonusPointPoolTest extends AbstractBackgroundRulesTest {

  private LiegeBonusPointPool liegeBonusPointPool;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    liegeBonusPointPool = new LiegeBonusPointPool(getBackgroundTemplate());
  }

  public void testAllowedForMagic() throws Exception {
    assertTrue(liegeBonusPointPool.isAllowedForMagic(null, createCharm()));
    assertTrue(liegeBonusPointPool.isAllowedForMagic(null, createSpell(CircleType.Celestial)));
  }

  public void testAllowedForTrait() throws Exception {
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("AbyssalCommand"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Artifact"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Followers"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Influence"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Manse"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Necromancy"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Resources"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("UnderworldManse"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Whispers"), 0))); //$NON-NLS-1$
    assertFalse(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Liege"), 0))); //$NON-NLS-1$
    assertFalse(liegeBonusPointPool.isAllowedForTrait(getTraitCollection(), new DummyGenericTrait(
        new SimpleBackgroundTemplate("Spies"), 0))); //$NON-NLS-1$
    assertTrue(liegeBonusPointPool.isAllowedForMagic(null, createSpell(CircleType.Celestial)));
  }

  public void testBackgroundValue0() throws Exception {
    initTraitCollectionControlForValue(0);
    assertEquals(0, liegeBonusPointPool.getAmount(getTraitCollection()));
  }

  public void testBackgroundValue1() throws Exception {
    initTraitCollectionControlForValue(1);
    assertEquals(2, liegeBonusPointPool.getAmount(getTraitCollection()));
  }

  public void testBackgroundValue2() throws Exception {
    initTraitCollectionControlForValue(2);
    assertEquals(4, liegeBonusPointPool.getAmount(getTraitCollection()));
  }

  public void testBackgroundValue3() throws Exception {
    initTraitCollectionControlForValue(3);
    assertEquals(6, liegeBonusPointPool.getAmount(getTraitCollection()));
  }

  public void testBackgroundValue4() throws Exception {
    initTraitCollectionControlForValue(4);
    assertEquals(9, liegeBonusPointPool.getAmount(getTraitCollection()));
  }

  public void testBackgroundValue5() throws Exception {
    initTraitCollectionControlForValue(5);
    assertEquals(12, liegeBonusPointPool.getAmount(getTraitCollection()));
  }
}