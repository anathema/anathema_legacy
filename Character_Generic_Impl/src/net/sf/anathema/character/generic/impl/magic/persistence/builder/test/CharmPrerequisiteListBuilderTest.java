package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.CharmPrerequisiteListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.magic.persistence.test.CharmXmlTestUtils;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CharmPrerequisiteListBuilderTest extends BasicTestCase {

  private static Element createBasicCharmPrerequisiteXml() {
    Element prerequisiteListElement = DocumentHelper.createElement("prerequisiteListElement"); //$NON-NLS-1$
    CharmXmlTestUtils.fillBasicPrerequisites(prerequisiteListElement);
    return prerequisiteListElement;
  }

  private static CharmPrerequisiteList parsePrerequisiteList(Element prerequisiteListElement)
      throws PersistenceException {
    return new CharmPrerequisiteListBuilder().buildPrerequisiteList(prerequisiteListElement);
  }

  public void testCorrectCharmWithMultiplePrerequisiteCharms() throws Exception {
    Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
    String correctId1 = "Solar.CorrectId1"; //$NON-NLS-1$
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId1); //$NON-NLS-1$
    String correctId2 = "Solar.CorrectId2"; //$NON-NLS-1$
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId2); //$NON-NLS-1$
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertEquals(correctId1, prerequisiteList.getParentIDs()[0]);
    assertEquals(correctId2, prerequisiteList.getParentIDs()[1]);
  }

  public void testCorrectCharmWithPrerequisiteCharm() throws Exception {
    Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
    String correctId = "Solar.CorrectId"; //$NON-NLS-1$
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId); //$NON-NLS-1$
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertEquals(correctId, prerequisiteList.getParentIDs()[0]);
  }
}