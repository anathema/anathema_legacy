package net.sf.anathema.hero.intimacies.display;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;
import static net.sf.anathema.lib.gui.layout.SwingLayoutUtils.constraintsForImageButton;

public class SwingStringEntryView implements StringEntryView {

  private final LabelTextView labelTextView;
  private final JButton button;
  private final JPanel content = new JPanel(new MigLayout(withoutInsets()));

  public SwingStringEntryView(String labelText, Icon addButtonIcon) {
    LineTextView lineTextView = new LineTextView(45);
    labelTextView = new LabelTextView(labelText, lineTextView);
    this.button = new JButton(addButtonIcon);
    button.setPreferredSize(new Dimension(addButtonIcon.getIconWidth() + 4, addButtonIcon.getIconHeight() + 4));
    labelTextView.addToMigPanel(content);
    content.add(button, constraintsForImageButton(button));
  }

  @Override
  public void addTextChangeListener(ObjectValueListener<String> listener) {
    labelTextView.addTextChangedListener(listener);
  }

  @Override
  public void addAddButtonListener(final Command listener) {
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.execute();
      }
    });
  }

  @Override
  public void setAddButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  @Override
  public void clear() {
    labelTextView.setText(null);
  }

  public JComponent getComponent() {
    return content;
  }
}