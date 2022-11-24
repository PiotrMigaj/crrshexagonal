package pl.migibud.exception;

import lombok.Data;

import java.util.List;

@Data
class ErrorInfo {
	private final List<String> message;
}
