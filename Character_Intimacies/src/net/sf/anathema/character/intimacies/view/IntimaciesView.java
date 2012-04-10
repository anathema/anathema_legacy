package net.sf.anathema.character.intimacies.view;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.selection.StringSelectionView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.RearToggleButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class IntimaciesView extends AbstractRemovableEntryView<IRemovableTraitView<IToggleButtonTraitView< ? >>> implements
    IIntimaciesView,
    IView {

  private final JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel entryPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel overviewPanel = new JPanel(new GridDialogLayout(1, false));
  private final IIntValueDisplayFactory factory;
  private final IIconToggleButtonProperties properties;

  public IntimaciesView(IIntValueDisplayFactory factory, IIconToggleButtonProperties properties) {
    this.factory = factory;
    this.properties = properties;
  }

  @Override
  public JComponent getComponent() {
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createTopData();
    data.setHorizontalAlignment(GridAlignment.FILL);
    mainPanel.add(entryPanel, data);
    content.add(mainPanel, GridDialogLayoutDataFactory.createTopData());
    content.add(overviewPanel, data);
    return content;
  }

  @Override
  public IStringSelectionView addSelectionView(String labelText, Icon addIcon) {
    return new StringSelectionView(mainPanel, labelText, addIcon);
  }

  @Override
  public IRemovableTraitView<IToggleButtonTraitView< ? >> addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string) {
    SimpleTraitView view = new SimpleTraitView(factory, string, 0, 5);
    RearToggleButtonTraitViewWrapper<SimpleTraitView> oneButtonView = new RearToggleButtonTraitViewWrapper<SimpleTraitView>(
        view,
        properties,
        false);
    RearButtonTraitViewWrapper<IToggleButtonTraitView< ? >> twoButtonView = new RearButtonTraitViewWrapper<IToggleButtonTraitView< ? >>(
        oneButtonView,
        removeIcon);
    twoButtonView.addComponents(entryPanel);
    return twoButtonView;
  }

  @Override
  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(overviewPanel, borderLabel, false);
  }

  @Override
  public void setOverview(IOverviewCategory overviewView) {
    overviewPanel.removeAll();
    overviewPanel.add(overviewView.getComponent());
  }
}