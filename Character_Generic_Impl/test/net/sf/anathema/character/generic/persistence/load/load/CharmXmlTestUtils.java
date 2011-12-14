package net.sf.anathema.character.generic.persistence.load.load;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EXALT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_GROUP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PREREQUISITE_LIST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

public class CharmXmlTestUtils {

  public static Element createCharmElement(String charmId) {
    Element charmElement = new DefaultElement(TAG_CHARM);
    charmElement.addAttribute(ATTRIB_ID, charmId);
    charmElement.addAttribute(ATTRIB_EXALT, "Solar"); //$NON-NLS-1$
    charmElement.addAttribute(ATTRIB_GROUP, "AbilityGroup"); //$NON-NLS-1$
    Element prerequisiteListElement = charmElement.addElement(TAG_PREREQUISITE_LIST);
    fillBasicPrerequisites(prerequisiteListElement);
    charmElement.addElement(TAG_DURATION).addAttribute(ATTRIB_DURATION, "Duration"); //$NON-NLS-1$
    charmElement.addElement(TAG_CHARMTYPE).addAttribute(ATTRIB_TYPE, "Simple"); //$NON-NLS-1$
    return charmElement;
  }

  public static void fillBasicPrerequisites(Element prerequisiteListElement) {
    Element prerequisiteElement = prerequisiteListElement.addElement(TAG_TRAIT);
    prerequisiteElement.addAttribute("id", "Archery"); //$NON-NLS-1$ //$NON-NLS-2$
    prerequisiteElement.addAttribute("value", "4"); //$NON-NLS-1$ //$NON-NLS-2$
    prerequisiteListElement.addElement("essence").addAttribute("value", "3"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }
}