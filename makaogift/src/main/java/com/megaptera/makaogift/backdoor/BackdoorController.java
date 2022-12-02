package com.megaptera.makaogift.backdoor;

import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        LocalDateTime now = LocalDateTime.now();

        // TODO: delete from order
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM person");

        jdbcTemplate.update("" +
                        "INSERT INTO person(" +
                        "  id, name, username, password," +
                        "  amount, created_at, updated_at" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?, ?, ?)",
                "홍길동", "myid", passwordEncoder.encode("Abcdef1!"),
                50_000, now, now
        );

        jdbcTemplate.update("" +
                        "INSERT INTO person(" +
                        "  id, name, username, password," +
                        "  amount, created_at, updated_at" +
                        ")" +
                        " VALUES(2, ?, ?, ?, ?, ?, ?)",
                "동길홍", "myid2", passwordEncoder.encode("Abcdef1!"),
                50_000, now, now
        );

        return "OK";
    }

    @GetMapping("add-items")
    public String addItems(@RequestParam int count) {
        jdbcTemplate.execute("DELETE FROM product");

        IntStream.range(0, count)
                .forEach(i -> {
                    jdbcTemplate.execute("INSERT INTO product (id, name, producer, price, description, image_url) VALUES\n" +
                            "  (" + (i + 1) + ",'갈비천왕+콜라1.25L','굽네치킨','10000','갈비천왕+콜라1.25L','https://img1.kakaocdn.net/thumb/C320x320@2x.q82.fwebp/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20220503173239_52adf00ef3c54f96931ddd31229920c7.jpg');\n"
                    );
                });

        return "OK";
    }

    @GetMapping("add-all-items")
    public String addAllItems() {
        jdbcTemplate.execute("DELETE FROM product");

        jdbcTemplate.execute("INSERT INTO product (id, name, producer, price, description, image_url) VALUES\n" +
                "  (1,'갈비천왕+콜라1.25L','굽네치킨','10000','갈비천왕+콜라1.25L','https://img1.kakaocdn.net/thumb/C320x320@2x.q82.fwebp/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20220503173239_52adf00ef3c54f96931ddd31229920c7.jpg'),\n" +
                "  (2,'클래식 라인드 클로그 203591-459','크록스','37900','클래식 라인드 클로그 203591-459','https://img.danawa.com/prod_img/500000/478/791/img/9791478_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (3,'클래식 라인드 클로그 203591-2YB','크록스','37905','클래식 라인드 클로그 203591-2YB','https://img.danawa.com/prod_img/500000/344/165/img/13165344_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (4,'클래식 라인드 클로그 203591-10M','크록스','29300','클래식 라인드 클로그 203591-10M','https://img.danawa.com/prod_img/500000/526/791/img/9791526_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (5,'클래식 라인드 클로그 203591-23B','크록스','38720','클래식 라인드 클로그 203591-23B','https://img.danawa.com/prod_img/500000/690/510/img/5510690_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (6,'클래식 라인드 블리치 다이 클로그 207299-001','크록스','43610','클래식 라인드 블리치 다이 클로그 207299-001','https://img.danawa.com/prod_img/500000/998/393/img/15393998_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (7,'클래식 라인드 클로그 203591-50P','크록스','36060','클래식 라인드 클로그 203591-50P','https://img.danawa.com/prod_img/500000/221/781/img/9781221_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (8,'클래식 라인드 클로그 203591-837','크록스','52390','클래식 라인드 클로그 203591-837','https://img.danawa.com/prod_img/500000/609/617/img/15617609_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (9,'클래식 퍼 슈어 클로그 207303-001','크록스','51710','클래식 퍼 슈어 클로그 207303-001','https://img.danawa.com/prod_img/500000/440/393/img/15393440_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (10,'클래식 퍼 슈어 클로그 207303-0C4','크록스','51680','클래식 퍼 슈어 클로그 207303-0C4','https://img.danawa.com/prod_img/500000/602/393/img/15393602_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (11,'클래식 퍼 슈어 클로그 207303-6SU','크록스','49510','클래식 퍼 슈어 클로그 207303-6SU','https://img.danawa.com/prod_img/500000/521/393/img/15393521_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (12,'클래식 퍼 슈어 클로그 207303-212','크록스','59750','클래식 퍼 슈어 클로그 207303-212','https://img.danawa.com/prod_img/500000/557/393/img/15393557_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (13,'클래식 라인드 네오 퍼프 클로그 206589-060','크록스','34000','클래식 라인드 네오 퍼프 클로그 206589-060','https://img.danawa.com/prod_img/500000/873/449/img/12449873_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (14,'클래식 라인드 네오 퍼프 클로그 206589-4RU','크록스','29530','클래식 라인드 네오 퍼프 클로그 206589-4RU','https://img.danawa.com/prod_img/500000/987/449/img/12449987_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (15,'클래식 라인드 네오 퍼프 클로그 206589-70U','크록스','102310','클래식 라인드 네오 퍼프 클로그 206589-70U','https://img.danawa.com/prod_img/500000/029/450/img/12450029_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (16,'클래식 라인드 네오 퍼프 클로그 206589-143','크록스','31500','클래식 라인드 네오 퍼프 클로그 206589-143','https://img.danawa.com/prod_img/500000/921/449/img/12449921_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (17,'바야 라인드 클로그 205969-060','크록스','31900','바야 라인드 클로그 205969-060','https://img.danawa.com/prod_img/500000/262/782/img/9782262_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (18,'바야 라인드 클로그 205969-22Z','크록스','45390','바야 라인드 클로그 205969-22Z','https://img.danawa.com/prod_img/500000/076/782/img/9782076_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (19,'바야 라인드 클로그 205969-50P','크록스','43060','바야 라인드 클로그 205969-50P','https://img.danawa.com/prod_img/500000/139/466/img/12466139_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (20,'바야 라인드 클로그 205969-11H','크록스','29910','바야 라인드 클로그 205969-11H','https://img.danawa.com/prod_img/500000/718/571/img/15571718_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (21,'바야 라인드 프린트 클로그 205975-0C4','크록스','44720','바야 라인드 프린트 클로그 205975-0C4','https://img.danawa.com/prod_img/500000/115/806/img/17806115_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (22,'바야 라인드 타이다이 클로그 207329-95T','크록스','41990','바야 라인드 타이다이 클로그 207329-95T','https://img.danawa.com/prod_img/500000/467/162/img/13162467_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (23,'바야 라인드 타이다이 클로그 207329-6SO','크록스','46270','바야 라인드 타이다이 클로그 207329-6SO','https://img.danawa.com/prod_img/500000/364/694/img/15694364_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (24,'바야 라인드 프린트 클로그 205975-10M','크록스','39130','바야 라인드 프린트 클로그 205975-10M','https://img.danawa.com/prod_img/500000/308/618/img/15618308_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (25,'바야 라인드 퍼즈 스트랩 클로그 206633-060','크록스','41200','바야 라인드 퍼즈 스트랩 클로그 206633-060','https://img.danawa.com/prod_img/500000/757/088/img/18088757_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (26,'바야 라인드 퍼즈 스트랩 클로그 206633-4HE','크록스','39910','바야 라인드 퍼즈 스트랩 클로그 206633-4HE','https://img.danawa.com/prod_img/500000/170/549/img/12549170_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (27,'바야 라인드 퍼즈 스트랩 클로그 206633-0IG','크록스','39910','바야 라인드 퍼즈 스트랩 클로그 206633-0IG','https://img.danawa.com/prod_img/500000/095/342/img/15342095_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (28,'바야 라인드 퍼즈 스트랩 클로그 206633-577','크록스','31700','바야 라인드 퍼즈 스트랩 클로그 206633-577','https://img.danawa.com/prod_img/500000/020/342/img/15342020_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (29,'라렌 라인드 클로그 16244-22Z','크록스','32880','라렌 라인드 클로그 16244-22Z','https://img.danawa.com/prod_img/500000/782/638/img/4638782_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (30,'라렌 라인드 클로그 16244-060','크록스','32880','라렌 라인드 클로그 16244-060','https://img.danawa.com/prod_img/500000/269/809/img/6809269_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (31,'라렌 라인드 클로그 16244-28A','크록스','32880','라렌 라인드 클로그 16244-28A','https://img.danawa.com/prod_img/500000/627/728/img/4728627_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (32,'라렌 라인드 클로그 16244-952','크록스','32880','라렌 라인드 클로그 16244-952','https://img.danawa.com/prod_img/500000/326/809/img/6809326_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (33,'보스턴 1011420','버켄스탁','93440','보스턴 1011420','https://img.danawa.com/prod_img/500000/337/046/img/15046337_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (34,'보스턴 1009543','버켄스탁','89700','보스턴 1009543','https://img.danawa.com/prod_img/500000/099/720/img/17720099_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (35,'보스턴 SFB 660461','버켄스탁','115000','보스턴 SFB 660461','https://img.danawa.com/prod_img/500000/695/069/img/6069695_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (36,'보스턴 SFB 560773','버켄스탁','187990','보스턴 SFB 560773','https://img.danawa.com/prod_img/500000/735/069/img/6069735_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (37,'보스턴 퍼 259881','버켄스탁','251890','보스턴 퍼 259881','https://img.danawa.com/prod_img/500000/818/892/img/9892818_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (38,'보스턴 퍼 1020567','버켄스탁','221470','보스턴 퍼 1020567','https://img.danawa.com/prod_img/500000/827/006/img/18006827_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (39,'보스턴 퍼 1020569','버켄스탁','221470','보스턴 퍼 1020569','https://img.danawa.com/prod_img/500000/842/006/img/18006842_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (40,'보스턴 시어링 1001140','버켄스탁','195340','보스턴 시어링 1001140','https://img.danawa.com/prod_img/500000/317/093/img/10093317_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (41,'버클리 1017825','버켄스탁','128007','버클리 1017825','https://img.danawa.com/prod_img/500000/489/182/img/18182489_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (42,'버클리 1017827','버켄스탁','136110','버클리 1017827','https://img.danawa.com/prod_img/500000/924/775/img/16775924_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (43,'버클리 1019462','버켄스탁','155610','버클리 1019462','https://img.danawa.com/prod_img/500000/495/182/img/18182495_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (44,'버클리 퍼 1020671','버켄스탁','192320','버클리 퍼 1020671','https://img.danawa.com/prod_img/500000/504/182/img/18182504_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (45,'로리아 K817004ND-W','베어파우','24760','로리아 K817004ND-W','https://img.danawa.com/prod_img/500000/463/496/img/15496463_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (46,'로리아 K817049ND-W','베어파우','24760','로리아 K817049ND-W','https://img.danawa.com/prod_img/500000/424/496/img/15496424_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (47,'로리아 K817002ND-W','베어파우','24760','로리아 K817002ND-W','https://img.danawa.com/prod_img/500000/481/496/img/15496481_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (48,'로리아 K817091ND-W','베어파우','24760','로리아 K817091ND-W','https://img.danawa.com/prod_img/500000/406/496/img/15496406_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (49,'테아 K824001OD-W','베어파우','36450','테아 K824001OD-W','https://img.danawa.com/prod_img/500000/289/163/img/18163289_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (50,'테아 K824049OD-W','베어파우','54280','테아 K824049OD-W','https://img.danawa.com/prod_img/500000/310/163/img/18163310_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (51,'테아 K824002OD-W','베어파우','36930','테아 K824002OD-W','https://img.danawa.com/prod_img/500000/250/163/img/18163250_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (52,'테아 K824303OD-W','베어파우','37950','테아 K824303OD-W','https://img.danawa.com/prod_img/500000/247/163/img/18163247_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (53,'벨 시어링 K917261ND-M','베어파우','54020','벨 시어링 K917261ND-M','https://img.danawa.com/prod_img/500000/542/952/img/17952542_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (54,'벨 시어링 K917261ND-W','베어파우','54020','벨 시어링 K917261ND-W','https://img.danawa.com/prod_img/500000/094/850/img/15850094_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (55,'벨 시어링 K917049OD-W','베어파우','53480','벨 시어링 K917049OD-W','https://img.danawa.com/prod_img/500000/463/163/img/18163463_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (56,'벨 K917030MD-W','베어파우','53580','벨 K917030MD-W','https://img.danawa.com/prod_img/500000/971/660/img/12660971_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (57,'벨 K917001NB-W','베어파우','62290','벨 K917001NB-W','https://img.danawa.com/prod_img/500000/535/575/img/14575535_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (58,'벨 시어링 K917267ND-W','베어파우','53160','벨 시어링 K917267ND-W','https://img.danawa.com/prod_img/500000/913/670/img/15670913_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (59,'벨 K917042MD-W','베어파우','53580','벨 K917042MD-W','https://img.danawa.com/prod_img/500000/938/660/img/12660938_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (60,'벨 K917003NB-W','베어파우','62290','벨 K917003NB-W','https://img.danawa.com/prod_img/500000/520/575/img/14575520_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (61,'코케트 5125-BLK','UGG','113540','코케트 5125-BLK','https://img.danawa.com/prod_img/500000/173/794/img/6794173_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (62,'코케트 5125-GREY','UGG','108990','코케트 5125-GREY','https://img.danawa.com/prod_img/500000/260/794/img/6794260_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (63,'코케트 5125-CHE','UGG','61640','코케트 5125-CHE','https://img.danawa.com/prod_img/500000/650/111/img/10111650_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (64,'코케트 16623-03500','UGG','77000','코케트 16623-03500','https://img.danawa.com/prod_img/500000/462/945/img/17945462_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (65,'디스케트 1122550-BLK','UGG','126370','디스케트 1122550-BLK','https://img.danawa.com/prod_img/500000/602/799/img/17799602_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (66,'디스케트 16623-03504','UGG','178200','디스케트 16623-03504','https://img.danawa.com/prod_img/500000/294/900/img/17900294_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (67,'디스케트 16623-03505','UGG','178200','디스케트 16623-03505','https://img.danawa.com/prod_img/500000/339/900/img/17900339_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (68,'디스케트 1122550-CHE','UGG','62400','디스케트 1122550-CHE','https://img.danawa.com/prod_img/500000/669/584/img/15584669_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (69,'타스만 5950-BLK','UGG','88100','타스만 5950-BLK','https://img.danawa.com/prod_img/500000/027/605/img/15605027_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (70,'타스만 W 5955-DGRY','UGG','88100','타스만 W 5955-DGRY','https://img.danawa.com/prod_img/500000/940/896/img/17896940_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (71,'타스만 2 5955-CHE','UGG','127590','타스만 2 5955-CHE','https://img.danawa.com/prod_img/500000/712/604/img/15604712_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (72,'타스만 16623-03513','UGG','128160','타스만 16623-03513','https://img.danawa.com/prod_img/500000/183/945/img/17945183_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (73,'글렌 스웨이드 클로그 FLOTCA4U02','오찌','69590','글렌 스웨이드 클로그 FLOTCA4U02','https://img.danawa.com/prod_img/500000/232/342/img/16342232_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (74,'글렌 스웨이드 클로그 FLOTCA4U03','오찌','74940','글렌 스웨이드 클로그 FLOTCA4U03','https://img.danawa.com/prod_img/500000/187/342/img/16342187_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (75,'글렌 스웨이드 클로그 FLOTCA4U01','오찌','77420','글렌 스웨이드 클로그 FLOTCA4U01','https://img.danawa.com/prod_img/500000/103/342/img/16342103_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (76,'글렌 스웨이드 클로그 FLOTCA4U04','오찌','77420','글렌 스웨이드 클로그 FLOTCA4U04','https://img.danawa.com/prod_img/500000/217/342/img/16342217_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (77,'오리지널 윈터 슬리퍼 (블랙)','락피쉬웨더웨어','48510','오리지널 윈터 슬리퍼 (블랙)','https://img.danawa.com/prod_img/500000/008/408/img/15408008_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (78,'오리지널 윈터 슬리퍼 (체스트넛)','락피쉬웨더웨어','48510','오리지널 윈터 슬리퍼 (체스트넛)','https://img.danawa.com/prod_img/500000/101/408/img/15408101_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (79,'오리지널 윈터 슬리퍼 (핑크)','락피쉬웨더웨어','48510','오리지널 윈터 슬리퍼 (핑크)','https://img.danawa.com/prod_img/500000/125/408/img/15408125_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (80,'오리지널 윈터 슬리퍼 (샌드)','락피쉬웨더웨어','48510','오리지널 윈터 슬리퍼 (샌드)','https://img.danawa.com/prod_img/500000/152/408/img/15408152_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (81,'취리히 SFB 1009528','버켄스탁','83000','취리히 SFB 1009528','https://img.danawa.com/prod_img/500000/358/620/img/8620358_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (82,'취리히 SFB RFSO1E071W3','버켄스탁','101360','취리히 SFB RFSO1E071W3','https://img.danawa.com/prod_img/500000/660/051/img/14051660_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (83,'취리히 SFB 1009533','버켄스탁','92110','취리히 SFB 1009533','https://img.danawa.com/prod_img/500000/840/069/img/6069840_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (84,'취리히 SFB 1009532','버켄스탁','75050','취리히 SFB 1009532','https://img.danawa.com/prod_img/500000/816/069/img/6069816_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (85,'취리히 1009531','버켄스탁','62390','취리히 1009531','https://img.danawa.com/prod_img/500000/200/871/img/7871200_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (86,'취리히 SFB RFSO1E071W2','버켄스탁','111200','취리히 SFB RFSO1E071W2','https://img.danawa.com/prod_img/500000/699/051/img/14051699_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (87,'취리히 1009535','버켄스탁','119000','취리히 1009535','https://img.danawa.com/prod_img/500000/224/117/img/8117224_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (88,'취리히 SFB RFSO9E156I3','버켄스탁','116820','취리히 SFB RFSO9E156I3','https://img.danawa.com/prod_img/500000/348/642/img/8642348_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (89,'아리조나 퍼 752661','버켄스탁','132630','아리조나 퍼 752661','https://img.danawa.com/prod_img/500000/516/182/img/18182516_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (90,'아리조나 퍼 1020566','버켄스탁','118600','아리조나 퍼 1020566','https://img.danawa.com/prod_img/500000/513/182/img/18182513_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (91,'아리조나 퍼 1001135','버켄스탁','134400','아리조나 퍼 1001135','https://img.danawa.com/prod_img/500000/611/672/img/5672611_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (92,'아리조나 퍼 RFSO1F005G1','버켄스탁','136940','아리조나 퍼 RFSO1F005G1','https://img.danawa.com/prod_img/500000/380/960/img/15960380_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (93,'아리조나 시어링 0752661','버켄스탁','238000','아리조나 시어링 0752661','https://img.danawa.com/prod_img/500000/630/182/img/18182630_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (94,'아리조나 시어링 1017402','버켄스탁','106800','아리조나 시어링 1017402','https://img.danawa.com/prod_img/500000/636/182/img/18182636_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (95,'아리조나 시어링 1020411','버켄스탁','263700','아리조나 시어링 1020411','https://img.danawa.com/prod_img/500000/660/182/img/18182660_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (96,'아리조나 퍼 1001128','버켄스탁','108600','아리조나 퍼 1001128','https://img.danawa.com/prod_img/500000/510/182/img/18182510_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (97,'아리조나 퍼 RFSO1F006BK','버켄스탁','136940','아리조나 퍼 RFSO1F006BK','https://img.danawa.com/prod_img/500000/775/961/img/15961775_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (98,'아리조나 퍼 RFSO1F006N3','버켄스탁','167200','아리조나 퍼 RFSO1F006N3','https://img.danawa.com/prod_img/500000/928/502/img/15502928_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (99,'아리조나 퍼 RFSO1F005W2','버켄스탁','198550','아리조나 퍼 RFSO1F005W2','https://img.danawa.com/prod_img/500000/626/501/img/15501626_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (100,'아리조나 퍼 RFSO1F005R3','버켄스탁','138450','아리조나 퍼 RFSO1F005R3','https://img.danawa.com/prod_img/500000/404/960/img/15960404_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (101,'아리조나 SFB 0951323','버켄스탁','181900','아리조나 SFB 0951323','https://img.danawa.com/prod_img/500000/612/182/img/18182612_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (102,'아리조나 SFB 0951313','버켄스탁','83400','아리조나 SFB 0951313','https://img.danawa.com/prod_img/500000/609/182/img/18182609_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (103,'아리조나 SFB 0552323','버켄스탁','179166','아리조나 SFB 0552323','https://img.danawa.com/prod_img/500000/600/182/img/18182600_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (104,'아리조나 151211','버켄스탁','54100','아리조나 151211','https://img.danawa.com/prod_img/500000/950/728/img/1728950_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (105,'아리조나 SFB 1009527','버켄스탁','81350','아리조나 SFB 1009527','https://img.danawa.com/prod_img/500000/556/941/img/14941556_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (106,'아리조나 SFB 1021396','버켄스탁','109190','아리조나 SFB 1021396','https://img.danawa.com/prod_img/500000/624/182/img/18182624_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (107,'아리조나 SFB 951303','버켄스탁','76900','아리조나 SFB 951303','https://img.danawa.com/prod_img/500000/899/917/img/16917899_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (108,'아리조나 SFB 1020557','버켄스탁','90990','아리조나 SFB 1020557','https://img.danawa.com/prod_img/500000/651/182/img/18182651_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (109,'아리조나 SFB 1020732','버켄스탁','215190','아리조나 SFB 1020732','https://img.danawa.com/prod_img/500000/654/182/img/18182654_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (110,'아리조나 SFB 1021195','버켄스탁','215210','아리조나 SFB 1021195','https://img.danawa.com/prod_img/500000/681/182/img/18182681_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (111,'아리조나 1023543','버켄스탁','193400','아리조나 1023543','https://img.danawa.com/prod_img/500000/678/182/img/18182678_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (112,'아리조나 SFB 1021424','버켄스탁','184000','아리조나 SFB 1021424','https://img.danawa.com/prod_img/500000/618/182/img/18182618_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (113,'교토 1022566','버켄스탁','195720','교토 1022566','https://img.danawa.com/prod_img/500000/528/182/img/18182528_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (114,'교토 1020689','버켄스탁','238000','교토 1020689','https://img.danawa.com/prod_img/500000/540/182/img/18182540_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (115,'교토 1020737','버켄스탁','238000','교토 1020737','https://img.danawa.com/prod_img/500000/531/182/img/18182531_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (116,'교토 RFSO0E034I3','버켄스탁','148220','교토 RFSO0E034I3','https://img.danawa.com/prod_img/500000/115/168/img/11168115_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (117,'교토 RFSO1F027N3','버켄스탁','164680','교토 RFSO1F027N3','https://img.danawa.com/prod_img/500000/871/961/img/15961871_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (118,'교토 RFSO1E041N3','버켄스탁','164680','교토 RFSO1E041N3','https://img.danawa.com/prod_img/500000/873/549/img/14549873_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (119,'교토 RFSO1E041W1','버켄스탁','143200','교토 RFSO1E041W1','https://img.danawa.com/prod_img/500000/684/781/img/13781684_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (120,'교토 RFSO1F028G1','버켄스탁','164680','교토 RFSO1F028G1','https://img.danawa.com/prod_img/500000/051/962/img/15962051_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (121,'클래식 코지 샌들 207446-060','크록스','42860','클래식 코지 샌들 207446-060','https://img.danawa.com/prod_img/500000/510/903/img/17903510_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (122,'클래식 코지 글리터 샌들 208124-067','크록스','44140','클래식 코지 글리터 샌들 208124-067','https://img.danawa.com/prod_img/500000/545/904/img/17904545_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (123,'클래식 코지 샌들 207446-2YC','크록스','43010','클래식 코지 샌들 207446-2YC','https://img.danawa.com/prod_img/500000/549/903/img/17903549_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (124,'클래식 코지 샌들 207446-100','크록스','42890','클래식 코지 샌들 207446-100','https://img.danawa.com/prod_img/500000/923/805/img/17805923_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (125,'락아웃 H1802 (블랙)','호보켄','25830','락아웃 H1802 (블랙)','https://img.danawa.com/prod_img/500000/862/965/img/11965862_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (126,'락아웃 H1802 (올리브)','호보켄','25830','락아웃 H1802 (올리브)','https://img.danawa.com/prod_img/500000/889/965/img/11965889_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (127,'슬라이드 HS40084 (올블랙)','호킨스','16780','슬라이드 HS40084 (올블랙)','https://img.danawa.com/prod_img/500000/444/735/img/17735444_1.jpg??shrink=360:360&_v=20221130190342'),\n" +
                "  (128,'슬라이드 HS40084 (베이지)','호킨스','16780','슬라이드 HS40084 (베이지)','https://img.danawa.com/prod_img/500000/438/735/img/17735438_1.jpg??shrink=360:360&_v=20221130190342');");

        return "OK";
    }
}
