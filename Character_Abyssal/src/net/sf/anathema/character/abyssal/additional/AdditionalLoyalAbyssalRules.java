package net.sf.anathema.character.abyssal.additional;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.module.IBackgroundIds;

public class AdditionalLoyalAbyssalRules extends AdditionalAbyssalRules {

  public AdditionalLoyalAbyssalRules() {
    super(IBackgroundIds.BACKGROUND_ID_BACKING, IBackgroundIds.BACKGROUND_ID_MENTOR);
  }

  public void addLiegeRules(IBackgroundTemplate lliegeTemplate) {
    addBonusPointPool(new LiegeBonusPointPool(lliegeTemplate));
  }
}