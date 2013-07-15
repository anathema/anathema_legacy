package net.sf.anathema.hero.languages.display.view;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.languages.display.presenter.LanguagesView;
import net.sf.anathema.hero.languages.display.presenter.ObjectSelectionViewWithTool;
import net.sf.anathema.hero.languages.display.presenter.RemovableEntryView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingLanguagesView implements LanguagesView, IView {
  private final FxLanguagesView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingLanguagesView(FxLanguagesView fxView) {
    this.fxView = fxView;
    panel.init(fxView);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }

  @Override
  public ObjectSelectionViewWithTool<Object> addSelectionView(String labelText, AgnosticUIConfiguration<Object> renderer) {
    return fxView.addSelectionView(labelText, renderer);
  }

  @Override
  public OverviewCategory addOverview(String border) {
    return fxView.addOverview(border);
  }

  @Override
  public RemovableEntryView addEntryView(RelativePath removeIcon, String label) {
    return fxView.addEntryView(removeIcon, label);
  }
}
