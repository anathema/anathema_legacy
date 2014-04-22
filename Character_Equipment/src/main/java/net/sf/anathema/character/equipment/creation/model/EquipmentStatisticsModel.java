package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

public class EquipmentStatisticsModel implements IEquipmentStatisticsModel {

  private final ITextualDescription name = new SimpleTextualDescription();

  @Override
  public final ITextualDescription getName() {
    return name;
  }
}