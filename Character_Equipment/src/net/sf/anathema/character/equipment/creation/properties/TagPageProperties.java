package net.sf.anathema.character.equipment.creation.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.lib.resources.IResources;

public class TagPageProperties {

  private final IResources resources;

  public TagPageProperties(IResources resources) {
    this.resources = resources;
  }

  public IBasicMessage getDefaultMessage() {
    return new BasicMessage("Select Weapon Tags");
  }

  public String getPageTitle() {
    return "Weapon Tags";
  }

  public String getLabel(IWeaponTag tag) {
    return tag.getId();
  }
}