package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.charm.special.charms.IMultiLearnableCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.IOxBodyTechniqueCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.IPainToleranceCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharmVisitor;
import net.sf.anathema.character.main.magic.charm.special.charms.ISubEffectCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.ITraitCapModifyingCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.IUpgradableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.MultiLearnCharmSpecials;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharmSpecials;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyTechniqueSpecials;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.display.ISpecialNodeView;

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
      MultiLearnCharmSpecials model = getModelFromCharm(visitedCharm);
      new MultiLearnableCharmPresenter(resources, view, model).initPresentation();
      result = view;
    }

    @Override
    public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
      SwingCategorizedSpecialView view = new SwingCategorizedSpecialView(visitedCharm);
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
      BooleanSelectionSpecialNodeView view = new BooleanSelectionSpecialNodeView(visitedCharm);
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
}
