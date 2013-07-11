package net.sf.anathema.hero.spells.display.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.hero.magic.display.FxMagicLearnView;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.hero.spells.display.presenter.SpellView;
import net.sf.anathema.hero.spells.display.presenter.SpellViewProperties;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import org.jmock.example.announcer.Announcer;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxSpellView implements SpellView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final Announcer<ObjectValueListener> circleControl = Announcer.to(ObjectValueListener.class);
  private ComboBoxSelectionView<Identifier> selectionView;

  @Override
  public void addCircleSelection(Identifier[] circles, SpellViewProperties properties) {
    this.selectionView = new ComboBoxSelectionView<>(properties.getCircleLabel(), properties.getCircleSelectionRenderer());
    selectionView.setObjects(circles);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        content.add(selectionView.getNode(), new CC().wrap());
      }
    });
    selectionView.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {
      @Override
      public void valueChanged(Identifier newValue) {
        circleControl.announce().valueChanged(newValue);
      }
    });
  }

  @Override
  public void showSelectedCircle(CircleType newValue) {
    selectionView.setSelectedObject(newValue);
  }

  @Override
  public MagicLearnView addMagicLearnView(MagicLearnProperties properties) {
    final FxMagicLearnView magicLearnView = new FxMagicLearnView(properties);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        content.add(magicLearnView.getNode(), new CC().grow().push());
      }
    });
    return magicLearnView;
  }

  @Override
  public void addCircleSelectionListener(ObjectValueListener<CircleType> listener) {
    circleControl.addListener(listener);
  }

  @Override
  public Node getNode() {
    return content;
  }
}