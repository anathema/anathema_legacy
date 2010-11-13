package net.sf.anathema.character.meritsflaws.presenter;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;

public interface IMeritsFlawsModel extends IQualityModel<IPerk> {

  public void addCharacterChangeListener(ICharacterChangeListener changeListener);

  public IPerk getPerk(String perkId, PerkType type, PerkCategory category);

  public IQualitySelection<IPerk>[] getSelectedFlaws();

  public IQualitySelection<IPerk>[] getSelectedMerits();

  public void setCurrentFilter(PerkType type, PerkCategory category);

  public IBasicCharacterData getBasicCharacterData();
}