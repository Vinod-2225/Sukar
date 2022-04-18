package com.designmaster.sukar.util

object AppConstants {
    val languageselected:String="isLoginSuc"
    val language:String="language"
    val isLoginSuc:String="isLoginSuc"

    object INTENT_KEY {
        const val OPEN_FROM = "openFrom"
        const val DATA = "data"
        const val TITLE = "title"
        const val ID = "id"
        const val TYPE = "type"
        const val WEB_URL = "webUrl"
        const val EMAIL = "email"
        const val CATEGORY_ID = "catId"
        const val PATH = "path"
        const val SUB_CATEGORY_ID = "subCatId"
        const val SPA_ITEM = "spaItem"
        const val SPA_DETAILS = "spaDetails"
        const val BOOKING_DETAILS = "bookingDetails"
        const val IS_ENABLE = "isEnable"
        const val IS_CHECKED = "isChecked"
        const val FILTER_TYPE = "filterType"
        const val FILTER_TYPE_ID = "filterTypeId"
        const val FILTER_ID = "filterId"
        const val MIN_PRICE = "minPrice"
        const val MAX_PRICE = "maxPrice"
        const val STORE_ID = "storeId"
    }

    object DATA {
        const val CATEGORY_FILTER = "catFilter"
        const val PRICE_FILTER = "priceFilter"
        const val OTHER_FILTER = "otherFilter"
        const val SPEAKER_3 = 3
        const val SPEAKER_4 = 4
        const val CEO = 1
        const val STORY = 2
        const val CULTURE = 3
        const val MILESTONE = 4
        const val RND = 5
        const val AWARDS = 6
        const val LOCATION_PERMISSION_REQUEST = 1430
        const val GPS_PERMISSION_REQUEST = 1431
        const val PICK_ADDRESS = 145
        const val ARABIC = 1
        const val ENGLISH = 2
        const val GUEST = 1
        const val SIGN_IN_UP = 2
        const val PRODUCT_IMAGE_WIDTH = "500"
        const val CATEGORY_IMAGE_WIDTH = "768"
        const val PRODUCT_LIMIT = "30"
        const val CATEGORY = 1
        const val PRICE = 2
        const val MANUFACTURER = 2
        const val SELECT_ADDRESS = 3
        const val FROM_SPLASH = 1
        const val FROM_MORE = 2
        const val FROM_GUEST = 1
        const val TYPE_CATEGORY = "category"
        const val TYPE_BRAND_IN_FOCUS = "brand_in_focus"
        const val TYPE_SPRING_SUMMER_BRANDS = "spring_summer_brands"
        const val TYPE_SPRING_SUMMER_20_BRANDS = "spring_summer_20_brands"
        const val PENDING = "pending"
        const val PROCESSING = "processing"
        const val PACKED = "packed"
        const val RETURN_STARTED = "return started"
        const val RETURNED = "returned"
        const val REFUNDED = "refunded"
        const val SHIPPED = "shipped"
        const val COMPLETE = "complete"
        const val DELIVERED = "delivered"
        const val CANCELED = "canceled"
        const val TERM_CONDITIONS = 5
        const val PRIVACY_POLICY = 3
        const val ABOUT_US = 4
    }

    object SORT_TYPE {
        const val DEFAULT = "p.sort_order"
        const val NAME = "pd.name"
        const val PRICE = "p.price"
        const val RATING = "rating"
        const val MODEL = "p.model"
        const val NEW_ARRIVAL = "p.product_id"
        const val DISCOUNT = "discount"
        const val POPULARITY = "popularity"
        const val ASCEDING = "ASC"
        const val DESCENDING = "DESC"
    }

    object SORTNEW_TYPE {

        const val NEW_ARRIVAL = "p.date_added"
        const val PRICE_LOW_TO_HIGH = "p.price"
        const val PRICE_HIGH_TO_Low = "p.price"

    }

    object REQUEST_CODE {
        const val SELECT_ADDRESS = 1003
        const val ADD_NEW_ADDRESS = 1004
        const val UPDATE_ADDRESS = 1005
        const val GUEST_SHIPPING_ADDRESS = 1006
        const val FILTER = 1007
        const val CANCEL_RETURN = 1008
        const val CANCEL = 1009
        const val RETURN = 10010
    }

    object BANNER_TYPE {
        const val CATEGORY = "category"
        const val CATEGORY_LIST_HORIZONTAL = "category_list_horizontal"
        const val CATEGORY_LIST_CIRCLE = "category_list_circle"
        const val CATEGORY_LIST_SMALL = "category_list_small"
        const val PRODUCT = "product"
        const val PRODUCT_LIST = "product_list"
        const val BRAND_LIST_VERTICAL = "brand_list_vertical"
        const val OTHERS = "others"
    }

    object MFSDK {

        const val PAYMENT_MODE_KNET = 1
        const val PAYMENT_MODE_VISA = 2
        const val PAYMENT_MODE_COD = 0

        object STAGING {
            const val MF_BASE_URL = "https://apidemo.myfatoorah.com/"
            const val MF_EMAIL = "apiaccount@myfatoorah.com"
            const val MF_PASSWORD = "api12345*"
            const val BASE_URL = "https://apitest.myfatoorah.com/"
            const val TOKEN =
                "7Fs7eBv21F5xAocdPvvJ-sCqEyNHq4cygJrQUFvFiWEexBUPs4AkeLQxH4pzsUrY3Rays7GVA6SojFCz2DMLXSJVqk8NG-plK-cZJetwWjgwLPub_9tQQohWLgJ0q2invJ5C5Imt2ket_-JAlBYLLcnqp_WmOfZkBEWuURsBVirpNQecvpedgeCx4VaFae4qWDI_uKRV1829KCBEH84u6LYUxh8W_BYqkzXJYt99OlHTXHegd91PLT-tawBwuIly46nwbAs5Nt7HFOozxkyPp8BW9URlQW1fE4R_40BXzEuVkzK3WAOdpR92IkV94K_rDZCPltGSvWXtqJbnCpUB6iUIn1V-Ki15FAwh_nsfSmt_NQZ3rQuvyQ9B3yLCQ1ZO_MGSYDYVO26dyXbElspKxQwuNRot9hi3FIbXylV3iN40-nCPH4YQzKjo5p_fuaKhvRh7H8oFjRXtPtLQQUIDxk-jMbOp7gXIsdz02DrCfQIihT4evZuWA6YShl6g8fnAqCy8qRBf_eLDnA9w-nBh4Bq53b1kdhnExz0CMyUjQ43UO3uhMkBomJTXbmfAAHP8dZZao6W8a34OktNQmPTbOHXrtxf6DS-oKOu3l79uX_ihbL8ELT40VjIW3MJeZ_-auCPOjpE3Ax4dzUkSDLCljitmzMagH2X8jN8-AYLl46KcfkBV"
        }

        object LIVE {
            const val MF_BASE_URL = "https://apikw.myfatoorah.com/"
            const val BASE_URL = "https://apitest.myfatoorah.com/"
            const val TOKEN =
                "7Fs7eBv21F5xAocdPvvJ-sCqEyNHq4cygJrQUFvFiWEexBUPs4AkeLQxH4pzsUrY3Rays7GVA6SojFCz2DMLXSJVqk8NG-plK-cZJetwWjgwLPub_9tQQohWLgJ0q2invJ5C5Imt2ket_-JAlBYLLcnqp_WmOfZkBEWuURsBVirpNQecvpedgeCx4VaFae4qWDI_uKRV1829KCBEH84u6LYUxh8W_BYqkzXJYt99OlHTXHegd91PLT-tawBwuIly46nwbAs5Nt7HFOozxkyPp8BW9URlQW1fE4R_40BXzEuVkzK3WAOdpR92IkV94K_rDZCPltGSvWXtqJbnCpUB6iUIn1V-Ki15FAwh_nsfSmt_NQZ3rQuvyQ9B3yLCQ1ZO_MGSYDYVO26dyXbElspKxQwuNRot9hi3FIbXylV3iN40-nCPH4YQzKjo5p_fuaKhvRh7H8oFjRXtPtLQQUIDxk-jMbOp7gXIsdz02DrCfQIihT4evZuWA6YShl6g8fnAqCy8qRBf_eLDnA9w-nBh4Bq53b1kdhnExz0CMyUjQ43UO3uhMkBomJTXbmfAAHP8dZZao6W8a34OktNQmPTbOHXrtxf6DS-oKOu3l79uX_ihbL8ELT40VjIW3MJeZ_-auCPOjpE3Ax4dzUkSDLCljitmzMagH2X8jN8-AYLl46KcfkBV"
        }
    }
}