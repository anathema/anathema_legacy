package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.hero.charms.display.view.SpecialCharmViewContainer;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.charms.model.special.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.MultiLearnCharmSpecials;
import net.sf.anathema.hero.charms.model.special.oxbody.IOxBodyTechniqueCharm;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyTechniqueSpecials;
import net.sf.anathema.hero.charms.model.special.paintolerance.IPainToleranceCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecials;
import net.sf.anathema.hero.charms.model.special.traitcap.ITraitCapModifyingCharm;
import net.sf.anathema.hero.charms.model.special.upgradable.IUpgradableCharm;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.display.SpecialNodeView;

public class AgnosticSpecialCharmViewBuilder implements SpecialCharmViewBuilder {

  private SpecialNodeView result;
  private final Resources resources;
  private final CharmsModel configuration;
  private final SpecialCharmViewContainer view;

  public AgnosticSpecialCharmViewBuilder(Resources resources, CharmsModel configuration, SpecialCharmViewContainer view) {
    this.resources = resources;
    this.configuration = configuration;
    this.view = view;
  }

  @Override
  public SpecialNodeView getResult() {
    return result;
  }

  @Override
  public void reset() {
    result = null;
  }

  @Override
  public boolean hasResult() {
    return result != null;
  }

  @Override
  public void buildFor(ISpecialCharm charm) {
    charm.accept(new AgnosticSpecialCharmVisitor());
  }

  private class AgnosticSpecialCharmVisitor implements ISpecialCharmVisitor {

    @Override
    public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
      CategorizedSpecialNodeView view = createCategorizedView(visitedCharm);
      MultiLearnCharmSpecials model = getModelFromCharm(visitedCharm);
      new MultiLearnableCharmPresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
      CategorizedSpecialNodeView view = createCategorizedView(visitedCharm);
      OxBodyTechniqueSpecials model = getModelFromCharm(visitedCharm);
      new OxBodyTechniquePresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitSubEffectCharm(ISubEffectCharm visited) {
      createMultipleEffectCharmView(visited);
    }

    @Override
    public void visitMultipleEffectCharm(IMultipleEffectCharm visited) {
      createMultipleEffectCharmView(visited);
    }

    @Override
    public void visitUpgradableCharm(IUpgradableCharm visited) {
      createMultipleEffectCharmView(visited);
    }

    private void createMultipleEffectCharmView(IMultipleEffectCharm visitedCharm) {
      ToggleButtonSpecialNodeView view = createBooleanView(visitedCharm);
      MultipleEffectCharmSpecials model = getModelFromCharm(visitedCharm);
      new MultipleEffectCharmPresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitPainToleranceCharm(IPainToleranceCharm charm) {
      // Nothing to do
    }

    @Override
    public void visitPrerequisiteModifyingCharm(IPrerequisiteModifyingCharm charm) {
      // Nothing to do
    }

    @Override
    public void visitTraitCapModifyingCharm(ITraitCapModifyingCharm charm) {
      // Nothing to do
    }

    @SuppressWarnings("unchecked")
    private <T> T getModelFromCharm(ISpecialCharm visitedCharm) {
      return (T) configuration.getSpecialCharmConfiguration(visitedCharm.getCharmId());
    }
  }

  private ToggleButtonSpecialNodeView createBooleanView(IMultipleEffectCharm visitedCharm) {
    ToggleButtonSpecialNodeView specialView = view.createToggleButtonSpecialView();
    specialView.setCharmId(visitedCharm.getCharmId());
    return specialView;
  }

  private CategorizedSpecialNodeView createCategorizedView(ISpecialCharm visitedCharm) {
    CategorizedSpecialNodeView specialView = view.createCategorizedSpecialView();
    specialView.setCharmId(visitedCharm.getCharmId());
    return specialView;
  }
}