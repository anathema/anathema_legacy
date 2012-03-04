package net.sf.anathema.character.view;

import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.gui.IDisposable;

public interface ICharacterView extends IItemView, OverviewContainer {

  ICharacterDescriptionView createCharacterDescriptionView();

  IExperienceConfigurationView createExperienceConfigurationView();

  IMultiContentView addMultiContentView(String header);

  void addDisposable(IDisposable disposable);

  ICharacterConceptAndRulesViewFactory createConceptViewFactory();

  IAdvantageViewFactory createAdvantageViewFactory();

  IMagicViewFactory createMagicViewFactory();

  IGroupedFavorableTraitViewFactory createGroupedFavorableTraitViewFactory();
}