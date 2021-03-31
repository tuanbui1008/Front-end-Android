package com.example.bookingroom.constants;

public class Constant {
    public static final String PATTERN_EMAIL = "^[a-zA-Z0-9_[^!@#%^&$*()\\s-+=]]+@[ge]mail.com$";
    public static final String PATTERN_PASSWORD = "^[a-zA-Z0-9[^_!@#%^&$*()\\s-+=]]{8,}$";
    public static final String SUBJECT_SEND = "Booking Room";
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String TAG = "Message";
    //    public static final String IP_ADDRESS = "192.168.1.214";
//    public static final String IP_ADDRESS = "localhost";
    public static final String IP_ADDRESS = "172.1.2.120";
//    public static final String IP_ADDRESS = "172.1.1.196";
//    public static final String IP_ADDRESS = "192.168.43.113";

    public static class KEY{
        public static final String KEY_GMAIL = "Gmail";
        public static final String KEY_PASSWORD = "Password";
        public static final String KEY_REMEMBER = "Remember";
        public static final String KEY_ACCOUNT = "Account Login";
        public static final String KEY_CUSTOMER = "Customer";
    }

    public static class Title{
        public static final String LOADING = "Vui lòng chờ...";
        public static final String LOGIN = "Đăng Nhập";
    }
    public static class MESSAGE {
        public static final String CHECK_INFO = "Kiểm tra lại thông tin";
        public static final String CREATED_SUCCESS = "Tạo thành công";
        public static final String CODE = "Mã code vừa nhập không đúng";
        public static final String ADD_FAIL = "Thêm bị lỗi";
        public static final String EXIST_EMAIL = "Email đã được sử dụng";
        public static final String CHECK_EMAIL = "Email không hợp lệ";
        public static final String CHECK_PASSWORD = "Kiểm tra lại mât khẩu(gồm 8 ký tự trở lên)";
        public static final String CHECK_USERNAME = "Kiểm tra lại tên";
        public static final String CHECK_ACCOUNT = "Tài khoản hoặc mật khẩu không đúng";
        public static final String LOGOUT_SUCCESS = "Đăng xuất thành công";
    }

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
    public static final String URL_CREATE_USER = "http://" + IP_ADDRESS + ":8080/api/customer/created";
    public static final String URL_LOGIN = "http://" + IP_ADDRESS + ":8080/api/customer/login";
    public static final String URL_SELECT_INFO_CUSTOMER = "http://" + IP_ADDRESS + ":8080/api/customer";
    public static final String URL_UPDATE_INFO_CUSTOMER = "https://xixonknight.000webhostapp.com/query_booking_room/UpdateInfoCustomer.php";
    public static final String URL_CHECK_GMAIL = "https://xixonknight.000webhostapp.com/query_booking_room/CheckGmail.php";
    public static final String URL_UPDATE_PASSWORD = "https://xixonknight.000webhostapp.com/query_booking_room/UpdatePasswordCustomer.php";
    public static final String URL_SELECT_ALL_AREA = "http://" + IP_ADDRESS + ":8080/api/area/get-all";
    public static final String URL_SELECT_ALL_CATEGORY = "https://xixonknight.000webhostapp.com/query_booking_room/SelectAllCategory.php";
    public static final String URL_CHECK_CREATE_GMAIL = "http://" + IP_ADDRESS + ":8080/api/customer/confirm-gmail";
    public static final String URL_SELECT_ALL_DATA_HOTEL = "http://" + IP_ADDRESS + ":8080/api/hotel/get-all";
    public static final String URL_SELECT_IMAGE_HOTEL = "https://xixonknight.000webhostapp.com/query_booking_room/SelectImageHotel.php";
    public static final String URL_SELECT_TYPE_ROOM = "https://xixonknight.000webhostapp.com/query_booking_room/SelectTypeRoom.php";



}
