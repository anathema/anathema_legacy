package net.sf.anathema.character.impl.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.view.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewProperties;
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

  public CharacterConceptAndRulesView(String header) {
    super(header);
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

  public JTextArea addWillpowerConditionView(final String headerLabelText) {
    final JTextArea conditionLabel = new JTextArea(2, 45);
    conditionLabel.setEditable(false);
    conditionLabel.setWrapStyleWord(true);
    conditionLabel.setLineWrap(true);
    conditionLabel.setDisabledTextColor(Color.DARK_GRAY);
    JLabel label = new JLabel();
    conditionLabel.setFont(label.getFont());
    conditionLabel.setBackground(label.getBackground());
    characterConceptPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelLayoutData = new GridDialogLayoutData();
        labelLayoutData.setHorizontalAlignment(GridAlignment.BEGINNING);
        labelLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(new JLabel(headerLabelText), labelLayoutData);
        GridDialogLayoutData contentData = new GridDialogLayoutData();
        contentData.setHorizontalAlignment(GridAlignment.FILL);
        contentData.setVerticalAlignment(GridAlignment.FILL);
        panel.add(conditionLabel, contentData);
      }
    });
    return conditionLabel;
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