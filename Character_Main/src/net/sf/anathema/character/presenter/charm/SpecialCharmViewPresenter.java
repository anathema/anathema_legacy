package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;
import net.sf.anathema.charmtree.presenter.view.ISpecialCharmViewContainer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;

import javax.swing.ToolTipManager;
import java.util.ArrayList;
import java.util.List;

public class SpecialCharmViewPresenter {
  private final List<ISVGSpecialNodeView> specialCharmViews = new ArrayList<ISVGSpecialNodeView>();
  private ICharacterStatistics statistics;
  private ISpecialCharmViewContainer view;
  private IResources resources;
  private CharmGroupInformer charmGroupInformer;

  public SpecialCharmViewPresenter(ICharacterStatistics statistics, ISpecialCharmViewContainer view, IResources resources, CharmGroupInformer informer) {
    this.statistics = statistics;
    this.view = view;
    this.resources = resources;
    this.charmGroupInformer = informer;
  }

  public void initPresentation() {
    for (ISpecialCharm charm : getCharmConfiguration().getSpecialCharms()) {
      SpecialCharmViewBuilder builder = new SpecialCharmViewBuilder(resources, statistics, view);
      charm.accept(builder);
      specialCharmViews.add(builder.getResult());
    }
  }

  public void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea() {
    for (ISVGSpecialNodeView charmView : specialCharmViews) {
      ICharm charm = getCharmConfiguration().getCharmById(charmView.getNodeId());
      boolean isVisible = isVisible(charm);
      if (isVisible) {
        charmView.reset();
      }
    }
    ToolTipManager.sharedInstance().setEnabled(false);
    ToolTipManager.sharedInstance().setEnabled(true);
  }

  public void showSpecialViews() {
    ICharmGroup group = charmGroupInformer.getCurrentGroup();
    if (group == null) {
      return;
    }
    for (ISVGSpecialNodeView charmView : specialCharmViews) {
      ICharm charm = getCharmConfiguration().getCharmById(charmView.getNodeId());
      boolean isVisible = isVisible(charm);
      view.setSpecialCharmViewVisible(charmView, isVisible);
    }
  }

  private boolean isVisible(ICharm charm) {
    ICharmGroup group = charmGroupInformer.getCurrentGroup();
    if (group == null) {
      return false;
    }
    boolean isOfGroupType = charm.getCharacterType() == group.getCharacterType();
    return isOfGroupType && charm.getGroupId().equals(group.getId());
  }

  private ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }
}