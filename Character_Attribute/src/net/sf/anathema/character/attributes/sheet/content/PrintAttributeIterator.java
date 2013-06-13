package net.sf.anathema.character.attributes.sheet.content;

import net.sf.anathema.character.attributes.model.GroupedTraitsIterator;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

public class PrintAttributeIterator implements GroupedTraitsIterator {

  public List<PrintAttributeGroup> groups = new ArrayList<>();
  private PrintAttributeGroup currentGroup;
  private Resources resources;
  private IGenericCharacter character;

  public PrintAttributeIterator(Resources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
  }

  @Override
  public void nextGroup(Identified groupId) {
    currentGroup = new PrintAttributeGroup();
    groups.add(currentGroup);
  }

  @Override
  public void nextTrait(ITraitType traitId, int currentValue) {
    PrintAttribute attribute = new PrintAttribute();
    attribute.name = resources.getString("AttributeType.Name." + traitId.getId());
    attribute.value = character.getTraitCollection().getTrait(traitId).getCurrentValue();
    currentGroup.attributes.add(attribute);
  }
}
