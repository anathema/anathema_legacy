package net.sf.anathema.hero.magic.display.tree;

import net.sf.anathema.character.main.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.main.presenter.magic.CharacterCharmTypes;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.magic.display.presenter.AlienCharmPresenter;
import net.sf.anathema.character.main.magic.display.view.charmtree.ICharmView;
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
