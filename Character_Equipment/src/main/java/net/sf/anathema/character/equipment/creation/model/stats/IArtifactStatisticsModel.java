package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IArtifactStatisticsModel extends IEquipmentStatisticsModel
{
	IIntValueModel getAttuneCostModel();
	
	BooleanValueModel getForeignAttunementModel();
	
	BooleanValueModel getRequireAttunementModel();
}
