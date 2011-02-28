package net.sf.anathema.character.generic.framework.xml.trait.alternate;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateParser;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AlternateMinimumTraitTemplateParser {

  private static final String ATTRIB_COUNT = "count"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final ITraitTypeGroup traitTypeGroup;

  public AlternateMinimumTraitTemplateParser(ITraitTypeGroup traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public GenericRestrictedTraitTemplate[] parseAlternateMinimumTraits(Element element) throws PersistenceException {
    int count = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_COUNT);
    int value = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(count, value);
    List<GenericRestrictedTraitTemplate> traitTemplates = new ArrayList<GenericRestrictedTraitTemplate>();
    for (Element traitElement : ElementUtilities.elements(element, TAG_TRAIT)) {
      GenericTraitTemplate template = GenericTraitTemplateParser.parseTraitTemplate(traitElement);
      ITraitType type = traitTypeGroup.getById(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
      traitTemplates.add(new GenericRestrictedTraitTemplate(template, restriction, type));
    }
    return traitTemplates.toArray(new GenericRestrictedTraitTemplate[traitTemplates.size()]);
  }
}