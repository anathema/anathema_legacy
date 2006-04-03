package net.sf.anathema.character.impl.view.concept;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ObjectSelectionView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.workflow.textualdescription.view.ILabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterConceptAndRulesView extends AbstractTabView<ICharacterConceptAndRulesViewProperties> implements
    ICharacterConceptAndRulesView {

  private GridDialogPanel characterConceptPanel = new GridDialogPanel(false);
  private GridDialogPanel rulesPanel = new GridDialogPanel(false);

  public CharacterConceptAndRulesView() {
    super(null);
  }

  @Override
  protected void createContent(JPanel panel, ICharacterConceptAndRulesViewProperties properties) {
    characterConceptPanel.getContent().setBorder(new TitledBorder(properties.getConceptTitle()));
    rulesPanel.getContent().setBorder(new TitledBorder(properties.getRulesTitle()));
    panel.setLayout(new BorderLayout());
    panel.add(characterConceptPanel.getContent(), BorderLayout.CENTER);
    panel.add(rulesPanel.getContent(), BorderLayout.SOUTH);
  }

  public IObjectSelectionView addConceptObjectSelectionView(
      String labelText,
      Object[] objects,
      ListCellRenderer renderer,
      boolean editable) {
    return addObjectSelectionView(labelText, objects, renderer, editable, characterConceptPanel);
  }

  private IObjectSelectionView addObjectSelectionView(
      String labelText,
      Object[] objects,
      ListCellRenderer renderer,
      boolean editable,
      GridDialogPanel panel) {
    ObjectSelectionView selectionView = new ObjectSelectionView(objects, editable);
    selectionView.getComboBox().getEditor().getEditorComponent().setEnabled(true);
    selectionView.addTo(labelText, renderer, panel, GridDialogLayoutData.FILL_HORIZONTAL);
    return selectionView;
  }

  public ILabelTextView addLabelTextView(String labelText) {
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