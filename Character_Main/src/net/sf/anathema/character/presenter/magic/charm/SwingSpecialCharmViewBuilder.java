package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.presenter.magic.SpecialCharmViewBuilder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeView;

public class SwingSpecialCharmViewBuilder implements SpecialCharmViewBuilder {

  private ISpecialNodeView result;
  private final IResources resources;
  private final ICharmConfiguration configuration;

  public SwingSpecialCharmViewBuilder(IResources resources, ICharmConfiguration configuration) {
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
    public void visitMultiLearnableCharm(IMultiLearnableCharm charm) {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visitOxBodyTechnique(IOxBodyTechniqueCharm visitedCharm) {
      //SVGCategorizedSpecialNodeView specialView = createViewForCharm(visitedCharm);
      IOxBodyTechniqueConfiguration model = getModelFromCharm(visitedCharm);
      //new OxBodyTechniquePresenter(resources, specialView, model).initPresentation();
      result = new ISpecialNodeView() {
        @Override
        public String getNodeId() {
          return "Solar.Ox-BodyTechnique";
        }

        @Override
        public void hide() {
          //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void reset() {
          //To change body of implemented methods use File | Settings | File Templates.
        }
      };
    }

    @Override
    public void visitSubeffectCharm(ISubeffectCharm charm) {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visitUpgradableCharm(IUpgradableCharm charm) {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void visitMultipleEffectCharm(IMultipleEffectCharm charm) {
      //To change body of implemented methods use File | Settings | File Templates.
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
