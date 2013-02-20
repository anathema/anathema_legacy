package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.SpecialCharmBuilder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.*;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.*;

public class GenericCharmSetBuilder extends AbstractCharmSetBuilder {
  private final GenericCharmBuilder genericsBuilder;
  private ITraitType[] types;

  public GenericCharmSetBuilder(CharacterTypes characterTypes, SpecialCharmBuilder specialCharmBuilder) {
    this.genericsBuilder = new GenericCharmBuilder(
        new GenericIdStringBuilder(),
        new GenericTraitPrerequisitesBuilder(),
        new GenericAttributeRequirementBuilder(),
        new GenericComboRulesBuilder(),
        new GenericCharmPrerequisiteBuilder(), characterTypes, specialCharmBuilder);
  }

  @Override
  protected void buildCharms(Collection<Charm> allCharms, List<ISpecialCharm> specialCharms, Element charmListElement) throws PersistenceException {
    List<Element> elements = ElementUtilities.elements(charmListElement, TAG_GENERIC_CHARM);
    Map<Element, Set<String>> traitSets = new HashMap<>();
    if (elements.isEmpty()) {
      return;
    }
    for (Element charmElementObject : elements) {
      List<Element> traitSetElements = ElementUtilities.elements(charmElementObject, TAG_GENERIC_TRAIT_SET);
      if (traitSetElements.isEmpty()) {
        continue;
      }
      Set<String> traits = new HashSet<>();
      for (Element traitSetObject : traitSetElements) {
        for (Element traitObject : ElementUtilities.elements(traitSetObject, TAG_GENERIC_TRAIT)) {
          traits.add(ElementUtilities.getRequiredAttrib(traitObject, ATTRIB_ID));
        }
      }
      traitSets.put(charmElementObject, traits);
    }
    for (ITraitType type : types) {
      genericsBuilder.setType(type);
      for (Element charmElementObject : elements) {
        if (!traitSets.containsKey(charmElementObject) || traitSets.get(charmElementObject).contains(type.getId())) {
          createCharm(allCharms, specialCharms, genericsBuilder, charmElementObject); 
        }
      }
    }
  }

  public void setTypes(ITraitType[] types) {
    this.types = types;
  }
}