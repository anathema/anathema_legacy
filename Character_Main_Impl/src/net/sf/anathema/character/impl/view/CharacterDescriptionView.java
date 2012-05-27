package net.sf.anathema.character.impl.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IMultiComponentLine;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class CharacterDescriptionView implements ICharacterDescriptionView {

  private static final int TEXT_COLUMNS = 45;
  private final JPanel content = new JPanel(new GridDialogLayout(3, false));
  private final List<JPanel> buttonPanels = new ArrayList<JPanel>();

  @Override
  public IMultiComponentLine addMultiComponentLine() {
    MultiComponentLine componentLine = new MultiComponentLine(content, buttonPanels);
    componentLine.init();
    return componentLine;
  }


  @Override
  public ITextView addLineView(String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMNS));
  }

  @Override
  public ITextView addAreaView(String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMNS));
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