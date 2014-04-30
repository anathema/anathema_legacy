package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.character.equipment.creation.presenter.IIntegerSpinner;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExtensibleEquipmentStatsView implements EquipmentStatsView {
  private static final int COLUMN_COUNT = 30;
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(4).fill().insets("2")));

  public JComponent getComponent() {
    return panel;
  }

  public ITextView addLineTextView(String label) {
    LineTextView textView = new LineTextView(COLUMN_COUNT);
    LabelTextView labelTextView = new LabelTextView(label, textView);
    labelTextView.addToMigPanelSpanning(panel);
    return textView;
  }

  public void addHorizontalSeparator() {
    panel.add(new HorizontalLine(), new CC().newline().pushX().growX().spanX());
  }

  public ToggleTool addToggleTool() {
    SwingToggleTool tool = new SwingToggleTool();
    panel.add(tool.getComponent(), new CC().spanX(2));
    return tool;
  }

  public <T> ObjectSelectionView<T> addObjectSelection(AgnosticUIConfiguration<T> agnosticUIConfiguration) {
    AdditiveObjectSelectionView<T> selectionView = new AdditiveObjectSelectionView<>();
    selectionView.setRenderer(agnosticUIConfiguration);
    panel.add(selectionView.getComponent(), new CC().growX().spanX());
    return selectionView;
  }

  public IBooleanValueView addBooleanSelector(String label) {
    SwingBooleanView booleanView = new SwingBooleanView();
    panel.add(new JLabel(label));
    panel.add(booleanView.getComponent(), new CC().growX().pushX());
    return booleanView;
  }

  public IIntegerSpinner addIntegerSpinner(String label, int initialValue) {
    SwingIntegerSpinner spinner = new SwingIntegerSpinner(initialValue);
    panel.add(new JLabel(label));
    panel.add(spinner.getComponent(), new CC().growX().pushX());
    return spinner;
  }

}