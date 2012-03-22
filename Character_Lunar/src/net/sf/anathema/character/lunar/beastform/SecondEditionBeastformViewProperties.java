package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.view.IBeastformViewProperties;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionBeastformViewProperties implements IBeastformViewProperties {
  private final IResources resources;
  private final SecondEditionBeastformModel secondmodel;

  public SecondEditionBeastformViewProperties(IResources resources, SecondEditionBeastformModel secondmodel) {
    this.resources = resources;
    this.secondmodel = secondmodel;
  }

  @Override
  public String getAttributesString() {
    return resources.getString("Lunar.DeadlyBeastmanTransformation.Attributes.Label"); //$NON-NLS-1$
  }

  @Override
  public String getGiftsString() {
    return resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label_2nd"); //$NON-NLS-1$
  }

  @Override
  public String getDBTBoxString() {
    return resources.getString("Lunar.DeadlyBeastmanTransformation");
  }

  @Override
  public String getSpiritFormBoxString() {
    return resources.getString("Lunar.SpiritForm");
  }

  @Override
  public String getSpiritFormBoxInitialString() {
    String shape = secondmodel.getSpiritForm();
    return shape.equals("") ? resources.getString("Lunar.SpiritForm.EnterShape") : shape;
  }
}
