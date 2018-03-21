package com.lcy.java7Coin;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by lcy on 2018/3/19.
 */
public class JavaNio {
	public static void main(String[] args) throws IOException {
		/*Path path = Paths.get("E://Code//beijing//ezfiresvr");
		//System.out.println(System.getProperty("user.dir"));
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.xml")) {
			for (Path path1 : stream) {
				System.out.println(path1.getFileName());
			}
		}*/

		//Files.walkFileTree(path, new FindJavaVisitor());

		//Path newPath = Paths.get("E://test.nio");
		Path logFile = Paths.get("e:\\test.py");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		FileChannel channel = FileChannel.open(logFile, StandardOpenOption.READ);
		channel.read(buffer, channel.size() - 10);
		System.out.println(channel.size());
		//Files.createFile(newPath);
		//System.out.println(Files.readAttributes(newPath, "*"));
		//Files.deleteIfExists(newPath);
		//System.in.read();
	}

	static class FindJavaVisitor
			extends SimpleFileVisitor<Path> //②扩展SimpleFileVisitor<Path>
	{
		@Override
		public FileVisitResult
		visitFile(Path file, BasicFileAttributes attrs) //③ 重写visitFile方法
		{
			if (file.toString().endsWith(".xml")) {
				System.out.println(file.getFileName());
			}
			return FileVisitResult.CONTINUE;
		}
	}
}
