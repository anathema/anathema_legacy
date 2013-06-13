package net.sf.anathema.character.attributes.sheet.content;

import net.sf.anathema.character.attributes.model.AttributesIterator;
import net.sf.anathema.character.attributes.model.AttributesModel;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

public class PrintAttributeIterator implements AttributesIterator {

  public List<PrintAttributeGroup> groups = new ArrayList<>();
  private PrintAttributeGroup currentGroup;
  private Resources resources;
  private TemporaryAttributesModel model;

  public PrintAttributeIterator(Resources resources, TemporaryAttributesModel model) {
    this.resources = resources;
    this.model = model;
  }

  @Override
  public void nextGroup(Identified groupId) {
    currentGroup = new PrintAttributeGroup();
    groups.add(currentGroup);
  }

  @Override
  public void nextTrait(Identified traitId, int currentValue) {
    PrintAttribute attribute = new PrintAttribute();
    attribute.name = resources.getString("AttributeType.Name." + traitId.getId());
    attribute.value = model.getCurrentValue(traitId);
    currentGroup.attributes.add(attribute);
  }
}
