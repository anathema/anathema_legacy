package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.lib.resources.IResources;

public class NecromancyPresenter extends AbstractSpellPresenter {

  public NecromancyPresenter(ICharacterStatistics statistics, IResources resources) {
    super(statistics, resources);
  }

  @Override
  protected String getTabTitleResourceKey() {
    return "CardView.CharmConfiguration.Necromancy.Title"; //$NON-NLS-1$
  }

  @Override
  protected CircleType[] getCircles() {
    return getCharacterTemplate().getMagicTemplate().getSpellMagic().getNecromancyCircles();
  }
}