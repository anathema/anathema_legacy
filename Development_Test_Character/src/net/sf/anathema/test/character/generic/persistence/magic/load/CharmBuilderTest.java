package net.sf.anathema.test.character.generic.persistence.magic.load;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.Element;

public class CharmBuilderTest extends BasicTestCase implements ICharmXMLConstants {

  // Todo: Cost, Combos

  private Element charmElement;
  private ICharmBuilder charmBuilder = new CharmBuilder(
      new IdStringBuilder(),
      new TraitPrerequisitesBuilder(),
      new AttributeRequirementBuilder(),
      new ComboRulesBuilder(),
      new CharmPrerequisiteBuilder());

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.charmElement = CharmXmlTestUtils.createCharmElement("Solar.TestCharm"); //$NON-NLS-1$
  }

  private void removeAttribute(String attribute) {
    charmElement.remove(charmElement.attribute(attribute));
  }

  private void removeElement(String element) {
    charmElement.remove(charmElement.element(element));
  }

  public void testNoId() throws Exception {
    try {
      removeAttribute("id"); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (PersistenceException e) {
      // nothing to do
    }
  }

  public void testBadId() throws Exception {
    try {
      charmElement.addAttribute("id", ""); //$NON-NLS-1$ //$NON-NLS-2$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (PersistenceException e) {
      // nothing to do
    }
  }

  public void testNoExalt() throws Exception {
    try {
      removeAttribute("exalt"); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testExaltNonExistant() throws Exception {
    try {
      charmElement.addAttribute("exalt", "NonExistant"); //$NON-NLS-1$ //$NON-NLS-2$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testNoPrerequisitesAtAll() throws Exception {
    try {
      removeElement("prerequisite"); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (PersistenceException e) {
      // nothing to do
    }
  }

  public void testEmptyIllegalPrerequisite() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      Element prerequisiteElement = prerequisitesElement.element(TAG_TRAIT);
      prerequisiteElement.remove(prerequisiteElement.attribute("id")); //$NON-NLS-1$
      prerequisiteElement.remove(prerequisiteElement.attribute("value")); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testNoIdIllegalPrerequisite() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      Element prerequisiteElement = prerequisitesElement.element(TAG_TRAIT);
      prerequisiteElement.remove(prerequisiteElement.attribute("id")); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testBadIdIllegalPrerequisite() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      Element prerequisiteElement = prerequisitesElement.element("trait"); //$NON-NLS-1$
      prerequisiteElement.addAttribute("id", "BadId"); //$NON-NLS-1$ //$NON-NLS-2$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testNoValueIllegalPrerequisite() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      Element prerequisiteElement = prerequisitesElement.element(TAG_TRAIT);
      prerequisiteElement.remove(prerequisiteElement.attribute("value")); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testBadValueIllegalPrerequisite() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      Element prerequisiteElement = prerequisitesElement.element(TAG_TRAIT);
      prerequisiteElement.addAttribute("value", "BadValue"); //$NON-NLS-1$ //$NON-NLS-2$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testNoEssence() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      prerequisitesElement.remove(prerequisitesElement.element("essence")); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testBadEssence() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      prerequisitesElement.element("essence").addAttribute("value", "BadEssence"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // Nothing to do
    }
  }

  public void testPrerequisiteCharmNoId() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      prerequisitesElement.addElement(TAG_CHARM_REFERENCE);
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // Nothing to do
    }
  }

  public void testPrerequisiteCharmBadId() throws Exception {
    try {
      Element prerequisitesElement = charmElement.element("prerequisite"); //$NON-NLS-1$
      Element prerequisiteCharmElement = prerequisitesElement.addElement(TAG_CHARM_REFERENCE);
      prerequisiteCharmElement.addAttribute("id", ""); //$NON-NLS-1$ //$NON-NLS-2$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // Nothing to do
    }
  }

  public void testNoCharmTypeElement() throws Exception {
    try {
      removeElement(TAG_CHARMTYPE);
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testNoCharmTypeAttribute() throws Exception {
    try {
      Element typeElement = charmElement.element(TAG_CHARMTYPE);
      typeElement.remove(typeElement.attribute(ATTRIB_TYPE));
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testBadCharmTypeAttribute() throws Exception {
    try {
      Element typeElement = charmElement.element(TAG_CHARMTYPE);
      typeElement.addAttribute(ATTRIB_TYPE, "BadType"); //$NON-NLS-1$
      charmBuilder.buildCharm(charmElement);
      fail();
    }
    catch (CharmException e) {
      // nothing to do
    }
  }

  public void testCorrectCharmWithInstantDuration() throws Exception {
    Element durationElement = charmElement.element(TAG_DURATION);
    durationElement.addAttribute(ATTRIB_DURATION, "Instant"); //$NON-NLS-1$
    assertTrue(charmBuilder.buildCharm(charmElement).getDuration() == SimpleDuration.INSTANT_DURATION);
  }

  public void testCorrectCharmWithOtherDuration() throws Exception {
    assertFalse(charmBuilder.buildCharm(charmElement).getDuration() == SimpleDuration.PERMANENT_DURATION);
    assertFalse(charmBuilder.buildCharm(charmElement).getDuration() == SimpleDuration.INSTANT_DURATION);
  }
}