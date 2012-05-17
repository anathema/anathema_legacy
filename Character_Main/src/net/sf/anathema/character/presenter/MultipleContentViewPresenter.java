package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.presenter.magic.IContentPresenter;

public interface MultipleContentViewPresenter {

  void initMultipleContentPresentation(String viewTitle, AdditionalModelType type, IContentPresenter... corePresenters);
}
