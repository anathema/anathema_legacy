package net.sf.anathema.character.impl.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

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
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class BasicAdvantageView extends AbstractTabView<IAdvantageViewProperties> implements IBasicAdvantageView {

  private final IGridDialogPanel virtuePanel = new DefaultGridDialogPanel(false);
  private final IGridDialogPanel willpowerPanel = new DefaultGridDialogPanel(false);
  private final IGridDialogPanel backgroundSelectionPanel = new DefaultGridDialogPanel(false);
  private final JPanel backgroundDisplayPanel = new JPanel(new GridDialogLayout(2, false));
  private final EssencePanelView essencePanelView;
  private final IIntValueDisplayFactory guiConfiguration;
  private JPanel backgroundPanel;

  public BasicAdvantageView(IIntValueDisplayFactory intValueDisplayFactory) {
    super(null, false);
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
    GridDialogLayoutData willpowerData = new GridDialogLayoutData();
    willpowerData.setHorizontalAlignment(GridAlignment.FILL);
    willpowerData.setGrabExcessHorizontalSpace(true);
    willpowerData.setVerticalAlignment(GridAlignment.BEGINNING);
    addTitledPanel(properties.getWillpowerTitle(), innerPanel, willpowerPanel, willpowerData);
    GridDialogLayoutData essenceData = new GridDialogLayoutData();
    essenceData.setHorizontalAlignment(GridAlignment.FILL);
    essenceData.setGrabExcessHorizontalSpace(true);
    essenceData.setVerticalAlignment(GridAlignment.END);
    addTitledPanel(properties.getEssenceTitle(), innerPanel, essencePanelView.getPanel(), essenceData);
    GridDialogLayoutData fullSpanData = new GridDialogLayoutData();
    fullSpanData.setHorizontalSpan(2);
    fullSpanData.setGrabExcessHorizontalSpace(true);
    fullSpanData.setHorizontalAlignment(GridAlignment.FILL);
    backgroundPanel = createBackgroundPanel(properties.getBackgroundTitle());
    innerPanel.add(backgroundPanel, fullSpanData);
  }

  private JPanel createBackgroundPanel(String title) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    panel.add(backgroundSelectionPanel.getContent(), BorderLayout.CENTER);
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
      ComboBoxEditor editor,
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

  private final JPanel addTitledPanel(
      String title,
      JPanel container,
      IGridDialogPanel contentPanel,
      IGridDialogLayoutData constraint) {
    JPanel newPanel = contentPanel.getContent();
    newPanel.setBorder(new TitledBorder(title));
    container.add(newPanel, constraint);
    return newPanel;
  }
}