package net.sf.anathema.character.impl.view;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.view.IMultiComponentLine;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

import static java.lang.Short.MAX_VALUE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class MultiComponentLine implements IMultiComponentLine {
  private static final int FIELD_COLUMNS = 6;
  private final JPanel content;
  private final List<JPanel> buttonPanels;
  private GroupLayout.SequentialGroup horizontal;
  private GroupLayout.ParallelGroup vertical;
  private GroupLayout layout;
  private JPanel fieldPanel;
  private int numberOfViews = 0;

  public MultiComponentLine(JPanel content, List<JPanel> buttonPanels) {
    this.content = content;
    this.buttonPanels = buttonPanels;
  }

  @Override
  public void init() {
    content.add(new JPanel());
    fieldPanel = new JPanel();
    layout = new GroupLayout(fieldPanel);
    fieldPanel.setLayout(layout);
    horizontal = layout.createSequentialGroup();
    vertical = layout.createParallelGroup(CENTER);
    layout.setHorizontalGroup(horizontal);
    layout.setVerticalGroup(vertical);
    GridDialogLayoutData panelLayout = new GridDialogLayoutData();
    panelLayout.setGrabExcessHorizontalSpace(true);
    content.add(fieldPanel, panelLayout);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
    buttonPanels.add(buttonPanel);
    content.add(buttonPanel);
  }

  @Override
  public ITextView addFieldsView(String labelText) {
    LineTextView view = new LineTextView(FIELD_COLUMNS);
    addView(labelText, view);
    numberOfViews++;
    return view;
  }

  private void addView(String text, ITextView iTextView) {
    JLabel label = new JLabel(text);
    if (numberOfViews > 0) {
      horizontal.addPreferredGap(UNRELATED, DEFAULT_SIZE, MAX_VALUE);
    }
    horizontal.addComponent(label);
    horizontal.addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE);
    horizontal.addComponent(iTextView.getComponent());
    vertical.addComponent(label);
    vertical.addComponent(iTextView.getComponent());
  }
}
