package net.sf.anathema.namegenerator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.namegenerator.presenter.view.INameGeneratorView;

public class NameGeneratorView implements INameGeneratorView {

  private final JPanel firstColumn = new JPanel(new GridDialogLayout(1, false));
  private final JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final ButtonGroup nameGeneratorTypeGroup = new ButtonGroup();
  private final AreaTextView resultTextView = new AreaTextView(5, 25);
  private final Map<JRadioButton, Object> generatorsByButton = new HashMap<JRadioButton, Object>();
  private final Map<Object, JRadioButton> buttonsByGenerator = new HashMap<Object, JRadioButton>();
  private final ChangeControl generatorListeners = new ChangeControl();
  private final JPanel generateButtonPanel = new JPanel(new GridLayout());
  private final ActionListener generatorButtonListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      generatorListeners.fireChangedEvent();
    }
  };

  public NameGeneratorView() {
    content.add(firstColumn, GridDialogLayoutDataFactory.createTopData());
    content.add(createSecondColumn(), GridDialogLayoutData.FILL_BOTH);
  }

  private JComponent createSecondColumn() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(resultTextView.getComponent(), BorderLayout.CENTER);
    panel.add(generateButtonPanel, BorderLayout.SOUTH);
    return panel;
  }

  public JComponent getComponent() {
    return content;
  }

  public void addNameGeneratorType(String label, JComponent component, Object generatorType) {
    JRadioButton generatorButton = new JRadioButton(label);
    generatorButton.addActionListener(generatorButtonListener);
    generatorsByButton.put(generatorButton, generatorType);
    buttonsByGenerator.put(generatorType, generatorButton);
    nameGeneratorTypeGroup.add(generatorButton);
    generatorButton.setSelected(nameGeneratorTypeGroup.getSelection() == null);
    firstColumn.add(generatorButton);
    if (component != null) {
      firstColumn.add(component, GridDialogLayoutData.FILL_BOTH);
    }
  }

  public void setResult(String result) {
    resultTextView.setText(result);
  }

  public Object getSelectedGeneratorType() {
    for (JRadioButton button : generatorsByButton.keySet()) {
      if (button.isSelected()) {
        return generatorsByButton.get(button);
      }
    }
    return null;
  }

  public void addGeneratorTypeChangeListener(IChangeListener listener) {
    generatorListeners.addChangeListener(listener);
  }

  public void setSelectedGeneratorType(Object generatorType) {
    buttonsByGenerator.get(generatorType).setSelected(true);
  }

  public void addGenerationAction(Action action) {
    generateButtonPanel.add(new JButton(action));
  }
}