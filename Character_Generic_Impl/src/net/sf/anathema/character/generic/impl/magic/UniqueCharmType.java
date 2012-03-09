package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class UniqueCharmType implements IUniqueCharmType {
  private final String label;
  private final String type;
  private final String keyword;

  public UniqueCharmType(String type, String label, String keyword) {
    this.keyword = keyword;
    this.label = label;
    this.type = type;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public boolean keywordMatches(String id) {
    return keyword.equals(id);
  }

  @Override
  public IIdentificate getId() {
    return new Identificate(type);
  }
}