package net.sf.anathema.character.library.trait.view.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import net.sf.anathema.character.generic.type.ICharacterType;
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
  public void initGui(ColumnCount columnCount, ICharacterType characterType) {
    fxView.initGui(columnCount, characterType);
    String coreSkin = "skin/anathema/dotselector.css";
    String characterTypeSkin;
    if (characterType.getId().equals("Solar")) {
      characterTypeSkin = "skin/solar/trait.css";
    } else {
      characterTypeSkin = "skin/anathema/character/trait.css";
    }
    Platform.runLater(new InitScene(panel, new ViewHolder(), coreSkin, characterTypeSkin));
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    fxView.startNewTraitGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue) {
    return fxView.addExtensibleTraitView(string, maximalValue);
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  private class ViewHolder implements ParentHolder {
    @Override
    public Parent getParent() {
      return (Parent) fxView.getNode();
    }
  }
}