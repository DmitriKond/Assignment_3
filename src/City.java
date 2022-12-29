public class City {
    private String cityName;
    private String region;
    private String[] cityStreets;

    public static final String REGION_NEGEV = "Negev";
    public static final String REGION_SOUTH = "South";
    public static final String REGION_CENTER = "Center";
    public static final String REGION_SHARON = "Sharon";
    public static final String REGION_NORTH = "North";
    public static final String[] EILAT_STREETS = {"Argaman" , "Grofit" , "Los Angeles", "Shoret", "Shahamon", "Malkat Shva", "Sheshet Ha-Yamim", "Lupit", "Dekel", "Dakar"};
    public static final String[] DIMONA_STREETS = {"Herzel", "Ha-Shalom", "Dugit", "Ha-Alon", "Tziporan", "Dafna", "Katzrin", "Adar", "Korzin", "Ha-Shaked"};
    public static final String[] BER_SHEVA_STREETS = {"Sanhedrin", "Biyalik", "Sokolov", "Ha-Shalom", "Alfasi", "Moriya", "Sinai", "Grosman", "Gilad", "Ayalla"};
    public static final String[] ASHKELON_STREETS = {"Shapira", "Ort", "Yoseftal", "Balfor", "Dov Breier", "Pines", "Helmonit", "Kafstat", "Giv'ati", "Tsvi Segai"};
    public static final String[] ASHDOD_STREETS = {"Kinor", "Iyar", "Sderot Oved", "Kineret", "Einstein", "Opal", "Inbar", "Har' Gilad", "Dakar", "Struma"};
    public static final String[] TEL_AVIV_STREETS = {"Wolfson", "Ma'on", "Ha-Yesod", "Hazak", "Shemaiah", "Yefet", "Lapin", "Metula", "Bialik", "Glikson"};
    public static final String[] NETANYA_STREETS = {"Levi Eshkol", "Elkharizi", "Etsel", "Geva", "David Yellin", "Borokhov", "Ha-Dekel", "Ha-Nagar", "Ha-Rina", "Smilanski"};
    public static final String[] JERUSALEM_STREETS = {"Diskin", "Ramban", "Alfasi", "Dubnov", "Nayot", "Ha-Khida", "Ha-Ofeh", "Khayim Vital", "Ben Tsiyot", "Ari'el"};
    public static final String[] HAIFA_STREETS = {"Sukkot", "Dror", "Amal", "Efron", "Nahlil", "Tsahal", "Dreyfus", "Ha-Toren", "Ha-Ela", "Tsiklag"};
    public static final String[] KIRYAT_SHMONA_STREETS = {"Dan Dayan", "Hatsav", "Baba Sali", "Ein Zahav", "Shai Agnon", "Herzl", "Ahi Eilat", "Tarshish", "Ha-Vradim", "Hartsit"};


    public City (String cityName, String region, String[] cityStreets) {
        this.cityName = cityName;
        this.region= region;
        this.cityStreets = cityStreets;
    }

    public String getCityName () {
        return this.cityName;
    }

    public String getRegion () {
        return this.region;
    }

    public String[] getStreetList () {
        return this.cityStreets;
    }

}
