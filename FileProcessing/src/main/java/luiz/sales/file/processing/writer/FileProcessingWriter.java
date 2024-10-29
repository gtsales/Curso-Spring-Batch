package luiz.sales.file.processing.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import luiz.sales.file.processing.model.Customers;

@Configuration
public class FileProcessingWriter {

	@Bean
	public ItemWriter<Customers> fileWriter() {
		return items -> items.forEach(System.out::println);
	}
}
