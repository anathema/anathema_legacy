package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.ITraitType;

import org.dom4j.Element;

public class GenericIdStringBuilder extends IdStringBuilder implements IIdStringBuilder {

  private final ITraitType type;

  public GenericIdStringBuilder(ITraitType type) {
    this.type = type;
  }

  @Override
  public String build(Element element) throws CharmException {
    return super.build(element) + type.getId();
  }
}