package net.sf.anathema.character.impl.view.concept;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.toolbar.ToolBarUtilities;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class CharacterConceptAndRulesView extends
    AbstractInitializableContentView<ICharacterConceptAndRulesViewProperties> implements ICharacterConceptAndRulesView {

  private final JPanel conceptPanel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel rulesPanel = new JPanel(new GridDialogLayout(1, false));
  private final List<JPanel> buttonPanels = new ArrayList<JPanel>();

  @Override
  protected void createContent(JPanel panel, ICharacterConceptAndRulesViewProperties properties) {
    conceptPanel.setBorder(new TitledBorder(properties.getConceptTitle()));
    rulesPanel.setBorder(new TitledBorder(properties.getRulesTitle()));
    panel.setLayout(new GridDialogLayout(1, false));
    panel.add(conceptPanel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
    panel.add(rulesPanel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
  }

  @Override
  public <V> IObjectSelectionView<V> addObjectSelectionView(
      String labelText,
      V[] objects,
      ListCellRenderer renderer,
      boolean editable) {
    ObjectSelectionView<V> selectionView = new ObjectSelectionView<V>(labelText, renderer, editable, objects);
    selectionView.getComboBox().getEditor().getEditorComponent().setEnabled(true);
    selectionView.setDisabledLabelColor(Color.DARK_GRAY);
    selectionView.addTo(conceptPanel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
    addButtonPanel();
    return selectionView;
  }

  @Override
  public ITextView addLabelTextView(String labelText) {
    LineTextView lineTextView = new LineTextView(45);
    lineTextView.getTextComponent().setDisabledTextColor(Color.DARK_GRAY);
    LabelTextView labelView = new LabelTextView(labelText, lineTextView);
    labelView.setDisabledLabelColor(Color.DARK_GRAY);
    labelView.addToStandardPanel(conceptPanel);
    addButtonPanel();
    return labelView;
  }
  
  @Override
  public void addSpinner(String labelText, IntegerSpinner spinner) {
	  JLabel label = new JLabel(labelText);
	  conceptPanel.add(label);
	  conceptPanel.add(spinner.getComponent());
  }

  private void addButtonPanel() {
    JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
    buttonPanels.add(buttonPanel);
    conceptPanel.add(buttonPanel);
  }

  @Override
  public void addRulesLabel(final String labelText) {
    rulesPanel.add(new JLabel(labelText));
  }

  @Override
  public AbstractButton addAction(SmartAction action, int row) {
    AbstractButton button = ToolBarUtilities.createToolBarButton(action);
    buttonPanels.get(row).add(button);
    return button;
  }
}