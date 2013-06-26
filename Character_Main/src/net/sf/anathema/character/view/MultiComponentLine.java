package net.sf.anathema.character.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.lang.Short.MAX_VALUE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class MultiComponentLine implements IMultiComponentLine {
  private static final int FIELD_COLUMNS = 6;
  private final JPanel content;
  private GroupLayout.SequentialGroup horizontal;
  private GroupLayout.ParallelGroup vertical;
  private int numberOfViews = 0;

  public MultiComponentLine(JPanel content) {
    this.content = content;
  }

  @Override
  public void init() {
    content.add(new JPanel());
    JPanel fieldPanel = new JPanel();
    GroupLayout layout = new GroupLayout(fieldPanel);
    fieldPanel.setLayout(layout);
    horizontal = layout.createSequentialGroup();
    vertical = layout.createParallelGroup(CENTER);
    layout.setHorizontalGroup(horizontal);
    layout.setVerticalGroup(vertical);
    content.add(fieldPanel, new CC().wrap());
  }

  @Override
  public ITextView addFieldsView(String labelText) {
    LineTextView view = new LineTextView(FIELD_COLUMNS);
    addLabeledComponent(labelText, view.getComponent());
    numberOfViews++;
    return view;
  }

  @Override
  public IIntegerView addIntegerView(String labelText, IIntegerDescription description) {
    IntegerSpinner spinner = new IntegerSpinner(description.getValue());
    spinner.setPreferredWidth(48);
    spinner.setStepSize(5);
    JComponent component = spinner.getComponent();
    addLabeledComponent(labelText, component);
    return spinner;
  }

  private void addLabeledComponent(String text, JComponent component) {
    if (numberOfViews > 0) {
      horizontal.addPreferredGap(UNRELATED, DEFAULT_SIZE, MAX_VALUE);
    }
    JLabel label = new JLabel(text);
    horizontal.addComponent(label);
    horizontal.addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE);
    horizontal.addComponent(component);
    vertical.addComponent(label);
    vertical.addComponent(component);
  }
}
