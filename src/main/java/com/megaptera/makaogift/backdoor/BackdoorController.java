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

        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("DELETE FROM orders");

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
                        "  id, name, username, password," +
                        "  amount, created_at, updated_at" +
                        ")" +
                        " VALUES(1, ?, ?, ?, ?, ?, ?)",
                "홍길동", "myid", passwordEncoder.encode("Abcdef1!"),
                50_000, now, now
        );

        jdbcTemplate.update("" +
                        "INSERT INTO users(" +
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
    public String addItems(@RequestParam Integer count) {
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
                "  (1,'밍크 플리스 파자마 세트 J212401121(그레이)','자주(JAJU)','27530','밍크 플리스 파자마 세트 J212401121(그레이)','https://img.danawa.com/prod_img/500000/525/230/img/18230525_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (2,'디즈니 라이트 밍크 플리스 파자마 세트 J212401126(크림)','자주(JAJU)','31200','디즈니 라이트 밍크 플리스 파자마 세트 J212401126(크림)','https://img.danawa.com/prod_img/500000/664/307/img/18307664_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (3,'라이트 밍크플리스 브이넥 파자마 세트 J212401120(베이지)','자주(JAJU)','27400','라이트 밍크플리스 브이넥 파자마 세트 J212401120(베이지)','https://img.danawa.com/prod_img/500000/742/307/img/18307742_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (4,'벨벳 파자마 세트 J212401123(네이비)','자주(JAJU)','31200','벨벳 파자마 세트 J212401123(네이비)','https://img.danawa.com/prod_img/500000/606/311/img/18311606_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (5,'몬드 긴소매 상하 OR22WMGEA127','오르시떼','31630','몬드 긴소매 상하 OR22WMGEA127','https://img.danawa.com/prod_img/500000/446/393/img/18393446_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (6,'몰리 긴소매 상하 OR22WMGEA056','오르시떼','31380','몰리 긴소매 상하 OR22WMGEA056','https://img.danawa.com/prod_img/500000/356/393/img/18393356_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (7,'윈터띠아모 긴소매 상하 OR22WMGEA04200','오르시떼','34000','윈터띠아모 긴소매 상하 OR22WMGEA04200','https://img.danawa.com/prod_img/500000/305/393/img/18393305_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (8,'하이루이 긴소매 상하 OR22WMGEA346','오르시떼','35030','하이루이 긴소매 상하 OR22WMGEA346','https://img.danawa.com/prod_img/500000/515/393/img/18393515_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (9,'다운타우너 yum-yum 잠옷 SPPPB4TU12(블루)','스파오','24310','다운타우너 yum-yum 잠옷 SPPPB4TU12(블루)','https://img.danawa.com/prod_img/500000/965/461/img/16461965_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (10,'X 스폰지밥 돌아온 비키니시티 수면 잠옷 SPPPB4TU04','스파오','19900','X 스폰지밥 돌아온 비키니시티 수면 잠옷 SPPPB4TU04','https://img.danawa.com/prod_img/500000/573/506/img/15506573_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (11,'울트라 기모 겨울 수면 잠옷 세트 URA9994-4','BYC','27960','울트라 기모 겨울 수면 잠옷 세트 URA9994-4','https://img.danawa.com/prod_img/500000/059/408/img/18408059_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (12,'울트라 기모 겨울 수면 잠옷 세트 URA9994-3','BYC','27960','울트라 기모 겨울 수면 잠옷 세트 URA9994-3','https://img.danawa.com/prod_img/500000/978/407/img/18407978_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (13,'남성 윈터베이지 긴소매 페어 1046','이브니에','44420','남성 윈터베이지 긴소매 페어 1046','https://img.danawa.com/prod_img/500000/862/394/img/18394862_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (14,'트리맨 긴소매 페어 1322','이브니에','27510','트리맨 긴소매 페어 1322','https://img.danawa.com/prod_img/500000/775/397/img/18397775_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (15,'코튼캔디 잠옷 파자마세트 YSMNYA93','예스','38240','코튼캔디 잠옷 파자마세트 YSMNYA93','https://img.danawa.com/prod_img/500000/755/405/img/18405755_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (16,'수면 파자마세트 YSMNX982','예스','20030','수면 파자마세트 YSMNX982','https://img.danawa.com/prod_img/500000/657/260/img/18260657_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (17,'남성 극세사 벨벳 겨울 파자마 ANPA07','안파','34760','남성 극세사 벨벳 겨울 파자마 ANPA07','https://img.danawa.com/prod_img/500000/209/059/img/13059209_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (18,'남성 카시오페아 극세사 수면잠옷 세트','알콩단잠','30150','남성 카시오페아 극세사 수면잠옷 세트','https://img.danawa.com/prod_img/500000/233/408/img/18408233_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (19,'스누피 밍크스판 파자마 ANJS23A61','애니바디','41910','스누피 밍크스판 파자마 ANJS23A61','https://img.danawa.com/prod_img/500000/298/415/img/18415298_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (20,'밍크스판 스트라이프 파자마 ANJS23A53','애니바디','40710','밍크스판 스트라이프 파자마 ANJS23A53','https://img.danawa.com/prod_img/500000/415/415/img/18415415_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (21,'커플 홈웨어 로제 체크 극세사 수면 잠옷 세트','핑크우드','24900','커플 홈웨어 로제 체크 극세사 수면 잠옷 세트','https://img.danawa.com/prod_img/500000/603/176/img/15176603_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (22,'커플 겨울 홈웨어 바닐라 극세사 수면 잠옷 세트','핑크우드','24900','커플 겨울 홈웨어 바닐라 극세사 수면 잠옷 세트','https://img.danawa.com/prod_img/500000/557/531/img/15531557_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (23,'밍크 수면잠옷 상하세트 400558','혀니미니','8480','밍크 수면잠옷 상하세트 400558','https://img.danawa.com/prod_img/500000/936/413/img/18413936_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (24,'따뜻한 털 후리스 커플잠옷','몽트노블','23900','따뜻한 털 후리스 커플잠옷','https://img.danawa.com/prod_img/500000/643/412/img/18412643_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (25,'극세사 폼폼이 커플 수면 잠옷','루다벨','23900','극세사 폼폼이 커플 수면 잠옷','https://img.danawa.com/prod_img/500000/523/412/img/18412523_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (26,'극세사 노카라 커플 수면 잠옷 세트','루다벨','21800','극세사 노카라 커플 수면 잠옷 세트','https://img.danawa.com/prod_img/500000/619/412/img/18412619_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (27,'커플용 데일리 밍크 수면 커플 잠옷 상하의 세트','어나더디','20990','커플용 데일리 밍크 수면 커플 잠옷 상하의 세트','https://img.danawa.com/prod_img/500000/132/476/img/15476132_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (28,'따뜻한 곰돌이 밍크 극세사 커플 수면 잠옷','데이지베어','34800','따뜻한 곰돌이 밍크 극세사 커플 수면 잠옷','https://img.danawa.com/prod_img/500000/856/412/img/18412856_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (29,'홀리데이 벨벳 극세사 커플 잠옷','김양리빙','20380','홀리데이 벨벳 극세사 커플 잠옷','https://img.danawa.com/prod_img/500000/615/413/img/18413615_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (30,'벨벳리 극세사 수면잠옷 커플 파자마 홈웨어','김양리빙','23100','벨벳리 극세사 수면잠옷 커플 파자마 홈웨어','https://img.danawa.com/prod_img/500000/697/475/img/15475697_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (31,'커플 테디베어 곰돌이 겨울 극세사 수면 잠옷 세트','핑크우드','24900','커플 테디베어 곰돌이 겨울 극세사 수면 잠옷 세트','https://img.danawa.com/prod_img/500000/581/828/img/15828581_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (32,'폭닥폭닥 스트라이프 극세사 수면 잠옷 세트','핑크우드','23900','폭닥폭닥 스트라이프 극세사 수면 잠옷 세트','https://img.danawa.com/prod_img/500000/580/412/img/18412580_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (33,'벨벳 파자마 세트 J212401030(핑크)','자주(JAJU)','31200','벨벳 파자마 세트 J212401030(핑크)','https://img.danawa.com/prod_img/500000/705/311/img/18311705_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (34,'벨벳 파자마 세트 J212401030(네이비)','자주(JAJU)','31200','벨벳 파자마 세트 J212401030(네이비)','https://img.danawa.com/prod_img/500000/714/311/img/18311714_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (35,'라이트 밍크플리스 브이넥 파자마 세트 J212401026(베이지)','자주(JAJU)','27400','라이트 밍크플리스 브이넥 파자마 세트 J212401026(베이지)','https://img.danawa.com/prod_img/500000/089/303/img/18303089_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (36,'디즈니 라이트 밍크 플리스 파자마 세트 J212401033(라이트민트)','자주(JAJU)','31200','디즈니 라이트 밍크 플리스 파자마 세트 J212401033(라이트민트)','https://img.danawa.com/prod_img/500000/158/303/img/18303158_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (37,'런던포인트극세사 긴소매 상하 OR22WFGEA129','오르시떼','32300','런던포인트극세사 긴소매 상하 OR22WFGEA129','https://img.danawa.com/prod_img/500000/500/393/img/18393500_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (38,'몬드 긴소매 상하 OR22WFGEA128','오르시떼','34040','몬드 긴소매 상하 OR22WFGEA128','https://img.danawa.com/prod_img/500000/323/393/img/18393323_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (39,'몰리 긴소매 상하 OR22WFGEA055001','오르시떼','31680','몰리 긴소매 상하 OR22WFGEA055001','https://img.danawa.com/prod_img/500000/386/393/img/18393386_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (40,'윈터띠아모 긴소매 상하 OR22WFGEA043001','오르시떼','37300','윈터띠아모 긴소매 상하 OR22WFGEA043001','https://img.danawa.com/prod_img/500000/422/393/img/18393422_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (41,'X 잔망루피 군싹 루피 수면 잠옷 SPPPC4TU10(핑크)','스파오','41410','X 잔망루피 군싹 루피 수면 잠옷 SPPPC4TU10(핑크)','https://img.danawa.com/prod_img/500000/562/016/img/18016562_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (42,'먼가 작고 귀여운 잠옷 SPPPC4TU11(네이비)','스파오','49900','먼가 작고 귀여운 잠옷 SPPPC4TU11(네이비)','https://img.danawa.com/prod_img/500000/933/398/img/18398933_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (43,'울트라 기모 겨울 수면 잠옷 세트 EAR0524-3','BYC','27960','울트라 기모 겨울 수면 잠옷 세트 EAR0524-3','https://img.danawa.com/prod_img/500000/291/407/img/18407291_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (44,'울트라 기모 겨울 수면 잠옷 세트 EAR0524-5','BYC','27960','울트라 기모 겨울 수면 잠옷 세트 EAR0524-5','https://img.danawa.com/prod_img/500000/462/407/img/18407462_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (45,'극세사 포근파자마 EBPPB4T11M','에블린','16720','극세사 포근파자마 EBPPB4T11M','https://img.danawa.com/prod_img/500000/662/741/img/15741662_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (46,'꿀자마 꿀잠자는 포근파자마 EBPPA4V01M','에블린','44900','꿀자마 꿀잠자는 포근파자마 EBPPA4V01M','https://img.danawa.com/prod_img/500000/871/970/img/12970871_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (47,'레터링 수면 잠옷 세트 YSWNX981','예스','36400','레터링 수면 잠옷 세트 YSWNX981','https://img.danawa.com/prod_img/500000/928/986/img/17986928_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (48,'하트 별 수면 잠옷 세트 YSWNX982','예스','19890','하트 별 수면 잠옷 세트 YSWNX982','https://img.danawa.com/prod_img/500000/042/987/img/17987042_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (49,'여성 윈터베이지 긴소매 페어 1046','이브니에','44410','여성 윈터베이지 긴소매 페어 1046','https://img.danawa.com/prod_img/500000/814/394/img/18394814_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (50,'여성 사일러스 극세사 긴팔페어 1041','이브니에','43670','여성 사일러스 극세사 긴팔페어 1041','https://img.danawa.com/prod_img/500000/045/395/img/18395045_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (51,'밀크라떼 긴팔 투피스 WTLL22175C','울랄라','44410','밀크라떼 긴팔 투피스 WTLL22175C','https://img.danawa.com/prod_img/500000/722/399/img/18399722_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (52,'소이라떼 긴팔 투피스 WTLL22174C','울랄라','52440','소이라떼 긴팔 투피스 WTLL22174C','https://img.danawa.com/prod_img/500000/896/399/img/18399896_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (53,'토끼 수면 파자마 세트','시글락','11900','토끼 수면 파자마 세트','https://img.danawa.com/prod_img/500000/509/405/img/18405509_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (54,'부르르 파자마','오끼뜨','64141','부르르 파자마','https://img.danawa.com/prod_img/500000/220/406/img/18406220_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (55,'덤보 캐릭터 밍크스판 파자마 ANJS22T82','애니바디','33680','덤보 캐릭터 밍크스판 파자마 ANJS22T82','https://img.danawa.com/prod_img/500000/926/405/img/18405926_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (56,'꽈배기 V넥 가디건 극세사 수면 잠옷 홈웨어 세트','핑크우드','24900','꽈배기 V넥 가디건 극세사 수면 잠옷 홈웨어 세트','https://img.danawa.com/prod_img/500000/528/803/img/15803528_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (57,'여성 카시오페아 극세사 수면잠옷 세트','알콩단잠','30160','여성 카시오페아 극세사 수면잠옷 세트','https://img.danawa.com/prod_img/500000/362/408/img/18408362_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (58,'밍크 긴팔 9부 잠옷 상하 세트 IWS7022','이너쿠키','23950','밍크 긴팔 9부 잠옷 상하 세트 IWS7022','https://img.danawa.com/prod_img/500000/743/408/img/18408743_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (59,'뽀글이 양털 수면 잠옷세트','김양리빙','23000','뽀글이 양털 수면 잠옷세트','https://img.danawa.com/prod_img/500000/866/408/img/18408866_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (60,'벨벳 극세사 수면잠옷 겨울 파자마','김양리빙','19900','벨벳 극세사 수면잠옷 겨울 파자마','https://img.danawa.com/prod_img/500000/234/413/img/18413234_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (61,'수면잠옷 밍크 상하세트 400557','혀니미니','10020','수면잠옷 밍크 상하세트 400557','https://img.danawa.com/prod_img/500000/999/413/img/18413999_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (62,'소프트 도톰 꽈배기 수면 맨투맨 잠옷 세트','테라우드','30180','소프트 도톰 꽈배기 수면 맨투맨 잠옷 세트','https://img.danawa.com/prod_img/500000/215/414/img/18414215_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (63,'코튼캔디 잠옷 파자마세트 YSWNYA93','예스','38240','코튼캔디 잠옷 파자마세트 YSWNYA93','https://img.danawa.com/prod_img/500000/815/405/img/18405815_1.jpg??shrink=360:360&_v=20221207191647'),\n" +
                "  (64,'밍크 파자마 상하세트 YSWNYA91','예스','38240','밍크 파자마 상하세트 YSWNYA91','https://img.danawa.com/prod_img/500000/540/323/img/18323540_1.jpg??shrink=360:360&_v=20221207191647');");

        return "OK";
    }
}
