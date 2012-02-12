package net.sf.anathema.character.presenter.charm;

import java.util.List;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.IIdentificate;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(
          ITemplateRegistry templateRegistry,
          ICharmGroupArbitrator arbitrator,
          List<ICharmFilter> charmFilterSet,
          IExaltedEdition edition, CharmTreeRenderer treeRenderer) {
    super(templateRegistry, arbitrator, charmFilterSet, edition, treeRenderer);
  }

  @Override
  protected void modifyCharmVisuals(IIdentificate type) {
    // Nothing to do
  }

  @Override
  public ILearningCharmGroup getCurrentGroup() {
    return (ILearningCharmGroup) super.getCurrentGroup();
  }
}