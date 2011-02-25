package net.sf.anathema.character.generic.framework.xml.trait.allocation;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateParser;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AllocationMinimumTraitTemplateParser
{
  private static final String ATTRIB_COUNT = "count"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final ITraitTypeGroup traitTypeGroup;

  public AllocationMinimumTraitTemplateParser(ITraitTypeGroup traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public GenericRestrictedTraitTemplate[] parseAllocationMinimumTraits(Element element,
		  List<AllocationMinimumRestriction> list)
  throws PersistenceException {
    int count = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_COUNT);
    AllocationMinimumRestriction restriction = new AllocationMinimumRestriction(count, list);
    list.add(restriction);
    List<GenericRestrictedTraitTemplate> traitTemplates = new ArrayList<GenericRestrictedTraitTemplate>();
    for (Element traitElement : ElementUtilities.elements(element, TAG_TRAIT)) {
      GenericTraitTemplate template = GenericTraitTemplateParser.parseTraitTemplate(traitElement);
      ITraitType type = traitTypeGroup.getById(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
      traitTemplates.add(new GenericRestrictedTraitTemplate(template, restriction, type));
    }
    return traitTemplates.toArray(new GenericRestrictedTraitTemplate[traitTemplates.size()]);
  }
}