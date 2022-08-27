package razi.constructor.recyclerviewpractice.SharedData;

import java.util.ArrayList;
import java.util.List;

public class Shared {

    public static List<String> historicalPrices = new ArrayList<>();
    public static List<String> historicalPricesDates = new ArrayList<>();

    public static String[] currencies = {"AOA",
            "BIF",
            "BWP",
            "CVE",
            "ETB",
            "GHS",
            "GMD",
            "GNF",
            "KES",
            "KMF",
            "LRD",
            "LSL",
            "MGA",
            "MUR",
            "MWK",
            "MZN",
            "NAD",
            "NGN",
            "RWF",
            "SHP",
            "SLL",
            "STD",
            "SZL",
            "TZS",
            "UGX",
            "XAF",
            "XOF",
            "ZAR",
            "ZMK",
            "ZWD",
            "ANG",
            "AWG",
            "BBD",
            "BMD",
            "BSD",
            "BZD",
            "CAD",
            "CRC",
            "CUP",
            "DOP",
            "GTQ",
            "HNL",
            "HTG",
            "JMD",
            "MXN",
            "NIO",
            "PAB",
            "SVC",
            "TTD",
            "USD",
            "XCD",
            "ARS",
            "BOB",
            "BRL",
            "CLP",
            "COP",
            "FKP",
            "GYD",
            "PEN",
            "PYG",
            "SRD",
            "UYU",
            "VEF",
            "AUD",
            "NZD",
            "PGK",
            "TOP",
            "WST",
            "AFN",
            "AMD",
            "AZN",
            "BDT",
            "BND",
            "BTN",
            "CNY",
            "GEL",
            "HKD",
            "IDR",
            "ILS",
            "INR",
            "IRR",
            "JPY",
            "KGS",
            "KHR",
            "KPW",
            "KRW",
            "KZT",
            "LAK",
            "LKR",
            "MMK",
            "MNT",
            "MOP",
            "MVR",
            "MYR",
            "NPR",
            "PHP",
            "PKR",
            "SBD",
            "SCR",
            "SGD",
            "THB",
            "TJS",
            "TMT",
            "TWD",
            "UZS",
            "VND",
            "VUV",
            "ALL",
            "BAM",
            "BYR",
            "CHF",
            "CZK",
            "DKK",
            "EUR",
            "GBP",
            "GIP",
            "HRK",
            "HUF",
            "ISK",
            "LTL",
            "LVL",
            "MDL",
            "MKD",
            "NOK",
            "PLN",
            "RON",
            "RSD",
            "RUB",
            "SEK",
            "TRY",
            "UAH",
            "AED",
            "BHD",
            "DJF",
            "DZD",
            "EGP",
            "IQD",
            "JOD",
            "KWD",
            "LBP",
            "LYD",
            "MAD",
            "MRO",
            "OMR",
            "QAR",
            "SAR",
            "SDG",
            "SOS",
            "SYP",
            "TND",
            "YER"};

    public static String[] countrieswithcurrency = {"Angolan kwanza",
            "Burundi Franc",
            "Botswana Pula",
            "Cape Verdean escudo",
            "Ethiopian Birr",
            "Ghana cedi",
            "Gambian Dalasi",
            "Guinean franc",
            "Kenyan Shilling",
            "Comorian franc",
            "Liberian Dollar",
            "Lesotho Loti",
            "Malagasy ariary",
            "Mauritian rupee",
            "Malawian kwacha",
            "Mozambican Metical",
            "Namibian Dollar",
            "Nigerian Naira",
            "Rwandan franc",
            "Saint Helena Pound",
            "Sierra Leonean leone",
            "Sao Tome and Principe dobra",
            "Swazi Lilangeni",
            "Tanzanian Shilling",
            "Ugandan Shilling",
            "Central African CFA Franc",
            "West African CFA Franc",
            "South African Rand",
            "Zambian Kwacha",
            "Zimbabwe Dollar",
            "Netherlands Antillean guilder",
            "Aruban Florin",
            "Barbadian dollar",
            "Bermudian dollar",
            "Bahamian Dollar",
            "Belize Dollar",
            "Canadian Dollar",
            "Costa Rican colón",
            "Cuban Peso",
            "Dominican Peso",
            "Guatemalan quetzal",
            "Honduran lempira",
            "Haitian gourde",
            "Jamaican Dollar",
            "Mexican Peso",
            "Nicaraguan córdoba",
            "Panamanian balboa",
            "Salvadoran colón",
            "Trinidad and Tobago Dollar",
            "U.S. Dollar",
            "East Caribbean Dollar",
            "Argentine Peso",
            "Bolivian Boliviano",
            "Brazilian Real",
            "Chilean Peso",
            "Colombian Peso",
            "Falkland Islands Pound",
            "Guyanese dollar",
            "Peruvian Nuevo Sol",
            "Paraguayan Guarani",
            "Surinamese Dollar",
            "Uruguayan Peso",
            "Venezuelan Bolívar Fuerte",
            "Australian Dollar",
            "New Zealand Dollar",
            "Papua New Guinea Kina",
            "Tongan paanga",
            "Samoan tala",
            "Afghan afghani",
            "Armenian dram",
            "Azerbaijani manat",
            "Bangladeshi Taka",
            "Brunei Dollar",
            "Bhutanese ngultrum",
            "Chinese Yuan",
            "Georgian lari",
            "Hong Kong Dollar",
            "Indonesian Rupiah",
            "Israeli Shekel",
            "Indian Rupee",
            "Iranian Rial",
            "Japanese Yen",
            "Kyrgyzstani som",
            "Cambodian riel",
            "North Korean Won",
            "South Korean Won",
            "Kazakhstani tenge",
            "Lao Kip",
            "Sri Lanka Rupee",
            "Myanma kyat",
            "Mongolian Tugrik",
            "Macanese pataca",
            "Maldivian rufiyaa",
            "Malaysian Ringgit",
            "Nepalese Rupee",
            "Philippine Peso",
            "Pakistani Rupee",
            "Solomon Islands Dollar",
            "Seychellois rupee",
            "Singapore Dollar",
            "Thai Baht",
            "Tajikistani somoni",
            "Turkmen new manat",
            "Taiwan Dollar",
            "Uzbekistan som",
            "Vietnamese dong",
            "Vanuatu Vatu",
            "Albanian Lek",
            "Bosnia and Herzegovina convertible Mark",
            "Belarusian ruble",
            "Swiss Franc",
            "Czech Koruna",
            "Danish Krone",
            "Euro",
            "British Pound",
            "Gibraltar Pound",
            "Croatian Kuna",
            "Hungarian Forint",
            "Icelandic króna",
            "Lithuanian Lita",
            "Latvian Lat",
            "Moldovan Leu",
            "Macedonian Denar",
            "Norwegian Krone",
            "Polish Zloty",
            "Romanian leu",
            "Serbian dinar",
            "Russian Rouble",
            "Swedish Krona",
            "Turkish lira",
            "Ukrainian hryvnia",
            "United Arab Emirates dirham",
            "Bahraini Dinar",
            "Djiboutian franc",
            "Algerian Dinar",
            "Egyptian Pound",
            "Iraqi Dinar",
            "Jordanian Dinar",
            "Kuwaiti Dinar",
            "Lebanese Pound",
            "Libyan Dinar",
            "Moroccan Dirham",
            "Mauritanian ouguiya",
            "Omani Rial",
            "Qatari riyal",
            "Saudi Arabian Riyal",
            "Sudanese Pound",
            "Somali Shilling",
            "Syrian Pound",
            "Tunisian Dinar",
            "Yemeni rial"};

    public static String[] state = {"africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "africa",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "north-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "south-america",
            "oceania",
            "oceania",
            "oceania",
            "oceania",
            "oceania",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "asia",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "europe",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east",
            "middle-east"};


    public static String[] countries = {"Angola",
    "Burundi",
    "Botswana",
    "Cape Verde",
    "Ethiopia",
    "Ghana",
    "Gambia",
    "Guinea",
    "Kenya",
    "Comoros",
    "Liberia",
    "Lesotho",
    "Madagascar",
    "Mauritius",
    "Malawi",
    "Mozambique",
    "Namibia",
    "Nigeria",
    "Rwanda",
    "Saint Helena",
    "Sierra Leone",
    "Sao Tome and Principe",
    "Swaziland",
    "Tanzania",
    "Uganda",
    "Cameroon",
    "Senegal",
    "South Africa",
    "Zambia",
    "Zimbabwe",
    "Netherlands Antilles",
    "Aruba",
    "Barbados",
    "Bermuda",
    "Bahamas",
    "Belize",
    "Canada",
    "Costa Rica",
    "Cuba",
    "Dominican Republic",
    "Guatemala",
    "Honduras",
    "Haiti",
    "Jamaica",
    "Mexico",
    "Nicaragua",
    "Panama",
    "El Salvador",
    "Trinidad and Tobago",
    "United States",
    "Saint Kitts",
    "Argentina",
    "Bolivia",
    "Brazil",
    "Chile",
    "Colombia",
    "Falkland Islands",
    "Guyana",
    "Peru",
    "Paraguay",
    "Suriname",
    "Uruguay",
    "Venezuela",
    "Australia",
    "New Zealand",
    "Papua New Guinea",
    "Tonga Islands",
    "Samoa",
    "Afghanistan",
    "Armenia",
    "Azerbaijan",
    "Bangladesh",
    "Brunei",
    "Bhutan",
    "China",
    "Georgia",
    "Hong Kong",
    "Indonesia",
    "Israel",
    "India",
    "Iran",
    "Japan",
    "kyrgyzstan",
    "Cambodia",
    "North Korea",
    "South Korea",
    "Kazakhstan",
    "Laos",
    "Sri Lanka",
    "Myanmar",
    "Mongolia",
    "Macau",
    "Maldives",
    "Malaysia",
    "Nepal",
    "Philippines",
    "Pakistan",
    "Solomon Islands",
    "Seychelles",
    "Singapore",
    "Thailand",
    "Tajikistan",
    "Turkmenistan",
    "Taiwan",
    "Uzbekistan",
    "Vietnam",
    "Vanuatu",
    "Albania",
    "Bosnia and Herzegov",
    "Belarus",
    "Switzerland",
    "Czech Republic",
    "Denmark",
    "Europe",
    "United Kingdom",
    "Gibraltar",
    "Croatia",
    "Hungary",
    "Iceland",
    "Lithuania",
    "Latvia",
    "Moldova",
    "Macedonia",
    "Norway",
    "Poland",
    "Romania",
    "Serbia",
    "Russia",
    "Sweden",
    "Turkey",
    "Ukraine",
    "United Arab Emirates",
    "Bahrain",
    "Djibouti",
    "Algeria",
    "Egypt",
    "Iraq",
    "Jordan",
    "Kuwait",
    "Lebanon",
    "Libya",
    "Morocco",
    "Mauritania",
    "Oman",
    "Qatar",
    "Saudi Arabia",
    "Sudan",
    "Somalia",
    "Syria",
    "Tunisia",
    "Yemen"};


}