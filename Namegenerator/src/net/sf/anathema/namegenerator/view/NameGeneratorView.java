package net.sf.anathema.namegenerator.view;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.namegenerator.presenter.view.INameGeneratorView;
import org.jmock.example.announcer.Announcer;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class NameGeneratorView implements INameGeneratorView {

  private final JPanel firstColumn = new JPanel(new MigLayout(new LC().wrapAfter(1)));
  private final JPanel content = new JPanel(
          new MigLayout(new LC().wrapAfter(2), new AC().grow(100, 1).fill(1), new AC().grow().fill()));
  private final ButtonGroup nameGeneratorTypeGroup = new ButtonGroup();
  private final AreaTextView resultTextView = new AreaTextView(5, 25);
  private final Map<JRadioButton, Object> generatorsByButton = new HashMap<>();
  private final Map<Object, JRadioButton> buttonsByGenerator = new HashMap<>();
  private final Announcer<IChangeListener> generatorListeners = Announcer.to(IChangeListener.class);
  private final JPanel generateButtonPanel = new JPanel(new GridLayout());
  private final ActionListener generatorButtonListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      generatorListeners.announce().changeOccurred();
    }
  };

  public NameGeneratorView() {
    content.add(firstColumn);
    content.add(createSecondColumn(), new CC().grow());
  }

  private JComponent createSecondColumn() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(resultTextView.getComponent(), BorderLayout.CENTER);
    panel.add(generateButtonPanel, BorderLayout.SOUTH);
    return panel;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public void addNameGeneratorType(String label, Object generatorType) {
    JRadioButton generatorButton = new JRadioButton(label);
    generatorButton.addActionListener(generatorButtonListener);
    generatorsByButton.put(generatorButton, generatorType);
    buttonsByGenerator.put(generatorType, generatorButton);
    nameGeneratorTypeGroup.add(generatorButton);
    generatorButton.setSelected(nameGeneratorTypeGroup.getSelection() == null);
    firstColumn.add(generatorButton);
  }

  @Override
  public void setResult(String result) {
    resultTextView.setText(result);
  }

  @Override
  public Object getSelectedGeneratorType() {
    for (JRadioButton button : generatorsByButton.keySet()) {
      if (button.isSelected()) {
        return generatorsByButton.get(button);
      }
    }
    return null;
  }

  @Override
  public void addGeneratorTypeChangeListener(IChangeListener listener) {
    generatorListeners.addListener(listener);
  }

  @Override
  public void setSelectedGeneratorType(Object generatorType) {
    buttonsByGenerator.get(generatorType).setSelected(true);
  }

  @Override
  public void addGenerationAction(Action action) {
    generateButtonPanel.add(new JButton(action));
  }
}