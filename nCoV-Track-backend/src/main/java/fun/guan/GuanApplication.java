package fun.guan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author JingXi
 */
@SpringBootApplication
@MapperScan(basePackages = "fun.guan.mapper")
@EnableSwagger2
@EnableTransactionManagement
@EnableAsync
public class GuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuanApplication.class, args);
    }

}
