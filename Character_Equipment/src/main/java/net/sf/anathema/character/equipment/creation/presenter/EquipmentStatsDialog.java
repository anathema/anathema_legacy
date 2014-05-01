package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.message.IBasicMessage;

public interface EquipmentStatsDialog {

  void setCanFinish();

  void setCannotFinish();

  void setMessage(IBasicMessage message);

  void setTitle(String title);

  void show(OperationResultHandler handler);

  EquipmentStatsView getEquipmentStatsView();
}