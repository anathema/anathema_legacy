package net.sf.anathema.character.main.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ITraitPrerequisiteBuilder {

  ValuedTraitType build(Element element) throws PersistenceException;

}