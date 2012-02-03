package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.*;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;

import java.awt.*;
import java.util.List;

public class SpecialCharmViewInitializer implements ISpecialCharmVisitor {
  private List<ISVGSpecialNodeView> specialCharmViews;
  private IResources resources;
  private ICharacterStatistics statistics;
  private IMagicViewFactory viewFactory;

  public SpecialCharmViewInitializer(List<ISVGSpecialNodeView> specialCharmViews, IResources resources, ICharacterStatistics statistics, IMagicViewFactory viewFactory) {
    this.specialCharmViews = specialCharmViews;
    this.resources = resources;
    this.statistics = statistics;
    this.viewFactory = viewFactory;
  }

  public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
    SVGCategorizedSpecialNodeView specialView = createViewForCharm(visitedCharm);
    IMultiLearnableCharmConfiguration model = getModelFormCharm(visitedCharm);
    new MultiLearnableCharmPresenter(resources, specialView, model).initPresentation();
    addViewDirectly(specialView);
  }

  public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
    SVGCategorizedSpecialNodeView specialView = createViewForCharm(visitedCharm);
    IOxBodyTechniqueConfiguration model = getModelFormCharm(visitedCharm);
    new OxBodyTechniquePresenter(resources, specialView, model).initPresentation();
    if (requiresMoreThanOneLine(visitedCharm, model)) {
      addButtonForCharmConfiguration("CharmTreeView.Ox-Body.HealthLevels", specialView);
    } else {
      addViewDirectly(specialView);
    }
  }

  public void visitPrerequisiteModifyingCharm(IPrerequisiteModifyingCharm visited) {
    // Nothing to do
  }

  public void visitTraitCapModifyingCharm(ITraitCapModifyingCharm visited) {
    // Nothing to do
  }

  public void visitPainToleranceCharm(IPainToleranceCharm visited) {
    // Nothing to do
  }

  public void visitSubeffectCharm(ISubeffectCharm visited) {
    createMultipleEffectCharmView(visited, "CharmTreeView.SubeffectCharm.ButtonLabel"); //$NON-NLS-1$
  }

  public void visitMultipleEffectCharm(IMultipleEffectCharm visited) {
    createMultipleEffectCharmView(visited, visited.getCharmId() + ".ControlButton"); //$NON-NLS-1$
  }

  public void visitUpgradableCharm(IUpgradableCharm visited) {
    createMultipleEffectCharmView(visited, visited.getCharmId() + ".ControlButton"); //$NON-NLS-1$
  }

  private void createMultipleEffectCharmView(IMultipleEffectCharm visitedCharm, final String labelKey) {
    SVGToggleButtonSpecialNodeView subeffectView = viewFactory.createSubeffectCharmView(
            visitedCharm,
            getCharmWidth(),
            getCharacterColor());
    IMultipleEffectCharmConfiguration model = getModelFormCharm(visitedCharm);
    new MultipleEffectCharmPresenter(resources, subeffectView, model).initPresentation();
    if (requiredMoreThanOneLine(visitedCharm, model)) {
      addButtonForCharmConfiguration(labelKey, subeffectView);
    } else {
      addViewDirectly(subeffectView);
    }
  }

  private double getCharmWidth() {
    return statistics.getCharacterTemplate()
            .getPresentationProperties()
            .getCharmPresentationProperties()
            .getNodeDimension()
            .getWidth();
  }

  private ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }

  private Color getCharacterColor() {
    return statistics.getCharacterTemplate().getPresentationProperties().getColor();
  }

  private void addButtonForCharmConfiguration(String labelKey, ISVGSpecialNodeView subeffectView) {
    specialCharmViews.add(viewFactory.createViewControlButton(
            subeffectView,
            getCharmWidth(),
            resources.getString(labelKey)));
  }

  private void addViewDirectly(ISVGSpecialNodeView view) {
    specialCharmViews.add(view);
  }

  private boolean requiresMoreThanOneLine(ISpecialCharm visitedCharm, IOxBodyTechniqueConfiguration model) {
    ICharm originalCharm = findOriginalCharm(visitedCharm);
    return otherCharmsAreRenderedBeneath(originalCharm) && hasMoreThanOneCategory(model);
  }

  private boolean requiredMoreThanOneLine(ISpecialCharm visitedCharm, IMultipleEffectCharmConfiguration model) {
    ICharm originalCharm = findOriginalCharm(visitedCharm);
    return otherCharmsAreRenderedBeneath(originalCharm) && hasMoreThanOneEffect(model);
  }

  private ICharm findOriginalCharm(ISpecialCharm visitedCharm) {
    return getCharmConfiguration().getCharmById(visitedCharm.getCharmId());
  }

  private boolean hasMoreThanOneEffect(IMultipleEffectCharmConfiguration model) {
    return model.getEffects().length > 1;
  }

  private boolean hasMoreThanOneCategory(IOxBodyTechniqueConfiguration model) {
    return model.getCategories().length > 1;
  }

  private boolean otherCharmsAreRenderedBeneath(ICharm originalCharm) {
    return (originalCharm.hasChildren() || originalCharm.isTreeRoot());
  }

  private SVGCategorizedSpecialNodeView createViewForCharm(ISpecialCharm visitedCharm) {
    return viewFactory.createMultiLearnableCharmView(
            visitedCharm,
            getCharmWidth(),
            getCharacterColor());
  }

  @SuppressWarnings("unchecked")
  private <T> T getModelFormCharm(ISpecialCharm visitedCharm) {
    return (T) getCharmConfiguration().getSpecialCharmConfiguration(visitedCharm.getCharmId());
  }
}