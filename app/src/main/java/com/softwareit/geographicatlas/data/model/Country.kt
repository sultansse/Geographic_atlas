package com.softwareit.geographicatlas.data.model


data class Country(

    /*  @SerializedName("common")
      val name: String,
      @SerializedName("alpha2Code")
      val alpha2Code: String,
      @SerializedName("alpha3Code")
      val alpha3Code: String,
      @SerializedName("capital")
      val capital: String,
      @SerializedName("region")
      val region: String,
      @SerializedName("population")
      val population: Int,
      @SerializedName("latlng")
      val latlng: List<Double>,
      @SerializedName("area")
      val area: Double,
      @SerializedName("currencies")
      val currencies: List<Currency>,
      @SerializedName("flag")
      val flag: String*/

//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0,
    val area: Double,
    val capital: List<String>,
    val cca2: String,
    val continents: List<String>,
//    val currencies: Currencies,
    val flags: Flags,
    val latlng: List<Double>,
//  val maps: Maps,
    val name: Name,
    val population: Long,
    val region: String,
)




