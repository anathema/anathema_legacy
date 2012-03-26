package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

public class WeaponryContent extends AbstractSubBoxContent {

  public WeaponryContent(IResources resources) {
    super(resources);
  }

  @Override
  public String getHeaderKey() {
    return "Weapons"; //$NON-NLS-1$
  }
}