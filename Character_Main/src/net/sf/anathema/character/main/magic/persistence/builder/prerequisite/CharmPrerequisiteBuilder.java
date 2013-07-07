package net.sf.anathema.character.main.magic.persistence.builder.prerequisite;

import com.google.common.base.Strings;
import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.TAG_CHARM_REFERENCE;

public class CharmPrerequisiteBuilder implements ICharmPrerequisiteBuilder {

  @Override
  public final String[] buildCharmPrerequisites(Element parent) throws CharmException {
    List<String> prerequisiteCharmIds = new ArrayList<>();
    prerequisiteCharmIds.addAll(getCharmIds(parent));
    return prerequisiteCharmIds.toArray(new String[prerequisiteCharmIds.size()]);
  }

  protected Collection<String> getCharmIds(Element parent) throws CharmException {
    List<String> prerequisiteCharmIds = new ArrayList<>();
    List<Element> prerequisiteCharmList = ElementUtilities.elements(parent, TAG_CHARM_REFERENCE);
    for (Element element : prerequisiteCharmList) {
      String id = element.attributeValue(ATTRIB_ID);
      if (Strings.isNullOrEmpty(id)) {
        throw new CharmException("Prerequisite charm id is null or empty.");
      }
      prerequisiteCharmIds.add(id);
    }
    return prerequisiteCharmIds;
  }
}