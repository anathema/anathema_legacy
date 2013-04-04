package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.view.IBeastformViewProperties;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionBeastformViewProperties implements IBeastformViewProperties {
  private final Resources resources;
  private final SecondEditionBeastformModel secondmodel;

  public SecondEditionBeastformViewProperties(Resources resources, SecondEditionBeastformModel secondmodel) {
    this.resources = resources;
    this.secondmodel = secondmodel;
  }

  @Override
  public String getAttributesString() {
    return resources.getString("Lunar.DeadlyBeastmanTransformation.Attributes.Label");
  }

  @Override
  public String getGiftsString() {
    return resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label_2nd");
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
