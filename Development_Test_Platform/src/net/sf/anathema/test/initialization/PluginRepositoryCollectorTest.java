package net.sf.anathema.test.initialization;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import net.sf.anathema.PluginRepositoryCollector;

import org.junit.Test;

public class PluginRepositoryCollectorTest {
	private final PluginRepositoryCollector collector = new PluginRepositoryCollector();

	@Test
	public void doesNotCollectRootFolderAsPluginLocation() throws Exception {
		String repositories = collector.getPluginRepositories();
		assertThat(repositories, not(containsString(",/,")));
	}
}