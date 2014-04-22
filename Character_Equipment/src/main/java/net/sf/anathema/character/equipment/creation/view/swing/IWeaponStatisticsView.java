package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IWeaponStatisticsView extends IView {

  ITextView addLineTextView(String label);

  void addView(AdditiveView additiveView);
}