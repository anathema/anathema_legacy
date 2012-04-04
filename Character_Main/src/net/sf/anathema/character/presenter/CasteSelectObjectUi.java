package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.framework.view.renderer.AbstractSelectObjectUi;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public class CasteSelectObjectUi extends AbstractSelectObjectUi<ICasteType> {

  private CasteUI casteUI;

  public CasteSelectObjectUi(IResources resources, IPresentationProperties properties) {
    super(resources);
    this.casteUI = new CasteUI(resources, properties);
  }

  @Override
  protected String getNonNullLabel(ICasteType value) {
    return getResources().getString("Caste." + value.getId()); //$NON-NLS-1$
  }

  @Override
  protected Icon getNonNullIcon(ICasteType value) {
    return casteUI.getSmallCasteIcon(value);
  }
}