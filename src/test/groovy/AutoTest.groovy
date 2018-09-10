import geb.Page
import geb.spock.GebSpec


    class searchYearFilterTest extends GebSpec {

        def "Can navigate to AutoHero Search Page"() {

            given: "Go to Search page"
            to AutoHeroSearchPage

            when: "Page is opened, model filter is present"
            searchPageIsLoaded()

            then: "Apply year filter"
            searchByRegistrationYear('2015')

            then: "Check that year filter is correctly applied"
            checkFilterAppliedByYearDescending('2015')
        }
 }

    class searchPriceFilterTest extends GebSpec {

        def "Can navigate to AutoHero Search Page"() {

            given: "Go to Search page"
            to AutoHeroSearchPage

            when: "Page is opened, model filter is present"
            searchPageIsLoaded()

            then: "Apply price filter by Descending"
            filterResultsByPrice('Höchster Preis')

            then: "Check that price filter is correctly applied"
            checkFilterAppliedByPriceDescending()

        }
}