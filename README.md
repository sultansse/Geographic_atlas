# “Geographic atlas” android application
Link to video: (same video)

https://drive.google.com/file/d/1ybvILG46XyvZuDqppDCtTttDJOLlsGIj/view?usp=sharing

or 

https://youtu.be/fMF067I-pNg

## Advanced Features
1.✅ DONE  Use “nice” naming (common readability, self-documenting code);

2.✅ DONE  Write safe code, leave no possibility of crashes (correct optionals, array indices handling etc.);

3.✅ DONE  Follow any of the architectures (MVP, MVVM etc.);

4.✅ DONE  Organize code using clean architecture;

5.✅ DONE  Use DI (Koin, Dagger 2 etc);

6.❌NOT DONE  Add basic unit-tests coverage;

7.✅ DONE  Make the expand/collapse feature for CountriesList animated, with no UI bugs;

8.✅ DONE  Use “skeleton loading” for all the elements while API requests are performed;

9.🟡 NOT FINISHED  Add the images caching and reuse them from cache (don’t download any image  twice);

10.✅ DONE  Make the “Capital coordinates” latitude + longitude value on the CountryDetails screen tappable; on tap open the link obtained from the “maps → Google Maps”;

11.❌NOT DONE  Add the push-notifications feature:
    - a. Make the push notification appear after 1-5 seconds after the app launch;
    - b. The push should contain random country basic info;
    - c. The user’s tap should be handled by opening the corresponding country’s CountryDetails screen.



# Task and Completion
### The CountriesList screen should fulfill the following requirements:

1.✅ DONE  Should consist of the scrollable list of all the world countries grouped by parts of the world. The data should be obtained by calling https://restcountries.com/v3.1/all endpoint. 

2.✅ DONE  Grouping should be made by using the “continents” property from the endpoint response.

3.✅ DONE  Each element in the list should be able to switch between expanded/collapsed state when the user clicks the expand/collapse image button. By default all the list elements are in the collapsed state.

4.✅ DONE  The collapsed element should show the following data:
   - a.✅ DONE  Country flag obtained from the “flags → png” property.
   - b.✅ DONE  Country name obtained from the “name → common” property.
   - c.✅ DONE  Country capital name obtained “capital” property.

5.✅ DONE  The expanded element should show the following data:
   - a.✅ DONE  Country flag obtained from the “flags → png” property.
   - b.✅ DONE  Country name obtained from the “name → common” property.
   - c.✅ DONE  Country capital name obtained from the “capital” property.
   - d.✅ DONE  Country population obtained from the “population” property.
   - e.✅ DONE  List of country’s currencies obtained from the “currencies[i] → name” property separated by comma.
   - f.✅ DONE  “Learn more” button to open the CountryDetails screen.

6.✅ DONE  Each of the list elements should provide a segue to the CountryDetails screen showing the corresponding country details. It should be opened by tapping the “Learn more” button in the expanded element state

### The CountryDetails screen should fulfill the following requirements:
1.✅ DONE  Should provide the detailed information of the currently selected country. The data should be obtained by calling https://restcountries.com/v3.1/alpha/[countryCode/cca2] where the countryCode/cca2 parameter is obtained from the cca2 property of the “Get all countries” API. All the data should be viewable for the user; thus, if some of the data goes beyond the screen bottom, it should be made scrollable.

2.✅ DONE  The screen should show the following data:
    - a.✅ DONE  Toolbar with the “Back” button allowing the user to go back to the CountriesList screen. Toolbars title contains “Country name” from the “name → common” property.
    - b.✅ DONE  Large country flag image obtained from the “flags → png” property.
    - c.✅ DONE  The set of “Title + value” fields consisting of the following data:
        - i.  ✅ DONE  “Capital” + value obtained from the “capital” property.
        - ii. ✅ DONE  “Capital coordinates” + values obtained from “capitalInfo → latlng” property separated by comma.
        - iii.✅ DONE  “Population” + value obtained from the “population” property.
        - iv. ✅ DONE  “Area” + value obtained from the “area” property.
        - v.  ✅ DONE  “Currencies” + values obtained from the “currencies[i] → name” property separated by line breaks.
        - vi. ✅ DONE  “Region” + value obtained from the “subregion” property.
        - vii.✅ DONE  “Timezones” + values obtained from the “timezones” property separated by line breaks.
