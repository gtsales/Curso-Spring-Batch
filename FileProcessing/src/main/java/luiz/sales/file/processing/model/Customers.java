package luiz.sales.file.processing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

	private String name;
	private String lastName;
	private String accountNumber;
}
