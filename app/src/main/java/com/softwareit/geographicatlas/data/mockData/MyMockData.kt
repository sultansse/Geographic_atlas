package com.softwareit.geographicatlas.data.mockData

import com.softwareit.geographicatlas.data.local.entities.CountryEntity
import com.softwareit.geographicatlas.data.model.Currency


class MyMockData {

    companion object {

        val mockCountries = listOf(
            CountryEntity(
                "Argentina",
                "AR",
                "ARG",
                "Buenos Aires",
                "Americas",
                45376763,
                listOf(-34.6037, -58.3816),
                2780400.0,
                listOf(
                    Currency(
                        "ARS",
                        "Argentine peso",
                        "$"
                    )
                ),
                "https://restcountries.com/data/arg.svg"
            ),
            CountryEntity(
                "Brazil",
                "BR",
                "BRA",
                "Brasília",
                "Americas",
                212559417,
                listOf(-15.7801, -47.9292),
                8515767.0,
                listOf(
                    Currency(
                        "BRL",
                        "Brazilian real",
                        "R$"
                    )
                ),
                "https://restcountries.com/data/bra.svg"
            ),
            CountryEntity(
                "Canada",
                "CA",
                "CAN",
                "Ottawa",
                "Americas",
                38005238,
                listOf(45.4215, -75.6972),
                9984670.0,
                listOf(
                    Currency(
                        "CAD",
                        "Canadian dollar",
                        "$"
                    )
                ),
                "https://restcountries.com/data/can.svg"
            ),
            CountryEntity(
                "China",
                "CN",
                "CHN",
                "Beijing",
                "Asia",
                1403500365,
                listOf(39.9042, 116.4074),
                9706961.0,
                listOf(
                    Currency(
                        "CNY",
                        "Chinese yuan",
                        "¥"
                    )
                ),
                "https://restcountries.com/data/chn.svg"
            ),
            CountryEntity(
                "France",
                "FR",
                "FRA",
                "Paris",
                "Europe",
                67413000,
                listOf(48.8566, 2.3522),
                640679.0,
                listOf(
                    Currency(
                        "EUR",
                        "Euro",
                        "€"
                    )
                ),
                "https://restcountries.com/data/fra.svg"
            ),
            CountryEntity(
                "Germany",
                "DE",
                "DEU",
                "Berlin",
                "Europe",
                83166711,
                listOf(52.5200, 13.4050),
                357114.0,
                listOf(
                    Currency(
                        "EUR",
                        "Euro",
                        "€"
                    )
                ),
                "https://restcountries.com/data/deu.svg"
            ),
            CountryEntity(
                "India",
                "IN",
                "IND",
                "New Delhi",
                "Asia",
                1391000000,
                listOf(28.6139, 77.2090),
                3287263.0,
                listOf(
                    Currency(
                        "INR",
                        "Indian rupee",
                        "₹"
                    )
                ),
                "https://restcountries.com/data/ind.svg"
            ),
            CountryEntity(
                "Italy",
                "IT",
                "ITA",
                "Rome",
                "Europe",
                60252824,
                listOf(41.9028, 12.4964),
                301340.0,
                listOf(
                    Currency(
                        "EUR",
                        "Euro",
                        "€"
                    )
                ),
                "https://restcountries.com/data/ita.svg"
            ),
        )
    }
}