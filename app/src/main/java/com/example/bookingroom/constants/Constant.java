package com.example.bookingroom.constants;

public class Constant {
    public static final String PATTERN_EMAIL = "^[a-zA-Z0-9_[^!@#%^&$*()\\s-+=]]+@[ge]mail.com$";
    public static final String PATTERN_PASSWORD = "^[a-zA-Z0-9[^_!@#%^&$*()\\s-+=]]{8,}$";
    public static final String SUBJECT_SEND = "Booking Room";
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String TAG = "Message";


    //    id Area
    public static final int ID_HANOI = 1;
    public static final int ID_DA_NANG = 2;
    public static final int ID_QUANG_NINH = 3;
    public static final int ID_TP_HCM = 4;
    public static final int ID_VUNG_TAU = 5;
    public static final int ID_NHA_TRANG = 6;
    public static final int ID_CAT_BA = 7;
    public static final int ID_QUY_NHON = 8;
    public static final int ID_PHU_QUOC = 9;

    //    Id Categories
    public static final int ID_SINGLE_ROOM = 1;
    public static final int ID_DOUBLE_ROOM = 2;
    public static final int ID_FAMILY_ROOM = 3;

//    Id Hotel


    //    local server
    public static final String URL_CREATE_USER = "https://xixonknight.000webhostapp.com/query_booking_room/CreateUser.php";
    public static final String URL_LOGIN = "https://xixonknight.000webhostapp.com/query_booking_room/CheckLogin.php";
    public static final String URL_SELECT_INFO_CUSTOMER = "https://xixonknight.000webhostapp.com/query_booking_room/SelectInfoCustomer.php";
    public static final String URL_UPDATE_INFO_CUSTOMER = "https://xixonknight.000webhostapp.com/query_booking_room/UpdateInfoCustomer.php";
    public static final String URL_CHECK_GMAIL = "https://xixonknight.000webhostapp.com/query_booking_room/CheckGmail.php";
    public static final String URL_UPDATE_PASSWORD = "https://xixonknight.000webhostapp.com/query_booking_room/UpdatePasswordCustomer.php";
    public static final String URL_SELECT_ALL_AREA = "http://192.168.1.214:8080/api/area/get-all";
    public static final String URL_SELECT_ALL_CATEGORY = "https://xixonknight.000webhostapp.com/query_booking_room/SelectAllCategory.php";
    public static final String URL_CHECK_CREATE_GMAIL = "http://192.168.1.214:8080/api/customer/confirm-gmail";
    public static final String URL_SELECT_ALL_DATA_HOTEL = "http://192.168.1.214:8080/api/hotel/get-all";
    public static final String URL_SELECT_IMAGE_HOTEL = "https://xixonknight.000webhostapp.com/query_booking_room/SelectImageHotel.php";
    public static final String URL_SELECT_TYPE_ROOM = "https://xixonknight.000webhostapp.com/query_booking_room/SelectTypeRoom.php";

    public static class MESSAGE {
        public static final String CHECK_INFO = "Kiểm tra lại thông tin";
        public static final String CREATED_SUCCESS = "Tạo thành công";
        public static final String CODE = "Mã code vừa nhập không đúng";
        public static final String ADD_FAIL = "Thêm bị lỗi";
        public static final String EXIST_EMAIL = "Email đã được sử dụng";
        public static final String CHECK_EMAIL = "Email không hợp lệ";
        public static final String CHECK_PASSWORD = "Kiểm tra lại mât khẩu(gồm 8 ký tự trở lên)";
        public static final String CHECK_USERNAME = "Kiểm tra lại tên";
    }

}
