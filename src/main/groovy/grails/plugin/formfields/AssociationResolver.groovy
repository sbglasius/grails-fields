package grails.plugin.formfields

import grails.core.GrailsDomainClassProperty
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.model.PersistentProperty
import org.grails.datastore.mapping.model.types.ManyToMany
import org.grails.datastore.mapping.model.types.ManyToOne
import org.grails.datastore.mapping.model.types.OneToMany
import org.grails.datastore.mapping.model.types.OneToOne

@CompileStatic
@Slf4j
class AssociationResolver {
	static boolean isOneToMany(def property) {
		isType(property, OneToMany)
	}

	static boolean isOneToOne(def property) {
		isType(property, OneToOne)
	}

	static boolean isManyToOne(def property) {
		isType(property, ManyToOne)
	}


	static boolean isManyToMany(def property) {
		isType(property,  ManyToMany)
	}

	private static boolean isType(def property, Class<? extends PersistentProperty> propType ) {
		switch (property) {
			case GrailsDomainClassProperty:
				log.warn("Rendering an input with a GrailsDomainClassProperty is deprecated. Use a PersistentProperty instead.")
				return checkGrailsDomainClassProperty((GrailsDomainClassProperty) property, propType)
			case PersistentProperty:
				return propType.isAssignableFrom(property?.getClass())
			default:
				false
		}
	}

	private static boolean checkGrailsDomainClassProperty(GrailsDomainClassProperty property, Class<? extends PersistentProperty> propType) {
		switch(propType) {
			case OneToMany:
				return property.oneToMany
			case OneToOne:
				return property.oneToOne
			case ManyToOne:
				return property.manyToOne
			case ManyToMany:
				return property.manyToMany
			default:
				return false
		}
	}
}
