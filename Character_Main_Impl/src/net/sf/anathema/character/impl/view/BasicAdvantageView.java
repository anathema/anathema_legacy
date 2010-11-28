package net.sf.anathema.character.impl.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.impl.view.advantage.EssencePanelView;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class BasicAdvantageView extends AbstractInitializableContentView<IAdvantageViewProperties> implements
    IBasicAdvantageView {

  private final JPanel virtuePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel willpowerPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel backgroundSelectionPanel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel backgroundDisplayPanel = new JPanel(new GridDialogLayout(2, false));
  private final EssencePanelView essencePanelView;
  private final IIntValueDisplayFactory guiConfiguration;
  private JPanel backgroundPanel;

  public BasicAdvantageView(IIntValueDisplayFactory intValueDisplayFactory) {
    this.guiConfiguration = intValueDisplayFactory;
    essencePanelView = new EssencePanelView(intValueDisplayFactory);
  }

  @Override
  public void createContent(JPanel content, IAdvantageViewProperties properties) {
    content.setLayout(new FlowLayout(FlowLayout.LEFT));
    JPanel innerPanel = new JPanel(new GridDialogLayout(2, false));
    content.add(innerPanel);
    GridDialogLayoutData virtueData = new GridDialogLayoutData();
    virtueData.setVerticalSpan(2);
    virtueData.setVerticalAlignment(GridAlignment.FILL);
    addTitledPanel(properties.getVirtueTitle(), innerPanel, virtuePanel, virtueData);
    GridDialogLayoutData willpowerData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
    willpowerData.setVerticalAlignment(GridAlignment.BEGINNING);
    addTitledPanel(properties.getWillpowerTitle(), innerPanel, willpowerPanel, willpowerData);
    GridDialogLayoutData essenceData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
    essenceData.setVerticalAlignment(GridAlignment.END);
    addTitledPanel(properties.getEssenceTitle(), innerPanel, essencePanelView.getComponent(), essenceData);
    backgroundPanel = createBackgroundPanel(properties.getBackgroundTitle());
    innerPanel.add(backgroundPanel, GridDialogLayoutDataUtilities.createHorizontalSpanData(
        2,
        GridDialogLayoutData.FILL_HORIZONTAL));
  }

  private JPanel createBackgroundPanel(String title) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    panel.add(backgroundSelectionPanel, BorderLayout.CENTER);
    panel.add(backgroundDisplayPanel, BorderLayout.SOUTH);
    return panel;
  }

  public IIntValueView addVirtue(String labelText, int value, int maxValue) {
    SimpleTraitView virtueView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    virtueView.addComponents(virtuePanel);
    return virtueView;
  }

  public IIntValueView addWillpower(String labelText, int value, int maxValue) {
    SimpleTraitView willpowerView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    willpowerView.addComponents(willpowerPanel);
    return willpowerView;
  }

  public IButtonControlledObjectSelectionView<Object> addBackgroundSelectionView(
      String labelText,
      ITextFieldComboBoxEditor editor,
      ListCellRenderer renderer,
      Icon addIcon) {
    ButtonControlledObjectSelectionView<Object> objectSelectionView = new ButtonControlledObjectSelectionView<Object>(
        renderer,
        addIcon,
        labelText,
        editor);
    objectSelectionView.addComponents(backgroundSelectionPanel);
    return objectSelectionView;
  }

  public IRemovableTraitView<SimpleTraitView> addBackgroundView(
      Icon deleteIcon,
      String labelText,
      int value,
      int maxValue) {
    SimpleTraitView view = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    RearButtonTraitViewWrapper<SimpleTraitView> backgroundView = new RearButtonTraitViewWrapper<SimpleTraitView>(
        view,
        deleteIcon);
    backgroundView.addComponents(backgroundDisplayPanel);
    return backgroundView;
  }

  public IIntValueView addEssenceView(String labelText, int value, int maxValue) {
    return essencePanelView.addEssenceView(labelText, value, maxValue);
  }

  public IValueView<String> addPoolView(String labelText, String value) {
    return essencePanelView.addPoolView(labelText, value);
  }

  public void setBackgroundPanelEnabled(boolean enabled) {
    GuiUtilities.setEnabled(backgroundPanel, enabled);
  }

  private final JComponent addTitledPanel(
      String title,
      JPanel container,
      JComponent component,
      IGridDialogLayoutData constraint) {
    component.setBorder(new TitledBorder(title));
    container.add(component, constraint);
    return component;
  }
}