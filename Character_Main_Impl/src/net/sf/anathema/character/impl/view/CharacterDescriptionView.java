package net.sf.anathema.character.impl.view;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Short.MAX_VALUE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class CharacterDescriptionView implements ICharacterDescriptionView {

  private static final int FIELD_COLUMNS = 6;
  private static final int TEXT_COLUMNS = 45;
  private final JPanel content = new JPanel(new GridDialogLayout(3, false));
  private final List<JPanel> buttonPanels = new ArrayList<JPanel>();

  @Override
  public ITextView[] addFieldsView(String[] labelText) {
    ITextView[] textView = new ITextView[labelText.length];
    for (int i = 0; i < textView.length; i++) {
      textView[i] = new LineTextView(FIELD_COLUMNS);
    }
    return addTextViews(labelText, textView);
  }

  @Override
  public ITextView addLineView(String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMNS));
  }

  @Override
  public ITextView addAreaView(String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMNS));
  }

  private synchronized ITextView[] addTextViews(String[] labelText, ITextView[] textView) {
    Preconditions.checkArgument(labelText.length == textView.length, "There must be as many labels as text views.");
    Preconditions.checkArgument(textView.length > 0, "You must add some positive number of text views.");

    content.add(new JPanel());

    JPanel fieldPanel = new JPanel();
    GroupLayout layout = new GroupLayout(fieldPanel);
    fieldPanel.setLayout(layout);
    SequentialGroup horizontal = layout.createSequentialGroup();
    ParallelGroup vertical = layout.createParallelGroup(CENTER);
    for (int i = 0; i < textView.length; i++) {
      JLabel label = new JLabel(labelText[i]);

      if (i > 0) {
        horizontal.addPreferredGap(UNRELATED, DEFAULT_SIZE, MAX_VALUE);
      }
      horizontal.addComponent(label);
      horizontal.addPreferredGap(RELATED, DEFAULT_SIZE, MAX_VALUE);
      horizontal.addComponent(textView[i].getComponent());

      vertical.addComponent(label);
      vertical.addComponent(textView[i].getComponent());
    }
    layout.setHorizontalGroup(horizontal);
    layout.setVerticalGroup(vertical);

    GridDialogLayoutData panelLayout = new GridDialogLayoutData();
    panelLayout.setGrabExcessHorizontalSpace(true);
    content.add(fieldPanel, panelLayout);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
    buttonPanels.add(buttonPanel);
    content.add(buttonPanel);

    return textView;
  }

  private synchronized ITextView addTextView(String labelText, ITextView textView) {
    new LabelTextView(labelText, textView).addToStandardPanel(content);
    JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
    buttonPanels.add(buttonPanel);
    content.add(buttonPanel);
    return textView;
  }

  @Override
  public void addEditAction(SmartAction action, int row) {
    buttonPanels.get(row).add(ToolBarUtilities.createToolBarButton(action));
  }

  @Override
  public JComponent getComponent() {
    return content;
  }
}