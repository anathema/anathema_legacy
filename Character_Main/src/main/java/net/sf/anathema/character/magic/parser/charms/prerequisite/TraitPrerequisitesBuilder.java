package net.sf.anathema.character.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_TRAIT;

public class TraitPrerequisitesBuilder implements ITraitPrerequisitesBuilder {
  @Override
  public ValuedTraitType[] buildTraitPrerequisites(Element prerequisiteListElement) throws PersistenceException {
    List<ValuedTraitType> allPrerequisites = new ArrayList<>();
    ITraitPrerequisiteBuilder traitBuilder = new TraitPrerequisiteBuilder();
    for (Element element : ElementUtilities.elements(prerequisiteListElement, TAG_TRAIT)) {
      try {
        allPrerequisites.add(traitBuilder.build(element));
      } catch (Exception e) {
        throw new PersistenceException(e);
      }
    }
    return allPrerequisites.toArray(new ValuedTraitType[allPrerequisites.size()]);
  }
}