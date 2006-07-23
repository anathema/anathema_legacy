package net.sf.anathema.character.equipment.impl.creation.model;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class EquipmentStatisticsModel implements IEquipmentStatisticsModel {

  private final ITextualDescription name = new SimpleTextualDescription();
  private final BooleanModel nameDefined = new BooleanModel();

  public ITextualDescription getName() {
    return name;
  }

  public BooleanModel isNameSpecified() {
    return nameDefined;
  }
}