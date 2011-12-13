package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.dummy.DummyGenericTraitCollection;
import net.sf.anathema.character.generic.dummy.magic.DummySpell;
import net.sf.anathema.character.generic.impl.additional.GenericMagicLearnPool;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.util.IPointModification;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericMagicLearnPoolTest {

  private DummyGenericTraitCollection collection;
  private GenericMagicLearnPool pool;
  private CustomizedBackgroundTemplate background;

  @Before
  public void setUp() {
    this.background = new CustomizedBackgroundTemplate("Background");//$NON-NLS-1$
    this.pool = new GenericMagicLearnPool(background, true);
    this.collection = new DummyGenericTraitCollection();
    collection.setValue(background, 0);
  }

  @Test
  public void defaultMaximumCircle() throws Exception {

    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Terrestrial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
  }

  @Test
  public void exceptionChangesResponse() throws Exception {
    this.pool = new GenericMagicLearnPool(background, false);
    String string = "Test"; //$NON-NLS-1$
    DummySpell dummySpell = new DummySpell(string);
    dummySpell.setCircleType(CircleType.Terrestrial);
    pool.addIdException(string);
    Assert.assertTrue(pool.isAllowedFor(collection, dummySpell));
  }

  @Test
  public void setMaximumCircle() throws Exception {
    pool.setMaximumCircle(CircleType.Celestial);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Terrestrial)));
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Solar)));
  }

  @Test
  public void setAttachCondition() throws Exception {
    pool.setMaximumCircle(CircleType.Celestial);
    pool.attachCondition(CircleType.Celestial, 3);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Terrestrial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    collection.setValue(background, 3);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Solar)));
  }

  @Test
  public void setConditionReachesHigherCircles() throws Exception {
    pool.setMaximumCircle(CircleType.Solar);
    pool.attachCondition(CircleType.Celestial, 3);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Terrestrial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    collection.setValue(background, 3);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Solar)));
    collection.setValue(background, 2);
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Solar)));
  }

  @Test
  public void necromancyNotIncludedInSorcery() throws Exception {
    pool.setMaximumCircle(CircleType.Solar);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Terrestrial)));
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Solar)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Shadowlands)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Labyrinth)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Void)));
  }

  @Test
  public void sorceryNotIncludedInNecromancy() throws Exception {
    pool.setMaximumCircle(CircleType.Void);
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Shadowlands)));
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Labyrinth)));
    Assert.assertTrue(pool.isAllowedFor(collection, new DummySpell(CircleType.Void)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Terrestrial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Celestial)));
    Assert.assertFalse(pool.isAllowedFor(collection, new DummySpell(CircleType.Solar)));
  }

  @Test
  public void testUnmodifiedPointAllowance() {
    assertValueEqualsCount(0);
    assertValueEqualsCount(1);
    assertValueEqualsCount(2);
    assertValueEqualsCount(3);
    assertValueEqualsCount(4);
    assertValueEqualsCount(5);
  }

  private void assertValueEqualsCount(int count) {
    assertDotsForValue(count, count);
  }

  private void assertDotsForValue(int value, int count) {
    collection.setValue(background, value);
    Assert.assertEquals(count, pool.getAdditionalMagicCount(collection));
  }

  @Test
  public void testModifiedValue() {
    pool.setCostModification(new IPointModification() {
      public int getAdditionalPoints(int value) {
        return Math.max(0, value - 3);
      }
    });
    assertDotsForValue(0,0);
    assertDotsForValue(1,1);
    assertDotsForValue(2,2);
    assertDotsForValue(3,3);
    assertDotsForValue(4,5);
    assertDotsForValue(5,7);
  }
}