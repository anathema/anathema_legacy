package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IPainToleranceCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmVisitor;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharm;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.presenter.view.ISpecialNodeView;

public class SwingSpecialCharmViewBuilder implements SpecialCharmViewBuilder {

  private ISpecialNodeView result;
  private final Resources resources;
  private final CharmsModel configuration;

  public SwingSpecialCharmViewBuilder(Resources resources, CharmsModel configuration) {
    this.resources = resources;
    this.configuration = configuration;
  }

  @Override
  public ISpecialNodeView getResult() {
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
    charm.accept(new SwingSpecialCharmVisitor());
  }

  private class SwingSpecialCharmVisitor implements ISpecialCharmVisitor {

    @Override
    public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
      SwingCategorizedSpecialView view = new SwingCategorizedSpecialView(visitedCharm);
      IMultiLearnableCharmConfiguration model = getModelFromCharm(visitedCharm);
      new MultiLearnableCharmPresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
      SwingCategorizedSpecialView view = new SwingCategorizedSpecialView(visitedCharm);
      IOxBodyTechniqueConfiguration model = getModelFromCharm(visitedCharm);
      new OxBodyTechniquePresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitSubeffectCharm(ISubeffectCharm visited) {
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
      BooleanSelectionSpecialNodeView view = new BooleanSelectionSpecialNodeView(visitedCharm);
      IMultipleEffectCharmConfiguration model = getModelFromCharm(visitedCharm);
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
}
