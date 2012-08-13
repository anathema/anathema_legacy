package net.sf.anathema.character.presenter.magic.svgcharms;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.presenter.magic.SpecialCharmList;
import net.sf.anathema.charmtree.presenter.view.ISpecialCharmViewContainer;
import net.sf.anathema.charmtree.presenter.view.SpecialCharmViewFactory;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;

import java.util.ArrayList;
import java.util.List;

public class SvgSpecialCharmList implements SpecialCharmList {
  private final List<ISVGSpecialNodeView> specialCharmViews = new ArrayList<ISVGSpecialNodeView>();
  private final IResources resources;
  private final SpecialCharmViewFactory factory;
  private final CharacterCharmModel charmModel;
  private final ITreePresentationProperties presentationProperties;
  private final ISpecialCharmViewContainer container;
  private Predicate<String> visibilityPredicate = Predicates.alwaysFalse();

  public SvgSpecialCharmList(IResources resources, SpecialCharmViewFactory factory, CharacterCharmModel charmModel,
                             ITreePresentationProperties presentationProperties, ISpecialCharmViewContainer container) {
    this.resources = resources;
    this.factory = factory;
    this.charmModel = charmModel;
    this.presentationProperties = presentationProperties;
    this.container = container;
  }

  @Override
  public void add(ISpecialCharm charm) {
    SpecialCharmViewBuilder builder = new SpecialCharmViewBuilder(resources, factory, charmModel,
            presentationProperties);
    charm.accept(builder);
    ISVGSpecialNodeView nodeView = builder.getResult();
    if (nodeView != null) {
      specialCharmViews.add(nodeView);
    }
  }

  @Override
  public void hideAllViews() {
    for (ISVGSpecialNodeView charmView : specialCharmViews) {
      boolean isVisible = isVisible(charmView);
      if (isVisible) {
        charmView.reset();
      }
    }
  }

  @Override
  public void showViews() {
    for (ISVGSpecialNodeView charmView : specialCharmViews) {
      boolean isVisible = isVisible(charmView);
      container.setSpecialCharmViewVisible(charmView, isVisible);
    }
  }

  @Override
  public void setVisibilityPredicate(Predicate<String> predicate) {
    this.visibilityPredicate = predicate;
  }

  private boolean isVisible(ISVGSpecialNodeView charmView) {
    String nodeId = charmView.getNodeId();
    return visibilityPredicate.apply(nodeId);
  }
}