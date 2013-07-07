package net.sf.anathema.character.main.magic.persistence.builder;

import com.google.common.base.Strings;
import net.sf.anathema.character.main.magic.model.charm.CharmException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.TAG_GENERIC_CHARM_REFERENCE;

public class GenericCharmUtilities {

  public static Collection<String> getAllReferencedGenericCharms(Element parent, Identifier type) throws CharmException {
    List<String> charmIds = new ArrayList<>();
    List<Element> charmReferences = ElementUtilities.elements(parent, TAG_GENERIC_CHARM_REFERENCE);
    for (Element element : charmReferences) {
      String id = element.attributeValue(ATTRIB_ID);
      if (Strings.isNullOrEmpty(id)) {
        throw new CharmException("Prerequisite charm id is null or empty.");
      }
      charmIds.add(id + "." + type.getId());
    }
    return charmIds;
  }
}