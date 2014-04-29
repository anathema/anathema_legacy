package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.framework.environment.Resources;

public class TraitBoostStatisticsProperties extends AbstractProperties {

  public TraitBoostStatisticsProperties(Resources resources) {
    super(resources);
  }

  public String getDDVLabel() {
    return getLabelString("Equipment.Stats.Long.DDV");
  }
  
  public String getPDVLabel() {
	    return getLabelString("Equipment.Stats.Long.PDV");
	  }
  
  public String getMDDVLabel() {
	    return getLabelString("Equipment.Stats.Long.MDDV");
	  }
  
  public String getMPDVLabel() {
	    return getLabelString("Equipment.Stats.Long.MPDV");
	  }
  
  public String getMeleeSpeedLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeSpeed");
	  }
  
  public String getMeleeAccuracyLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeAccuracy");
	  }
  
  public String getMeleeDamageLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeDamage");
	  }
  
  public String getMeleeRateLabel() {
	    return getLabelString("Equipment.Stats.Long.MeleeRate");
	  }

  public String getRangedSpeedLabel() {
	    return getLabelString("Equipment.Stats.Long.RangedSpeed");
	  }

	public String getRangedAccuracyLabel() {
		    return getLabelString("Equipment.Stats.Long.RangedAccuracy");
		  }
	
	public String getRangedDamageLabel() {
		    return getLabelString("Equipment.Stats.Long.RangedDamage");
		  }
	
	public String getRangedRateLabel() {
		    return getLabelString("Equipment.Stats.Long.RangedRate");
		  }
	
	public String getJoinBattleLabel() {
	    return getLabelString("Equipment.Stats.Long.JoinBattle");
	  }
	
	public String getJoinDebateLabel() {
	    return getLabelString("Equipment.Stats.Long.JoinDebate");
	  }
	
	public String getJoinWarLabel() {
	    return getLabelString("Equipment.Stats.Long.JoinWar");
	  }
}