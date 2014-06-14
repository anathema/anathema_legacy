package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.message.Message;

public interface EquipmentStatsDialog {

  void setCanFinish();

  void setCannotFinish();

  void setMessage(Message message);

  void setTitle(String title);

  void show(OperationResultHandler handler);

  EquipmentStatsView getEquipmentStatsView();
}