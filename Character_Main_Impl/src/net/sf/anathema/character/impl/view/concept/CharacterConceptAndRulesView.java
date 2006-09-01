package net.sf.anathema.character.impl.view.concept;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.impl.view.EditButtonDialogPanel;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterConceptAndRulesView extends AbstractTabView<ICharacterConceptAndRulesViewProperties> implements
    ICharacterConceptAndRulesView {

  private final EditButtonDialogPanel characterConceptPanel = new EditButtonDialogPanel();
  private final IGridDialogPanel rulesPanel = new DefaultGridDialogPanel(false);

  public CharacterConceptAndRulesView() {
    super(null, false);
  }

  @Override
  protected void createContent(JPanel panel, ICharacterConceptAndRulesViewProperties properties) {
    characterConceptPanel.getContent().setBorder(new TitledBorder(properties.getConceptTitle()));
    rulesPanel.getContent().setBorder(new TitledBorder(properties.getRulesTitle()));
    panel.setLayout(new GridDialogLayout(1, false));
    panel.add(characterConceptPanel.getContent(), GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    panel.add(rulesPanel.getContent(), GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
  }

  public <V> IObjectSelectionView<V> addConceptObjectSelectionView(
      String labelText,
      V[] objects,
      ListCellRenderer renderer,
      boolean editable) {
    ObjectSelectionView<V> selectionView = new ObjectSelectionView<V>(labelText, renderer, editable, objects);
    selectionView.getComboBox().getEditor().getEditorComponent().setEnabled(true);
    selectionView.addComponents(characterConceptPanel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    return selectionView;
  }

  public ITextView addLabelTextView(String labelText) {
    LineTextView lineTextView = new LineTextView(45);
    lineTextView.getTextComponent().setDisabledTextColor(Color.DARK_GRAY);
    LabelTextView conceptView = new LabelTextView(labelText, lineTextView);
    conceptView.addTo(characterConceptPanel);
    return conceptView;
  }

  public void addRulesLabel(final String labelText) {
    rulesPanel.add(new IDialogComponent() {

      public int getColumnCount() {
        return 1;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(new JLabel(labelText));
      }
    });
  }

  public JTextComponent addWillpowerConditionView(final String headerLabelText) {
    WillpowerConditionView view = new WillpowerConditionView(headerLabelText);
    view.addComponents(characterConceptPanel);
    return view.getTextComponent();
  }

  public void setEnabled(boolean enabled) {
    GuiUtilities.setEnabled(characterConceptPanel.getContent(), enabled);
    handleSpecialComponents(characterConceptPanel.getContent(), enabled);
  }

  private void handleSpecialComponents(Container container, boolean enabled) {
    for (Component component : container.getComponents()) {
      if (component instanceof Container) {
        handleSpecialComponents((Container) component, enabled);
      }
    }
    ExperienceUtilities.setLabelColor(container, enabled);
  }
}