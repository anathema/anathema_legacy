package net.sf.anathema.hero.intimacies.display;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.swing.hero.overview.SwingOverviewCategory;
import net.sf.anathema.swing.hero.traitview.SimpleTraitView;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class IntimaciesViewImpl implements IntimaciesView, IView {
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets()));
  private final JPanel mainPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final JPanel entryPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(3).fillX()));
  private final JPanel overviewPanel = new JPanel(new MigLayout());
  private final IntegerViewFactory factory;

  public IntimaciesViewImpl(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public JComponent getComponent() {
    mainPanel.add(entryPanel, new CC().alignY("top").growX());
    content.add(mainPanel, new CC().alignY("top"));
    content.add(overviewPanel, new CC().alignY("top").growX());
    return content;
  }

  @Override
  public StringEntryView addSelectionView(String labelText) {
    SwingStringEntryView view = new SwingStringEntryView(labelText);
    mainPanel.add(view.getComponent(), 0);
    return view;
  }

  @Override
  public OverviewCategory addOverview(String border) {
    return new SwingOverviewCategory(overviewPanel, border, false);
  }

  @Override
  public void setOverview(OverviewCategory overviewView) {
    overviewPanel.removeAll();
    IView view = (IView) overviewView;
    overviewPanel.add(view.getComponent());
  }

  @Override
  public ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue) {
    SimpleTraitView view = SimpleTraitView.RightAlignedWithoutUpperBounds(factory, name, currentValue, maximalValue);
    SwingExtensibleTraitView traitView = new SwingExtensibleTraitView(view);
    traitView.addComponents(entryPanel);
    return traitView;
  }
}