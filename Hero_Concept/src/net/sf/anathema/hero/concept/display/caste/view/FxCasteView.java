package net.sf.anathema.hero.concept.display.caste.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxCasteView implements CasteView, NodeHolder {
  private MigPane node = new MigPane(withoutInsets().wrapAfter(2));

  @Override
  public IObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> configuration) {
    final ComboBoxSelectionView<CasteType> view = new ComboBoxSelectionView<>(labelText, configuration);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        node.add(view.getNode(), new CC().growX().pushX());
      }
    });
    return view;
  }

  public Node getNode() {
    return node;
  }
}