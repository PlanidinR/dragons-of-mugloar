package com.bigbank.dragonsofmugloar;

import com.bigbank.dragonsofmugloar.engine.Engine;
import com.bigbank.dragonsofmugloar.exception.IORuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import java.io.IOException;


@EnableRetry
@RequiredArgsConstructor
@SpringBootApplication
public class DragonsOfMugloarApplication implements CommandLineRunner {

	private final Engine engine;

	public static void main(String[] args) {
		SpringApplication.run(DragonsOfMugloarApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			engine.run();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void print(String text) {
		System.out.println(text);
	}
}
