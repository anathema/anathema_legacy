package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

public class SpellContentPresenter implements Presenter {

  public static Presenter ForSorcery(ICharacter character, Resources resources,
                                     ISpellView view, MagicDescriptionProvider magicDescriptionProvider) {
    return SpellPresenter.ForSorcery(character, resources, view, magicDescriptionProvider);
  }

  public static Presenter ForNecromancy(ICharacter character, Resources resources,
                                        ISpellView view, MagicDescriptionProvider magicDescriptionProvider) {
    return SpellPresenter.ForNecromancy(character, resources, view, magicDescriptionProvider);
  }

  private SpellPresenter spellPresenter;

  public SpellContentPresenter(SpellPresenter spellPresenter) {
    this.spellPresenter = spellPresenter;
  }

  @Override
  public void initPresentation() {
    spellPresenter.initPresentation();
  }
}