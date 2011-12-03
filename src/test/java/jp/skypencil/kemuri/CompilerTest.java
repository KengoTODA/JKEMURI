package jp.skypencil.kemuri;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CompilerTest extends AbstractTest {

	@Override
	protected void test(String source, String expected) throws Exception {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(byteArray);
		System.setOut(stream);

		Compiler compiler = new Compiler();
		StringReader reader = new StringReader(source);

		// for javap debug
		// compiler.compileTo(new StringReader(source), "Main", new File("."));

		byte[] binary = compiler.compile(reader, "Main");
		Class<?> clazz = new OriginalClassLoader().defineClass("Main", binary);
		Object instance = clazz.newInstance();
		Method method = clazz.getMethod("main", String[].class);
		assertThat(Modifier.isStatic(method.getModifiers()), is(true));
		assertThat(Modifier.isPublic(method.getModifiers()), is(true));

		method.invoke(instance, new Object[] { new String[]{ } });
		assertThat(byteArray.toString(), is(expected));
	}

	private static class OriginalClassLoader extends ClassLoader {
		public Class<?> defineClass(String name, byte[] b) {
			return defineClass(name, b, 0, b.length);
		}
	}
}
