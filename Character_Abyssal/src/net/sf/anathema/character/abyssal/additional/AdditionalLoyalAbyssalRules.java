package net.sf.anathema.character.abyssal.additional;

import java.util.ArrayList;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.module.MortalCharacterModule;

public class AdditionalLoyalAbyssalRules extends AdditionalAbyssalRules {

  public AdditionalLoyalAbyssalRules() {
    super(new ArrayList<String>() {
      {
        add(MortalCharacterModule.BACKGROUND_ID_BACKING);
        add(MortalCharacterModule.BACKGROUND_ID_MENTOR);
      }
    });
  }

  public void addLiegeRules(IBackgroundTemplate lliegeTemplate) {
    addBonusPointPool(new LiegeBonusPointPool(lliegeTemplate));
  }
}