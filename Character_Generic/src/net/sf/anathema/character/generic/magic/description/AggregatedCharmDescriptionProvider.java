package net.sf.anathema.character.generic.magic.description;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class AggregatedCharmDescriptionProvider implements CharmDescriptionProvider {

  private final List<CharmDescriptionProvider> providerList = new ArrayList<CharmDescriptionProvider>();

  public AggregatedCharmDescriptionProvider(IResources resources) {
    providerList.add(new ShortCharmDescriptionProvider(resources));
  }

  public void addProvider(CharmDescriptionProvider provider) {
    providerList.add(0, provider);
  }

  @Override
  public CharmDescription getCharmDescription(IMagic magic) {
    List<CharmDescription> descriptions = transform(providerList, new ToDescription(magic));
    return new AggregatedCharmDescription(descriptions);
  }

  private static class ToDescription implements Function<CharmDescriptionProvider, CharmDescription> {
    private final IMagic magic;

    public ToDescription(IMagic magic) {
      this.magic = magic;
    }

    @Override
    public CharmDescription apply(@Nullable CharmDescriptionProvider input) {
      return input.getCharmDescription(magic);
    }
  }
}
