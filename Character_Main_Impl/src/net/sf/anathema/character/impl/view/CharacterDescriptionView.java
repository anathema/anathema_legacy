package net.sf.anathema.character.impl.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.toolbar.ToolBarUtilities;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterDescriptionView implements ICharacterDescriptionView {

  private static final int TEXT_COLUMS = 45;
  private JPanel content = new JPanel(new GridDialogLayout(3, false));
  private List<JPanel> buttonPanels = new ArrayList<JPanel>();

  public ITextView addLineView(final String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMS));
  }

  public ITextView addAreaView(String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMS));
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