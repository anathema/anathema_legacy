package net.sf.anathema.charmentry.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.util.ToggleComponentEnabler;
import net.sf.anathema.charmentry.presenter.view.IDurationEntryView;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class DurationEntryView implements IDurationEntryView {

  private final ButtonGroup group = new ButtonGroup();
  private final JPanel panel = new JPanel(new GridDialogLayout(2, false));
  private final ChangeControl control = new ChangeControl();

  public JRadioButton addRadioButton(String string) {
    JRadioButton button = createRadioButton(string);
    panel.add(new EndOfLineMarkerComponent());
    return button;
  }

  public ITextView addRadioButtonTextField(String string) {
    JRadioButton radioButton = createRadioButton(string);
    LineTextView textField = new LineTextView(15);
    ToggleComponentEnabler.connect(radioButton, textField.getComponent());
    panel.add(textField.getComponent());
    panel.add(new EndOfLineMarkerComponent());
    return textField;
  }

  private JRadioButton createRadioButton(String string) {
    JRadioButton radioButton = new JRadioButton(string);
    group.add(radioButton);
    panel.add(radioButton);
    radioButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        control.fireChangedEvent();
      }
    });
    return radioButton;
  }

  public JComponent getContent() {
    return panel;
  }

  public void addTypeChangeListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  public void requestFocus() {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }
}