package com.example.bookingroom.constants;

public class Constant {
    public static final String PATTERN_EMAIL = "^[a-zA-Z0-9_[^!@#%^&$*()\\s-+=]]+@[ge]mail.com$";
    public static final String PATTERN_PASSWORD = "^[a-zA-Z0-9[^_!@#%^&$*()\\s-+=]]{8,}$";
    public static final String SUBJECT_SEND = "Booking Room";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String TAG = "Message";
        public static final String IP_ADDRESS = "192.168.1.214";
//    public static final String IP_ADDRESS = "172.1.2.120";
//    public static final String IP_ADDRESS = "172.1.1.196";
//    public static final String IP_ADDRESS = "192.168.43.113";

    public static class KEY{
        public static final String KEY_GMAIL = "Gmail";
        public static final String KEY_PASSWORD = "Password";
        public static final String KEY_REMEMBER = "Remember";
        public static final String KEY_ACCOUNT = "Account Login";
        public static final String KEY_CUSTOMER = "Customer";
        public static final String KEY_MALE = "Nam";
        public static final String KEY_FEMALE = "Nữ";
        public static final String KEY_TYPE_ROOM = "Type Room";
        public static final String KEY_CONTENT = "content";
        public static final String KEY_HOTEl = "hotel";
        public static final String KEY_CODE_SUCCESS = "success";
        public static final String KEY_CODE_CONFIRM = "confirm";
        public static final String KEY_CODE_ACCEPT = "Code";
        public static final String KEY_CONFIRM_NEW_PASSWORD = "confirm_new_password";
        public static final String KEY_DATA_RES = "data";
    }

    public static class Title{
        public static final String LOADING = "Vui lòng chờ...";
        public static final String LOADING_UPDATE = "Đang cập nhật";
        public static final String LOADING_CHANGE = "Đang đổi mật khẩu";
        public static final String LOGIN = "Đăng Nhập";
        public static final String BOOK_ROOM = "Đặt phòng";
        public static final String CHECK = "Đang kiểm tra";
        public static final String Chose = "Hãy Chọn";
    }
    public static class MESSAGE {
        public static final String CHECK_INFO = "Kiểm tra lại thông tin";
        public static final String CREATED_SUCCESS = "Tạo thành công";
        public static final String CODE = "Mã code vừa nhập không đúng";
        public static final String ADD_FAIL = "Thêm bị lỗi";
        public static final String EXIST_EMAIL = "Email đã được sử dụng";
        public static final String CHECK_EMAIL = "Email không hợp lệ";
        public static final String CHECK_PASSWORD = "Kiểm tra lại mât khẩu(gồm 8 ký tự trở lên)";
        public static final String CONFIRM_PASSWORD = "Mật khẩu không trùng nhau";
        public static final String ERROR_PASSWORD = "Sai mật khẩu";
        public static final String CHECK_USERNAME = "Kiểm tra lại tên";
        public static final String CHECK_ACCOUNT = "Tài khoản hoặc mật khẩu không đúng";
        public static final String LOGOUT_SUCCESS = "Đăng xuất thành công";
        public static final String UPDATE_SUCCESS = "Cập nhật thành công";
        public static final String UPDATE_ERROR = "Lỗi cập nhật";
        public static final String CHANGE_PASSWORD_SUCCESS = "Đổi mật khẩu thành công";
        public static final String CHANGE_PASSWORD_ERROR = "Lỗi đổi mật khẩu";
        public static final String NOT_FIND = "Không tìm thấy data";
        public static final String BOOK_SUCCESS = "Đặt phòng thành công";
        public static final String BOOK_FAIL = "Đặt phòng thất bại";
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
    public static final String URL_UPDATE_INFO_CUSTOMER = "http://" + IP_ADDRESS + ":8080/api/customer/update";
//    public static final String URL_CHECK_GMAIL = "https://xixonknight.000webhostapp.com/query_booking_room/CheckGmail.php";
//    public static final String URL_UPDATE_PASSWORD = "https://xixonknight.000webhostapp.com/query_booking_room/UpdatePasswordCustomer.php";
    public static final String URL_SELECT_ALL_AREA = "http://" + IP_ADDRESS + ":8080/api/area/get-all";
    public static final String URL_SELECT_ALL_CATEGORY = "https://xixonknight.000webhostapp.com/query_booking_room/SelectAllCategory.php";
    public static final String URL_CHECK_EXIST_GMAIL = "http://" + IP_ADDRESS + ":8080/api/customer/confirm-gmail";
    public static final String URL_SELECT_ALL_DATA_HOTEL = "http://" + IP_ADDRESS + ":8080/api/hotel/get-all";
    public static final String URL_SELECT_IMAGE_HOTEL = "http://" + IP_ADDRESS + ":8080/api/image-room";
    public static final String URL_SELECT_TYPE_ROOM = "http://" + IP_ADDRESS + ":8080/api/category/getByIdHotel";
    public static final String URL_GET_COMMENT = "http://" + IP_ADDRESS + ":8080/api/comment/getComments";
    public static final String URL_ADD_COMMENT = "http://" + IP_ADDRESS + ":8080/api/comment/add";
    public static final String URL_GET_LST_ROOM = "http://" + IP_ADDRESS + ":8080/api/room/getRoom";
    public static final String URL_BOOK_ROOM = "http://" + IP_ADDRESS + ":8080/api/book-room/add";
    public static final String URL_LOAD_HISTORY = "http://" + IP_ADDRESS + ":8080/api/book-room/getData";



}
