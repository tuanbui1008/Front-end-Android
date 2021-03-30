package com.example.bookingroom.common;

public class Constants {
    public static String SCHEMA_PAYROLL = "vhr";

    public static class RESPONSE_TYPE {
        public static final String SUCCESS = "SUCCESS";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String CONFIRM = "CONFIRM";
        public static final String invalidPermission = "invalidPermission";

        public static final String doesNotExist = "doesNotExist";
        public static final String OK = "OK";
        public static final String NOK = "NOK";
        public static final String EXIST = "EXIST";

    }

    public static class RESPONSE_CODE {
        public static final String SUCCESS = "success";
        public static final String DELETE_SUCCESS = "deleteSuccess";
        public static final String UPDATE_STATUS_SUCCESS = "updateStatusSuccess";
        public static final String UPDATE_SUCCESS = "updateSuccess";
        public static final String ERROR = "error";
        public static final String WARNING = "warning";
        public static final String RECORD_DELETED = "record.deleted";
        public static final String EMAIL_ADDRESS_DELETED = "emailAddress.deleted";
        public static final String RECORD_INUSED = "record.inUsed";
        public static final String DOCUMENT_TYPE_EXISTED = "documentTypeExits";
        public static final String DUPICATE_DATA_REDUCTION = "taxReduction.duplicateData";
        public static final String PARAMETER_USED = "parameterUsed";
        public static final String NOT_ALLOWED_DELETE_DATA_TYPE = "dataType.recordInUsed";
        public static final String NOT_ALLOWED_DELETE_FORMULA = "formula.config.cannotDelete";
        public static final String NOT_ALLOWED_CHANGE_STATUS_FORMULA = "formula.config.cannotChange";
        public static final String NOT_ALLOWED_EVALUATION = "evaluation.cannotEvaluation";
        public static final String NO_RECORDS = "evaluation.noRecords";
        public static final String LOCK_UNIT = "evaluation.orglocked";
        public static final String NO_DATA_EVALUATION = "evaluation.noData";
        public static final String PAYROLL_IS_LOCK = "ERROR.payroll.calculate.isLock";
        public static final String PAYROLL_IS_PAID = "ERROR.payroll.calculate.paid";
        public static final String ROLE_EXIST = "permission.role.exist";
        public static final String MENU_HAVE_CHILD = "permission.menu.haveChild";
        public static final String ERROR_COMPOSITE = "error.composite";
        public static final String SUCCESS_COMPOSITE = "success.composite";
        public static final String ERROR_SEND = "error.send";
        public static final String SUCCESS_SEND = "success.send";
        public static final String SUCCESS_SAVE = "success.save";
        public static final String DELETE_ERROR = "error.delete";
        public static final String SAVE_DUPLICATE_CODE = "save.duplicateCode";
        public static final String DOMAIN_DUPLICATE_CODE = "permission.duplicateDomain";
        public static final String SYNC_TAX_SUCCESS = "synctax.success";
        public static final String SYNC_TAX_ERROR = "synctax.error";
    }

    public static class WARNING_TYPE {

        public static final Long EMAIL = 0l;
        public static final Long SMS = 1l;
        public static final Long EMAIL_SMS = 2l;
    }


    public enum ProcessType {
        //Qua trinh trong
        INTERIOR(1L, "Interior"),
        //Quá trình ngoài
        EXTERIOR(2L, "Exterior"),
        //Qua trinh nha'p
        TYPE_DRAFT(3L, "Draft"),
        //la qua trinh hien tại
        STATUS_CURRENT(1L, "Current"),
        //ko phai qua trinh hien tai
        STATUS_NOT_CURRENT(null, "NotCurrent");
        private Long key;
        private String code;

        private ProcessType(Long key, String code) {
            this.key = key;
            this.code = code;
        }

        public Long getKey() {
            return key;
        }

        public String getCode() {
            return code;
        }
    }

    public interface COMMON {
        String MARKET_COMPANY_ID = "MARKET_COMPANY_ID";
        //Thu muc chua file tam de import
        String IMPORT_FOLDER = "/share/import/";
        String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    }

    public enum attributeType {
        DATE1(1L, "dd/MM/yyyy"),
        DATE2(2L, "MM/dd/yyyy"),
        DATE3(3L, "yyyy/MM/dd"),
        DOUBLE(4L, "Double"),
        LONG(5L, "Long"),
        STRING(6L, "String"),
        DATE(7L, "Date");

        private Long key;
        private String code;

        private attributeType(Long key, String code) {
            this.key = key;
            this.code = code;
        }

        public Long getKey() {
            return key;
        }

        public String getCode() {
            return code;
        }
    }

        public static class HANOI {
            public static final CreatedHotel Hotel1 = new CreatedHotel("Acoustic Hotel & Spa",
                    "39 Thợ Nhuộm, Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                    "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/Hanoi/AcousticHotel&Spa/5.jpg");
            public static final CreatedHotel Hotel2 = new CreatedHotel("Hanoi Paradise Center Hotel & Spa",
                    "22/5 Hang Voi Street, Ly Thai To Ward, Hoan Kiem District, Vietnam, Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                    "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/Hanoi/HanoiParadiseCenterHotel&Spa/1.jpg");
            public static final CreatedHotel Hotel3 = new CreatedHotel("Halais Hotel",
                    "48 Tran Nhan Tong, Nguyen Binh Khiem, Quận Hai Bà Trưng, Hà Nội, Việt Nam",
                    "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/Hanoi/HalaisHotel/1.jpg");
            public static final CreatedHotel Hotel4 = new CreatedHotel("Hestia Legend Hotel",
                    " 53 Hang Bun, Quận Ba Đình, Hà Nội, Việt Nam",
                    "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/Hanoi/HestiaLegendHotel/5.jpg");
            public static final CreatedHotel Hotel5 = new CreatedHotel("La Sinfonía del Rey Hotel & Spa",
                    " 33-35 Hàng Dầu , Quận Hoàn Kiếm, Hà Nội, Việt Nam",
                    "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/Hanoi/LaSinfon%C3%ADadelReyHotel&Spa/1.jpg");
        }

    public static class DaNang {
        public static final CreatedHotel Hotel1 = new CreatedHotel("Grand Jeep Hotel - Managed by RHM GROUP",
                "142-146 Ho Nghinh, Đà Nẵng, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/DaNang/GrandJeepHotel-ManagedbyRHMGROUP/1.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Sala Danang Beach Hotel",
                "36-38 Lam Hoanh Street, Phuoc My, Son Tra, Đà Nẵng, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/DaNang/SalaDanangBeachHotel/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Pullman Danang Beach Resort",
                "101 Vo Nguyen Giap Street, Khue My Ward, Ngu Hanh Son District, Da Nang, Vietnam, Đà Nẵng, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/DaNang/PullmanDanangBeachResort/1.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Muong Thanh Luxury Da Nang Hotel",
                "270 Vo Nguyen Giap, My An Ward, Ngu Hanh Son District, Đà Nẵng, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/DaNang/MuongThanhLuxuryDaNangHotel/1.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Stella Maris Beach Danang",
                "3 Vo Van Kiet, Phuoc My Ward, Son Tra District, Đà Nẵng, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/DaNang/StellaMarisBeachDanang/2.jpg");
    }
    public static class QuangNinh {
        public static final CreatedHotel Hotel1 = new CreatedHotel("Wyndham Legend Halong",
                "No 12 Halong Road, Bai Chay ward, Ha Long City, Hạ Long, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuangNinh/WyndhamLegendHalong/3.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Muong Thanh Luxury Ha Long Centre Hotel",
                "Zone 2, Ha Long Road, Bai Chay Ward, Hạ Long City, Quang Ninh Province, Hạ Long, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/DaNang/SalaDanangBeachHotel/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Vinpearl Resort & Spa Ha Long",
                "Reu Island, Bai Chay Ward, Quang Ninh, Hạ Long, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuangNinh/VinpearlResort&SpaHaLong/2.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Premier Village Ha Long Bay Resort",
                "09 Đường Hạ Long, Phường Bãi Cháy, Hạ Long, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuangNinh/PremierVillageHaLongBayResort/2.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Green Suites Hotel",
                "Sun Premier Village TT43-46 Thuy Tung, Ha Long Road, Hạ Long, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuangNinh/GreenSuitesHotel/1.jpg");
    }

    public static class TP_HCM {
        public static final CreatedHotel Hotel1 = new CreatedHotel("Cicilia Saigon Hotels & Spa",
                "12D Cach Mang Thang Tam, Ben Thanh ward, Quận 1, TP. Hồ Chí Minh, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/TP.HCM/OrchidsSaigonHotel/5.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Orchids Saigon Hotel",
                "192 Pasteur Street, Ward 6, District 3, Quận 3, TP. Hồ Chí Minh, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/TP.HCM/OrchidsSaigonHotel/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Grand Hotel Saigon",
                "8 Dong Khoi Street, Quận 1, TP. Hồ Chí Minh, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/TP.HCM/GrandHotelSaigon/1.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Winsuites Saigon",
                "28-30-32 Le Lai Street, Ben Thanh Ward, Quận 1, TP. Hồ Chí Minh, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/TP.HCM/WinsuitesSaigon/5.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Caravelle Saigon",
                "19 - 23 Lam Son Square, Quận 1, TP. Hồ Chí Minh, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/TP.HCM/CaravelleSaigon/1.jpg");
    }

    public static class VUNG_TAU {

        public static final CreatedHotel Hotel1 = new CreatedHotel("The Imperial Hotel Vung Tau",
                "159 Thuy Van Street, Vũng Tàu, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/VungTau/TheImperialHotelVungTau/1.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Joi Hotel",
                "07-08 Ta Uyen, Ward 3, Vũng Tàu, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/VungTau/JoiHotel/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Pullman Vung Tau",
                "15 Thi Sach, Thang Tam, Vũng Tàu, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/VungTau/PullmanVungTau/2.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Fusion Suites Vung Tau",
                "02 Truong Cong Dinh Street, Ward 2, Vung Tau City, BR-VT, Vietnam, Vũng Tàu, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/VungTau/FusionSuitesVungTau/1.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Malibu Hotel",
                "263 Le Hong Phong, Vũng Tàu, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/VungTau/MalibuHotel/1.jpg");
    }

    public static class NhaTrang {

        public static final CreatedHotel Hotel1 = new CreatedHotel("The Imperial Hotel Vung Tau",
                "16 Ton Dan Street (Old address: 4 Ton Dan), Nha Trang, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/NhaTrang/MapleHotel&Apartment/1.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Joi Hotel",
                "1711A Nguyen Thi Minh Khai , Loc Tho , Nha Trang 1711A, Nha Trang, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/NhaTrang/AtlanticNhaTrangHotel/2.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Muong Thanh Luxury Nha Trang Hotel",
                "60 Tran Phu, Nha Trang, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/NhaTrang/MuongThanhLuxuryNhaTrangHotel/3.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Muong Thanh Luxury Khanh Hoa",
                "Zone 1 – Tan Lap Resident District, Xuong Huan Ward, Nha Trang City, Khanh Hoa Province, Nha Trang, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/NhaTrang/MuongThanhLuxuryKhanhHoa/1.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Hotel Novotel Nha Trang",
                "50 Tran Phu , Nha Trang, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/NhaTrang/HotelNovotelNhaTrang/1.jpg");
    }

    public static class CAT_BA {

        public static final CreatedHotel Hotel1 = new CreatedHotel("Hôtel Perle d'Orient Cat Ba - MGallery",
                "Cat Co 3 Beach Cat Ba Town Hai Phong city, 180000 Đảo Cát Bà, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/CatBa/HotelPerledOrientCatBa-MGallery/1.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Flamingo Cat Ba Beach Resort",
                "Cat Co Beach, Cat Hai, Hai Phong, Đảo Cát Bà, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/CatBa/FlamingoCatBaBeachResort/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Nam Cat Island Resort - CatbaBay",
                "Nam Cat Island, Cat Hai, Đảo Cát Bà, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/CatBa/NamCatIslandResort-CatbaBay/1.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Cat Ba Island Resort & Spa",
                "Cat Co 1, Đảo Cát Bà, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/CatBa/CatBaIslandResort&Spa/1.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Cat Ba Xanh - Green Catba Hotel",
                "No 43, 1/4 Street, Catba town, Đảo Cát Bà, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/CatBa/CatBaXanh-GreenCatbaHotel/1.jpg");
    }

    public static class QUY_NHON {

        public static final CreatedHotel Hotel1 = new CreatedHotel("FLC Luxury Hotel Quy Nhon",
                "Zone 4, Nhon Ly, Cat Tien Beach, Quy Nhơn, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuyNhon/FLCLuxuryHotelQuyNhon/1.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Avani Quy Nhon Resort",
                "Ghenh Rang, Bai Dai Beach, Quy Nhơn, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuyNhon/AvaniQuyNhonResort/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("Seaside Boutique Resort Quy Nhon",
                "Đường 1D Bãi Bàng 2, Xuân Hải, Xuân Cầu, Bai Dai Beach, Quy Nhon (Binh Dinh), Vietnam, Quy Nhơn, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuyNhon/SeasideBoutiqueResortQuyNhon/2.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Casa Marina Resort",
                "Zone 1, Km 15, National Road 1D, Ghenh Rang, Quy Nhơn, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuyNhon/CasaMarinaResort/1.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Fleur De Lys Hotel Quy Nhon",
                "16 Đường Nguyễn Huệ, Quy Nhơn, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/QuyNhon/FleurDeLysHotelQuyNhon/3.jpg");
    }

    public static class PHU_QUOC {

        public static final CreatedHotel Hotel1 = new CreatedHotel("Seashells Phu Quoc Hotel & Spa",
                "1 Vo Thi Sau, Duong Dong, Phú Quốc, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/PhuQuoc/SeashellsPhuQuocHotel&Spa/1.jpg");
        public static final CreatedHotel Hotel2 = new CreatedHotel("Phu Quoc Ocean Pearl Hotel",
                "99 Tran Hung Dao, Duong Dong, Phú Quốc, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/PhuQuoc/PhuQuocOceanPearlHotel/1.jpg");
        public static final CreatedHotel Hotel3 = new CreatedHotel("M Hotel Phu Quoc",
                "46 Tran Hung Dao, Duong Dong, Duong Dong, Phú Quốc, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/PhuQuoc/MHotelPhuQuoc/2.jpg");
        public static final CreatedHotel Hotel4 = new CreatedHotel("Novotel Phu Quoc Resort",
                "Duong Bao Hamlet Duong To Commune Kien Giang Province, Duong To, Phú Quốc, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/PhuQuoc/NovotelPhuQuocResort/1.jpg");
        public static final CreatedHotel Hotel5 = new CreatedHotel("Lahana Resort Phu Quoc & Spa",
                "91/3 Tran Hung Dao Street, Duong Dong, Phú Quốc, Việt Nam",
                "https://halongchat.000webhostapp.com/BookingRoom/imageHotel/PhuQuoc/LahanaResortPhuQuoc&Spa/1.jpg");
    }
}
