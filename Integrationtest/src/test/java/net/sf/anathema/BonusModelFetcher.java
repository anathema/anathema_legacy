package net.sf.anathema;

import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.IValueModel;

public class BonusModelFetcher {

  private CharacterHolder character;

  public BonusModelFetcher(CharacterHolder character) {
    this.character = character;
  }

  public IValueModel<Integer> findBonusModel(String category, String id) {
    PointsModel pointsModel = PointModelFetcher.fetch(character.getCharacter());
    for (IOverviewModel model : pointsModel.getBonusOverviewModels()) {
      if (model.getId().equals(id) && model.getCategoryId().equals(category)) {
        return (IValueModel<Integer>) model;
      }
    }
    throw new IllegalArgumentException();
  }
}