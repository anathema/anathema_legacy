package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class EquipmentStatisticsModel implements IEquipmentStatisticsModel {

  private final ITextualDescription name = new SimpleTextualDescription();
  private final BooleanValueModel nameDefined = new BooleanValueModel(false);

  public final ITextualDescription getName() {
    return name;
  }

  public final BooleanValueModel getNameSpecified() {
    return nameDefined;
  }
}