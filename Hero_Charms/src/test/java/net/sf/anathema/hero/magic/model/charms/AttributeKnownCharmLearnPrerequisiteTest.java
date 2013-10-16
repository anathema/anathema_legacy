package net.sf.anathema.hero.magic.model.charms;

import java.util.Arrays;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.basic.attribute.MagicAttributeImpl;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.AttributeKnownCharmLearnPrerequisite;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.lib.util.Identifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttributeKnownCharmLearnPrerequisiteTest {

  private MagicAttributeImpl attribute;

  @Before
  public void createAttribute() {
    this.attribute = new MagicAttributeImpl("Expected", false);
  }

  @Test
  public void isNotFulfilledWithoutCharms() throws Exception {
    AttributeKnownCharmLearnPrerequisite requirement = new AttributeKnownCharmLearnPrerequisite(attribute, 1);
    Assert.assertFalse(requirement.isSatisfied(getLearnArbiter(new Charm[0])));
  }

  @Test
  public void isFulfilledIfAttributeIsPresent() throws Exception {
    AttributeKnownCharmLearnPrerequisite requirement = new AttributeKnownCharmLearnPrerequisite(attribute, 1);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(requirement.isSatisfied(getLearnArbiter(new Charm[]{charm})));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttribute() throws Exception {
    AttributeKnownCharmLearnPrerequisite requirement = new AttributeKnownCharmLearnPrerequisite(attribute, 1);
    DummyCharm charm = new DummyCharm();
    Assert.assertFalse(requirement.isSatisfied(getLearnArbiter(new Charm[]{charm})));
  }

  @Test
  public void isNotFulfilledWithoutCorrectCount() throws Exception {
    AttributeKnownCharmLearnPrerequisite requirement = new AttributeKnownCharmLearnPrerequisite(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(requirement.isSatisfied(getLearnArbiter(new Charm[]{charm})));
  }

  @Test
  public void isNotFulfilledWithoutCorrectAttributesAndCount() throws Exception {
    AttributeKnownCharmLearnPrerequisite requirement = new AttributeKnownCharmLearnPrerequisite(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertFalse(requirement.isSatisfied(getLearnArbiter(new Charm[]{charm, new DummyCharm()})));
  }

  @Test
  public void isFulfilledEvenIfChainIsBroken() throws Exception {
    AttributeKnownCharmLearnPrerequisite requirement = new AttributeKnownCharmLearnPrerequisite(attribute, 2);
    DummyCharm charm = createAttributedDummyCharm();
    Assert.assertTrue(requirement.isSatisfied(getLearnArbiter(new Charm[]{charm, new DummyCharm(), charm})));
  }
  
  private ICharmLearnArbitrator getLearnArbiter(final Charm[] charms) {
	  return new ICharmLearnArbitrator() {

		@Override
		public boolean isLearned(Charm charm) {
			return Arrays.asList(charms).contains(charm);
		}
			
		@Override
		public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold) {
			return false;
		}
		  
	  };
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