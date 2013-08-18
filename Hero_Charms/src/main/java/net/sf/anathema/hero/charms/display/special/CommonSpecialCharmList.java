package net.sf.anathema.hero.charms.display.special;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.SpecialCharmList;
import net.sf.anathema.platform.tree.display.SpecialNodeView;
import net.sf.anathema.platform.tree.display.TreeView;

import java.util.ArrayList;
import java.util.List;

public class CommonSpecialCharmList implements SpecialCharmList {
  private final List<SpecialNodeView> specialCharmViews = new ArrayList<>();
  private final SpecialCharmViewBuilder builder;
  private Predicate<String> visibilityPredicate = Predicates.alwaysFalse();
  private TreeView treeView;

  public CommonSpecialCharmList(SpecialCharmViewBuilder specialViewBuilder) {
    this.builder = specialViewBuilder;
  }

  @Override
  public void add(ISpecialCharm charm) {
    builder.reset();
    builder.buildFor(charm);
    if (builder.hasResult()) {
      SpecialNodeView nodeView = builder.getResult();
      specialCharmViews.add(nodeView);
    }
  }

  @Override
  public void showViews() {
    for (SpecialNodeView charmView : specialCharmViews) {
      boolean isVisible = isVisible(charmView);
      if (isVisible){
        treeView.addSpecialControl(charmView.getNodeId(), charmView);
      }
    }
  }

  @Override
  public void setVisibilityPredicate(Predicate<String> predicate) {
    this.visibilityPredicate = predicate;
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }

  private boolean isVisible(SpecialNodeView charmView) {
    String nodeId = charmView.getNodeId();
    return visibilityPredicate.apply(nodeId);
  }
}