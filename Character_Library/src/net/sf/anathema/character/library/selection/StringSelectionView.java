package net.sf.anathema.character.library.selection;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.constraintsForImageButton;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class StringSelectionView implements IStringSelectionView {

  private final LabelTextView labelTextView;
  private final JButton button;

  public StringSelectionView(JPanel parent, String labelText, Icon addButtonIcon) {
    LineTextView lineTextView = new LineTextView(45);
    labelTextView = new LabelTextView(labelText, lineTextView);
    this.button = new JButton(addButtonIcon);
    button.setPreferredSize(new Dimension(addButtonIcon.getIconWidth() + 4, addButtonIcon.getIconHeight() + 4));
    JPanel panel = new JPanel(new MigLayout(withoutInsets()));
    labelTextView.addToMigPanel(panel);
    panel.add(button, constraintsForImageButton(button));
    parent.add(panel);
  }

  @Override
  public void addTextChangeListener(ObjectValueListener<String> listener) {
    labelTextView.addTextChangedListener(listener);
  }

  @Override
  public void addAddButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  @Override
  public void setAddButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  @Override
  public void clear() {
    labelTextView.setText(null);
  }
}