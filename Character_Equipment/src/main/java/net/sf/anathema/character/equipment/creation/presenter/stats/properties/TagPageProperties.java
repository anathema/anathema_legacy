package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

public class TagPageProperties {

  private final Resources resources;

  public TagPageProperties(Resources resources) {
    this.resources = resources;
  }

  public IBasicMessage getDefaultMessage() {
    return new BasicMessage(resources.getString("Equipment.Creation.Tags.DefaultMessage"));
  }

  public String getPageTitle() {
    return resources.getString("Equipment.Creation.Tags.PageTitle");
  }

  public String getLabel(IWeaponTag tag) {
    return resources.getString("Equipment.Tag." + tag.getId());
  }

  public IBasicMessage getSelectRangedWeaponTagMessage() {
    return new BasicMessage(resources.getString("Equipment.Creation.Tags.SelectRangedTagMessage"));
  }

  public IBasicMessage getThrownTagButNotThrownTypeMessage() {
    return new BasicMessage(
            resources.getString("Equipment.Creation.Tags.ThrownTagButNotThrownTypeMessage"));
  }

  public String getToolTip(IWeaponTag tag) {
    String abbreviation = resources.getString("Weapons.Tags." + tag.getId() + ".Short");
    String explanation = resources.getString("Equipment.Tag.Tooltip." + tag.getId());
    return explanation + " (" + abbreviation + ")";
  }
}