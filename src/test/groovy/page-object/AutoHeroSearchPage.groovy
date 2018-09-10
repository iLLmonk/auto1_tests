import geb.Page
import org.openqa.selenium.By

class AutoHeroSearchPage extends Page {

    static content = {

        // General selectors as page-loaded reference

        markeModelSelector(wait:true) { $("div", 'data-qa-selector': "filter-make-model") }
        topOptionsControls(wait:true) { $("div", 'data-qa-selector': "top-options") }
        searchResults(wait:true) { $("div", 'data-qa-selector': "results-found") }

        // Controls for Registration search

        firstRegistrationSelect(wait:true) { $("div", 'data-qa-selector': "filter-year") }
        yearRangeSelect(wait:true) { $("select", name: "yearRange.min") }
        filterAppliedView(wait:true) { $("li", 'data-qa-selector': "active-filter") }

        // Controls for Price Filtering

        priceFilter(wait:true) { $("select", 'data-qa-selector': "select", name: "sort") }

        // Validation points within search results returned

        modelYear(wait:true) { $(By.xpath("//ul[@data-qa-selector='spec-list']//li[1][@data-qa-selector='spec']")) }

        firstModelPriceValue(wait:true) { $(By.xpath("(//div[@data-qa-selector='price'])[1]")) }
        modelPrice(wait:true) { $("div", 'data-qa-selector': "price") }


    }

    def searchPageIsLoaded() {

        waitFor { $(markeModelSelector) }
        waitFor { $(topOptionsControls) }
        waitFor { $(searchResults) }
        waitFor { $(firstRegistrationSelect) }

    }

    def searchByRegistrationYear(String regYear) {

            firstRegistrationSelect.click()
            yearRangeSelect.click()
            yearRangeSelect = regYear
            yearRangeSelect.click()
            waitFor { $(filterAppliedView) }
    }

    def filterResultsByPrice(String priceFilterOption) {

            priceFilter.click()
            priceFilter = priceFilterOption
            priceFilter.click()
    }

    def checkFilterAppliedByYearDescending(String filterYear) {

            modelYear.each {
                def allYears = it.text().replace("•\n", "")
                if (allYears < filterYear) {
                throw new Exception("Wrong year found" + it)
            }
        }
    }

    def checkFilterAppliedByPriceDescending() {

                def price = firstModelPriceValue.text().replace(/\D*/, "")

            modelPrice.each {
                def allPrices = it.text().replace(/\D*/, "")
                if (allPrices > price) {
                throw new Exception("Wrong price found" + it)
            }
        }
    }
}