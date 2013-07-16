package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charmtree.CharmAttributeRequirement;
import net.sf.anathema.character.main.magic.model.attribute.MagicAttributeImpl;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CharmAttributeRequirementTest {

  private MagicAttributeImpl attribute;

  @Before
  public void createAttribute() {
    this.attribute = new MagicAttributeImpl("Expected", false);
  }

  @Test
  public void isNotFulfilledWithoutCharms() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 1);
    Assert.assertFalse(requirement.isFulfilled(new Charm[0]));
  }

  @Test
  public void isFulfilledIfAttributeIsPresent() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 1);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(requirement.isFulfilled(new Charm[]{charm}));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttribute() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 1);
    DummyCharm charm = new DummyCharm();
    Assert.assertFalse(requirement.isFulfilled(new Charm[]{charm}));
  }

  @Test
  public void isNotFulfilledWithoutCorrectCount() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(requirement.isFulfilled(new Charm[]{charm}));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttributesAndCount() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(requirement.isFulfilled(new Charm[]{charm, new DummyCharm()}));
  }

  @Test
  public void isFulfilledEvenIfChainIsBroken() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(requirement.isFulfilled(new Charm[]{charm, new DummyCharm(), charm}));
  }

  private DummyCharm createAttributedDummyCharm() {
    return new DummyCharm() {
      @Override
      public boolean hasAttribute(Identifier charmAttribute) {
        return true;
      }
    };
  }
}