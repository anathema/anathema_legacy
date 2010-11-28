package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

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
    return new BasicMessage(resources.getString("Equipment.Creation.Tags.DefaultMessage")); //$NON-NLS-1$
  }

  public String getPageTitle() {
    return resources.getString("Equipment.Creation.Tags.PageTitle"); //$NON-NLS-1$
  }

  public String getLabel(IWeaponTag tag) {
    return resources.getString("Equipment.Tag." + tag.getId()); //$NON-NLS-1$
  }

  public IBasicMessage getSelectRangedWeaponTagMessage() {
    return new BasicMessage(resources.getString("Equipment.Creation.Tags.SelectRangedTagMessage")); //$NON-NLS-1$
  }

  public IBasicMessage getThrownTagButNotThrownTypeMessage() {
    return new BasicMessage(resources.getString("Equipment.Creation.Tags.ThrownTagButNotThrownTypeMessage")); //$NON-NLS-1$
  }
}