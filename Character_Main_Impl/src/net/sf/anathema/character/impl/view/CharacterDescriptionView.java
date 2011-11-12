package net.sf.anathema.character.impl.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.toolbar.ToolBarUtilities;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterDescriptionView implements ICharacterDescriptionView {

  private static final int FIELD_COLUMNS = 6;
  private static final int TEXT_COLUMNS = 45;
  private final JPanel content = new JPanel(new GridDialogLayout(3, false));
  private final List<JPanel> buttonPanels = new ArrayList<JPanel>();
  
  public void addBlankLine() {
    GridDialogLayoutData lineLayout = new GridDialogLayoutData();
    lineLayout.setGrabExcessHorizontalSpace(true);
    lineLayout.setHorizontalSpan(3);
    content.add(new JPanel(), lineLayout);
  }
  
  public ITextView[] addFieldsView(final String[] labelText) {
    ITextView[] textView = new ITextView[labelText.length];
    for (int i = 0; i < textView.length; i++) {
      textView[i] = new LineTextView(FIELD_COLUMNS);
    }
    return addTextViews(labelText, textView);
  }

  public ITextView addLineView(final String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMNS));
  }

  public ITextView addAreaView(final String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMNS));
  }
  
  private synchronized ITextView[] addTextViews(final String[] labelText, final ITextView[] textView)
      throws InputMismatchException, IllegalArgumentException {
    if (labelText.length != textView.length) {
      throw new InputMismatchException("There must be as many labels as text views.");
    }
    if (textView.length == 0) {
      throw new IllegalArgumentException("You must add some positive number of text views.");
    }
    
    content.add(new JPanel());
    
    JPanel fieldPanel = new JPanel();
    GroupLayout layout = new GroupLayout(fieldPanel);
    fieldPanel.setLayout(layout);
    SequentialGroup horizontal = layout.createSequentialGroup();
    ParallelGroup vertical = layout.createParallelGroup(Alignment.CENTER);
    for (int i = 0; i < textView.length; i++) {
      JLabel label = new JLabel(labelText[i]);
      
      if (i > 0) {
        horizontal.addPreferredGap(ComponentPlacement.UNRELATED,
                                   GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
      }
      horizontal.addComponent(label);
      horizontal.addPreferredGap(ComponentPlacement.RELATED,
                                 GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
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

  private synchronized ITextView addTextView(final String labelText, final ITextView textView) {
    new LabelTextView(labelText, textView).addToStandardPanel(content);
    JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
    buttonPanels.add(buttonPanel);
    content.add(buttonPanel);
    return textView;
  }

  public void addEditAction(SmartAction action, int row) {
    buttonPanels.get(row).add(ToolBarUtilities.createToolBarButton(action));
  }

  public JComponent getComponent() {
    return content;
  }
}