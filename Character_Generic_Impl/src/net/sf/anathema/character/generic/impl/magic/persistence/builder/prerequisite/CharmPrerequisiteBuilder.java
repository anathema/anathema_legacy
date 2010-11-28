package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmPrerequisiteBuilder implements ICharmPrerequisiteBuilder {

  public final String[] buildCharmPrerequisites(Element parent) throws CharmException {
    List<String> prerequisiteCharmIds = new ArrayList<String>();
    prerequisiteCharmIds.addAll(getCharmIds(parent));
    return prerequisiteCharmIds.toArray(new String[prerequisiteCharmIds.size()]);
  }

  protected Collection<String> getCharmIds(Element parent) throws CharmException {
    List<String> prerequisiteCharmIds = new ArrayList<String>();
    List<Element> prerequisiteCharmList = ElementUtilities.elements(parent, TAG_CHARM_REFERENCE);
    for (Element element : prerequisiteCharmList) {
      String id = element.attributeValue(ATTRIB_ID);
      if (StringUtilities.isNullOrEmpty(id)) {
        throw new CharmException("Prerequisite charm id is null or empty."); //$NON-NLS-1$
      }
      prerequisiteCharmIds.add(id);
    }
    return prerequisiteCharmIds;
  }
}