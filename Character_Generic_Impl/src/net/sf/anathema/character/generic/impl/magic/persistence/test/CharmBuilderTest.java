package net.sf.anathema.character.generic.impl.magic.persistence.test;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.Element;

public class CharmBuilderTest extends BasicTestCase implements ICharmXMLConstants {

  // Todo: Cost, Combos

  private Element charmElement;
  private ICharmBuilder charmBuilder = new CharmBuilder();

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

  public void testNoGroup() throws Exception {
    try {
      removeAttribute("group"); //$NON-NLS-1$
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
    catch (IllegalArgumentException e) {
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
    if (charmBuilder.buildCharm(charmElement).getDuration().getType() != DurationType.Instant) {
      fail();
    }
  }

  public void testCorrectCharmWithOtherDuration() throws Exception {
    if (charmBuilder.buildCharm(charmElement).getDuration().getType() != DurationType.Other) {
      fail();
    }
  }

  public void testCorrectCharmWithBadSource() throws Exception {
    charmElement.addElement("source"); //$NON-NLS-1$
    charmBuilder.buildCharm(charmElement);
  }

  public void testCorrectCharmWithSourceOnly() throws Exception {
    Element sourceElement = charmElement.addElement(TAG_SOURCE);
    sourceElement.addAttribute(ATTRIB_SOURCE, "Source"); //$NON-NLS-1$
    charmBuilder.buildCharm(charmElement);
  }

  public void testCorrectCharmWithPageOnly() throws Exception {
    Element sourceElement = charmElement.addElement(TAG_SOURCE);
    sourceElement.addAttribute(ATTRIB_PAGE, "pagenumber"); //$NON-NLS-1$
    ICharm charm = charmBuilder.buildCharm(charmElement);
    assertEquals(charm.getSource().getSource(), "Custom"); //$NON-NLS-1$
    assertNull(charm.getSource().getPage());
  }

  public void testCorrectCharmWithCompleteSource() throws Exception {
    Element sourceElement = charmElement.addElement(TAG_SOURCE);
    String source = "Source"; //$NON-NLS-1$
    sourceElement.addAttribute(ATTRIB_SOURCE, source);
    String page = "pagenumber"; //$NON-NLS-1$
    sourceElement.addAttribute(ATTRIB_PAGE, page);
    ICharm charm = charmBuilder.buildCharm(charmElement);
    assertEquals(source, charm.getSource().getSource());
    assertEquals(page, charm.getSource().getPage());
  }
}