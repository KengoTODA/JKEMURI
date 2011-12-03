package jp.skypencil.kemuri;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class InterpreterTest extends AbstractTest {

	@Override
	protected void test(String source, String expected) throws Exception {
		StringReader reader = new StringReader(source);
		StringWriter writer = new StringWriter();
		Interpreter interpreter = new Interpreter();

		interpreter.run(reader, new PrintWriter(writer));
		assertThat(writer.toString(), is(expected));
	}
}
