package net.sf.anathema.character.equipment.impl.reporting.first;

import net.sf.anathema.character.equipment.impl.reporting.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.AbstractWeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.first.weaponstats.FirstEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  private final IGenericTraitCollection collection;

  public FirstEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources, IGenericTraitCollection collection) {
    super(baseFont, resources);
    this.collection = collection;
  }

  @Override
  protected AbstractSpeedWeaponStatsGroup getSpeedWeaponStatsGroup() {
    return new FirstEditionSpeedWeaponStatsGroup(getResources(), collection);
  }
}