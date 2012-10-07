package net.sf.anathema.character.impl.view.concept;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class CharacterConceptAndRulesView implements ICharacterConceptAndRulesView, IInitializableContentView<ICharacterConceptAndRulesViewProperties> {
  private final JPanel conceptPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2).debug(1)));
  private final JPanel rulesPanel = new JPanel(new MigLayout(withoutInsets()));
  private final JPanel content = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final JPanel buttonPanel = new JPanel(new GridLayout(1, 0));

  @Override
  public void initGui(ICharacterConceptAndRulesViewProperties properties) {
    conceptPanel.setBorder(new TitledBorder(properties.getConceptTitle()));
    rulesPanel.setBorder(new TitledBorder(properties.getRulesTitle()));
    content.add(conceptPanel, new CC().growX());
    content.add(rulesPanel, new CC().growX());
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public <V> IObjectSelectionView<V> addObjectSelectionView(String labelText, V[] objects, ListCellRenderer renderer,
                                                            boolean editable) {
    ObjectSelectionView<V> selectionView = new ObjectSelectionView<V>(labelText, renderer, editable, objects);
    selectionView.getComboBox().getEditor().getEditorComponent().setEnabled(true);
    selectionView.setDisabledLabelColor(Color.DARK_GRAY);
    selectionView.addTo(conceptPanel, new CC().growX().pushX());
    return selectionView;
  }

  @Override
  public ITextView addLabelTextView(String labelText) {
    LineTextView lineTextView = new LineTextView(45);
    lineTextView.getTextComponent().setDisabledTextColor(Color.DARK_GRAY);
    LabelTextView labelView = new LabelTextView(labelText, lineTextView);
    labelView.setDisabledLabelColor(Color.DARK_GRAY);
    labelView.addToMigPanel(conceptPanel, new CC().growX().split(2));
    conceptPanel.add(buttonPanel);
    return labelView;
  }

  @Override
  public void addRulesLabel(String labelText) {
    rulesPanel.add(new JLabel(labelText));
  }

  @Override
  public AbstractButton addAction(SmartAction action) {
    AbstractButton button = ToolBarUtilities.createToolBarButton(action);
    buttonPanel.add(button);
    return button;
  }
}