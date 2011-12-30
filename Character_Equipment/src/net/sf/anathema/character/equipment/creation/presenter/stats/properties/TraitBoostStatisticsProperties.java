package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class TraitBoostStatisticsProperties extends AbstractEquipmentStatisticsProperties {

  private final BasicMessage defaultMessage;

  public TraitBoostStatisticsProperties(IResources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.TraitModifying.DefaultMessage")); //$NON-NLS-1$
  }

  public String getDDVLabel() {
    return getLabelString("Equipment.Stats.Long.DDV"); //$NON-NLS-1$
  }
  
  public String getPDVLabel() {
	    return getLabelString("Equipment.Stats.Long.PDV"); //$NON-NLS-1$
	  }
  
  public String getMDDVLabel() {
	    return getLabelString("Equipment.Stats.Long.MDDV"); //$NON-NLS-1$
	  }
  
  public String getMPDVLabel() {
	    return getLabelString("Equipment.Stats.Long.MPDV"); //$NON-NLS-1$
	  }
  
  public String getMeleeSpeedLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeSpeed"); //$NON-NLS-1$
	  }
  
  public String getMeleeAccuracyLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeAccuracy"); //$NON-NLS-1$
	  }
  
  public String getMeleeDamageLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeDamage"); //$NON-NLS-1$
	  }
  
  public String getMeleeRateLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeRate"); //$NON-NLS-1$
	  }

  public String getRangedSpeedLabel() {
	    return getLabelString("Equipment.Stats.Long.RangedSpeed"); //$NON-NLS-1$
	  }

	public String getRangedAccuracyLabel() {
		    return getLabelString("Equipment.Stats.Long.RangedAccuracy"); //$NON-NLS-1$
		  }
	
	public String getRangedDamageLabel() {
		    return getLabelString("Equipment.Stats.Long.RangedDamage"); //$NON-NLS-1$
		  }
	
	public String getRangedRateLabel() {
		    return getLabelString("Equipment.Stats.Long.RangedRate"); //$NON-NLS-1$
		  }
	
	public String getJoinBattleLabel() {
	    return getLabelString("Equipment.Stats.Long.JoinBattle"); //$NON-NLS-1$
	  }
	
	public String getJoinDebateLabel() {
	    return getLabelString("Equipment.Stats.Long.JoinDebate"); //$NON-NLS-1$
	  }
	
	public String getJoinWarLabel() {
	    return getLabelString("Equipment.Stats.Long.JoinWar"); //$NON-NLS-1$
	  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.TraitModifying.PageTitle"); //$NON-NLS-1$
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.TraitModifying.DefaultName"); //$NON-NLS-1$
  }
}