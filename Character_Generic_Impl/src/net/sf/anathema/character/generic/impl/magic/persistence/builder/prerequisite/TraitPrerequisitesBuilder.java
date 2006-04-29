package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class TraitPrerequisitesBuilder {
  public IGenericTrait[] buildTraitPrerequisites(Element prerequisiteListElement) throws PersistenceException {
    List<IGenericTrait> allPrerequisites = new ArrayList<IGenericTrait>();
    TraitPrerequisiteBuilder traitBuilder = new TraitPrerequisiteBuilder();
    for (Element element : ElementUtilities.elements(prerequisiteListElement, TAG_TRAIT)) {
      try {
        allPrerequisites.add(traitBuilder.build(element));
      }
      catch (Exception e) {
        throw new PersistenceException(e);
      }
    }
    return allPrerequisites.toArray(new IGenericTrait[allPrerequisites.size()]);
  }
}