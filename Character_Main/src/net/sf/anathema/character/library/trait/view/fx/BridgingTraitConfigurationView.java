package net.sf.anathema.character.library.trait.view.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.InitScene;
import net.sf.anathema.platform.fx.ParentHolder;

import javax.swing.JComponent;

public class BridgingTraitConfigurationView implements IView, GroupedFavorableTraitConfigurationView {
  private final FxGroupedTraitConfigurationView fxView;
  private final JFXPanel panel = new JFXPanel();

  public BridgingTraitConfigurationView(FxGroupedTraitConfigurationView fxView) {
    this.fxView = fxView;
  }

  @Override
  public void initGui(ColumnCount columnCount) {
    fxView.initGui(columnCount);
    Platform.runLater(new InitScene(panel, new ViewHolder()));
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    fxView.startNewTraitGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String string, int currentValue, int maximalValue, Trait favorableTrait) {
    return fxView.addExtensibleTraitView(string, currentValue, maximalValue, favorableTrait);
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  private class ViewHolder implements ParentHolder{
    @Override
    public Parent getParent() {
      return (Parent) fxView.getNode();
    }
  }
}