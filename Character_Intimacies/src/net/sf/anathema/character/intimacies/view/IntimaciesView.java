package net.sf.anathema.character.intimacies.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.selection.StringSelectionView;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.library.trait.view.SwingExtensibleTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class IntimaciesView implements IIntimaciesView, IView {
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets()));
  private final JPanel mainPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final JPanel entryPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(3).fillX()));
  private final JPanel overviewPanel = new JPanel(new MigLayout());
  private final IntegerViewFactory factory;

  public IntimaciesView(IntegerViewFactory factory) {
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
  public IStringSelectionView addSelectionView(String labelText, RelativePath addIcon) {
    ImageIcon icon = new ImageProvider().getImageIcon(addIcon);
    StringSelectionView view = new StringSelectionView(labelText, icon);
    mainPanel.add(view.getComponent(), 0);
    return view;
  }

  @Override
  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(overviewPanel, borderLabel, false);
  }

  @Override
  public void setOverview(IOverviewCategory overviewView) {
    overviewPanel.removeAll();
    IView view = (IView) overviewView;
    overviewPanel.add(view.getComponent());
  }

  @Override
  public ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue) {
    SimpleTraitView view = new SimpleTraitView(factory, name, currentValue, maximalValue);
    SwingExtensibleTraitView traitView = new SwingExtensibleTraitView(view);
    traitView.addComponents(entryPanel);
    return traitView;
  }
}