package luiz.sales.parimparjob.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;import ch.qos.logback.core.net.SyslogOutputStream;

@Configuration
@EnableBatchProcessing
public class ParImparBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job printParImparJob() {
		
		return jobBuilderFactory
				.get("printParImparJob")
				.start(printParImparStep())
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	public Step printParImparStep() {
		
		return stepBuilderFactory
				.get("printParImparStep")
				.<Integer, String>chunk(1)
				.reader(countToTenReader())
				.processor(parImparProcessor())
				.writer(printWriter())
				.build();
	}
	
	public IteratorItemReader<Integer> countToTenReader() {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		return new IteratorItemReader<Integer>(numbers.iterator());
	}
	
	public ItemProcessor<Integer, String> parImparProcessor() {
		
		return new FunctionItemProcessor<Integer, String>(item -> item % 2 == 0 ? String.format("Item %s é par", item) : String.format("Item %s é impar", item));
	}
	
	public ItemWriter<String> printWriter() {
		
		return itens -> itens.forEach(System.out::println);
	}
}
