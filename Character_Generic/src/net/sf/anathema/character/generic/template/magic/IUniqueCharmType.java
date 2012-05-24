package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.lib.util.Identified;

public interface IUniqueCharmType {
  Identified getId();

  String getLabel();

  boolean keywordMatches(String id);
}