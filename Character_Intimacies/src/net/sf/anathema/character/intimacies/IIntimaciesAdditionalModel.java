package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;

public interface IIntimaciesAdditionalModel extends IAdditionalModel {

  public IIntimaciesModel getIntimaciesModel();
}