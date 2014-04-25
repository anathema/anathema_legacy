package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import java.awt.Component;

public interface EquipmentStatsView {
  IntegerSpinner initIntegerSpinner(IIntValueModel rateModel);

  void addLabelledComponentRow(String[] labels, Component[] components);

  void addView(AdditiveView view);

  ITextView addLineTextView(String nameLabel);

  void setCanFinish();

  void setCannotFinish();
}