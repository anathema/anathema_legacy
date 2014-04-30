package net.sf.anathema.character.equipment.creation.view.fx;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.fx.hero.configurableview.IIntegerSpinner;
import net.sf.anathema.fx.hero.configurableview.IntegerSpinner;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import net.sf.anathema.platform.tool.FxCheckToggleTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentStatsView implements EquipmentStatsView {
  private final MigPane panel = new MigPane(new LC().wrapAfter(4).fill().insets("2"));

  public Node getNode() {
    return panel;
  }

  public ITextView addLineTextView(String label) {
    FxTextView textView = FxTextView.SingleLine(label);
    panel.add(textView.getNode(), new CC().spanX().push().grow());
    return textView;
  }

  public void addHorizontalSeparator() {
    panel.add(new Separator(), new CC().newline().pushX().growX().spanX());
  }

  public ToggleTool addToggleTool() {
    FxCheckToggleTool tool = FxCheckToggleTool.create();
    panel.add(tool.getNode(), new CC().spanX(2));
    return tool;
  }

  public <T> ObjectSelectionView<T> addObjectSelection(AgnosticUIConfiguration<T> agnosticUIConfiguration) {
    ComboBoxSelectionView<T> selectionView = new ComboBoxSelectionView<>("", agnosticUIConfiguration);
    panel.add(selectionView.getNode(), new CC().growX().spanX());
    return selectionView;
  }

  public BooleanValueView addBooleanSelector(String label) {
    FxBooleanView booleanView = new FxBooleanView();
    panel.add(new Label(label));
    panel.add(booleanView.getNode(), new CC().growX().pushX());
    return booleanView;
  }

  public IIntegerSpinner addIntegerSpinner(String label, int initialValue) {
    IntegerSpinner spinner = new IntegerSpinner(1);
    spinner.setValue(initialValue);
    panel.add(new Label(label));
    panel.add(spinner.getNode(), new CC().growX().pushX());
    return spinner;
  }

}