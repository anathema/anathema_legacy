package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public interface ITraitPrerequisiteBuilder {

  GenericTrait build(Element element) throws PersistenceException;

}