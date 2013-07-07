package net.sf.anathema.character.main.xml.abilitygroup;

import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.traits.groups.ITraitTypeGroup;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TraitTypeGroupTemplateParser extends AbstractXmlTemplateParser<GenericGroupedTraitTypeProvider> {

  private static final String ATTRIB_ID = "id";
  private static final String CASTE_ID = "casteId";
  private static final String ATTRIB_TYPE = "type";
  private static final String TAG_GROUP = "group";
  private static final String TAG_TRAIT = "trait";
  private final ITraitTypeGroup traitTypeGroup;

  public TraitTypeGroupTemplateParser(IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> templateRegistry, ITraitTypeGroup traitTypeGroup) {
    super(templateRegistry);
    this.traitTypeGroup = traitTypeGroup;
  }

  @Override
  public GenericGroupedTraitTypeProvider parseTemplate(Element element) throws PersistenceException {
    GenericGroupedTraitTypeProvider abilityGroupProvider = getBasicTemplate(element);
    updateGroups(element, abilityGroupProvider);
    return abilityGroupProvider;
  }

  private void updateGroups(Element element, GenericGroupedTraitTypeProvider abilityGroupProvider) {
    List<Element> groups = ElementUtilities.elements(element, TAG_GROUP);
    for (Element group : groups) {
      String groupId = group.attributeValue(ATTRIB_ID);
      String groupCasteId = group.attributeValue(CASTE_ID);
      List<Element> traitElements = ElementUtilities.elements(group, TAG_TRAIT);
      for (Element traitElement : traitElements) {
        List<String> traitCastes = new ArrayList<>();
        String attributeTypeValue = traitElement.attributeValue(ATTRIB_TYPE);
        if (traitElement.attributeValue(CASTE_ID) != null) {
          Collections.addAll(traitCastes, traitElement.attributeValue(CASTE_ID).split(","));
        } else if (groupCasteId != null) {
          traitCastes.add(groupCasteId);
        }
        abilityGroupProvider.addGroupedAbilityType(attributeTypeValue, groupId, traitCastes);
      }
    }
  }

  @Override
  protected GenericGroupedTraitTypeProvider createNewBasicTemplate() {
    return new GenericGroupedTraitTypeProvider(traitTypeGroup);
  }
}