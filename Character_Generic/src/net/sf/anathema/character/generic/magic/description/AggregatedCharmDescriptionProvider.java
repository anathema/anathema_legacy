package net.sf.anathema.character.generic.magic.description;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class AggregatedCharmDescriptionProvider implements MagicDescriptionProvider {

  private final List<MagicDescriptionProvider> providerList = new ArrayList<MagicDescriptionProvider>();

  public AggregatedCharmDescriptionProvider(IResources resources) {
    providerList.add(new ShortMagicDescriptionProvider(resources));
  }

  public void addProvider(MagicDescriptionProvider provider) {
    providerList.add(0, provider);
  }

  @Override
  public MagicDescription getCharmDescription(IMagic magic) {
    List<MagicDescription> descriptions = transform(providerList, new ToDescription(magic));
    return new AggregatedMagicDescription(descriptions);
  }

  private static class ToDescription implements Function<MagicDescriptionProvider, MagicDescription> {
    private final IMagic magic;

    public ToDescription(IMagic magic) {
      this.magic = magic;
    }

    @Override
    public MagicDescription apply(@Nullable MagicDescriptionProvider input) {
      return input.getCharmDescription(magic);
    }
  }
}
