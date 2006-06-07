package net.sf.anathema.character.impl.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterDescriptionView extends AbstractTabView<Object> implements ICharacterDescriptionView {

  public static class EditButtonDialogPanel implements IGridDialogPanel {
    private final IGridDialogPanel dialogPanel = new DefaultGridDialogPanel();
    private final Map<Integer, JPanel> editPanelsByRow = new HashMap<Integer, JPanel>();
    private int currentRow = 0;

    public void add(final IDialogComponent component) {
      final JPanel editPanel = new JPanel(new GridLayout());
      editPanelsByRow.put(currentRow++, editPanel);
      dialogPanel.add(new IDialogComponent() {
        public void fillInto(JPanel panel, int columnCount) {
          component.fillInto(panel, columnCount);
          panel.add(editPanel);
        }

        public int getColumnCount() {
          return component.getColumnCount() + 1;
        }
      });
    }

    public void addVerticalSpacing(int height) {
      dialogPanel.addVerticalSpacing(height);
    }

    public JPanel getContent() {
      return dialogPanel.getContent();
    }

    public void addEditAction(SmartAction action, int row) {
      JPanel panel = editPanelsByRow.get(row);
      JButton button = new JButton(action);
      if (action.getName() == null && action.getIcon() != null) {
        button.setPreferredSize(IconToggleButton.getPreferredSize(action.getIcon()));
      }
      panel.add(button);
    }
  }

  private static final int TEXT_COLUMS = 45;
  private EditButtonDialogPanel dialogPanel = new EditButtonDialogPanel();

  public CharacterDescriptionView(String header) {
    super(header);
  }

  @Override
  protected void createContent(JPanel panel, Object object) {
    panel.setLayout(new BorderLayout());
    panel.add(dialogPanel.getContent(), BorderLayout.CENTER);
  }

  public ITextView addLineView(final String labelText) {
    return addTextView(labelText, new LineTextView(TEXT_COLUMS));
  }

  public ITextView addAreaView(String labelText, int rows) {
    return addTextView(labelText, new AreaTextView(rows, TEXT_COLUMS));
  }

  private ITextView addTextView(final String labelText, final ITextView textView) {
    new LabelTextView(labelText, textView).addTo(dialogPanel, false);
    return textView;
  }

  public void addEditAction(SmartAction action, int row) {
    dialogPanel.addEditAction(action, row);
  }
}