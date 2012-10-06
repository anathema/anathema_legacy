package net.sf.anathema.character.intimacies.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
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
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class IntimaciesView extends AbstractRemovableEntryView<IRemovableTraitView<IToggleButtonTraitView<?>>> implements IIntimaciesView, IView {
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets()));
  private final JPanel mainPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final JPanel entryPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2).fillX()));
  private final JPanel overviewPanel = new JPanel(new MigLayout());
  private final IntegerViewFactory factory;
  private final IIconToggleButtonProperties properties;

  public IntimaciesView(IntegerViewFactory factory, IIconToggleButtonProperties properties) {
    this.factory = factory;
    this.properties = properties;
  }

  @Override
  public JComponent getComponent() {
    mainPanel.add(entryPanel, new CC().alignY("top").growX());
    content.add(mainPanel, new CC().alignY("top"));
    content.add(overviewPanel, new CC().alignY("top").growX());
    return content;
  }

  @Override
  public IStringSelectionView addSelectionView(String labelText, Icon addIcon) {
    return new StringSelectionView(mainPanel, labelText, addIcon);
  }

  @Override
  public IRemovableTraitView<IToggleButtonTraitView<?>> addEntryView(Icon removeIcon, IModifiableCapTrait trait,
                                                                     String string) {
    SimpleTraitView view = new SimpleTraitView(factory, string, 0, 5);
    RearToggleButtonTraitViewWrapper<SimpleTraitView> oneButtonView = new RearToggleButtonTraitViewWrapper<SimpleTraitView>(
            view, properties, false);
    RearButtonTraitViewWrapper<IToggleButtonTraitView<?>> twoButtonView = new RearButtonTraitViewWrapper<IToggleButtonTraitView<?>>(
            oneButtonView, removeIcon);
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