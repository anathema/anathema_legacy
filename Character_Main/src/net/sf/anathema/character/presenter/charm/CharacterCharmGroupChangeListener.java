package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
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
      IExaltedEdition edition) {
    super(view, templateRegistry, arbitrator);
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
}