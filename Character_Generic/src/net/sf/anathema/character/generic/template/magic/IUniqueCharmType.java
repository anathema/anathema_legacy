package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.lib.util.IIdentificate;

public interface IUniqueCharmType {
  IIdentificate getId();

  String getLabel();

  boolean keywordMatches(String id);
}