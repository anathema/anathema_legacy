package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.SwingIntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import java.awt.Component;

public interface EquipmentStatsView {
  SwingIntegerSpinner initIntegerSpinner(IIntValueModel rateModel);

  void addLabelledComponentRow(String[] labels, Component[] components);

  void addView(AdditiveView view);

  ITextView addLineTextView(String nameLabel);

  void setCanFinish();

  void setCannotFinish();

  void setMessage(IBasicMessage message);

  void setTitle(String title);

  void setDescription(String description);

  void show(OperationResultHandler handler);
}