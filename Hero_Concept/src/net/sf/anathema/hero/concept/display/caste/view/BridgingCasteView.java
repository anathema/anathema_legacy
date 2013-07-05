package net.sf.anathema.hero.concept.display.caste.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.platform.fx.InitScene;
import net.sf.anathema.platform.fx.ViewHolder;

import javax.swing.JComponent;

public class BridgingCasteView implements CasteView, IView {
  private final FxCasteView fxView;
  private final JFXPanel panel = new JFXPanel();

  public BridgingCasteView(FxCasteView fxView) {
    this.fxView = fxView;
    Platform.runLater(new InitScene(panel, new ViewHolder(fxView)));
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public IObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> renderer) {
    return fxView.addObjectSelectionView(labelText, renderer);
  }

}