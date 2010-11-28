package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_GENERIC_CHARM;

import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericCharmSetBuilder extends AbstractCharmSetBuilder {
  private final GenericCharmBuilder genericsBuilder = new GenericCharmBuilder(
      new GenericIdStringBuilder(),
      new GenericTraitPrerequisitesBuilder(),
      new GenericAttributeRequirementBuilder(),
      new GenericComboRulesBuilder(),
      new GenericCharmPrerequisiteBuilder());
  private ITraitType[] types;

  @Override
  protected void buildCharms(Collection<Charm> allCharms, Element charmListElement) throws PersistenceException {
    final List<Element> elements = ElementUtilities.elements(charmListElement, TAG_GENERIC_CHARM);
    if (elements.isEmpty()) {
      return;
    }
    for (ITraitType type : types) {
      genericsBuilder.setType(type);
      for (Element charmElementObject : elements) {
        createCharm(allCharms, genericsBuilder, charmElementObject);
      }
    }
  }

  public void setTypes(ITraitType[] types) {
    this.types = types;
  }
}