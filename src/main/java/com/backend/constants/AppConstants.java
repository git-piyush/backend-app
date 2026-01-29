package com.backend.constants;

public interface AppConstants {

    static String DEFAULT_PAGE = "0";

    static String DEFAULT_PAGE_SIZE="5";

    static String DEFAULT_SORT_BY = "category";

    static String DEFAULT_ORDER = "asc";

    //CAT_REFCODECATEGORY_REFCODELONGNAME = "REFCODE";
    //TRANSPORT_STATUS
    static String CAT_TRANSPORT_STATUS_UPCOMING = "RCUPC";

    static String CAT_TRANSPORT_STATUS_IN_PROGRESS = "RCINC";

    static String CAT_TRANSPORT_STATUS_COMPLETED = "RCCOM";

    //PAYMENT_STATUS
    static String CAT_PAYMENT_STATUS_PENDING = "RCPEN";

    static String CAT_PAYMENT_STATUS_COMPLETED = "RCCMP";

    //TRANSPORT_TYPE
    static String CAT_TRANSPORT_TYPE_GOODS = "RCGDS";

    static String CAT_TRANSPORT_TYPE_HEAVY_MACHINARY = "RCHVY";

    static String CAT_TRANSPORT_TYPE_PASSENGER = "RCPSG";

    //TRANSPORT_VEHICLE
    static String CAT_TRANSPORT_VEHICLE_MINITRUCK = "RCMTR";

    static String CAT_TRANSPORT_VEHICLE_TRUCK = "RCTRK";

    static String CAT_TRANSPORT_VEHICLE_TRAILER = "RCTRL";

    static String CAT_TRANSPORT_VEHICLE_CAR = "RCCAR";

    static String CAT_TRANSPORT_VEHICLE_BUS = "RCBUS";

    //ORIGIN_CITY
    static String CAT_ORIGIN_CITY_BENGALURU = "RCBLR";

    static String CAT_ORIGIN_CITY_MANGALURU = "RCMLR";

    static String CAT_ORIGIN_CITY_GOA = "RCGOA";

    //ORIGIN_CITY
    static String CAT_DESTINATION_CITY_BENGALURU = "RCBLR";

    static String CAT_DESTINATION_CITY_MANGALURU = "RCMLR";

    static String CAT_DESTINATION_CITY_GOA = "RCGOA";

    static String Y = "RCYES";

    static String N = "RCNO";

}
