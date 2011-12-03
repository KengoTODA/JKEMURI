package jp.skypencil.kemuri;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

public class InterpreterTest {

	@Test
	public void testHelloWorld() throws IOException {
		test("`|", "Hello, world!");
	}

	@Test
	public void testRemoveHead() throws IOException {
		test("`\"^^|", "ello, world!");
	}

	@Test
	public void testRemove2nd3rd() throws IOException {
		test("`'^\"^^|", "Hlo, world!");
	}

	@Test
	public void testPythonHacker() throws IOException {
		test(
				"`\"^^\"^^^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^\"'\"^^\"^^`\"^^\"^^^^\"^^\"^^\"^^\"^^\"^^'\"^^\"^^\"'\"^^\"^^`\"^^'^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^\"'\"^^\"^^`'\"^^\"^^^^'\"^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^\"^^'\"^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^^`'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^'\"^^^'\"^^\"^^'\"^^\"^^'\"^^^`\"^^\"^^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`'\"^^''^^'\"^^\"^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^\"^^^'\"^^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^\"^^\"^^''\"^^^^'\"^^\"'`'\"^^\"^^'\"^^\"^^'\"^^\"^^^^'\"^^\"^^'\"^^\"^^\"'`\"^^\"^^^^\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^\"^^\"^^\"^^'\"^^\"^^\"'\"^^\"^^`\"^^'^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^\"'\"^^\"^^`'\"^^''^^'\"^^\"^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^\"^^^'\"^^^'\"^^\"^^`\"^^\"^^^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^'\"^^^'\"^^\"^^'\"^^\"^^'\"^^^`^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^'\"^^\"^^'\"^^\"^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^\"^^^'\"^^^'\"^^\"^^`\"^^\"^^^^\"^^\"^^'\"^^\"^^^'\"^^\"^^^`'\"^^\"^^'\"^^\"^^'\"^^^'\"^^^^'\"^^''`\"^^\"^^^^\"^^\"^^^^^'\"^^\"^^'\"^^\"^^\"'^\"^^|",
				"Just Another Python Hacker,");
	}

	private void test(String source, String expected) throws IOException {
		StringReader reader = new StringReader(source);
		StringWriter writer = new StringWriter();
		Interpreter interpreter = new Interpreter();

		interpreter.run(reader, new PrintWriter(writer));
		assertThat(writer.toString(), is(expected));
	}
}
