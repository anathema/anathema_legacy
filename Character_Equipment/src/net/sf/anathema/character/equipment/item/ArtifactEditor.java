package net.sf.anathema.character.equipment.item;

import com.google.common.eventbus.EventBus;
import net.sf.anathema.character.equipment.item.view.NewStatsEditor;

public interface ArtifactEditor extends NewStatsEditor{
  void registerOn(EventBus eventBus);
}
