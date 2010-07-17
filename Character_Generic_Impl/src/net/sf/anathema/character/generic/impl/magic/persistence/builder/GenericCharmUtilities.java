package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_GENERIC_CHARM_REFERENCE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericCharmUtilities {

  public static Collection<String> getAllReferencedGenericCharms(Element parent, ITraitType type) throws CharmException{
    List<String> charmIds = new ArrayList<String>();
    List<Element> charmReferences = ElementUtilities.elements(parent, TAG_GENERIC_CHARM_REFERENCE);
    for (Element element : charmReferences) {
      String id = element.attributeValue(ATTRIB_ID);
      if (StringUtilities.isNullOrEmpty(id)) {
        throw new CharmException("Prerequisite charm id is null or empty."); //$NON-NLS-1$
      }
      charmIds.add(id + "." + type.getId()); //$NON-NLS-1$
    }
    return charmIds;
  }
}