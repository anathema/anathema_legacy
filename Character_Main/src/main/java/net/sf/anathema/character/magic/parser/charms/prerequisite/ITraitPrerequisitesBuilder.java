package net.sf.anathema.character.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ITraitPrerequisitesBuilder {

  ValuedTraitType[] buildTraitPrerequisites(Element prerequisiteListElement) throws PersistenceException;
}