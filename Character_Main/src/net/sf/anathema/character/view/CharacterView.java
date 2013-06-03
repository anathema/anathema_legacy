package net.sf.anathema.character.view;

import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;

public interface CharacterView extends IView, OverviewContainer {

  ICharacterDescriptionView createCharacterDescriptionView();

  IExperienceConfigurationView createExperienceConfigurationView();

  MultipleContentView addMultipleContentView(String header);

  IConceptAndRulesViewFactory createConceptViewFactory();

  IAdvantageViewFactory createAdvantageViewFactory();

  IMagicViewFactory createMagicViewFactory();

  IGroupedFavorableTraitViewFactory createGroupedFavorableTraitViewFactory();

  BackgroundView createBackgroundView();
}