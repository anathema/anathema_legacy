package net.sf.anathema.character.impl.view.advantage;

import net.sf.anathema.character.impl.view.LabelledOverviewStringValueView;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class EssencePanelView {

  private final IGridDialogPanel essencePanel = new DefaultGridDialogPanel(false);
  private final IIntValueDisplayFactory guiConfiguration;
  
  public EssencePanelView(IIntValueDisplayFactory guiConfiguration) {
    this.guiConfiguration = guiConfiguration;
  }

  public IIntValueView addEssenceView(String labelText, int value, int maxValue) {
    SimpleTraitView essenceView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    essenceView.addComponents(essencePanel);
    return essenceView;
  }

  public ILabelledValueView<String> addPoolView(String labelText, String value) {
    LabelledOverviewStringValueView poolView = new LabelledOverviewStringValueView(labelText, value, false);
    poolView.addComponents(essencePanel);
    return poolView;
  }

  public IGridDialogPanel getPanel() {
    return essencePanel;
  }
}