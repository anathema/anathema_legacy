package net.sf.anathema.test.character.generic.template;

import net.sf.anathema.character.generic.impl.additional.GenericMagicLearnPool;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.dummy.character.magic.DummySpell;
import net.sf.anathema.dummy.character.trait.DummyGenericTraitCollection;

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
}
