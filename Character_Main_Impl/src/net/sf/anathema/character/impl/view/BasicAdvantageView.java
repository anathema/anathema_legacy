package net.sf.anathema.character.impl.view;

import java.awt.BorderLayout;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.character.impl.view.advantage.EssencePanelView;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IRemovableIntValueView;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class BasicAdvantageView extends AbstractTabView<IAdvantageViewProperties> implements IBasicAdvantageView {

  private final GridDialogPanel virtuePanel = new GridDialogPanel(false);
  private final GridDialogPanel willpowerPanel = new GridDialogPanel(false);
  private final GridDialogPanel backgroundSelectionPanel = new GridDialogPanel(false);
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
  public void createContent(JPanel panel, IAdvantageViewProperties properties) {
    panel.setLayout(new GridDialogLayout(2, false));
    GridDialogLayoutData virtueData = new GridDialogLayoutData();
    virtueData.setVerticalSpan(2);
    virtueData.setVerticalAlignment(GridAlignment.FILL);
    addTitledPanel(properties.getVirtueTitle(), panel, virtuePanel, virtueData);
    addTitledPanel(properties.getWillpowerTitle(), panel, willpowerPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    addTitledPanel(
        properties.getEssenceTitle(),
        panel,
        essencePanelView.getPanel(),
        GridDialogLayoutData.FILL_HORIZONTAL);
    GridDialogLayoutData fullSpanData = new GridDialogLayoutData();
    fullSpanData.setHorizontalSpan(2);
    fullSpanData.setGrabExcessHorizontalSpace(true);
    fullSpanData.setHorizontalAlignment(GridAlignment.FILL);
    backgroundPanel = createBackgroundPanel(properties.getBackgroundTitle());
    panel.add(backgroundPanel, fullSpanData);
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
    GridDialogLayoutData layoutData = new GridDialogLayoutData(GridDialogLayoutData.RIGHT);
    layoutData.setGrabExcessHorizontalSpace(true);
    willpowerView.setDotLayoutData(layoutData);
    willpowerView.addComponents(willpowerPanel);
    return willpowerView;
  }

  public IObjectSelectionView addBackgroundSelectionView(
      String labelText,
      Object[] selectionObjects,
      ComboBoxEditor editor,
      ListCellRenderer renderer,
      Icon addIcon) {
    ButtonControlledObjectSelectionView objectSelectionView = new ButtonControlledObjectSelectionView(
        selectionObjects,
        editor,
        addIcon);
    objectSelectionView.addTo(labelText, renderer, backgroundSelectionPanel);
    return objectSelectionView;
  }

  public IRemovableIntValueView addBackgroundView(Icon deleteIcon, String labelText, int value, int maxValue) {
    SimpleTraitView view = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    RearButtonTraitViewWrapper backgroundView = new RearButtonTraitViewWrapper(view, deleteIcon);
    backgroundView.addComponents(backgroundDisplayPanel);
    return backgroundView;
  }

  public IIntValueView addEssenceView(String labelText, int value, int maxValue) {
    return essencePanelView.addEssenceView(labelText, value, maxValue);
  }

  public ILabelledValueView<String> addPoolView(String labelText, String value) {
    return essencePanelView.addPoolView(labelText, value);
  }

  public void setBackgroundPanelEnabled(boolean enabled) {
    GuiUtilities.setEnabled(backgroundPanel, enabled);
  }
}