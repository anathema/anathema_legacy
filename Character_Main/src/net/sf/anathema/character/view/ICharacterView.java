package net.sf.anathema.character.view;

import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.gui.swing.IDisposable;

public interface ICharacterView extends IItemView, OverviewContainer {

  ICharacterDescriptionView createCharacterDescriptionView();

  IExperienceConfigurationView createExperienceConfigurationView();

  MultipleContentView addMultipleContentView(String header);

  void addDisposable(IDisposable disposable);

  IConceptAndRulesViewFactory createConceptViewFactory();

  IAdvantageViewFactory createAdvantageViewFactory();

  IMagicViewFactory createMagicViewFactory();

  IGroupedFavorableTraitViewFactory createGroupedFavorableTraitViewFactory();
}