package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.JComponent;

public interface EquipmentStatsView {

  void addView(AdditiveView view);

  ITextView addLineTextView(String nameLabel);

  void setCanFinish();

  void setCannotFinish();

  void setMessage(IBasicMessage message);

  void setTitle(String title);

  void setDescription(String description);

  void show(OperationResultHandler handler);

  void addElement(String label, JComponent component);

  IIntegerSpinner addIntegerSpinner(String label, int initialValue);
}