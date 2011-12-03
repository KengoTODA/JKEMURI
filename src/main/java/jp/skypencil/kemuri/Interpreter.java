package jp.skypencil.kemuri;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.collect.Lists;

public class Interpreter {
	private static final List<Character> HELLO_WORLD = Lists.reverse(Arrays.asList(new Character[] {
			72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33
	}));

	private final Logger logger = Logger.getLogger(getClass().getName());
	private final Deque<Character> stack = new ArrayDeque<Character>();

	public void run(Reader reader, PrintWriter writer) throws IOException {
		int i;
		while ((i = reader.read()) != -1) {
			exec(i, writer);
		}
	}

	private void exec(int i, PrintWriter writer) {
		switch (i) {
		case '^':
			xor();
			break;
		case '~':
			not();
			break;
		case '"':
			dup();
			break;
		case '\'':
			rot();
			break;
		case '`':
			hello();
			break;
		case '|':
			print(writer);
			break;
		default: 
				logger.warning("unknown command: " + Character.toString((char) i));
		}
	}

	private void print(PrintWriter writer) {
		while (!stack.isEmpty()) {
			writer.print(stack.pop());
		}
	}

	private void hello() {
		for (char c : HELLO_WORLD) {
			stack.push(c);
		}
	}

	private void rot() {
		Character first = stack.pop();
		Character last = stack.pop();
		Character second = stack.pop();
		stack.push(first);
		stack.push(second);
		stack.push(last);
	}

	private void dup() {
		Character newValue = stack.pop();
		stack.push(newValue);
		stack.push(newValue);
	}

	private void xor() {
		int newValue = stack.pop().charValue() ^ stack.pop().charValue();
		stack.push(Character.valueOf((char) newValue));
	}

	private void not() {
		int newValue = 255 - stack.pop();
		stack.push(Character.valueOf((char) newValue));
	}
	
}
