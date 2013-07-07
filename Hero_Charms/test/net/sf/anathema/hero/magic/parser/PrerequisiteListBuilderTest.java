package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.main.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrerequisiteListBuilderTest {

  private static Element createBasicCharmPrerequisiteXml() {
    Element prerequisiteListElement = DocumentHelper.createElement("prerequisiteListElement");
    CharmXmlTestUtils.fillBasicPrerequisites(prerequisiteListElement);
    return prerequisiteListElement;
  }

  private static CharmPrerequisiteList parsePrerequisiteList(Element prerequisiteListElement) throws PersistenceException {
    return new PrerequisiteListBuilder(new TraitPrerequisitesBuilder(), new AttributeRequirementBuilder(), new CharmPrerequisiteBuilder())
            .buildPrerequisiteList(prerequisiteListElement);
  }

  @Test
  public void testCorrectCharmWithMultiplePrerequisiteCharms() throws Exception {
    Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
    String correctId1 = "Solar.CorrectId1";
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId1);
    String correctId2 = "Solar.CorrectId2";
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId2);
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertEquals(correctId1, prerequisiteList.getParentIDs()[0]);
    assertEquals(correctId2, prerequisiteList.getParentIDs()[1]);
  }

  @Test
  public void testCorrectCharmWithPrerequisiteCharm() throws Exception {
    Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
    String correctId = "Solar.CorrectId";
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId);
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertEquals(correctId, prerequisiteList.getParentIDs()[0]);
  }
}