package net.sf.anathema.character.presenter.charm;

import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final IExaltedEdition edition;

  public CharacterCharmGroupChangeListener(
      ISvgTreeView view,
      ITemplateRegistry templateRegistry,
      ICharmGroupArbitrator arbitrator,
      List<ICharmFilter> charmFilterSet,
      IExaltedEdition edition) {
    super(view, templateRegistry, arbitrator, charmFilterSet);
    this.edition = edition;
  }

  @Override
  protected void modifyCharmVisuals(IIdentificate type) {
    // Nothing to do
  }

  @Override
  protected IExaltedEdition getEdition() {
    return edition;
  }

  @Override
  public ILearningCharmGroup getCurrentGroup() {
    return (ILearningCharmGroup) super.getCurrentGroup();
  }

	@Override
	protected boolean filterCharm(ICharm charm, boolean isAncestor)
	{
		for (ICharmFilter filter : charmFilterSet)
			if (!filter.acceptsCharm(charm,isAncestor))
				return false;
		return true;
	}
}