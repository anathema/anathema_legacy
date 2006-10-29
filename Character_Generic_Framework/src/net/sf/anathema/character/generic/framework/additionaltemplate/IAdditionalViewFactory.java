package net.sf.anathema.character.generic.framework.additionaltemplate;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public interface IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, CharacterType type);
}