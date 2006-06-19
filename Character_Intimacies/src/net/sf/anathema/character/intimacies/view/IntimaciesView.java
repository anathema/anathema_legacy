package net.sf.anathema.character.intimacies.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.RearToggleButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;

public class IntimaciesView extends AbstractRemovableEntryView<IRemovableTraitView<IToggleButtonTraitView<?>>> implements
    IIntimaciesView {

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

  public JComponent getComponent() {
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(GridAlignment.FILL);
    data.setVerticalAlignment(GridAlignment.BEGINNING);
    mainPanel.add(entryPanel, data);
    GridDialogLayoutData mainData = new GridDialogLayoutData();
    mainData.setVerticalAlignment(GridAlignment.BEGINNING);
    content.add(mainPanel, mainData);
    content.add(overviewPanel, data);
    return content;
  }

  public boolean needsScrollbar() {
    return false;
  }

  public IIntimaciesSelectionView addSelectionView(String labelText, Icon addIcon) {
    IntimaciesSelectionView view = new IntimaciesSelectionView(labelText, addIcon);
    mainPanel.add(view.getComponent());
    return view;
  }

  public IRemovableTraitView<IToggleButtonTraitView<?>> addEntryView(Icon removeIcon, String string) {
    SimpleTraitView view = new SimpleTraitView(factory, string, 0, 5);
    RearToggleButtonTraitViewWrapper<SimpleTraitView> oneButtonView = new RearToggleButtonTraitViewWrapper<SimpleTraitView>(view, properties, false);
    RearButtonTraitViewWrapper<IToggleButtonTraitView<?>> twoButtonView = new RearButtonTraitViewWrapper<IToggleButtonTraitView<?>>(
        oneButtonView,
        removeIcon);
    twoButtonView.addComponents(entryPanel);
    return twoButtonView;
  }

  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(borderLabel, false);
  }

  public void setOverview(IOverviewCategory overviewView) {
    overviewPanel.removeAll();
    overviewPanel.add(overviewView.getComponent());
  }
}