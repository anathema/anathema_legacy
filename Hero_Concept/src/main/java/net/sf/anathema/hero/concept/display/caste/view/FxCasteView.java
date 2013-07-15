package net.sf.anathema.hero.concept.display.caste.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxCasteView implements CasteView, NodeHolder {
  private MigPane node = new MigPane(withoutInsets().wrapAfter(2));

  @Override
  public ObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> configuration) {
    final ComboBoxSelectionView<CasteType> view = new ComboBoxSelectionView<>(labelText, configuration);
    view.setStyleClass("casteselection");
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