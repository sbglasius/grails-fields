package grails.plugin.formfields

import grails.core.GrailsDomainClassProperty
import org.grails.datastore.mapping.model.types.ManyToMany
import org.grails.datastore.mapping.model.types.ManyToOne
import org.grails.datastore.mapping.model.types.OneToMany
import org.grails.datastore.mapping.model.types.OneToOne
import org.grails.datastore.mapping.model.types.Simple
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class AssociationResolverSpec extends Specification {
	@Shared
	GrailsDomainClassProperty oneToManyGDCP = Stub { isOneToMany() >> true }
	@Shared
	GrailsDomainClassProperty oneToOneGDCP = Stub { isOneToOne() >> true }
	@Shared
	GrailsDomainClassProperty manyToOneGDCP = Stub { isManyToOne() >> true }
	@Shared
	GrailsDomainClassProperty manyToManyGDCP = Stub { isManyToMany() >> true }

	@Shared
	OneToMany oneToManyPresistentProperty = Stub()
	@Shared
	OneToOne oneToOnePresistentProperty = Stub()
	@Shared
	ManyToOne manyToOnePresistentProperty = Stub()
	@Shared
	ManyToMany manyToManyPresistentProperty = Stub()
	@Shared
	Simple simplePersistentProperty = Stub()

	def "test IsOneToMany"() {
		expect:
		AssociationResolver.isOneToMany(property) == expected

		where:
		property                     | expected
		null                         | false
		"string"                     | false
		simplePersistentProperty     | false
		oneToManyGDCP                | true
		oneToOneGDCP                 | false
		manyToOneGDCP                | false
		manyToManyGDCP               | false
		oneToManyPresistentProperty  | true
		oneToOnePresistentProperty   | false
		manyToOnePresistentProperty  | false
		manyToManyPresistentProperty | false
	}

	def "test IsOneToOne"() {
		expect:
		AssociationResolver.isOneToOne(property) == expected

		where:
		property                     | expected
		null                         | false
		"string"                     | false
		simplePersistentProperty     | false
		oneToManyGDCP                | false
		oneToOneGDCP                 | true
		manyToOneGDCP                | false
		manyToManyGDCP               | false
		oneToManyPresistentProperty  | false
		oneToOnePresistentProperty   | true
		manyToOnePresistentProperty  | false
		manyToManyPresistentProperty | false
	}

	def "test IsManyToOne"() {
		expect:
		AssociationResolver.isManyToOne(property) == expected

		where:
		property                     | expected
		null                         | false
		"string"                     | false
		simplePersistentProperty     | false
		oneToManyGDCP                | false
		oneToOneGDCP                 | false
		manyToOneGDCP                | true
		manyToManyGDCP               | false
		oneToManyPresistentProperty  | false
		oneToOnePresistentProperty   | false
		manyToOnePresistentProperty  | true
		manyToManyPresistentProperty | false
	}

	def "test IsManyToMany"() {
		expect:
		AssociationResolver.isManyToMany(property) == expected

		where:
		property                     | expected
		null                         | false
		"string"                     | false
		simplePersistentProperty     | false
		oneToManyGDCP                | false
		oneToOneGDCP                 | false
		manyToOneGDCP                | false
		manyToManyGDCP               | true
		oneToManyPresistentProperty  | false
		oneToOnePresistentProperty   | false
		manyToOnePresistentProperty  | false
		manyToManyPresistentProperty | true
	}

}
