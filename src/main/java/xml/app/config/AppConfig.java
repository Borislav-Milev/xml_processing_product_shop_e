package xml.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xml.app.util.FileIOImpl;
import xml.app.util.ReaderImpl;
import xml.app.util.ValidatorUtilImpl;
import xml.app.util.XmlParserImpl;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.Reader;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;


@Configuration
public class AppConfig {

    @Bean
    public FileIO fileReader(){
        return new FileIOImpl();
    }
    @Bean
    public Reader reader(){
        return new ReaderImpl();
    }
    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }
}
