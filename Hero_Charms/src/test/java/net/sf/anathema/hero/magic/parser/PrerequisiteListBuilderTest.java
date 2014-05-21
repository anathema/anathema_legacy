package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.character.magic.charm.ICharmXMLConstants;
import net.sf.anathema.character.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.magic.charm.prerequisite.DirectCharmLearnPrerequisite;
import net.sf.anathema.character.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.character.magic.parser.charms.prerequisite.AttributePrerequisiteBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrerequisiteListBuilderTest {
  private static Element createBasicCharmPrerequisiteXml() {
    Element prerequisiteListElement = DocumentHelper.createElement("prerequisiteListElement");
    CharmXmlTestUtils.fillBasicPrerequisites(prerequisiteListElement);
    return prerequisiteListElement;
  }

  private Element prerequisiteListElement = createBasicCharmPrerequisiteXml();
  private Map<String, CharmImpl> charmsById = new HashMap<>();

  @Test
  public void testCorrectCharmWithPrerequisiteCharm() throws Exception {
    String correctId = "Solar.CorrectId";
    createCharmWithId(correctId);
    addCharmWithIdToXml(prerequisiteListElement, correctId);
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertThatExpectedNumberOfPrerequisitesMatches(prerequisiteList, 1);
    DirectCharmLearnPrerequisite prerequisite = getPrerequisite(prerequisiteList, 0);
    prerequisite.link(charmsById);
    Charm actualParentCharm = prerequisite.getDirectPredecessors()[0];
    assertThat(actualParentCharm.getId(), is(correctId));
  }

  @Test
  public void testCorrectCharmWithMultiplePrerequisiteCharms() throws Exception {
    String correctId1 = "Solar.CorrectId1";
    String correctId2 = "Solar.CorrectId2";
    createCharmWithId(correctId1);
    createCharmWithId(correctId2);
    addCharmWithIdToXml(prerequisiteListElement, correctId1);
    addCharmWithIdToXml(prerequisiteListElement, correctId2);
    CharmPrerequisiteList prerequisiteList = parsePrerequisiteList(prerequisiteListElement);
    assertThatExpectedNumberOfPrerequisitesMatches(prerequisiteList, 2);
    DirectCharmLearnPrerequisite prerequisite1 = getPrerequisite(prerequisiteList, 0);
    DirectCharmLearnPrerequisite prerequisite2 = getPrerequisite(prerequisiteList, 1);
    prerequisite1.link(charmsById);
    prerequisite2.link(charmsById);
    Charm parentCharm1 = prerequisite1.getDirectPredecessors()[0];
    Charm parentCharm2 = prerequisite2.getDirectPredecessors()[0];
    assertThat(parentCharm1.getId(), is(correctId1));
    assertThat(parentCharm2.getId(), is(correctId2));
  }

  private DirectCharmLearnPrerequisite getPrerequisite(CharmPrerequisiteList prerequisiteList, int index) {
    CharmLearnPrerequisite charmLearnPrerequisite = prerequisiteList.getCharmPrerequisites()[index];
    return (DirectCharmLearnPrerequisite) charmLearnPrerequisite;
  }

  private CharmPrerequisiteList parsePrerequisiteList(Element prerequisiteListElement) throws PersistenceException {
    return new PrerequisiteListBuilder(new TraitPrerequisitesBuilder(), new AttributePrerequisiteBuilder(),
            new CharmPrerequisiteBuilder()).buildPrerequisiteList(prerequisiteListElement);
  }

  private CharmImpl createCharmWithId(String correctId) {
    CharmImpl charm = mock(CharmImpl.class);
    when(charm.getId()).thenReturn(correctId);
    charmsById.put(correctId, charm);
    return charm;
  }

  private void addCharmWithIdToXml(Element prerequisiteListElement, String correctId) {
    prerequisiteListElement.addElement(ICharmXMLConstants.TAG_CHARM_REFERENCE).addAttribute("id", correctId);
  }

  private void assertThatExpectedNumberOfPrerequisitesMatches(CharmPrerequisiteList prerequisiteList,
                                                              int expectedPrerequisites) {
    assertThat(prerequisiteList.getCharmPrerequisites().length, is(expectedPrerequisites));
  }
}