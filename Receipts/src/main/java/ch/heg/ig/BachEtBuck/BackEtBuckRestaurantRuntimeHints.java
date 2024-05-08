package ch.heg.ig.BachEtBuck;

import ch.heg.ig.BachEtBuck.model.BaseEntity;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class BackEtBuckRestaurantRuntimeHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		hints.resources().registerPattern("db/*"); // https://github.com/spring-projects/spring-boot/issues/32654
		hints.resources().registerPattern("messages/*");
		hints.resources().registerPattern("META-INF/resources/webjars/*");
		hints.resources().registerPattern("mysql-default-conf");
		hints.serialization().registerType(BaseEntity.class);
	}

}
