package net.sf.anathema.character.main.xml.trait.alternate;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.TraitTypeList;
import net.sf.anathema.character.main.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateParser;
import net.sf.anathema.character.main.xml.trait.IClonableTraitTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class AlternateMinimumTraitTemplateParser {

  private static final String ATTRIB_COUNT = "count";
  private static final String ATTRIB_VALUE = "value";
  private static final String TAG_TRAIT = "trait";
  private static final String ATTRIB_ID = "id";
  private static final String TAG_FREEBIE = "isFreebie";
  private final TraitTypeList traitTypeGroup;

  public AlternateMinimumTraitTemplateParser(TraitTypeList traitTypeGroup) {
    this.traitTypeGroup = traitTypeGroup;
  }

  public GenericRestrictedTraitTemplate[] parseAlternateMinimumTraits(Element element) throws PersistenceException {
    int count = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_COUNT);
    int value = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(count, value);
    restriction.setIsFreebie(ElementUtilities.getBooleanAttribute(element, TAG_FREEBIE, false));
    List<GenericRestrictedTraitTemplate> traitTemplates = new ArrayList<>();
    for (Element traitElement : ElementUtilities.elements(element, TAG_TRAIT)) {
      IClonableTraitTemplate template = GenericTraitTemplateParser.parseTraitTemplate(traitElement);
      TraitType type = traitTypeGroup.getById(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
      traitTemplates.add(new GenericRestrictedTraitTemplate(template, restriction, type));
    }
    return traitTemplates.toArray(new GenericRestrictedTraitTemplate[traitTemplates.size()]);
  }

  public GenericRestrictedTraitTemplate[] parseAlternateMinimumTraitsSoft(Element element) throws PersistenceException {
    int count = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_COUNT);
    int value = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    AlternateMinimumRestriction restriction = new AlternateMinimumRestriction(count, value);
    List<GenericRestrictedTraitTemplate> traitTemplates = new ArrayList<>();
    for (Element traitElement : ElementUtilities.elements(element, TAG_TRAIT)) {
      GenericTraitTemplate template = GenericTraitTemplateParser.parseTraitTemplateSoft(traitElement);
      TraitType type = traitTypeGroup.getById(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
      traitTemplates.add(new GenericRestrictedTraitTemplate(template, restriction, type));
    }
    return traitTemplates.toArray(new GenericRestrictedTraitTemplate[traitTemplates.size()]);
  }
}