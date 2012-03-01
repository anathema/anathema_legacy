package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.AlienCharmPresenter;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class CharacterAlienCharmPresenter implements AlienCharmPresenter {

  private ICharmView view;
  private CharacterCharmModel model;

  public CharacterAlienCharmPresenter(CharacterCharmModel model, ICharmView view) {
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    model.addCasteChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        boolean alienCharms = model.isAllowedAlienCharms();
        ICharmConfiguration charmConfiguration = model.getCharmConfiguration();
        if (!alienCharms) {
          charmConfiguration.unlearnAllAlienCharms();
        }
        IIdentificate[] currentCharmTypes = new CharacterCharmTypes(model).getCurrentCharmTypes();
        view.fillCharmTypeBox(currentCharmTypes);
      }
    });
  }
}