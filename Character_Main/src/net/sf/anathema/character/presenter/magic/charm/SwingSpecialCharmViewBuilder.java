package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.IUpgradableCharm;
import net.sf.anathema.character.presenter.magic.SpecialCharmViewBuilder;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeView;

public class SwingSpecialCharmViewBuilder implements SpecialCharmViewBuilder {
  @Override
  public ISpecialNodeView getResult() {
    return new ISpecialNodeView() {
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
  public void reset() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean hasResult() {
    return true;
  }

  @Override
  public void visitMultiLearnableCharm(IMultiLearnableCharm charm) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void visitOxBodyTechnique(IOxBodyTechniqueCharm charm) {
    //To change body of implemented methods use File | Settings | File Templates.
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
}
