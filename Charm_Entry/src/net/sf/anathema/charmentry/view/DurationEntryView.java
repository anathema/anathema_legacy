package net.sf.anathema.charmentry.view;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmentry.presenter.view.IDurationEntryView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.swing.ToggleComponentEnabler;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import org.jmock.example.announcer.Announcer;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DurationEntryView implements IDurationEntryView {

  private final ButtonGroup group = new ButtonGroup();
  private final JPanel panel = new JPanel(new GridDialogLayout(2, false));
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);

  @Override
  public JRadioButton addRadioButton(String string) {
    JRadioButton button = createRadioButton(string);
    panel.add(new EndOfLineMarkerComponent());
    return button;
  }

  @Override
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
      @Override
      public void actionPerformed(ActionEvent e) {
        control.announce().changeOccurred();
      }
    });
    return radioButton;
  }

  @Override
  public JComponent getContent() {
    return panel;
  }

  @Override
  public void addTypeChangeListener(IChangeListener changeListener) {
    control.addListener(changeListener);
  }

  @Override
  public void requestFocus() {
    // Nothing to do
  }
}