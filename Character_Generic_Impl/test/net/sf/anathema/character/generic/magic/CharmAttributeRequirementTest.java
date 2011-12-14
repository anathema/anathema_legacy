package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.util.IIdentificate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CharmAttributeRequirementTest {

  private CharmAttribute attribute;

  @Before
  public void createAttribute() {
    this.attribute = new CharmAttribute("Expected", false); //$NON-NLS-1$
  }

  @Test
  public void isNotFulfilledWithoutCharms() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 1);
    Assert.assertFalse(requirement.isFulfilled(new ICharm[0]));
  }

  @Test
  public void isFulfilledIfAttributeIsPresent() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 1);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(requirement.isFulfilled(new ICharm[] { charm }));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttribute() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 1);
    DummyCharm charm = new DummyCharm();
    Assert.assertFalse(requirement.isFulfilled(new ICharm[] { charm }));
  }

  @Test
  public void isNotFulfilledWithoutCorrectCount() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(requirement.isFulfilled(new ICharm[] { charm }));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttributesAndCount() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(requirement.isFulfilled(new ICharm[] { charm, new DummyCharm() }));
  }

  @Test
  public void isFulfilledEvenIfChainIsBroken() throws Exception {
    CharmAttributeRequirement requirement = new CharmAttributeRequirement(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(requirement.isFulfilled(new ICharm[] { charm, new DummyCharm(), charm }));
  }

  private DummyCharm createAttributedDummyCharm() {
    return new DummyCharm() {
      @Override
      public boolean hasAttribute(IIdentificate charmAttribute) {
        return true;
      }
    };
  }
}