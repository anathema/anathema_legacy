package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.rendering.weaponry.WeaponryContentClassFinder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

public class WeaponryContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public WeaponryContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Weapons"; //$NON-NLS-1$
  }

  public Class<? extends AbstractWeaponryContent> getTableContentClass() {
    WeaponryContentClassFinder contentClassFinder = new WeaponryContentClassFinder();
    character.getRules().getEdition().accept(contentClassFinder);
    return contentClassFinder.contentClass;
  }
}
