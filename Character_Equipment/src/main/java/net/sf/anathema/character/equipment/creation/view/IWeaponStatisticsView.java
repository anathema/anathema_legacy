package net.sf.anathema.character.equipment.creation.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface IWeaponStatisticsView extends IPageContent {

  ITextView addLineTextView(String label);

  void addView(AdditiveView additiveView, CC cc);
}