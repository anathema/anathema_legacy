package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.SubViewFactory;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.character.linguistics.view.LinguisticsView;
import net.sf.anathema.character.platform.RegisteredCharacterView;

@RegisteredCharacterView(ILinguisticsView.class)
public class LinguisticsSubViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new LinguisticsView();
  }
}