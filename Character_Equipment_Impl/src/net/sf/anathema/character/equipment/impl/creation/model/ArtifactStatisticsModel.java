package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class ArtifactStatisticsModel extends EquipmentStatisticsModel implements IArtifactStatisticsModel
{
	private final IIntValueModel attuneCostModel = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
	
	@Override
	public IIntValueModel getAttuneCostModel() {
		return attuneCostModel;
	}

}
