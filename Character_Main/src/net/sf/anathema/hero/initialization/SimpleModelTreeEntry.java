package net.sf.anathema.hero.initialization;

import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleModelTreeEntry implements ModelTreeEntry {

  private final List<Identifier> requiredModels = new ArrayList<>();
  private Identifier modelId;

  public SimpleModelTreeEntry(Identifier modelId, Identifier... requiredIds) {
    this.modelId = modelId;
    this.requiredModels.addAll(Arrays.asList(requiredIds));
  }

  @Override
  public final Iterable<Identifier> getRequiredModelIds() {
    return new ArrayList<>(requiredModels);
  }

  @Override
  public final Identifier getModelId() {
    return modelId;
  }
}
