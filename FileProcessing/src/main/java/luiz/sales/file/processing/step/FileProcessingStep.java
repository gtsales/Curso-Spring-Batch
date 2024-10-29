package luiz.sales.file.processing.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import luiz.sales.file.processing.model.Customers;

@Configuration
public class FileProcessingStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step fileStep(ItemReader<Customers> fileReader, ItemWriter<Customers> fileWriter) {
		
		return stepBuilderFactory
				.get("fileStep")
				.<Customers, Customers>chunk(1)
				.reader(fileReader)
				.writer(fileWriter)
				.build();
	}
}
