package luiz.sales.file.processing.reader;

import java.io.IOException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import luiz.sales.file.processing.model.Customers;

@Configuration
public class FileProcessingReader {

	@StepScope
	@Bean
	public FlatFileItemReader<Customers> fileReader(){
		
		String path = "E:\\projetoSpringBoot\\CursoSpringBatch\\CustomersFile.txt";
		Resource inputResource = getFileResource(path);
		
		return new FlatFileItemReaderBuilder<Customers>()
				.name("fileProcessingReader")
				.resource(inputResource)
				.delimited()
				.names(new String [] {"name", "lastName", "accountNumber"})
				.targetType(Customers.class)
				.build();
	}
	
    private FileSystemResource getFileResource(String path) {
    	
        try {
        	
            return new FileSystemResource(ResourceUtils.getFile(path));
        } catch (IOException e) {
        	
            throw new RuntimeException(e);
        }
    }
}
