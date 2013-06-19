package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.framework.view.renderer.AbstractSelectObjectUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class CasteSelectObjectUi extends AbstractSelectObjectUi<CasteType> {

  private CasteUI casteUI;

  public CasteSelectObjectUi(Resources resources, IPresentationProperties properties) {
    super(resources);
    this.casteUI = new CasteUI(properties);
  }

  @Override
  protected String getNonNullLabel(CasteType value) {
    return getResources().getString("Caste." + value.getId());
  }

  @Override
  protected Icon getNonNullIcon(CasteType value) {
    return casteUI.getSmallCasteIcon(value);
  }
}