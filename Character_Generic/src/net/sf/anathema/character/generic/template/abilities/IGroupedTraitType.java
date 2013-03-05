package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.List;

public interface IGroupedTraitType {

  ITraitType getTraitType();

  String getGroupId();

  List<String> getTraitCasteSet();
}