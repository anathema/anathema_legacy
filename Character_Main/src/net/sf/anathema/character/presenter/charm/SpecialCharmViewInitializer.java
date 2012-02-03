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

  public void visitMultiLearnableCharm(final IMultiLearnableCharm visitedCharm) {
    SVGCategorizedSpecialNodeView multiLearnableCharmView = viewFactory.createMultiLearnableCharmView(
            visitedCharm,
            getCharmWidth(),
            getCharacterColor());
    IMultiLearnableCharmConfiguration model = (IMultiLearnableCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visitedCharm.getCharmId());
    new MultiLearnableCharmPresenter(resources, multiLearnableCharmView, model).initPresentation();
    addViewDirectly(multiLearnableCharmView);
  }

  public void visitOxBodyTechnique(final IOxBodyTechniqueCharm visited) {
    SVGCategorizedSpecialNodeView oxBodyTechniqueView = viewFactory.createMultiLearnableCharmView(
            visited,
            getCharmWidth(),
            getCharacterColor());
    ICharm originalCharm = statistics.getCharms().getCharmById(visited.getCharmId());
    IOxBodyTechniqueConfiguration model = (IOxBodyTechniqueConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visited.getCharmId());
    new OxBodyTechniquePresenter(resources, oxBodyTechniqueView, model).initPresentation();
    if (requiresMoreThanOneLine(originalCharm, model)) {
      addButtonForCharmConfiguration("CharmTreeView.Ox-Body.HealthLevels", oxBodyTechniqueView);
    } else {
      addViewDirectly(oxBodyTechniqueView);
    }
  }

  public void visitPrerequisiteModifyingCharm(final IPrerequisiteModifyingCharm visited) {
    // Nothing to do
  }

  public void visitTraitCapModifyingCharm(final ITraitCapModifyingCharm visited) {
    // Nothing to do
  }

  public void visitPainToleranceCharm(final IPainToleranceCharm visited) {
    // Nothing to do
  }

  public void visitSubeffectCharm(final ISubeffectCharm visited) {
    createMultipleEffectCharmView(visited, "CharmTreeView.SubeffectCharm.ButtonLabel"); //$NON-NLS-1$
  }

  public void visitMultipleEffectCharm(final IMultipleEffectCharm visited) {
    createMultipleEffectCharmView(visited, visited.getCharmId() + ".ControlButton"); //$NON-NLS-1$
  }

  public void visitUpgradableCharm(final IUpgradableCharm visited) {
    createMultipleEffectCharmView(visited, visited.getCharmId() + ".ControlButton"); //$NON-NLS-1$
  }

  private void createMultipleEffectCharmView(final IMultipleEffectCharm visited, final String labelKey) {
    SVGToggleButtonSpecialNodeView subeffectView = viewFactory.createSubeffectCharmView(
            visited,
            getCharmWidth(),
            getCharacterColor());
    ICharm originalCharm = getCharmConfiguration().getCharmById(visited.getCharmId());
    IMultipleEffectCharmConfiguration model = (IMultipleEffectCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visited.getCharmId());
    new MultipleEffectCharmPresenter(resources, subeffectView, model).initPresentation();
    if (requiredMoreThanOneLine(originalCharm, model)) {
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

  private boolean requiresMoreThanOneLine(ICharm originalCharm, IOxBodyTechniqueConfiguration model) {
    return otherCharmsAreRenderedBeneath(originalCharm) && hasMoreThanOneCategory(model);
  }

  private boolean requiredMoreThanOneLine(ICharm originalCharm, IMultipleEffectCharmConfiguration model) {
    return otherCharmsAreRenderedBeneath(originalCharm) && hasMoreThanOneEffect(model);
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
}