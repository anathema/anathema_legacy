package net.sf.anathema.hero.languages.display.view;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.languages.display.presenter.LanguagesView;
import net.sf.anathema.hero.languages.display.presenter.ObjectSelectionViewWithTool;
import net.sf.anathema.hero.languages.display.presenter.RemovableEntryView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public class BridgingLanguagesView extends AbstractBridgingView implements LanguagesView {
  private final FxLanguagesView fxView;

  public BridgingLanguagesView(FxLanguagesView fxView) {
    this.fxView = fxView;
    init(fxView);
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
