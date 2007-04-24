package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeView;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final ICharacterCharmSelectionPresenter presenter;
  private final IExaltedEdition edition;

  public CharacterCharmGroupChangeListener(
      ISvgTreeView view,
      ICharacterCharmSelectionPresenter presenter,
      ITemplateRegistry templateRegistry,
      ICharmGroupArbitrator arbitrator,
      IExaltedEdition edition) {
    super(view, templateRegistry, arbitrator);
    this.presenter = presenter;
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
  public void updateColors() {
    presenter.setCharmVisuals(getSelectedLearnCharmGroup());
  }

  public ILearningCharmGroup getSelectedLearnCharmGroup() {
    return (ILearningCharmGroup) getCurrentGroup();
  }
}