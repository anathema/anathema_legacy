package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.lib.gui.Presenter;

public class SpellContentPresenter implements Presenter {

  private SpellPresenter spellPresenter;

  public SpellContentPresenter(SpellPresenter spellPresenter) {
    this.spellPresenter = spellPresenter;
  }

  @Override
  public void initPresentation() {
    spellPresenter.initPresentation();
  }
}