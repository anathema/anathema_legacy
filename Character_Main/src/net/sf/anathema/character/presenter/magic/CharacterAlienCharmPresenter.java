package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.charmtree.presenter.AlienCharmPresenter;
import net.sf.anathema.charmtree.view.ICharmView;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;

public class CharacterAlienCharmPresenter implements AlienCharmPresenter {

  private ICharmView view;
  private CharacterCharmModel model;

  public CharacterAlienCharmPresenter(CharacterCharmModel model, ICharmView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    model.addCasteChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        boolean alienCharms = model.isAllowedAlienCharms();
        CharmsModel charmConfiguration = model.getCharmConfiguration();
        if (!alienCharms) {
          charmConfiguration.unlearnAllAlienCharms();
        }
        Identifier[] currentCharmTypes = new CharacterCharmTypes(model).getCurrentCharmTypes();
        view.fillCharmTypeBox(currentCharmTypes);
      }
    });
  }
}
