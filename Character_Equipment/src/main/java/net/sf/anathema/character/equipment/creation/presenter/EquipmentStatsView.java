package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface EquipmentStatsView {

  void addView(AdditiveView view);

  ITextView addLineTextView(String nameLabel);

  IIntegerSpinner addIntegerSpinner(String label, int initialValue);

  IBooleanValueView addBooleanSelector(String label);

  ToggleTool addToggleTool();

  void setCanFinish();

  void setCannotFinish();

  void setMessage(IBasicMessage message);

  void setTitle(String title);

  void setDescription(String description);

  void show(OperationResultHandler handler);

  void addHorizontalSeparator();
}