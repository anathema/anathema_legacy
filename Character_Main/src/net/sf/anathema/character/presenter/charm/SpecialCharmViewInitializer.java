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
  private Color characterColor;

  public SpecialCharmViewInitializer(List<ISVGSpecialNodeView> specialCharmViews, IResources resources, ICharacterStatistics statistics, IMagicViewFactory viewFactory, Color characterColor) {
    this.specialCharmViews = specialCharmViews;
    this.resources = resources;
    this.statistics = statistics;
    this.viewFactory = viewFactory;
    this.characterColor = characterColor;
  }

  public void visitMultiLearnableCharm(final IMultiLearnableCharm visitedCharm) {
    SVGCategorizedSpecialNodeView multiLearnableCharmView = viewFactory.createMultiLearnableCharmView(
            visitedCharm,
            getCharmWidth(),
            characterColor);
    IMultiLearnableCharmConfiguration model = (IMultiLearnableCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visitedCharm.getCharmId());
    new MultiLearnableCharmPresenter(resources, multiLearnableCharmView, model).initPresentation();
    specialCharmViews.add(multiLearnableCharmView);
  }

  public void visitOxBodyTechnique(final IOxBodyTechniqueCharm visited) {
    SVGCategorizedSpecialNodeView oxBodyTechniqueView = viewFactory.createMultiLearnableCharmView(
            visited,
            getCharmWidth(),
            characterColor);
    ICharm originalCharm = statistics.getCharms().getCharmById(visited.getCharmId());
    IOxBodyTechniqueConfiguration model = (IOxBodyTechniqueConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visited.getCharmId());
    new OxBodyTechniquePresenter(resources, oxBodyTechniqueView, model).initPresentation();
    if ((originalCharm.hasChildren() || originalCharm.isTreeRoot()) && model.getCategories().length > 1) {
      specialCharmViews.add(viewFactory.createViewControlButton(
              oxBodyTechniqueView,
              getCharmWidth(),
              resources.getString("CharmTreeView.Ox-Body.HealthLevels"))); //$NON-NLS-1$
    } else {
      specialCharmViews.add(oxBodyTechniqueView);
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
            characterColor);
    ICharm originalCharm = getCharmConfiguration().getCharmById(visited.getCharmId());
    IMultipleEffectCharmConfiguration model = (IMultipleEffectCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visited.getCharmId());
    new MultipleEffectCharmPresenter(resources, subeffectView, model).initPresentation();
    if ((originalCharm.hasChildren() || originalCharm.isTreeRoot()) && model.getEffects().length > 1) {
      specialCharmViews.add(viewFactory.createViewControlButton(
              subeffectView,
              getCharmWidth(),
              resources.getString(labelKey)));
    } else {
      specialCharmViews.add(subeffectView);
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
}
