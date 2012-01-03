package net.sf.anathema.charmentry.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.util.ToggleComponentEnabler;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.charmentry.presenter.view.IAmountDurationEntryView;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class QualifiedAmountDurationView implements IAmountDurationEntryView {
  private final ButtonGroup group = new ButtonGroup();
  private final JPanel panel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel textPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(2, false));
  private final ChangeControl control = new ChangeControl();

  public IntegerSpinner addRadioButtonSpinner() {
    JRadioButton button = createRadioButton(""); //$NON-NLS-1$
    IntegerSpinner integerSpinner = new IntegerSpinner(1);
    panel.add(integerSpinner.getComponent());
    panel.add(new EndOfLineMarkerComponent());
    ToggleComponentEnabler.connect(button, integerSpinner.getComponent());
    return integerSpinner;
  }

  public JComponent getContent() {
    mainPanel.add(panel);
    mainPanel.add(textPanel);
    return mainPanel;
  }

  public ObjectSelectionView<ITraitType> addRadioButtonComboBox(
      String label,
      ListCellRenderer renderer,
      ITraitType[] types) {
    JRadioButton radioButton = createRadioButton(""); //$NON-NLS-1$
    ObjectSelectionView<ITraitType> view = new ObjectSelectionView<ITraitType>(label, renderer, false, types);
    view.addTo(panel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
    JComboBox comboBox = view.getComboBox();
    ToggleComponentEnabler.connect(radioButton, comboBox);
    return view;
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

  public ITextView addTextView() {
    LineTextView lineTextView = new LineTextView(15);
    textPanel.add(lineTextView.getComponent());
    return lineTextView;
  }

  public void addTypeChangeListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);
  }

  public void requestFocus() {
    // nothing to do;
  }

  public void dispose() {
    // nothing to do;
  }
}