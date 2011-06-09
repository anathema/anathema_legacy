package net.sf.anathema.character.generic.framework.xml.trait.caste;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.xml.trait.GenericRestrictedTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateParser;
import net.sf.anathema.character.generic.framework.xml.trait.allocation.AllocationMinimumRestriction;
import net.sf.anathema.character.generic.framework.xml.trait.allocation.AllocationMinimumTraitTemplateParser;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.AlternateMinimumTraitTemplateParser;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CasteMinimumTraitTemplateParser
{
	  private static final String ATTRIB_CASTE = "caste";
	  private static final String TAG_SPECIAL_TRAIT = "specialTrait"; //$NON-NLS-1$
	  private static final String TAG_ALLOCATION_MINIMUM_TRAITS = "allocationMinimumTraits";
	  private static final String TAG_ALTERNATE_MINMUM_TRAITS = "alternateMinimumTraits"; //$NON-NLS-1$
	  private final ITraitTypeGroup type;
	  private String caste;
	  
  public CasteMinimumTraitTemplateParser(ITraitTypeGroup type)
  {
	  this.type = type;
  }

  public GenericRestrictedTraitTemplate[] parseCasteMinimumTraits(Element element,
		  List<AllocationMinimumRestriction> list)
  {
	  GenericRestrictedTraitTemplate[] templates = null;
	  List<GenericRestrictedTraitTemplate> limits = new ArrayList<GenericRestrictedTraitTemplate>();
	  try
	  {
		  caste = element.attributeValue(ATTRIB_CASTE);
		  parseSpecialTraitTemplates(limits, element);
		  parseAllocationMinimumTraitTemplates(limits, element, list);
	  	  parseAlternateMinimumTraitTemplates(limits, element);
	  	  
	  	  templates = new GenericRestrictedTraitTemplate[limits.size()];
		  limits.toArray(templates);
	  }
	  catch (PersistenceException e)
	  {
		  return new GenericRestrictedTraitTemplate[0];
	  }
	  return templates;
  }
  
  private void parseSpecialTraitTemplates(List<GenericRestrictedTraitTemplate> pool, Element element) throws PersistenceException {
	    for (Element specialTraitElement : ElementUtilities.elements(element, TAG_SPECIAL_TRAIT)) {
	      GenericTraitTemplate specialTraitTemplate = GenericTraitTemplateParser.parseTraitTemplateSoft(specialTraitElement);
	      String traitTypeId = ElementUtilities.getRequiredAttrib(specialTraitElement, "id"); //$NON-NLS-1$
	      pool.add(new GenericRestrictedTraitTemplate(specialTraitTemplate,
	    		  new CasteMinimumRestriction(caste, specialTraitTemplate.getMinimumValue(null)), type.getById(traitTypeId)));
	      specialTraitTemplate.setMinimumValue(0);
	    }
	  }

	  private void parseAlternateMinimumTraitTemplates(List<GenericRestrictedTraitTemplate> pool, Element element)
	      throws PersistenceException {
		  AlternateMinimumTraitTemplateParser parser = new AlternateMinimumTraitTemplateParser(type);
		    for (Element specialTraitElement : ElementUtilities.elements(element, TAG_ALTERNATE_MINMUM_TRAITS)) {
		      for (GenericRestrictedTraitTemplate template : parser.parseAlternateMinimumTraitsSoft(specialTraitElement)) {
		        pool.add(new GenericRestrictedTraitTemplate(template.getTemplate(),
		        	new CasteMinimumRestriction(caste, template.getRestrictions().get(0)), template.getTraitType()));
		      }
		    }
	  }
	  
	  private void parseAllocationMinimumTraitTemplates(List<GenericRestrictedTraitTemplate> pool, Element element,
			  List<AllocationMinimumRestriction> list)
	  throws PersistenceException {
		  AllocationMinimumTraitTemplateParser parser = new AllocationMinimumTraitTemplateParser(type);
		    for (Element specialTraitElement : ElementUtilities.elements(element, TAG_ALLOCATION_MINIMUM_TRAITS)) {
		      for (GenericRestrictedTraitTemplate template : parser.parseAllocationMinimumTraits(specialTraitElement, list)) {
		        pool.add(new GenericRestrictedTraitTemplate(template.getTemplate(),
		        	new CasteMinimumRestriction(caste, template.getRestrictions().get(0)), template.getTraitType()));
		      }
		    }
		}
}