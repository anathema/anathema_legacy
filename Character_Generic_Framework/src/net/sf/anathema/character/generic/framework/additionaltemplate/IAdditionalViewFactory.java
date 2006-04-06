package net.sf.anathema.character.generic.framework.additionaltemplate;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.resources.IResources;

public interface IAdditionalViewFactory {

  public ISimpleTabView createView(IAdditionalModel model, IResources resources, CharacterType type);
}